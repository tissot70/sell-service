package kg.itschool.sellservice.models.dtos.code;

import kg.itschool.sellservice.models.dtos.user.UserResponseDTO;
import kg.itschool.sellservice.models.enums.CodeStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CodeResponse {
    Long id;
    String code;
    LocalDateTime startDate;
    LocalDateTime endDate;
    CodeStatus codeStatus;
    UserResponseDTO user;

}
