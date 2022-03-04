package kg.itschool.sellservice.models.entities.base;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@MappedSuperclass
public class BaseDate extends BaseId {
    @CreationTimestamp
    LocalDateTime startDate;
    LocalDateTime endDate;
}
