package kg.itschool.sellservice.exeptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TimeExpiredException extends RuntimeException{
    String title;

    public TimeExpiredException(String message, String title) {
        super(message);
        this.title = title;
    }
}
