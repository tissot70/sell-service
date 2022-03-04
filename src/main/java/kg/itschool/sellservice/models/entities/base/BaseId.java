package kg.itschool.sellservice.models.entities.base;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@MappedSuperclass
public class BaseId {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
}
