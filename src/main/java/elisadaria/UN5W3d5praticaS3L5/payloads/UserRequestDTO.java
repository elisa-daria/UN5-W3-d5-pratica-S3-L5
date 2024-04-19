package elisadaria.UN5W3d5praticaS3L5.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record UserRequestDTO(
        @NotEmpty(message = "Campo obbligatorio")
        @Size(min = 3,max = 30, message = "Nome incorretto: min3 max 30 caratteri")
        String name,
        @NotEmpty(message = "Campo obbligatorio")
        @Size(min = 3,max = 30, message = "Cognome incorretto: min3 max 30 caratteri")
        String surname,
        @NotNull(message = "data di nascita obbligatoria")
        LocalDate dateOfBirth,
        @NotEmpty(message = "Campo obbligatorio")
        @Email(message = "email non valida")
        String email,
        @NotEmpty(message = "Campo obbligario")
        @Size(min = 6, message = "la password deve avere un minimo di 6 caratteri")
        String password

) {
}
