package microservice.com.agenda.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI api(){
        return new OpenAPI().info(new Info()
        .title("Spring Boot REST API")
        .description("Aplicação de Agendamento de Consulta")
        .version("1.0.0")
        .contact(new Contact().name("Felipe").url("https://github.com/FelipeAraujo32").email("felipecafsx@gmail.com"))
        );
    }
    
    
    
    
}
