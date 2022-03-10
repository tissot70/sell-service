package kg.itschool.sellservice.services;

import kg.itschool.sellservice.models.dtos.price.PriceResponse;
import kg.itschool.sellservice.models.dtos.product.ProductResponse;
import org.springframework.http.ResponseEntity;

public interface PriceService {
    ResponseEntity<?> save(String token, PriceResponse priceResponse);

    ResponseEntity<?> getActualPrice(String token, long id);

    double findPriceByProduct(ProductResponse productResponse);
}
