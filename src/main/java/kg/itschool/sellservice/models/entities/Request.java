package kg.itschool.sellservice.models.entities;

import kg.itschool.sellservice.models.entities.base.BaseId;
import lombok.*;
import lombok.experimental.FieldDefaults;

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
@Table(name = "requests")
public class Request extends BaseId {
        LocalDateTime addDate;
        boolean success;
        @ManyToOne
        @JoinColumn(name = "codes_id")
        Code code;
}
