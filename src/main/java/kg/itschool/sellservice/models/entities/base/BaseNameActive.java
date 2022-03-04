package kg.itschool.sellservice.models.entities.base;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.MappedSuperclass;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@MappedSuperclass
public class BaseNameActive extends BaseId {
    String name;
    boolean active;
}
