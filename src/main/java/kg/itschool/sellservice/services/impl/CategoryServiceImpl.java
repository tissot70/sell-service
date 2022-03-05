package kg.itschool.sellservice.services.impl;

import kg.itschool.sellservice.dao.CategoryRepo;
import kg.itschool.sellservice.exeptions.AlreadyExistsException;
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
            throw new AlreadyExistsException("Ошибка","Такое название уже есть");
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
    public ResponseEntity<?> getAll(String token) {
        ResponseEntity<?> responseEntity =codeService.verifyToken(token);
        if (!responseEntity.getStatusCode().equals(HttpStatus.OK)){
            return responseEntity;
        }
        List<Category> categories = categoryRepo.findAll();
        return new ResponseEntity<>(categories,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> update(String token, CategoryResponse categoryResponse) {
        ResponseEntity<?> responseEntity =codeService.verifyToken(token);
        if (!responseEntity.getStatusCode().equals(HttpStatus.OK)){
            return responseEntity;
        }
        boolean exist = categoryRepo.existsByName(categoryResponse.getName());
        if (exist){
            throw new AlreadyExistsException("Ошибка","Такое название уже есть");
        }
        Category category = CategoryMapper.INSTANCE.categoryResponseToCategory(categoryResponse);
        categoryRepo.saveAndFlush(category);
        return new ResponseEntity<>("Успешно сохранено",HttpStatus.OK);
    }
}