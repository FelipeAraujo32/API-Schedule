package microservice.com.agenda.api.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import microservice.com.agenda.api.dto.request.AgendaRequest;
import microservice.com.agenda.api.dto.response.AgendaResponse;
import microservice.com.agenda.domain.entities.Agenda;

@Component
public class AgendaMapper {

    private ModelMapper modelMapper;

    public AgendaMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Agenda toAgenda(AgendaRequest agendaRequest) {
        return modelMapper.map(agendaRequest, Agenda.class);
    }

    public AgendaResponse toAgendaRespose(Agenda agenda) {
        return modelMapper.map(agenda, AgendaResponse.class);
    }

    public List<AgendaResponse> toAgendaResposeList(List<Agenda> agendas) {
        return agendas.stream().map(this::toAgendaRespose).collect(Collectors.toList());
    }
}
