package kg.itschool.sellservice.dao;

import kg.itschool.sellservice.models.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {

    Product findByName(String name);

    Product findByNameAndBarcode(String name, String barcode);

    @Query(value = "select p.name, d.discount, pp.price from products p \n" +
            "inner join discounts d on p.id = d.products_id\n" +
            "inner join prices pp on p.id= pp.products_id\n" +
            "where pp.active = true\n" +
            "and d.active=true",nativeQuery = true)
    List<Object[]> findAllActual();

    Product findByBarcode(String barcode);
}
