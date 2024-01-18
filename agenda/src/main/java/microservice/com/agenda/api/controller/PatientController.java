package microservice.com.agenda.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
import microservice.com.agenda.api.dto.request.PatientRequest;
import microservice.com.agenda.api.dto.response.PatientResponse;
import microservice.com.agenda.domain.entities.Patient;
import microservice.com.agenda.domain.service.PatientService;

@RestController
@RequestMapping("/patient")
public class PatientController {
    
    @Autowired
    private PatientService patientService;
    private PatientRequest patientRequest;
    private PatientResponse patientResponse;

    public PatientController() {
    }

    
    public PatientController(PatientService patientService, PatientRequest patientRequest,
            PatientResponse patientResponse) {
        this.patientService = patientService;
        this.patientRequest = patientRequest;
        this.patientResponse = patientResponse;
    }

    @PostMapping("/save")
    public ResponseEntity<Patient> toSaveData(@Valid @RequestBody Patient patientRequest){
        Patient patient = patientRequest;
        Patient patientSave = patientService.toSave(patient);
        return ResponseEntity.status(HttpStatus.CREATED).body(patientSave);
    }

    @GetMapping("/listall")
    public ResponseEntity<List<PatientResponse>> listAll(){
        List<Patient> patient = patientService.listAll();
        List<PatientResponse> patientResponses = patientResponse.patientResponseSearchAll(patient);
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

    @PutMapping("/{id}/alter")
    public ResponseEntity<PatientResponse> toAlter(@PathVariable Long id, @RequestBody PatientRequest pacienteRequest){
        Patient patient = patientRequest.PatientRequestData(pacienteRequest);
        Patient patientData = patientService.toAlter(id, patient);
        patientResponse = patientResponse.patientResponseData(patientData);
        return ResponseEntity.status(HttpStatus.OK).body(patientResponse);
    }
    
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        patientService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
