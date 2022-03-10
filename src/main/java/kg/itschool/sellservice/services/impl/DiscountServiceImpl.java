package kg.itschool.sellservice.services.impl;

import kg.itschool.sellservice.dao.DiscountRepo;
import kg.itschool.sellservice.exeptions.NotFoundException;
import kg.itschool.sellservice.mappers.DiscountMapper;
import kg.itschool.sellservice.models.dtos.discount.DiscountResponse;
import kg.itschool.sellservice.models.dtos.product.ProductResponse;
import kg.itschool.sellservice.models.entities.Discount;
import kg.itschool.sellservice.services.CodeService;
import kg.itschool.sellservice.services.DiscountService;
import kg.itschool.sellservice.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscountServiceImpl implements DiscountService {

    private final DiscountRepo discountRepo;
    private final CodeService codeService;
    private final ProductService productService;

    public DiscountServiceImpl(DiscountRepo discountRepo, CodeService codeService, ProductService productService) {
        this.discountRepo = discountRepo;
        this.codeService = codeService;
        this.productService = productService;
    }

    @Override
    public ResponseEntity<?> save(String token, DiscountResponse discountResponse) {
        ResponseEntity<?> responseEntity = codeService.verifyToken(token);
        if (!responseEntity.getStatusCode().equals(HttpStatus.OK)) {
            return responseEntity;
        }
        ProductResponse productResponse = productService.findByNameAndBarcode(discountResponse.getProduct().getName(),
                                                                              discountResponse.getProduct().getBarcode());
        if (!productResponse.getName().equals(discountResponse.getProduct().getName()) &&
                !productResponse.getBarcode().equals(discountResponse.getProduct().getBarcode())) {
            throw new NotFoundException("Ошибка", "продукт не найден");
        }
        Discount discount = DiscountMapper.INSTANCE.discountResponseToDiscount(discountResponse);
        List<Discount> discountList = discountRepo.findAllByActiveIsTrue(discountResponse.getProduct().getId());
        for (Discount s : discountList) {
            if (s.getStartDate().compareTo(discountResponse.getStartDate()) <= 0) {
                s.setActive(false);
                discountRepo.saveAndFlush(s);
            } else {
                discount.setActive(false);
            }
        }
        discountRepo.saveAndFlush(discount);
        return new ResponseEntity<>("Успешно сохранено", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getActualDiscount(String token, long id) {
        ResponseEntity<?> responseEntity = codeService.verifyToken(token);
        if (!responseEntity.getStatusCode().equals(HttpStatus.OK)) {
            return responseEntity;
        }
        Discount discount= discountRepo.findByActiveIsTrue(id);
        return new ResponseEntity<>(discount, HttpStatus.OK);
    }

    @Override
    public double findDiscountByProduct(ProductResponse productResponse) {
        Discount discount=discountRepo.findByActiveIsTrue(productResponse.getId());
        return discount.getDiscount();
    }
}