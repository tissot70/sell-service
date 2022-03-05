package kg.itschool.sellservice.services;

import kg.itschool.sellservice.models.dtos.price.PriceResponse;
import org.springframework.http.ResponseEntity;

public interface PriceService {
    ResponseEntity<?> save(String token, PriceResponse priceResponse);
}
