package microservice.com.agenda.domain.repository;


import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import microservice.com.agenda.domain.entities.Agenda;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Long> {

    Optional<Agenda>findByAgendamento(LocalDateTime agendamento);


}
