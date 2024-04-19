package elisadaria.UN5W3d5praticaS3L5.exceptions;

import elisadaria.UN5W3d5praticaS3L5.payloads.ErrRespDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExsHandler {
    @ExceptionHandler(NotFoundEx.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrRespDTO handle404(NotFoundEx ex){
        return new ErrRespDTO(ex.getMessage(), LocalDateTime.now());
    }
    @ExceptionHandler(BadRequestEx.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrRespDTO handle400(BadRequestEx ex){
        if(ex.getErrorsList()!=null){
            String msg=ex.getErrorsList().stream().map(objectError -> objectError.getDefaultMessage()).collect(Collectors.joining("/ "));
            return new ErrRespDTO(msg,LocalDateTime.now());
        }else{
        return new ErrRespDTO(ex.getMessage(), LocalDateTime.now());
        }
    }
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrRespDTO handleGenericERR(Exception ex){
        ex.printStackTrace();
        return new ErrRespDTO("Problema lato server: WORKING ON IT!",LocalDateTime.now());
    }
    @ExceptionHandler(UnAuthorizedEx.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrRespDTO handle401(UnAuthorizedEx ex){
        return new ErrRespDTO("NON AUTORIZZATO,problemi con il token",LocalDateTime.now());
    }

}

