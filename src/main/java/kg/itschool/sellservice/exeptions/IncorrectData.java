package kg.itschool.sellservice.exeptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IncorrectData extends RuntimeException{
    String title;

    public IncorrectData(String message, String title) {
        super(message);
        this.title = title;
    }
}
