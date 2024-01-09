package microservice.com.agenda.api.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import microservice.com.agenda.api.dto.request.PatientRequest;
import microservice.com.agenda.api.dto.response.PatientResponse;
import microservice.com.agenda.domain.entities.Patient;

@Component
public class PatientMapper {

    private ModelMapper mapper;

    public PatientMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public Patient toPatient(PatientRequest pacienteRequest) {
        return mapper.map(pacienteRequest, Patient.class);
    }

    public PatientResponse toPatientResponse(Patient pacienteresponse) {
        return mapper.map(pacienteresponse, PatientResponse.class);
    }

    public List<PatientResponse> toPatientResponseList(List<Patient> pacientes) {
        return pacientes.stream().map(this::toPatientResponse).collect(Collectors.toList());
    }
}