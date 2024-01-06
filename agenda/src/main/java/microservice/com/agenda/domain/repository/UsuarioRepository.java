package microservice.com.agenda.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import microservice.com.agenda.domain.entities.Usuario;

@Transactional
@Repository
public interface UsuarioRepository  extends JpaRepository<Usuario, Long>{
   
    public UserDetails findByUsuario(String usuario);
}
