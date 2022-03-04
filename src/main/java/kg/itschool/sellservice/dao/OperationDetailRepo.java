package kg.itschool.sellservice.dao;

import kg.itschool.sellservice.models.entities.OperationDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationDetailRepo extends JpaRepository<OperationDetail,Long> {
}
