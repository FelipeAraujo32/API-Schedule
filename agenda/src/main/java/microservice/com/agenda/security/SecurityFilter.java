package microservice.com.agenda.security;

import java.io.IOException;

import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import microservice.com.agenda.domain.repository.UserRepository;

@Component
public class SecurityFilter extends OncePerRequestFilter{

    private TokenService tokenService;
    private UserRepository userRepository;

    public SecurityFilter(TokenService tokenService, UserRepository userRepository) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        var token = this.recoverToken(request);
        if(token != null){
            var user_token = tokenService.validateToken(token);
            UserDetails user = userRepository.findByUser(user_token);
            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request){
        var authHeader = request.getHeader("Authorization");
        if(authHeader == null) {
            return null;}
        else return authHeader.replace("Bearer ", "");//Passei muito tempo para descobrir que precisava de um espaço para validar do jeito correo. Como eu coloquei: "Bearer" - Jeito correto: "Bearer "

    }
    
}
