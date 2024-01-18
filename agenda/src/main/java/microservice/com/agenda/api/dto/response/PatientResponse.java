package microservice.com.agenda.api.dto.response;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import microservice.com.agenda.domain.entities.Patient;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PatientResponse {
    
    private Long id;
    private String name;
    private String surname;
    private String cpf;
    private String email;

     public PatientResponse patientResponseData(Patient patient){
        PatientResponse patientResponse = new PatientResponse();
        patientResponse.setId(patient.getId());
        patientResponse.setName(patient.getName());
        patientResponse.setSurname(patient.getsurname());
        patientResponse.setCpf(patient.getCpf()); 
        patientResponse.setEmail(patient.getEmail());
        return patientResponse;
    }

    public List<PatientResponse> patientResponseSearchAll(List<Patient> patients){
        List<PatientResponse> patientResponses = new ArrayList<>();
        for (Patient patient : patients){
            patientResponses.add(patientResponseData(patient));
        }
        return patientResponses;
    }

}
