package kg.itschool.sellservice.services;

import kg.itschool.sellservice.models.dtos.category.CategoryResponse;
import org.springframework.http.ResponseEntity;

public interface CategoryService {
    ResponseEntity<?> saveCategory(String token, CategoryResponse categoryResponse);

    CategoryResponse findByName(String category);

    ResponseEntity<?> getAll(String token, CategoryResponse categoryResponse);
}
