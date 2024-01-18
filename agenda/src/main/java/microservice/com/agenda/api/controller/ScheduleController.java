package microservice.com.agenda.api.controller;

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
import microservice.com.agenda.api.dto.response.ScheduleResponse;
import microservice.com.agenda.domain.entities.Schedule;
import microservice.com.agenda.domain.service.ScheduleService;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;
    private ScheduleRequest scheduleRequest;
    private ScheduleResponse scheduleResponse;

    public ScheduleController() {
    }

    
    public ScheduleController(ScheduleService scheduleService, ScheduleRequest scheduleRequest,
            ScheduleResponse scheduleResponse) {
        this.scheduleService = scheduleService;
        this.scheduleRequest = scheduleRequest;
        this.scheduleResponse = scheduleResponse;
    }

    @GetMapping("/listall")
    public ResponseEntity<List<ScheduleResponse>> searchAll() {
        List<Schedule> listAllSchedule = scheduleService.ListallSchedule();
        List<ScheduleResponse> scheduleResponses = scheduleResponse.scheduleResponseSearchAll(listAllSchedule);
        return ResponseEntity.status(HttpStatus.OK).body(scheduleResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponse> searchById(@PathVariable Long id) {
        Optional<Schedule> optionalSchedule = scheduleService.searchByid(id);
        if (optionalSchedule.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        scheduleResponse = scheduleResponse.scheduleResponseData(optionalSchedule.get());
        return ResponseEntity.status(HttpStatus.OK).body(scheduleResponse);
    }

    @PostMapping("/save")
    public ResponseEntity<ScheduleResponse> tosave(@Valid @RequestBody ScheduleRequest scheduleRequestdata) {
        Schedule scheduledata = scheduleRequest.toScheduleRequestData(scheduleRequestdata);
        Schedule Schedule = scheduleService.toSaveSchedule(scheduledata);
        scheduleResponse = scheduleResponse.scheduleResponseData(Schedule);
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleResponse);
    }
}
