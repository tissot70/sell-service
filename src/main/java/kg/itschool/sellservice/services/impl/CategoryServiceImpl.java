package kg.itschool.sellservice.services.impl;

import kg.itschool.sellservice.dao.CategoryRepo;
import kg.itschool.sellservice.mappers.CategoryMapper;
import kg.itschool.sellservice.models.dtos.category.CategoryResponse;
import kg.itschool.sellservice.models.entities.Category;
import kg.itschool.sellservice.services.CategoryService;
import kg.itschool.sellservice.services.CodeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepo categoryRepo;
    private final CodeService codeService;

    public CategoryServiceImpl(CategoryRepo categoryRepo, CodeService codeService) {
        this.categoryRepo = categoryRepo;
        this.codeService = codeService;
    }

    @Override
    public ResponseEntity<?> saveCategory(String token,CategoryResponse categoryResponse) {
        ResponseEntity<?> responseEntity =codeService.verifyToken(token);
        if (!responseEntity.getStatusCode().equals(HttpStatus.OK)){
            return responseEntity;
        }
        boolean exist = categoryRepo.existsByName(categoryResponse.getName());
        if (exist){
            return new ResponseEntity<>("Такое название уже есть", HttpStatus.CONFLICT);
        }
        Category category = CategoryMapper.INSTANCE.categoryResponseToCategory(categoryResponse);
        categoryRepo.saveAndFlush(category);
        return new ResponseEntity<>("Успешно сохранено",HttpStatus.OK);
    }

    @Override
    public CategoryResponse findByName(String name) {
        return CategoryMapper.INSTANCE.categoryToCategoryResponse(categoryRepo.findByName(name));
    }

    @Override
    public ResponseEntity<?> getAll(String token, CategoryResponse categoryResponse) {
        ResponseEntity<?> responseEntity =codeService.verifyToken(token);
        if (!responseEntity.getStatusCode().equals(HttpStatus.OK)){
            return responseEntity;
        }
        List<CategoryResponse> categoryResponses = categoryRepo.findAllByName(categoryResponse.getName());
        return new ResponseEntity<>(categoryResponses,HttpStatus.OK);
    }
}