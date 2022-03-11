package kg.itschool.sellservice.services;

import kg.itschool.sellservice.models.dtos.operation.OperationDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OperationService {
    ResponseEntity<?> carryOutOperation(String token, List<OperationDTO> operationDTO);

    ResponseEntity<?> paying(String token, Long operationId, Double cash);

    ResponseEntity<?> getPayingOfUser(String token, String login);
}
