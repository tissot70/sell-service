package kg.itschool.sellservice.dao;

import kg.itschool.sellservice.models.entities.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceRepo extends JpaRepository<Price,Long> {

    @Query(value = "select * from prices where active = true and products_id=?1 ",nativeQuery = true)
    List<Price> findAllByActiveIsTrue(long id);

    @Query(value = "select * from prices where active = true and products_id=?1",nativeQuery = true)
    Price findByActiveIsTrue(long id);
}
