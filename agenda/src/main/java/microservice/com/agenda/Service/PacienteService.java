package microservice.com.agenda.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import microservice.com.agenda.Entities.Paciente;
import microservice.com.agenda.Repository.PacienteRepository;

@Service
@Transactional
public class PacienteService {
    
    private final PacienteRepository pacienteRepository;

    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public Paciente salvar(Paciente paciente){
        return pacienteRepository.save(paciente);
    }

    public List<Paciente> listarTodos(){
        return pacienteRepository.findAll();
    }

    public Optional<Paciente> buscarPorId(Long id){
        return pacienteRepository.findById(id);
    }

    public void deletar(Long id){
        pacienteRepository.deleteById(id);
    }

}
