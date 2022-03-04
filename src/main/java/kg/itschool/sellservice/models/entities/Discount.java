package kg.itschool.sellservice.models.entities;

import kg.itschool.sellservice.models.entities.base.BaseDate;
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
@Table(name = "discounts")
public class Discount extends BaseDate {
    int discount;
    @ManyToOne
    @JoinColumn(name = "products_id")
    Product product;
}
