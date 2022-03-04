package kg.itschool.sellservice.exeptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TimeExpired extends RuntimeException{
    String title;

    public TimeExpired(String message, String title) {
        super(message);
        this.title = title;
    }
}
