package microservice.com.agenda.api.dto.request;

import microservice.com.agenda.domain.entities.Roles.UsuarioRole;

public record RegisterRequest (String usuario, String senha, UsuarioRole role){
    
}
