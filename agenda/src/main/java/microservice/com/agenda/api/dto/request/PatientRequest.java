package microservice.com.agenda.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatientRequest {

    @NotBlank(message = "Patient name is required")
    private String name;
    @NotBlank(message = "Patient's surname is mandatory")
    private String surname;
    @NotBlank(message = "Patient CPF is mandatory")
    private String cpf;
    private String email;

}
