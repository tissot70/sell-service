package kg.itschool.sellservice.models.entities;

import kg.itschool.sellservice.models.entities.base.BaseNameActive;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "categories")
public class Category extends BaseNameActive {
}
