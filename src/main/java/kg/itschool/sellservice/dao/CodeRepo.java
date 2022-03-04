package kg.itschool.sellservice.dao;

import kg.itschool.sellservice.models.entities.Code;
import kg.itschool.sellservice.models.entities.User;
import kg.itschool.sellservice.models.enums.CodeStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeRepo extends JpaRepository<Code,Long> {

    Code findByUserAndCodeStatus(User user, CodeStatus status);

    @Query(value = "select * from codes where user_id=?1 order by start_date desc limit (1)",nativeQuery = true)
    Code findByUserAndCodeStatus(User user);

}