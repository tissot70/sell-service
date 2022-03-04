package kg.itschool.sellservice.services;

import kg.itschool.sellservice.models.dtos.product.ProductCreate;
import org.springframework.http.ResponseEntity;

public interface ProductService {
    ResponseEntity<?> saveProduct(String token, ProductCreate productCreate);
}
