package kg.itschool.sellservice.services;

import kg.itschool.sellservice.models.dtos.discount.DiscountResponse;
import org.springframework.http.ResponseEntity;

public interface DiscountService {
    ResponseEntity<?> save(String token, DiscountResponse discountResponse);
}
