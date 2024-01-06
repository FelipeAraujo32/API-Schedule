package microservice.com.agenda.domain.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import microservice.com.agenda.domain.entities.Agenda;
import microservice.com.agenda.domain.entities.Paciente;
import microservice.com.agenda.domain.repository.AgendaRepository;
import microservice.com.agenda.exception.BusinessException;

@Service
@Transactional
public class AgendaService {

    private AgendaRepository agendaRepository;
    private PacienteService pacienteService;

    public AgendaService(AgendaRepository agendaRepository, PacienteService pacienteService) {
        this.agendaRepository = agendaRepository;
        this.pacienteService = pacienteService;
    }

    public List<Agenda> listarTodosAgenda() {
        return agendaRepository.findAll();
    }

    public Optional<Agenda> bucarPoridAgenda(Long id) {
        return agendaRepository.findById(id);
    }

    public Agenda salvarAgenda(Agenda agenda) {
        Optional<Paciente> optionalPaciente = pacienteService.buscarPorId(agenda.getPaciente().getId());

        if (optionalPaciente.isEmpty()) {
            throw new BusinessException("Paciente não encontrado");
        }

        Optional<Agenda> optionalHorario = agendaRepository.findByAgendamento(agenda.getAgendamento());

        if (optionalHorario.isPresent()) {
            throw new BusinessException("Já existe agendamento para este horáio");
        }

        agenda.setPaciente(optionalPaciente.get());
        agenda.setEntrada(LocalDateTime.now());

        return agendaRepository.save(agenda);
    }
}