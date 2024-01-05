package microservice.com.agenda.domain.entities.Roles;

public enum UsuarioRole {
   
    ADMIN("admin"),
    USUARIO("usuario");

    private String role;

    private UsuarioRole(String role) {
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
