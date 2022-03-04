package kg.itschool.sellservice.models.entities;

import kg.itschool.sellservice.models.entities.base.BaseId;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "operations")
public class Operation extends BaseId {
    @CreationTimestamp
    LocalDateTime addDate;
    double totalPrice;
    double change;
    double cash;
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
}
