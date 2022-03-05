package kg.itschool.sellservice.models.dtos.price;

import kg.itschool.sellservice.models.dtos.product.ProductResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PriceResponse {
    long id;
    double price;
    LocalDateTime startDate;
    LocalDateTime endDate;
    boolean active;
    ProductResponse product;
}
