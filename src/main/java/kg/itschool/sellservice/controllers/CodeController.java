package kg.itschool.sellservice.controllers;

import kg.itschool.sellservice.models.dtos.user.UserCreateDTO;
import kg.itschool.sellservice.services.CodeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/code")
public class CodeController {

    private CodeService codeService;

    public CodeController(CodeService codeService) {
        this.codeService = codeService;
    }

    @PostMapping("/sendCode")
    public ResponseEntity<?> sendCode(@RequestBody UserCreateDTO userCreateDTO){
        return codeService.sendCode(userCreateDTO);
    }

    @PostMapping("/confirm")
    public ResponseEntity<?> confirm(@RequestParam String code, @RequestParam String login){
        return codeService.confirm( code,login);
    }

    @GetMapping("/verify")
    public ResponseEntity<?> verifyToken(@RequestHeader String token){
        return codeService.verifyToken(token);
    }
}
