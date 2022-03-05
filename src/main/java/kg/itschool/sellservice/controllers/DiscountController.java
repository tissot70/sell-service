package kg.itschool.sellservice.controllers;

import kg.itschool.sellservice.models.dtos.discount.DiscountResponse;
import kg.itschool.sellservice.services.DiscountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/discount")
public class DiscountController {

    private final DiscountService discountService;

    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestHeader String token, @RequestBody DiscountResponse discountResponse){
        return discountService.save(token,discountResponse);
    }
    
}
