package kg.itschool.sellservice.services.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import kg.itschool.sellservice.dao.OperationRepo;
import kg.itschool.sellservice.exeptions.IncorrectDataException;
import kg.itschool.sellservice.exeptions.NotFoundException;
import kg.itschool.sellservice.mappers.OperationMapper;
import kg.itschool.sellservice.mappers.UserMapper;
import kg.itschool.sellservice.models.dtos.operation.*;
import kg.itschool.sellservice.models.dtos.product.ProductResponse;
import kg.itschool.sellservice.models.dtos.user.UserResponseDTO;
import kg.itschool.sellservice.models.entities.Operation;
import kg.itschool.sellservice.services.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

@Service
public class OperationServiceImpl implements OperationService {
    private final OperationRepo operationRepo;
    private final CodeService codeService;
    private final OperationDetailService operationDetailService;
    private final ProductService productService;
    private final PriceService priceService;
    private final DiscountService discountService;
    private final UserService userService;

    @Value("${jwtSecret}")
    private String secretKey;

    public OperationServiceImpl(OperationRepo operationRepo, CodeService codeService,
                                OperationDetailService operationDetailService, ProductService productService,
                                PriceService priceService, DiscountService discountService, UserService userService) {
        this.operationRepo = operationRepo;
        this.codeService = codeService;
        this.operationDetailService = operationDetailService;
        this.productService = productService;
        this.priceService = priceService;
        this.discountService = discountService;
        this.userService = userService;
    }

    @Override
    public ResponseEntity<?> carryOutOperation(String token, List<OperationDTO> operationDTOList) {
        ResponseEntity<?> responseEntity = codeService.verifyToken(token);
        if (!responseEntity.getStatusCode().equals(HttpStatus.OK)) {
            return responseEntity;
        }

        ProductResponse productResponse;
        Operation operation = new Operation();

        List<OperationDetailResponse> operationDetailResponseList = new ArrayList<>();
        List<CheckDetailDTO> checkDetailDTOList=new ArrayList<>();

        double totalPrice=0;

        for (OperationDTO operationDTO: operationDTOList){
            productResponse=productService.findByBarcode(operationDTO.getBarcode());
            if (Objects.isNull(productResponse)) {
                throw  new NotFoundException("Ошибка", "Проверьте введенный штрихкод: " + operationDTO.getBarcode());
            }
            OperationDetailResponse operationDetailResponse = new OperationDetailResponse();

            double price= priceService.findPriceByProduct(productResponse);
            double discount=1-(discountService.findDiscountByProduct(productResponse))/100;
            double sum ;
            if (discountService.findDiscountByProduct(productResponse) == 0){
                sum= price * operationDTO.getQuantity();
            }else {
                sum= (price*discount) * operationDTO.getQuantity();
            }
            totalPrice+=sum;

            CheckDetailDTO checkDetailDTO= new CheckDetailDTO();

            operationDetailResponse.setProduct(productResponse);
            operationDetailResponse.setQuantity(operationDTO.getQuantity());
            operationDetailResponse.setAmount(sum);
            operationDetailResponseList.add(operationDetailResponse);

            checkDetailDTO.setName(productResponse.getName());
            checkDetailDTO.setBarcode(productResponse.getBarcode());
            checkDetailDTO.setQuantity(operationDTO.getQuantity());
            checkDetailDTO.setDiscount(discountService.findDiscountByProduct(productResponse));
            checkDetailDTO.setPrice(price);
            checkDetailDTO.setSum(sum);

            checkDetailDTOList.add(checkDetailDTO);
        }

        Jws<Claims> jwt = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
        UserResponseDTO userResponseDTO= userService.findByLogin((String) jwt.getBody().get("login"));

        CheckDTO checkDTO = new CheckDTO();
        checkDTO.setCashier(userResponseDTO.getName());
        checkDTO.setCheckDetailDTOS(checkDetailDTOList);
        checkDTO.setTotalPrice(totalPrice);

        operation.setTotalPrice(totalPrice);
        operation.setUser(UserMapper.INSTANCE.userResponseToUser(userResponseDTO));
        operationRepo.saveAndFlush(operation);

        for (OperationDetailResponse response: operationDetailResponseList){
            response.setOperation(OperationMapper.INSTANCE.operationToOperationResponse(operation));
        }
        operationDetailService.saveOperationDetails(operationDetailResponseList);
        return new ResponseEntity<>(checkDTO,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> paying(String token, Long operationId, Double cash) {
        ResponseEntity<?> responseEntity = codeService.verifyToken(token);
        if (!responseEntity.getStatusCode().equals(HttpStatus.OK)) {
            return responseEntity;
        }
        Operation operation=operationRepo.findOperationById(operationId);
        if (Objects.isNull(operation)){
            throw new NotFoundException("Ошибка","Не найдено");
        }
        double change = cash - operation.getTotalPrice();
        if (change < 0) {
            throw new IncorrectDataException("Ошибка","Недостаточно средств для проведения операции!");
        }
        operation.setCash(cash);
        operation.setChange(change);
        operationRepo.save(operation);
        OperationResponse operationResponse = OperationMapper.INSTANCE.operationToOperationResponse(operation);
        return new ResponseEntity<>(operationResponse,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getPayingOfUser(String token, String login) {
        ResponseEntity<?> responseEntity = codeService.verifyToken(token);
        if (!responseEntity.getStatusCode().equals(HttpStatus.OK)) {
            return responseEntity;
        }
        String loginValidate="(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        if (!Pattern.matches(loginValidate,login)){
            throw new IncorrectDataException("Ошибка","вы ввели некоректный email");
        }
        UserResponseDTO userResponseDTO =userService.findByLogin(login);
        if (Objects.isNull(userResponseDTO)){
            throw new NotFoundException("Ошибка","Такого пользователя нет");
        }

        List<Object[]> objects=operationRepo.find(userResponseDTO.getId());
        UserOperation userOperation=new UserOperation();
        userOperation.setLogin(userResponseDTO.getLogin());
        userOperation.setName(userResponseDTO.getName());
        userOperation.setSum(operationRepo.totalSum(userResponseDTO.getId()));
        userOperation.setChange(operationRepo.totalChange(userResponseDTO.getId()));
        userOperation.setProducts(objects);
        return new ResponseEntity<>(userOperation,HttpStatus.OK);
    }
}