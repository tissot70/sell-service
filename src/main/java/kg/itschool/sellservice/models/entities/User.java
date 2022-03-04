package kg.itschool.sellservice.models.entities;

import kg.itschool.sellservice.models.entities.base.BaseNameActive;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "users")
public class User extends BaseNameActive {
    @Column(unique = true)
    String login;
    LocalDateTime blockDate;
}
