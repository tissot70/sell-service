package kg.itschool.sellservice.dao;

import kg.itschool.sellservice.models.dtos.category.CategoryResponse;
import kg.itschool.sellservice.models.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Long> {

    boolean existsByName(String name);

    Category findByName(String name);

    List<CategoryResponse> findAllByName(String name);
}
