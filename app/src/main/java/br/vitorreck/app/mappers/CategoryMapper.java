package br.vitorreck.app.mappers;

import br.vitorreck.app.domain.dto.category.CategoryResponseDTO;
import br.vitorreck.app.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {

  CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

  CategoryResponseDTO toDTO(Category category);
}
