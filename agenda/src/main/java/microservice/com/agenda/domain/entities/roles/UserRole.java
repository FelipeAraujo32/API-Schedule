package microservice.com.agenda.domain.entities.roles;

public enum UserRole {
   
    ADMIN("admin"),
    USER("user");

    private String role;

    private UserRole(String role) {
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
