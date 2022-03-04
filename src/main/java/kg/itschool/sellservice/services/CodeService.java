package kg.itschool.sellservice.services;

import kg.itschool.sellservice.models.dtos.user.UserCreateDTO;
import org.springframework.http.ResponseEntity;

public interface CodeService {
    ResponseEntity<?> sendCode(UserCreateDTO userCreateDTO);

    ResponseEntity<?> confirm(String code, String login);

    ResponseEntity<?> verifyToken(String token);
}
