package kg.itschool.sellservice.mappers;

import kg.itschool.sellservice.models.dtos.product.ProductCreate;
import kg.itschool.sellservice.models.dtos.product.ProductResponse;
import kg.itschool.sellservice.models.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductResponse productToProductResponse(Product product);
    Product productResponseToProduct(ProductResponse productResponse);

    ProductCreate productToProductCreate(Product product);
    Product productCreateToProduct(ProductCreate productCreate);
}
