package elisadaria.UN5W3d5praticaS3L5.exceptions;

import lombok.Getter;
import org.springframework.validation.ObjectError;

import java.time.LocalDate;
import java.util.List;
@Getter
public class BadRequestEx extends RuntimeException{
    private List<ObjectError>errorsList;
    public BadRequestEx(String message) {
        super(message);
    }
    public BadRequestEx(List<ObjectError>errorsList){
        super("Errori di validazione nel body");
        this.errorsList=errorsList;
    }
}
