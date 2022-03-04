package kg.itschool.sellservice.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AlreadyExists.class)
    public ResponseEntity<?> handleAlreadyExist(AlreadyExists alreadyExists){
        return new ResponseEntity<>(new ResponseException (alreadyExists.getMessage(),alreadyExists.getTitle()),HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NotFound.class)
    public ResponseEntity<?> handleAlreadyExist(NotFound notFound){
        return new ResponseEntity<>(new ResponseException (notFound.getMessage(),notFound.getTitle()),HttpStatus.CONFLICT);
    }

    @ExceptionHandler(IncorrectData.class)
    public ResponseEntity<?> handleAlreadyExist(IncorrectData incorrectData){
        return new ResponseEntity<>(new ResponseException (incorrectData.getMessage(),incorrectData.getTitle()),HttpStatus.CONFLICT);
    }

    @ExceptionHandler(TimeExpired.class)
    public ResponseEntity<?> handleAlreadyExist(TimeExpired timeExpired){
        return new ResponseEntity<>(new ResponseException (timeExpired.getMessage(),timeExpired.getTitle()),HttpStatus.CONFLICT);
    }

}
