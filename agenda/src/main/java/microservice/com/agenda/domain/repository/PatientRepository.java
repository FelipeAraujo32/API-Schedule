package microservice.com.agenda.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import microservice.com.agenda.domain.entities.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long>{

    Optional<Patient>findByCpf(String cpf);
    Optional<Patient>findByEmail(String email);
    
}
