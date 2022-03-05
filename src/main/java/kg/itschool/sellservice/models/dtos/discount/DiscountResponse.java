package kg.itschool.sellservice.models.dtos.discount;

import kg.itschool.sellservice.models.dtos.product.ProductResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DiscountResponse {
    long id;
    int discount;
    boolean active;
    LocalDateTime startDate;
    LocalDateTime endDate;
    ProductResponse product;
}
