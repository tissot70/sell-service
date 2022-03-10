package kg.itschool.sellservice.models.dtos.operation;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CheckDTO {
    List<CheckDetailDTO> checkDetailDTOS;
    double totalPrice;
    String cashier;
}
