package microservice.com.agenda.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import microservice.com.agenda.api.dto.request.ScheduleRequest;
import microservice.com.agenda.api.dto.response.PatientResponse;
import microservice.com.agenda.api.dto.response.ScheduleResponse;
import microservice.com.agenda.domain.entities.Patient;
import microservice.com.agenda.domain.entities.Schedule;
import microservice.com.agenda.domain.service.PatientService;
import microservice.com.agenda.domain.service.ScheduleService;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private PatientService patientService;

    public ScheduleController() {
    }

    public ScheduleController(ScheduleService scheduleService, PatientService patientService) {
        this.scheduleService = scheduleService;
        this.patientService = patientService;
    }

    @PostMapping("/save")
    public ResponseEntity<ScheduleResponse> tosave(@Valid @RequestBody ScheduleRequest data) {

        Patient patientid = new Patient();
        patientid.setId(data.patientId());
        patientid.getId();
        Patient patient = patientService.searchById(data.patientId()).get();

        PatientResponse patientResponse = new PatientResponse(patient.getId(), patient.getName(), patient.getsurname(), patient.getCpf(), patient.getEmail());

        Schedule schedule = new Schedule(data.description(), data.schedulingDate(), patient);
        scheduleService.toSaveSchedule(schedule);

        ScheduleResponse scheduleResponse = new ScheduleResponse(schedule.getId(), schedule.getDescription(),
                schedule.getSchedulingDate(), patientResponse);

        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleResponse);
    }

    @GetMapping("/listall")
    public ResponseEntity<List<ScheduleResponse>> searchAll() {
        List<Schedule> listAllSchedule = scheduleService.ListallSchedule();
        List<ScheduleResponse> scheduleResponses = new ArrayList<>();

        for (Schedule schedule : listAllSchedule) {
            Patient patientId = schedule.getpatient();
            Patient patient = patientService.searchById(patientId.getId()).get();

            PatientResponse patientResponse = new PatientResponse(patient.getId(), patient.getName(), patient.getsurname(), patient.getCpf(), patient.getEmail());
            
            ScheduleResponse scheduleResponse = new ScheduleResponse(schedule.getId(), schedule.getDescription(),
                    schedule.getSchedulingDate(), patientResponse);
            scheduleResponses.add(scheduleResponse);
        }
        return ResponseEntity.status(HttpStatus.OK).body(scheduleResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponse> searchById(@PathVariable long id) {
        Optional<Schedule> optionalSchedule = scheduleService.searchByid(id);
        Schedule schedule = optionalSchedule.get();
        Patient patientId = schedule.getpatient();
        Patient patient = patientService.searchById(patientId.getId()).get();

        PatientResponse patientResponse = new PatientResponse(patient.getId(), patient.getName(), patient.getsurname(), patient.getCpf(), patient.getEmail());

        if (optionalSchedule.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        ScheduleResponse scheduleResponse = new ScheduleResponse(id, schedule.getDescription(), schedule.getSchedulingDate(), patientResponse);
        return ResponseEntity.status(HttpStatus.OK).body(scheduleResponse);
    }

}
