package kg.itschool.sellservice.exeptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlreadyExists extends RuntimeException {
    String title;
    public AlreadyExists(String message,String title) {
        super(message);
        this.title=title;
    }
}
