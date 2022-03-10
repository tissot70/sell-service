package kg.itschool.sellservice.models.dtos.operation;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OperationDTO {
    String barcode;
    int quantity;
}
