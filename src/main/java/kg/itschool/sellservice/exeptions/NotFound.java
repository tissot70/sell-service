package kg.itschool.sellservice.exeptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotFound extends RuntimeException{
    String title;

    public NotFound(String message, String title) {
        super(message);
        this.title = title;
    }
}
