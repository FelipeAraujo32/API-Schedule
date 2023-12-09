package microservice.com.agenda.domain.Service;

import java.util.List;
import java.util.Optional;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import microservice.com.agenda.domain.Entities.Paciente;
import microservice.com.agenda.domain.Repository.PacienteRepository;
import microservice.com.agenda.exception.BusinessException;

@Service
@Transactional
public class PacienteService {
     
    private final PacienteRepository pacienteRepository;


    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public Paciente salvar(Paciente paciente){
    
        //Teste para conferir se CPF existe no bd
        boolean existeCpf = false;
        Optional<Paciente> cpfPaciente = pacienteRepository.findByCpf(paciente.getCpf());
        if(cpfPaciente.isPresent()){
            if(!cpfPaciente.get().getId().equals(paciente.getId())){
                existeCpf = true;}}
        if(existeCpf){
            throw new BusinessException("Cpf já cadastrado.");
        }

        //Teste para conferir se email existe no bd
        boolean existeEmail = false;
        Optional<Paciente> emailPaciente = pacienteRepository.findByEmail(paciente.getEmail());
        if(emailPaciente.isPresent()){
            if(!emailPaciente.get().getId().equals(paciente.getId())){
                existeEmail = true;}}
        if(existeEmail){
            throw new BusinessException("Email já cadastrado.");
        }
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
