package kg.itschool.sellservice.controllers;

import kg.itschool.sellservice.models.dtos.price.PriceResponse;
import kg.itschool.sellservice.services.PriceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/price")
public class PriceController {
    private final PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestHeader String token, @RequestBody PriceResponse priceResponse){
        return priceService.save(token,priceResponse);
    }

    @GetMapping("/getActualPrice")
    public ResponseEntity<?> get(@RequestHeader String token, @RequestParam long id){
        return priceService.getActualPrice(token,id);
    }
}
