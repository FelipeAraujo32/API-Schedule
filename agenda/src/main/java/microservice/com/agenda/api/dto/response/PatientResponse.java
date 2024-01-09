package microservice.com.agenda.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatientResponse {
    
    private Long id;
    private String name;
    private String surname;
    private String cpf;
    private String email;
}
