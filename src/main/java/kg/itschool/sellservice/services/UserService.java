package kg.itschool.sellservice.services;

import kg.itschool.sellservice.models.dtos.user.UserCreateDTO;
import kg.itschool.sellservice.models.dtos.user.UserResponseDTO;
import kg.itschool.sellservice.models.entities.User;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<?> creatUser(UserCreateDTO userCreateDTO);

    UserResponseDTO findByLogin(String login);

    void saveUser(User user);
}
