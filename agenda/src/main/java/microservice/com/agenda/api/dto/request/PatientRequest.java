package microservice.com.agenda.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import microservice.com.agenda.domain.entities.Patient;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PatientRequest {

    private String name;
    private String surname;
    private String cpf;
    private String email;
       

    public Patient PatientRequestData(PatientRequest patientRequestData){
        Patient patient = new Patient();
        patient.setName(patientRequestData.getName());
        patient.setsurname(patientRequestData.getSurname());
        patient.setCpf(patientRequestData.getCpf()); 
        patient.setEmail(patientRequestData.getEmail());
        return patient;
    }


}
