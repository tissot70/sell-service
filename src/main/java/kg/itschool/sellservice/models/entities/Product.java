package kg.itschool.sellservice.models.entities;

import kg.itschool.sellservice.models.entities.base.BaseNameActive;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "products")
public class Product extends BaseNameActive {
    @ManyToOne
    @JoinColumn(name = "categoties_id")
    Category category;
    @Column(unique = true)
    String barcode;
}
