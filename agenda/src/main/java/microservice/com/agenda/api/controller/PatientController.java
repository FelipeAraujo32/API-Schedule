package microservice.com.agenda.api.controller;

import java.util.ArrayList;
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

    public PatientController() {
    }

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping("/save")
    public ResponseEntity<PatientResponse> toSaveData(@Valid @RequestBody PatientRequest data) {
        Patient patient = new Patient(data.name(), data.surname(), data.cpf(), data.email());
        patientService.toSave(patient);

        PatientResponse patientResponse = new PatientResponse(patient.getId(), patient.getName(), patient.getsurname(), patient.getCpf(), patient.getEmail());

        return ResponseEntity.status(HttpStatus.CREATED).body(patientResponse);
    }

    @GetMapping("/listall")
    public ResponseEntity<List<PatientResponse>> listAll() {

        List<Patient> patientListAll = patientService.listAll();
        List<PatientResponse> patientResponses = new ArrayList<>();

        for (Patient patient : patientListAll){
            PatientResponse patientResponse = new PatientResponse(patient.getId(), patient.getName(), patient.getsurname(), patient.getCpf(), patient.getEmail());
            patientResponses.add(patientResponse);
        }
        return ResponseEntity.status(HttpStatus.OK).body(patientResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> searchToId(@PathVariable Long id) {
        Optional<Patient> optionalPatient = patientService.searchById(id);

        if (optionalPatient.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(optionalPatient.get());
    }

    @PutMapping("/alter/{id}")
    public ResponseEntity<PatientResponse> toAlterPatient(@PathVariable long id, @RequestBody PatientRequest data) {
        Patient patient = new Patient(data.name(), data.surname(), data.cpf(), data.email());
        patientService.toAlter(id, patient);

        PatientResponse patientResponse = new PatientResponse(patient.getId(), patient.getName(), patient.getsurname(), patient.getCpf(), patient.getEmail());
        return ResponseEntity.status(HttpStatus.OK).body(patientResponse);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        patientService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
