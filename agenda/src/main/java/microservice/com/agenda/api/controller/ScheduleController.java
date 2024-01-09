package microservice.com.agenda.api.controller;

import java.util.List;
import java.util.Optional;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import microservice.com.agenda.api.dto.mapper.ScheduleMapper;
import microservice.com.agenda.api.dto.request.ScheduleRequest;
import microservice.com.agenda.api.dto.response.ScheduleResponse;
import microservice.com.agenda.domain.entities.Schedule;
import microservice.com.agenda.domain.service.ScheduleService;

@RestController
@RequestMapping("/agenda")
public class ScheduleController {

    
    private ScheduleService scheduleService;
    private ScheduleMapper scheduleMapper;

    public ScheduleController() {
    }

    public ScheduleController(ScheduleService scheduleService, ScheduleMapper scheduleMapper) {
        this.scheduleService = scheduleService;
        this.scheduleMapper = scheduleMapper;
    }

    @GetMapping
    public ResponseEntity<List<ScheduleResponse>> searchAll() {
        List<Schedule> listAllSchedule = scheduleService.ListallSchedule();
        List<ScheduleResponse> scheduleResponse = scheduleMapper.toScheduleResponseList(listAllSchedule);
        return ResponseEntity.status(HttpStatus.OK).body(scheduleResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponse> searchById(@PathVariable Long id) {
        Optional<Schedule> optionalSchedule = scheduleService.searchByid(id);
        if (optionalSchedule.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        ScheduleResponse scheduleResponse = scheduleMapper.toScheduleResponse(optionalSchedule.get());
        return ResponseEntity.status(HttpStatus.OK).body(scheduleResponse);
    }

    @PostMapping
    public ResponseEntity<ScheduleResponse> tosave(@Valid @RequestBody ScheduleRequest scheduleRequest) {
        Schedule schedule = scheduleMapper.toSchedule(scheduleRequest);
        Schedule saveSchedule = scheduleService.toSaveSchedule(schedule);
        ScheduleResponse scheduleResponse = scheduleMapper.toScheduleResponse(saveSchedule);
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleResponse);
    }
}
