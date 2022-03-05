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
@Table(name = "prices")
public class Price extends BaseDate {
    double price;
    boolean active;
    @ManyToOne
    @JoinColumn(name = "products_id")
    Product product;
}
