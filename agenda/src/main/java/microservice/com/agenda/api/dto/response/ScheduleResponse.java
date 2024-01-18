package microservice.com.agenda.api.dto.response;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import microservice.com.agenda.domain.entities.Schedule;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ScheduleResponse {

    private Long id;
    private String description;
    private LocalDateTime schedulingDate;
    private PatientResponse patientResponse;
   

    public ScheduleResponse scheduleResponseData(Schedule schedule){
        ScheduleResponse scheduleResponse = new ScheduleResponse();
        scheduleResponse.setId(schedule.getId());
        scheduleResponse.setDescription(schedule.getDescription());
        scheduleResponse.setSchedulingDate(schedule.getSchedulingDate());
        return scheduleResponse;
    }

    public List<ScheduleResponse> scheduleResponseSearchAll(List<Schedule> schedules){
        List<ScheduleResponse> scheduleResponses = new ArrayList<>();
        for (Schedule schedule : schedules){
            scheduleResponses.add(scheduleResponseData(schedule));
        }
        return scheduleResponses;
    }

    

}
