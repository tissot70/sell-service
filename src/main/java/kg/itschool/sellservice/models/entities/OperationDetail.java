package kg.itschool.sellservice.models.entities;

import kg.itschool.sellservice.models.entities.base.BaseId;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "operation_details")
public class OperationDetail extends BaseId {
    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product;

    @ManyToOne
    @JoinColumn(name = "operation_id")
    Operation operation;

    int quantity;
    double amount;
}
