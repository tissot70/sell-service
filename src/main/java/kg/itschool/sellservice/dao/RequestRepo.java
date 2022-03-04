package kg.itschool.sellservice.dao;

import kg.itschool.sellservice.models.entities.Code;
import kg.itschool.sellservice.models.entities.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepo extends JpaRepository<Request,Long> {

    int countByCodeAndSuccess(Code code, boolean value);
}
