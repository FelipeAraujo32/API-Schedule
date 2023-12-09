package microservice.com.agenda.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import microservice.com.agenda.domain.Entities.Paciente;
import microservice.com.agenda.domain.Service.PacienteService;

@RestController
@RequestMapping("/paciente")
public class PacienteController {
    
    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping
    public ResponseEntity<Paciente> salvar(@RequestBody Paciente paciente){
        Paciente pacienteSalvo = pacienteService.salvar(paciente);
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteSalvo);
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> listarTodos(){
        List<Paciente> pacientes = pacienteService.listarTodos();
        return ResponseEntity.status(HttpStatus.OK).body(pacientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPorId(@PathVariable Long id){
        Optional<Paciente> idPaciente = pacienteService.buscarPorId(id);
        
        if(idPaciente.isEmpty()){
            return ResponseEntity.notFound().build();  
        }
        return ResponseEntity.status(HttpStatus.OK).body(idPaciente.get());
    }

    @PutMapping
    public ResponseEntity<Paciente> alterar(@RequestBody Paciente paciente){
        Paciente pacienteSalvo = pacienteService.salvar(paciente);
        return ResponseEntity.status(HttpStatus.OK).body(pacienteSalvo);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        pacienteService.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
