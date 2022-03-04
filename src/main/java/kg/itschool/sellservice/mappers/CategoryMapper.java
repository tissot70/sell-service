package kg.itschool.sellservice.mappers;

import kg.itschool.sellservice.models.dtos.category.CategoryResponse;
import kg.itschool.sellservice.models.entities.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryResponse categoryToCategoryResponse(Category category);
    Category categoryResponseToCategory(CategoryResponse categoryResponse);
}
