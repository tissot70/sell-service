package kg.itschool.sellservice.exeptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class NumberOfAttemptsExceededException extends RuntimeException{
    String title;

    public NumberOfAttemptsExceededException(String message, String title) {
        super(message);
        this.title = title;
    }
}
