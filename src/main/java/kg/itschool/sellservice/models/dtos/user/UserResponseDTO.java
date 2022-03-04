package kg.itschool.sellservice.models.dtos.user;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponseDTO {

    long id;
    String name;
    boolean active;
    String login;
    LocalDateTime blockDate;
}
