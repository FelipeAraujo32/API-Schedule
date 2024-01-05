package microservice.com.agenda.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import microservice.com.agenda.api.dto.request.AuthenticationRequest;
import microservice.com.agenda.api.dto.request.RegisterRequest;
import microservice.com.agenda.api.dto.response.AuthenticationResponse;
import microservice.com.agenda.domain.entities.Usuario;
import microservice.com.agenda.domain.repository.UsuarioRepository;
import microservice.com.agenda.security.TokenService;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    private AuthenticationManager authenticationManager;
    private UsuarioRepository usuarioRepository;
    private TokenService tokenService;
    
    public AuthenticationController(AuthenticationManager authenticationManager, UsuarioRepository usuarioRepository,
            TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.usuarioRepository = usuarioRepository;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationRequest data) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                data.usuario(), data.senha());
        var authenticate = this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        var token = tokenService.generateToken((Usuario)authenticate.getPrincipal());

        return ResponseEntity.ok(new AuthenticationResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterRequest data) {
        if (this.usuarioRepository.findByUsuario(data.usuario()) != null)
            return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.senha());
        Usuario newuUsuario = new Usuario(data.usuario(), encryptedPassword, data.role());

        this.usuarioRepository.save(newuUsuario);
        return ResponseEntity.ok().build();
    }
}
