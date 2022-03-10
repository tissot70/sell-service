package kg.itschool.sellservice.controllers;

import kg.itschool.sellservice.models.dtos.category.CategoryResponse;
import kg.itschool.sellservice.services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/saveCategory")
    public ResponseEntity<?> saveCategory(@RequestHeader String token, @RequestBody CategoryResponse categoryResponse){
        return categoryService.saveCategory(token,categoryResponse);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(@RequestHeader String token){
        return  categoryService.getAll(token);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestHeader String token, @RequestBody CategoryResponse categoryResponse){
        return categoryService.update(token,categoryResponse);
    }

}
