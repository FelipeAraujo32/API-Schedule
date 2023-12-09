package microservice.com.agenda.domain.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import microservice.com.agenda.domain.Entities.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long>{

    Optional<Paciente>findByCpf(String cpf);
    Optional<Paciente>findByEmail(String email);
    
}
