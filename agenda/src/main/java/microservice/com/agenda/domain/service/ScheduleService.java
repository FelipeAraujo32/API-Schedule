package microservice.com.agenda.domain.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import microservice.com.agenda.domain.entities.Schedule;
import microservice.com.agenda.domain.entities.Patient;
import microservice.com.agenda.domain.repository.ScheduleRepository;
import microservice.com.agenda.exception.BusinessException;

@Service
@Transactional
public class ScheduleService {

    private ScheduleRepository scheduleRepository;
    private PatientService patientService;

    @Deprecated
    public ScheduleService() {
    }

    @Autowired
    public ScheduleService(ScheduleRepository scheduleRepository, PatientService patientService) {
        this.scheduleRepository = scheduleRepository;
        this.patientService = patientService;
    }

    public List<Schedule> ListallSchedule() {
        return scheduleRepository.findAll();
    }

    public Optional<Schedule> searchByid(long id) {
        return scheduleRepository.findById(id);
    }

    public Schedule toSaveSchedule(Schedule schedule) {
        Optional<Patient> optionalPatient = patientService.searchById(schedule.getpatient().getId());

        if (optionalPatient.isEmpty()) {
            throw new BusinessException("Patient not found");
        }

        Optional<Schedule> optionalTime = scheduleRepository.findByschedulingDate(schedule.getSchedulingDate());
        if (optionalTime.isPresent()) {
            throw new BusinessException("There is already a schedule for this time");
        }
        

        schedule.setpatient(optionalPatient.get());
        schedule.setStarTime(LocalDateTime.now());

        return scheduleRepository.save(schedule);
    }
}