package kg.itschool.sellservice.mappers;

import kg.itschool.sellservice.models.dtos.price.PriceResponse;
import kg.itschool.sellservice.models.entities.Price;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PriceMapper {
    PriceMapper INSTANCE = Mappers.getMapper(PriceMapper.class);

    PriceResponse priceToPriceResponse(Price price);
    Price priceResponseToPrice(PriceResponse priceResponse);
}
