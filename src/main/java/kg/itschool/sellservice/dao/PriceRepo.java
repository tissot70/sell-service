package kg.itschool.sellservice.dao;

import kg.itschool.sellservice.models.entities.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepo extends JpaRepository<Price,Long> {
}
