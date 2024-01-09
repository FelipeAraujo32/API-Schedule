package microservice.com.agenda.api.dto.request;

import microservice.com.agenda.domain.entities.roles.UserRole;

public record RegisterRequest (String user, String password, UserRole role){
    
}
