package kg.itschool.sellservice.exeptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlreadyExistsException extends RuntimeException {
    String title;

    public AlreadyExistsException(String message, String title) {
        super(message);
        this.title=title;
    }
}
