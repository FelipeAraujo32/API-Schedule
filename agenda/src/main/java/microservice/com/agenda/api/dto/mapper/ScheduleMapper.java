package microservice.com.agenda.api.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import microservice.com.agenda.api.dto.request.ScheduleRequest;
import microservice.com.agenda.api.dto.response.ScheduleResponse;
import microservice.com.agenda.domain.entities.Schedule;

@Component
public class ScheduleMapper {

    private ModelMapper modelMapper;

    public ScheduleMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Schedule toSchedule(ScheduleRequest agendaRequest) {
        return modelMapper.map(agendaRequest, Schedule.class);
    }

    public ScheduleResponse toScheduleResponse(Schedule agenda) {
        return modelMapper.map(agenda, ScheduleResponse.class);
    }

    public List<ScheduleResponse> toScheduleResponseList(List<Schedule> agendas) {
        return agendas.stream().map(this::toScheduleResponse).collect(Collectors.toList());
    }
}
