package kg.itschool.sellservice.controllers;

import kg.itschool.sellservice.models.dtos.user.UserCreateDTO;
import kg.itschool.sellservice.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/creat")
    public ResponseEntity<?> creatUser(@RequestBody UserCreateDTO userCreateDTO){
        return userService.creatUser(userCreateDTO);
    }
}
