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
import microservice.com.agenda.domain.entities.User;
import microservice.com.agenda.domain.repository.UserRepository;
import microservice.com.agenda.security.TokenService;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private TokenService tokenService;
    
    public AuthenticationController(AuthenticationManager authenticationManager, UserRepository userRepository,
            TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationRequest data) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                data.user(), data.password());
        var authenticate = this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        var token = tokenService.generateToken((User)authenticate.getPrincipal());

        return ResponseEntity.ok(new AuthenticationResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterRequest data) {
        if (this.userRepository.findByUser(data.user()) != null)
            return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newuser = new User(data.user(), encryptedPassword, data.role());

        this.userRepository.save(newuser);
        return ResponseEntity.ok().build();
    }
}
