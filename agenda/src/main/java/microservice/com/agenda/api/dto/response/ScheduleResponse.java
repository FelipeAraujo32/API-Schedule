package microservice.com.agenda.api.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleResponse {

    private Long id;
    private String description;
    private LocalDateTime schedulingDate;
    private PatientResponse patientResponse;
}
