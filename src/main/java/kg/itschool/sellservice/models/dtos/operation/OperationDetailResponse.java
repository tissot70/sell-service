package kg.itschool.sellservice.models.dtos.operation;

import kg.itschool.sellservice.models.dtos.product.ProductResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OperationDetailResponse {
    long id;
    int quantity;
    double amount;
    OperationResponse operation;
    ProductResponse product;
}
