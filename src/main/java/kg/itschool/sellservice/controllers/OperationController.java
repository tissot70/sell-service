package kg.itschool.sellservice.controllers;

import kg.itschool.sellservice.models.dtos.operation.OperationDTO;
import kg.itschool.sellservice.services.OperationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/operation")
public class OperationController {
    private final OperationService operationService;

    public OperationController(OperationService operationService) {
        this.operationService = operationService;
    }

    @PostMapping("/carryOutOperation")
    public ResponseEntity<?> carryOutOperation(@RequestHeader String token, @RequestBody List<OperationDTO> operationDTO){
        return operationService.carryOutOperation(token,operationDTO);
    }

    @GetMapping("/paying")
    public ResponseEntity<?> paying(@RequestHeader String token, @RequestParam Long operationId, @RequestParam Double cash ){
        return operationService.paying(token,operationId,cash);
    }

    //@GetMapping("/")
}