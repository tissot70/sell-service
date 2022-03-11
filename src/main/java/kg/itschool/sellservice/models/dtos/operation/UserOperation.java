package kg.itschool.sellservice.models.dtos.operation;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserOperation {
    String login;
    String name;
    double sum;
    double change;
    List<Object[]> products;
}
