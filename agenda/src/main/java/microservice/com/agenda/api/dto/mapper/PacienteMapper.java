package microservice.com.agenda.api.dto.mapper;

import java.util.ArrayList;
import java.util.List;

import microservice.com.agenda.api.dto.request.PacienteRequest;
import microservice.com.agenda.api.dto.response.PacienteResponse;
import microservice.com.agenda.domain.Entities.Paciente;

public class PacienteMapper {

    // DTO do método salvar da Paciente Controller
    public static Paciente toPaciente(PacienteRequest pacienteRequest) {
        Paciente paciente = new Paciente();
        paciente.setNome(pacienteRequest.getNome());
        paciente.setSobrenome((pacienteRequest.getSobrenome()));
        paciente.setEmail(pacienteRequest.getEmail());
        paciente.setCpf(pacienteRequest.getCpf());
        return paciente;
    }

    public static PacienteResponse toPacienteResponse(Paciente paciente) {
        PacienteResponse pacienteResponse = new PacienteResponse();
        pacienteResponse.setId(paciente.getId());
        pacienteResponse.setNome(paciente.getNome());
        pacienteResponse.setSobrenome((paciente.getSobrenome()));
        pacienteResponse.setEmail(paciente.getEmail());
        pacienteResponse.setCpf(paciente.getCpf());
        return pacienteResponse;
    }

    // DTO do método listarTodos da Paciente Controller
    public static PacienteResponse toPacienteResponselist(Paciente paciente) {
        PacienteResponse pacienteResponse = new PacienteResponse();
        pacienteResponse.setNome(paciente.getNome());
        pacienteResponse.setSobrenome((paciente.getSobrenome()));
        pacienteResponse.setCpf(paciente.getCpf());
        return pacienteResponse;
    }

    public static List<PacienteResponse> ListPacienteResponse(List<Paciente> pacientes) {
        List<PacienteResponse> pacienteResponselist = new ArrayList<>();
        for (Paciente paciente : pacientes) {
            pacienteResponselist.add(toPacienteResponselist(paciente));
        }
        return pacienteResponselist;

    }
}