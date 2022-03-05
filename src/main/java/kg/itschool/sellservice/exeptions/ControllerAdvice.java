package kg.itschool.sellservice.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<?> handleAlreadyExist(AlreadyExistsException alreadyExistsException){
        return new ResponseEntity<>(new ResponseException (alreadyExistsException.getTitle(), alreadyExistsException.getMessage()),HttpStatus.FOUND);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleAlreadyExist(NotFoundException notFoundException){
        return new ResponseEntity<>(new ResponseException (notFoundException.getTitle(),notFoundException.getMessage()),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IncorrectDataException.class)
    public ResponseEntity<?> handleAlreadyExist(IncorrectDataException incorrectDataException){
        return new ResponseEntity<>(new ResponseException (incorrectDataException.getTitle(),incorrectDataException.getMessage()),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TimeExpiredException.class)
    public ResponseEntity<?> handleAlreadyExist(TimeExpiredException timeExpiredException){
        return new ResponseEntity<>(new ResponseException (timeExpiredException.getTitle(),timeExpiredException.getMessage()),HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler(NumberOfAttemptsExceededException.class)
    public ResponseEntity<?> handleAlreadyExist(NumberOfAttemptsExceededException numberOfAttemptsExceededException){
        return new ResponseEntity<>(new ResponseException (numberOfAttemptsExceededException.getTitle(),numberOfAttemptsExceededException.getMessage()),HttpStatus.EXPECTATION_FAILED);
    }

}
