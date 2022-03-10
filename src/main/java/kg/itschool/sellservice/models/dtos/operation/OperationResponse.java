package kg.itschool.sellservice.models.dtos.operation;

import kg.itschool.sellservice.models.dtos.user.UserCreateDTO;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OperationResponse {
    Long id;
    LocalDateTime addDate;
    double totalPrice;
    double change;
    UserCreateDTO user;
    double cash;
}
