package kg.itschool.sellservice.models.dtos.category;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryResponse {
    long id;
    String name;
    boolean active;
}
