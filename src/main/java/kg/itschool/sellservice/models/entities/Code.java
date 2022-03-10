package kg.itschool.sellservice.models.entities;

import kg.itschool.sellservice.models.entities.base.BaseId;
import kg.itschool.sellservice.models.enums.CodeStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "codes")
public class Code extends BaseId {
    String code;
    @Enumerated(value = EnumType.STRING)
    CodeStatus codeStatus;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
    @CreationTimestamp
    LocalDateTime startDate;
    LocalDateTime endDate;
}
