package kg.itschool.sellservice.dao;

import kg.itschool.sellservice.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {

    boolean existsByLogin(String login);

    User findByLogin(String login);

}
