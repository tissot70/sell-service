package kg.itschool.sellservice.services.impl;

import kg.itschool.sellservice.dao.PriceRepo;
import kg.itschool.sellservice.exeptions.NotFoundException;
import kg.itschool.sellservice.mappers.PriceMapper;
import kg.itschool.sellservice.models.dtos.price.PriceResponse;
import kg.itschool.sellservice.models.dtos.product.ProductResponse;
import kg.itschool.sellservice.models.entities.Price;
import kg.itschool.sellservice.services.CodeService;
import kg.itschool.sellservice.services.PriceService;
import kg.itschool.sellservice.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceServiceImpl implements PriceService {
    private final PriceRepo priceRepo;
    private final CodeService codeService;
    private final ProductService productService;

    public PriceServiceImpl(PriceRepo priceRepo, CodeService codeService, ProductService productService) {
        this.priceRepo = priceRepo;
        this.codeService = codeService;
        this.productService = productService;
    }

    @Override
    public ResponseEntity<?> save(String token, PriceResponse priceResponse) {
        ResponseEntity<?> responseEntity =codeService.verifyToken(token);
        if (!responseEntity.getStatusCode().equals(HttpStatus.OK)){
            return responseEntity;
        }
        ProductResponse productResponse = productService.findByNameAndBarcode(priceResponse.getProduct().getName()
                                                                             ,priceResponse.getProduct().getBarcode());
        if (!productResponse.getName().equals(priceResponse.getProduct().getName()) &&
                !productResponse.getBarcode().equals(priceResponse.getProduct().getBarcode())){
            throw new NotFoundException("Ошибка","продукт не найден");
        }
        Price price = PriceMapper.INSTANCE.priceResponseToPrice(priceResponse);
        List<Price> priceList = priceRepo.findAllByActiveIsTrue(priceResponse.getProduct().getId());
        for (Price s : priceList) {
            if (s.getStartDate().compareTo(priceResponse.getStartDate()) <= 0) {
                s.setActive(false);
                priceRepo.saveAndFlush(s);
            } else {
                price.setActive(false);
            }
        }
        priceRepo.saveAndFlush(price);
        return new ResponseEntity<>("Успешно сохранено", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getActualPrice(String token, long id) {
        ResponseEntity<?> responseEntity =codeService.verifyToken(token);
        if (!responseEntity.getStatusCode().equals(HttpStatus.OK)){
            return responseEntity;
        }
        Price price= priceRepo.findByActiveIsTrue(id);
        return new ResponseEntity<>(price,HttpStatus.OK);
    }

    @Override
    public double findPriceByProduct(ProductResponse productResponse) {
        Price price=priceRepo.findByActiveIsTrue(productResponse.getId());
        return price.getPrice();
    }
}