package microservice.com.agenda.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private SecurityFilter securityFilter;
    
    public SecurityConfig(SecurityFilter securityFilter) {
        this.securityFilter = securityFilter;
    }

    // Filtro de Autorização de URL
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, AUTH_WHITELIST).permitAll()
                        .requestMatchers(HttpMethod.GET, AUTH_WHITELIST).permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, AUTH_WHITELIST_REGISTER).hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, PATIENT_DELETE).hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, POST_USER).hasRole("USER")
                        .requestMatchers(HttpMethod.GET, GET_USER).hasRole("USER")
                        .requestMatchers(HttpMethod.PUT, PATIENT_ALTER).hasRole("USER")
                        .anyRequest().authenticated())
                    .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                    .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private String[] AUTH_WHITELIST = {
        //Swagger UI v2
        "/v2/api-docs",
        "/swagger-resources",
        "/swagger-resources/**",
        "/configuration/ui",
        "/configuration/security",
        "/swagger-ui.html",
        "/webjars/**",
        //Swagger UI v3 (OpenAPI)
        "/v3/api-docs/**",
        "/swagger-ui/**",
        //Login   
    };

    private String[] AUTH_WHITELIST_REGISTER = {
        //Register ADMIN or USER
        "/auth/register"
    };

    private String[] PATIENT_DELETE = {
        //Register ADMIN or USER
        "/{id}/delete",
    };

    private String[] GET_USER = {
        //Patient
        "/patient/{id}",
        "/patient/listall",
        //Schedule
        "/schedule/listall",
        "/schedule/{id}",
    };

    private String[] POST_USER = {
        //Patient
        "/patient/save",
        //Schedule
        "/schedule/save",
    };

    private String[] PATIENT_ALTER = {
        //Alter Patient
        "/patient/alter/{id}",
    };

}
