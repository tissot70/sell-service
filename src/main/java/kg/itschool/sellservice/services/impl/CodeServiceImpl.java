package kg.itschool.sellservice.services.impl;

import io.jsonwebtoken.*;
import kg.itschool.sellservice.dao.CodeRepo;
import kg.itschool.sellservice.mappers.UserMapper;
import kg.itschool.sellservice.models.dtos.user.UserCreateDTO;
import kg.itschool.sellservice.models.dtos.user.UserResponseDTO;
import kg.itschool.sellservice.models.entities.Code;
import kg.itschool.sellservice.models.enums.CodeStatus;
import kg.itschool.sellservice.services.CodeService;
import kg.itschool.sellservice.services.RequestService;
import kg.itschool.sellservice.services.SendSimpleMessage;
import kg.itschool.sellservice.services.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Objects;
import java.util.regex.Pattern;

@Service
public class CodeServiceImpl implements CodeService {

    private final CodeRepo codeRepo;
    private final UserService userService;
    private final SendSimpleMessage sendSimpleMessage;
    private final RequestService requestService;

    @Value("${jwtSecret}")
    private String secretKey;

    public CodeServiceImpl(CodeRepo codeRepo, UserService userService, SendSimpleMessage sendSimpleMessage, RequestService requestService) {
        this.codeRepo = codeRepo;
        this.userService = userService;
        this.sendSimpleMessage = sendSimpleMessage;
        this.requestService = requestService;
    }

    @Override
    public ResponseEntity<?> sendCode(UserCreateDTO userCreateDTO) {
        String loginValidate="(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        if (!Pattern.matches(loginValidate,userCreateDTO.getLogin())){
            return new ResponseEntity<>("вы ввели некоректный email",HttpStatus.CONFLICT);
        }
        UserResponseDTO userResponseDTO =userService.findByLogin(userCreateDTO.getLogin());
        if (Objects.isNull(userResponseDTO)){
            return new ResponseEntity<>("такого пользователя нет ", HttpStatus.CONFLICT);
        }
        boolean verification= userBlockDateChecking(userResponseDTO);
        if (verification){
            return new ResponseEntity<>("Превышено количество попыток. Повторите в "+ userResponseDTO.getBlockDate(),HttpStatus.CONFLICT);
        }
        Code lastCode= codeRepo.findByUserAndCodeStatus(UserMapper.INSTANCE.userResponseToUser(userResponseDTO));
        if (Objects.nonNull(lastCode)) {
            lastCode.setEndDate(LocalDateTime.now());
            lastCode.setCodeStatus(CodeStatus.CANCELED);
            codeRepo.saveAndFlush(lastCode);
        }
        Code code = new Code();
        int randomCode = 999 + (int)(Math.random() * ((9999 - 999) + 1));
        String hashedCode = BCrypt.hashpw(String.valueOf(randomCode), BCrypt.gensalt());
        code.setCode(hashedCode);
        code.setEndDate(LocalDateTime.now().plusMinutes(3));
        code.setCodeStatus(CodeStatus.NEW);
        code.setUser(UserMapper.INSTANCE.userResponseToUser(userResponseDTO));
        codeRepo.saveAndFlush(code);
        sendSimpleMessage.sendSimpleMessage(userResponseDTO.getLogin(), String.valueOf(randomCode));
        return new ResponseEntity<>("Ваш код подтверждения отправлен на почту этот код действует 3 минуты",HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> confirm(String code, String login) {
        UserResponseDTO userResponseDTO= userService.findByLogin(login);
        String loginValidate="(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        if (!Pattern.matches(loginValidate,login)){
            return new ResponseEntity<>("вы ввели некоректный email",HttpStatus.CONFLICT);
        }

        if (Objects.isNull(userResponseDTO)){
            return new ResponseEntity<>("такого пользователя нет ", HttpStatus.CONFLICT);
        }

        boolean verification= userBlockDateChecking(userResponseDTO);
        if (verification){
            return new ResponseEntity<>("Превышено количество попыток. Повторите в "+ userResponseDTO.getBlockDate(),HttpStatus.CONFLICT);
        }

        Code codeChecking =codeRepo.findByUserAndCodeStatus(UserMapper.INSTANCE.userResponseToUser(userResponseDTO),CodeStatus.NEW);
        if (LocalDateTime.now().isAfter(codeChecking.getEndDate())){
            return new ResponseEntity<>("Время действия кода истек! Получите код повторно",HttpStatus.CONFLICT);
        }

        if (!BCrypt.checkpw(code, codeChecking.getCode())){
            requestService.saveRequest(codeChecking, false);
            if (requestService.countOfFailed(codeChecking) >= 3){
                userResponseDTO.setBlockDate(LocalDateTime.now().plusMinutes(3));
                userService.saveUser(UserMapper.INSTANCE.userResponseToUser(userResponseDTO));

                codeChecking.setCodeStatus(CodeStatus.FAILED);
                codeRepo.saveAndFlush(codeChecking);
            }
            return new ResponseEntity<>("Код не совподает",HttpStatus.ACCEPTED);
        }

        requestService.saveRequest(codeChecking, true);

        Calendar tokensTimeLive =
                Calendar.getInstance();
        tokensTimeLive
                .add(Calendar.MINUTE, 4);

        String token = Jwts.builder()
                        .claim("login", login)
                        .setExpiration(tokensTimeLive.getTime())
                        .signWith(SignatureAlgorithm.HS256, secretKey)
                        .compact();

        codeChecking.setCodeStatus(CodeStatus.APPROVED);
        codeRepo.saveAndFlush(codeChecking);
        return new ResponseEntity<>("Ваш код успешно подтверждён: "+ token,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> verifyToken(String token) {
        try {
            Jws<Claims> jwt = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);

            return ResponseEntity.ok(jwt.getBody().get("login"));
        } catch (ExpiredJwtException jwtException) {

            return new ResponseEntity<>("Время действия токена истек", HttpStatus.CONFLICT);
        } catch (UnsupportedJwtException jwtException) {

            return new ResponseEntity<>("Неподерживаемый токен", HttpStatus.CONFLICT);
        } catch (MalformedJwtException jwtException) {

            return new ResponseEntity<>("Некорректный токен", HttpStatus.CONFLICT);
        } catch (SignatureException signatureException) {

            return new ResponseEntity<>("Некорректная подпись в токене!", HttpStatus.CONFLICT);
        } catch (Exception exception) {

            return new ResponseEntity<>("unauthorized", HttpStatus.CONFLICT);
        }
    }

    public boolean userBlockDateChecking(UserResponseDTO userResponseDTO) {
        if (Objects.nonNull(userResponseDTO.getBlockDate())){
            if (LocalDateTime.now().isBefore(userResponseDTO.getBlockDate())){
                return true;
            }
        }
        return false;
    }
}