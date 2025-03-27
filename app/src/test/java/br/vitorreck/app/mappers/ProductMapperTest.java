package br.vitorreck.app.mappers;

import br.vitorreck.app.domain.dto.product.ProductRequestDTO;
import br.vitorreck.app.domain.dto.product.ProductResponseDTO;
import br.vitorreck.app.domain.model.Category;
import br.vitorreck.app.domain.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductMapperTest {

  @Spy private ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);

  private Product mockProduct;
  private ProductRequestDTO mockRequest;
  private Category mockCategory;

  @BeforeEach
  void setUp() {
    mockCategory = Category.builder()
        .id("1")
        .name("Higiene")
        .createdAt(Instant.now())
        .updatedAt(Instant.now())
        .build();

    mockRequest = ProductRequestDTO.builder()
        .name("Shampoo Clear Men Cristiano Ronaldo")
        .description("Shampoo only for champions!")
        .price(BigDecimal.valueOf(9.99))
        .category("Higiene")
        .stock(100)
        .build();

    mockProduct = productMapper.toEntity(mockRequest);
  }

  @Test
  void testShouldConvertToDTO_whenToDTO() {
    ProductResponseDTO responseDTO = ProductMapper.INSTANCE.toDTO(mockProduct);

    assertNotNull(responseDTO);
    assertInstanceOf(ProductResponseDTO.class, responseDTO);
  }

  @Test
  void testShouldConvertToEntity_whenToEntity() {
    Product product = ProductMapper.INSTANCE.toEntity(mockRequest);

    assertNotNull(product);
    assertInstanceOf(Product.class, product);
  }
}
