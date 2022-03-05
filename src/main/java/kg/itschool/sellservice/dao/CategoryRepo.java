package kg.itschool.sellservice.dao;

import kg.itschool.sellservice.models.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Long> {

    boolean existsByName(String name);

    Category findByName(String name);
}
