package kg.itschool.sellservice.models.dtos.operation;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CheckDetailDTO {
    String name;
    String barcode;
    int quantity;
    double price;
    double discount;
    double sum;
}
