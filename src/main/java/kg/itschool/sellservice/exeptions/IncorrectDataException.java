package kg.itschool.sellservice.exeptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IncorrectDataException extends RuntimeException{
    String title;

    public IncorrectDataException(String message, String title) {
        super(message);
        this.title = title;
    }
}
