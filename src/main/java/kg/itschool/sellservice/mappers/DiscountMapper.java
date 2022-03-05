package kg.itschool.sellservice.mappers;

import kg.itschool.sellservice.models.dtos.discount.DiscountResponse;
import kg.itschool.sellservice.models.entities.Discount;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DiscountMapper {
    DiscountMapper INSTANCE = Mappers.getMapper(DiscountMapper.class);

    DiscountResponse discountToDiscountResponse (Discount discount);
    Discount discountResponseToDiscount(DiscountResponse discountResponse);
}
