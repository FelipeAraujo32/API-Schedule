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

import jakarta.validation.Valid;
import microservice.com.agenda.api.dto.mapper.PacienteMapper;
import microservice.com.agenda.api.dto.request.PacienteRequest;
import microservice.com.agenda.api.dto.response.PacienteResponse;
import microservice.com.agenda.domain.Entities.Paciente;
import microservice.com.agenda.domain.Service.PacienteService;

@RestController
@RequestMapping("/paciente")
public class PacienteController {
    
    private PacienteService pacienteService;
    private PacienteMapper pacienteMapper;

    public PacienteController(PacienteService pacienteService, PacienteMapper pacienteMapper) {
        this.pacienteService = pacienteService;
        this.pacienteMapper = pacienteMapper;
    }

    @PostMapping
    public ResponseEntity<PacienteResponse> salvar(@Valid @RequestBody PacienteRequest pacienteRequest){
        Paciente paciente = pacienteMapper.toPaciente(pacienteRequest);
        pacienteService.salvar(paciente);
        PacienteResponse pacienteResponse = pacienteMapper.toPacienteResponse(paciente);
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteResponse);
    }

    @GetMapping
    public ResponseEntity<List<PacienteResponse>> listarTodos(){
        List<Paciente> pacientes = pacienteService.listarTodos();
        List<PacienteResponse> pacienteResponse = pacienteMapper.toPacienteResponseList(pacientes);
        return ResponseEntity.status(HttpStatus.OK).body(pacienteResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPorId(@PathVariable Long id){
        Optional<Paciente> idPaciente = pacienteService.buscarPorId(id);
        
        if(idPaciente.isEmpty()){
            return ResponseEntity.notFound().build();  
        }
        return ResponseEntity.status(HttpStatus.OK).body(idPaciente.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteResponse> alterar(@PathVariable Long id, @RequestBody PacienteRequest pacienteRequest){
        Paciente paciente = pacienteMapper.toPaciente(pacienteRequest);
        Paciente pacienteSalvo = pacienteService.alterar(id, paciente);
        PacienteResponse pacienteResponse = pacienteMapper.toPacienteResponse(pacienteSalvo);
        return ResponseEntity.status(HttpStatus.OK).body(pacienteResponse);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        pacienteService.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
