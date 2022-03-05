package kg.itschool.sellservice.controllers;

import kg.itschool.sellservice.models.dtos.product.ProductCreate;
import kg.itschool.sellservice.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/saveProduct")
    public ResponseEntity<?> saveProduct(@RequestHeader String token, @RequestBody ProductCreate productCreate){
        return productService.saveProduct(token,productCreate);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(@RequestHeader String token){
        return productService.getAll(token);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestHeader String token, @RequestBody ProductCreate productCreate){
        return productService.update(token,productCreate);
    }
}
