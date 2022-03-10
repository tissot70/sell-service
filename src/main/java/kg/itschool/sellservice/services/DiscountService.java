package kg.itschool.sellservice.services;

import kg.itschool.sellservice.models.dtos.discount.DiscountResponse;
import kg.itschool.sellservice.models.dtos.product.ProductResponse;
import org.springframework.http.ResponseEntity;

public interface DiscountService {
    ResponseEntity<?> save(String token, DiscountResponse discountResponse);

    ResponseEntity<?> getActualDiscount(String token, long id);

    double findDiscountByProduct(ProductResponse productResponse);
}
