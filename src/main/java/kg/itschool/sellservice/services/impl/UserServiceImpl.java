package kg.itschool.sellservice.services.impl;

import kg.itschool.sellservice.dao.UserRepo;
import kg.itschool.sellservice.exeptions.AlreadyExistsException;
import kg.itschool.sellservice.exeptions.IncorrectDataException;
import kg.itschool.sellservice.mappers.UserMapper;
import kg.itschool.sellservice.models.dtos.user.UserCreateDTO;
import kg.itschool.sellservice.models.dtos.user.UserResponseDTO;
import kg.itschool.sellservice.models.entities.User;
import kg.itschool.sellservice.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {

    private UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;

    }

    @Override
    public ResponseEntity<?> creatUser(UserCreateDTO userCreateDTO) {
        String loginValidate="(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        if (!Pattern.matches(loginValidate,userCreateDTO.getLogin())){
            throw new IncorrectDataException("Ошибка","вы ввели некоректный email");
        }
        boolean exist=userRepo.existsByLogin(userCreateDTO.getLogin());
        if (exist){
            throw new AlreadyExistsException("Ошибка","такой пользователь уже есть");
        }
        User user = UserMapper.INSTANCE.userCreateDTOToUser(userCreateDTO);
        userRepo.saveAndFlush(user);
        return new ResponseEntity<>("Вы успешно зарегистрировались", HttpStatus.OK);
    }

    @Override
    public UserResponseDTO findByLogin(String login) {
        return UserMapper.INSTANCE.userToUserResponse(userRepo.findByLogin(login));
    }

    @Override
    public void saveUser(User user) {
        userRepo.saveAndFlush(user);
    }
}
