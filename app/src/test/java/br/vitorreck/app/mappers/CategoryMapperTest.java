package br.vitorreck.app.mappers;

import br.vitorreck.app.domain.dto.category.CategoryResponseDTO;
import br.vitorreck.app.domain.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class CategoryMapperTest {

  @Spy private CategoryMapper categoryMapper = Mappers.getMapper(CategoryMapper.class);

  private Category mockCategory;

  @BeforeEach
  void setUp() {
    mockCategory = Category.builder()
        .id("1")
        .name("Higiene")
        .createdAt(Instant.now())
        .updatedAt(Instant.now())
        .build();
  }

  @Test
  void testShouldConvertToDTO_whenToDTO() {
    CategoryResponseDTO responseDTO = CategoryMapper.INSTANCE.toDTO(mockCategory);

    assertNotNull(responseDTO);
    assertInstanceOf(CategoryResponseDTO.class, responseDTO);
  }
}
