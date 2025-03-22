package br.vitorreck.app.mappers;

import br.vitorreck.app.domain.dto.product.ProductRequestDTO;
import br.vitorreck.app.domain.dto.product.ProductResponseDTO;
import br.vitorreck.app.domain.model.Category;
import br.vitorreck.app.domain.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {

  ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

  @Mapping(source = "category.name", target = "category")
  ProductResponseDTO toDTO(Product product);

  @Mapping(source = "category", target = "category", qualifiedByName = "categoryFromName")
  Product toEntity(ProductRequestDTO dto);

  @Named("categoryFromName")
  default Category categoryFromName(String name) {
    if (name == null) {
      return null;
    }

    Category category = new Category();
    category.setName(name);
    return category;
  }
}
