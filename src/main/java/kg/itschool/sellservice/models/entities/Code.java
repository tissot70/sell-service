package kg.itschool.sellservice.models.entities;

import kg.itschool.sellservice.models.entities.base.BaseDate;
import kg.itschool.sellservice.models.enums.CodeStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "codes")
public class Code extends BaseDate {
    String code;
    @Enumerated(value = EnumType.STRING)
    CodeStatus codeStatus;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
}
