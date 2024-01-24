package microservice.com.agenda.api.dto.request;

import java.time.LocalDateTime;

public record ScheduleRequest(String description, LocalDateTime schedulingDate, long patientId) {
}