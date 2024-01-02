package microservice.com.agenda.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import microservice.com.agenda.domain.entities.Usuario;
import microservice.com.agenda.security.AuthenticationRequest;
import microservice.com.agenda.security.LoginResponse;
import microservice.com.agenda.security.TokenService;

@RestController
@RequestMapping("auth")

public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    TokenService tokenSerice;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationRequest data){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        Authentication authenticate = this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        
        var token = tokenSerice.generateToken((Usuario)authenticate.getPrincipal());


        return ResponseEntity.ok(new LoginResponse(token));
    }
}
