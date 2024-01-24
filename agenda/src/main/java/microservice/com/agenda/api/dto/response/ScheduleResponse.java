package microservice.com.agenda.api.dto.response;

import java.time.LocalDateTime;


public record ScheduleResponse(Long id, String description, LocalDateTime schedulingDate, PatientResponse patientResponse) {
} 

