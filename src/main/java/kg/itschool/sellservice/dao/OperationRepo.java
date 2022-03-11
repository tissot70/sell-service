package kg.itschool.sellservice.dao;

import kg.itschool.sellservice.models.entities.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationRepo extends JpaRepository<Operation,Long> {

    Operation findOperationById(Long id);

    @Query(value = "select sum(total_price) from operations \n" +
            "where user_id=?1 and add_date>=(select now() \\:\\: date \\+ interval '0h')",nativeQuery = true)
    double totalSum(long id);

    @Query(value = "select sum(change) from operations \n" +
            "where user_id=1 and add_date>=(select now() \\:\\: date \\+ interval '0h')",nativeQuery = true)
    double totalChange(long id);
    @Query(value = "select sum(od.amount) as amount ,products.name,sum(od.quantity) as quantity \n" +
            "from operations as o\n" +
            "inner join operation_details as od on od.operation_id=o.id\n" +
            "inner join products on products.id=od.product_id\n" +
            "where o.add_date>=(select now() \\:\\: date \\+ interval '0h')\n" +
            "and o.user_id=?1 \n" +
            "group by products.name,o.user_id,od.amount",nativeQuery = true)
    List<Object[]> find (long id);
}
