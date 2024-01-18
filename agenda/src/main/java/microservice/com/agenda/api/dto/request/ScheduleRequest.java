package microservice.com.agenda.api.dto.request;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import microservice.com.agenda.domain.entities.Patient;
import microservice.com.agenda.domain.entities.Schedule;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ScheduleRequest {

    @NotBlank
    private String description;
    @Future
    @DateTimeFormat(pattern = "yyyy-MM-ddTHH:mm:ss")
    @NotNull
    private LocalDateTime schedulingDate;
    @NotNull
    private Long patientid;
 
    public Schedule toScheduleRequestData(ScheduleRequest scheduleRequest){
        Schedule schedule = new Schedule();
        Patient patient = new Patient();
        patient.setId(patientid);
        patient.getId();

        schedule.setDescription(description);
        schedule.setSchedulingDate(schedulingDate);
        schedule.setpatient(patient);
        return schedule;
        
    }

}
