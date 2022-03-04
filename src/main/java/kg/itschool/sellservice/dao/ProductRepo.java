package kg.itschool.sellservice.dao;

import kg.itschool.sellservice.models.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {

    Product findByName(String name);
}
