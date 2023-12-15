package microservice.com.agenda.api.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import microservice.com.agenda.api.dto.request.PacienteRequest;
import microservice.com.agenda.api.dto.response.PacienteResponse;
import microservice.com.agenda.domain.Entities.Paciente;

@Component
public class PacienteMapper {

    private ModelMapper mapper;

    public PacienteMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public Paciente toPaciente(PacienteRequest pacienteRequest) {
        return mapper.map(pacienteRequest, Paciente.class);
    }

    public PacienteResponse toPacienteResponse(Paciente pacienteresponse) {
        return mapper.map(pacienteresponse, PacienteResponse.class);
    }

    public List<PacienteResponse> toPacienteResponseList(List<Paciente> pacientes) {
        return pacientes.stream().map(this::toPacienteResponse).collect(Collectors.toList());
    }
}