package microservice.com.agenda.domain.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.EntityManager;

import microservice.com.agenda.domain.entities.Patient;
import microservice.com.agenda.domain.entities.Schedule;
import microservice.com.agenda.domain.repository.ScheduleRepository;
import microservice.com.agenda.exception.BusinessException;

@ExtendWith(MockitoExtension.class)
public class ScheduleServiceTest {

    @InjectMocks
    ScheduleService scheduleService;

    @Mock
    PatientService patientService;

    @Mock
    ScheduleRepository scheduleRepository;

    @Autowired
    EntityManager entityManager;

    @Captor
    ArgumentCaptor<Schedule> scheduleCaptor;


    @Test
    @DisplayName("Should save successfully")
    void testToSaveSchedule() {
        //Arrange
        Patient patient = new Patient();
        patient.setId(1L);
        patient.setName("Felipe");

        LocalDateTime now = LocalDateTime.now();
        Schedule schedule = new Schedule();
        schedule.setDescription("TEST123");
        schedule.setSchedulingDate(now);
        schedule.setpatient(patient);

        Mockito.when(patientService.searchById(schedule.getpatient().getId())).thenReturn(Optional.of(patient));

        Mockito.when(scheduleRepository.findByschedulingDate(now)).thenReturn(Optional.empty());

        //Action
        scheduleService.toSaveSchedule(schedule);

        //Assertions

        Mockito.verify(patientService).searchById(schedule.getpatient().getId());
        Mockito.verify(scheduleRepository).findByschedulingDate(now);
        Mockito.verify(scheduleRepository).save(scheduleCaptor.capture());
        Schedule scheduleSave = scheduleCaptor.getValue();

        Assertions.assertThat(scheduleSave.getpatient()).isNotNull();
        Assertions.assertThat(scheduleSave.getSchedulingDate()).isNotNull();
    }

    @Test
    @DisplayName("You should not save appointments without patients")
    void patientNotFinding(){
        //Arrange

        Patient patient = new Patient();
        patient.setId(1L);
        patient.setName("Felipe");

        LocalDateTime now = LocalDateTime.now();
        Schedule schedule = new Schedule();
        schedule.setDescription("TEST123");
        schedule.setSchedulingDate(now);
        schedule.setpatient(patient);

        
 
        Mockito.when(patientService.searchById(schedule.getpatient().getId())).thenReturn(Optional.empty());

        //Action
        BusinessException businessException = assertThrows(BusinessException.class, () -> scheduleService.toSaveSchedule(schedule));

         //Assertions

         Assertions.assertThat(businessException.getMessage()).isEqualTo("Patient not found");

    }

    
}
