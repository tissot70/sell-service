package kg.itschool.sellservice.services;

import kg.itschool.sellservice.models.dtos.product.ProductCreate;
import kg.itschool.sellservice.models.dtos.product.ProductResponse;
import org.springframework.http.ResponseEntity;

public interface ProductService {
    ResponseEntity<?> saveProduct(String token, ProductCreate productCreate);

    ResponseEntity<?> getAll(String token);

    ResponseEntity<?> update(String token, ProductCreate productCreate);

    ProductResponse findByNameAndBarcode(String name, String barcode);

    ResponseEntity<?> getActual(String token);

    ProductResponse findByBarcode(String barcode);
}
