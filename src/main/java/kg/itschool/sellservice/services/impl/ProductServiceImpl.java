package kg.itschool.sellservice.services.impl;

import kg.itschool.sellservice.dao.ProductRepo;
import kg.itschool.sellservice.exeptions.AlreadyExistsException;
import kg.itschool.sellservice.exeptions.NotFoundException;
import kg.itschool.sellservice.mappers.ProductMapper;
import kg.itschool.sellservice.models.dtos.category.CategoryResponse;
import kg.itschool.sellservice.models.dtos.product.ProductCreate;
import kg.itschool.sellservice.models.dtos.product.ProductResponse;
import kg.itschool.sellservice.models.entities.Product;
import kg.itschool.sellservice.services.CategoryService;
import kg.itschool.sellservice.services.CodeService;
import kg.itschool.sellservice.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Random;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;
    private final CodeService codeService;
    private final CategoryService categoryService;

    public ProductServiceImpl(ProductRepo productRepo, CodeService codeService, CategoryService categoryService) {
        this.productRepo = productRepo;
        this.codeService = codeService;
        this.categoryService = categoryService;
    }

    @Override
    public ResponseEntity<?> saveProduct(String token, ProductCreate productCreate ) {
        ResponseEntity<?> responseEntity =codeService.verifyToken(token);
        if (!responseEntity.getStatusCode().equals(HttpStatus.OK)){
            return responseEntity;
        }
        CategoryResponse categoryResponse=categoryService.findByName(productCreate.getCategory().getName());
        if (Objects.isNull(categoryResponse)){
            throw new NotFoundException("Ошибка","Такой категории нет");
        }
        Product product = productRepo.findByName(productCreate.getName());
        if (Objects.nonNull(product)){
            throw new AlreadyExistsException("Ошибка","Такой продукт уже есть");
        }
        int barcode = new Random().nextInt(999999) + 100000;
        product = ProductMapper.INSTANCE.productCreateToProduct(productCreate);
        product.setBarcode(String.valueOf(barcode));
        productRepo.saveAndFlush(product);
        return new ResponseEntity<>("Успешно сохранено",HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getAll(String token) {
        ResponseEntity<?> responseEntity =codeService.verifyToken(token);
        if (!responseEntity.getStatusCode().equals(HttpStatus.OK)){
            return responseEntity;
        }
        List<Product> products = productRepo.findAll();
        return new ResponseEntity<>(products,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> update(String token, ProductCreate productCreate) {
        ResponseEntity<?> responseEntity =codeService.verifyToken(token);
        if (!responseEntity.getStatusCode().equals(HttpStatus.OK)){
            return responseEntity;
        }
        CategoryResponse categoryResponse=categoryService.findByName(productCreate.getCategory().getName());
        if (Objects.isNull(categoryResponse)){
            throw new NotFoundException("Ошибка","Такой категории нет");
        }
        Product product = productRepo.findByName(productCreate.getName());
        if (Objects.nonNull(product)){
            throw new AlreadyExistsException("Ошибка","Такой продукт уже есть");
        }
        int barcode = new Random().nextInt(999999) + 100000;
        product = ProductMapper.INSTANCE.productCreateToProduct(productCreate);
        product.setBarcode(String.valueOf(barcode));
        productRepo.saveAndFlush(product);
        return new ResponseEntity<>("Успешно сохранено",HttpStatus.OK);
    }

    @Override
    public ProductResponse findByNameAndBarcode(String name, String barcode) {
        Product product = productRepo.findByNameAndBarcode(name,barcode);
        return ProductMapper.INSTANCE.productToProductResponse(product);
    }
}
