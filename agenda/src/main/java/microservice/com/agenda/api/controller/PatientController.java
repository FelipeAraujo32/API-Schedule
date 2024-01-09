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
import microservice.com.agenda.api.dto.mapper.PatientMapper;
import microservice.com.agenda.api.dto.request.PatientRequest;
import microservice.com.agenda.api.dto.response.PatientResponse;
import microservice.com.agenda.domain.entities.Patient;
import microservice.com.agenda.domain.service.PatientService;

@RestController
@RequestMapping("/patient")
public class PatientController {
    
    private PatientService patientService;
    private PatientMapper patientMapper;

    public PatientController(PatientService patientService, PatientMapper patientMapper) {
        this.patientService = patientService;
        this.patientMapper = patientMapper;
    }

    @PostMapping
    public ResponseEntity<PatientResponse> toSave(@Valid @RequestBody PatientRequest patientRequest){
        Patient patient = patientMapper.toPatient(patientRequest);
        patientService.toSave(patient);
        PatientResponse patientResponse = patientMapper.toPatientResponse(patient);
        return ResponseEntity.status(HttpStatus.CREATED).body(patientResponse);
    }

    @GetMapping("/listall")
    public ResponseEntity<List<PatientResponse>> listAll(){
        List<Patient> patient = patientService.listAll();
        List<PatientResponse> patientResponses = patientMapper.toPatientResponseList(patient);
        return ResponseEntity.status(HttpStatus.OK).body(patientResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> buscarPorId(@PathVariable Long id){
        Optional<Patient> optionalPatient = patientService.searchById(id);
        
        if(optionalPatient.isEmpty()){
            return ResponseEntity.notFound().build();  
        }
        return ResponseEntity.status(HttpStatus.OK).body(optionalPatient.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientResponse> alterar(@PathVariable Long id, @RequestBody PatientRequest pacienteRequest){
        Patient patient = patientMapper.toPatient(pacienteRequest);
        Patient patientSave = patientService.toAlter(id, patient);
        PatientResponse patientResponse = patientMapper.toPatientResponse(patientSave);
        return ResponseEntity.status(HttpStatus.OK).body(patientResponse);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        patientService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
