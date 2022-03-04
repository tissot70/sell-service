package kg.itschool.sellservice.models.dtos.product;

import kg.itschool.sellservice.models.dtos.category.CategoryResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductCreate {
    String name;
    CategoryResponse category;
    boolean active;
}
