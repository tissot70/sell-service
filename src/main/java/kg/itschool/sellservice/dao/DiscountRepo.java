package kg.itschool.sellservice.dao;

import kg.itschool.sellservice.models.entities.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscountRepo extends JpaRepository<Discount,Long> {

    @Query(value = "select * from discounts where active = true and products_id=?1 ",nativeQuery = true)
    List<Discount> findAllByActiveIsTrue(long id);

    @Query(value = "select * from discounts where active = true and products_id=?1",nativeQuery = true)
    Discount findByActiveIsTrue(long id);
}