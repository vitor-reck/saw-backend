package br.vitorreck.app.services;

import br.vitorreck.app.domain.dto.product.ProductRequestDTO;
import br.vitorreck.app.domain.dto.product.ProductResponseDTO;
import br.vitorreck.app.domain.model.Category;
import br.vitorreck.app.domain.model.Product;
import br.vitorreck.app.mappers.ProductMapper;
import br.vitorreck.app.repositories.ProductRepository;
import com.mongodb.DuplicateKeyException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

  @InjectMocks private ProductService productService;
  @Mock private ProductRepository productRepository;
  @Mock private CategoryService categoryService;
  @Spy private ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);

  private ProductService mockService;
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
    mockService = mock(ProductService.class);
  }

  @Test
  void testShouldReturnProduct_whenRetrievedById() {
    when(productRepository.findById(anyString())).thenReturn(Optional.of(mockProduct));

    ProductResponseDTO responseDTO = productService.retrieveProductById("1");

    assertNotNull(responseDTO);
    assertTrue(responseDTO.stock().equals(100));
  }

  @Test
  void testShouldThrowDuplicateKeyException_whenCreateProduct() {
    when(productRepository.findByName(anyString())).thenThrow(DuplicateKeyException.class);

    assertThrows(DuplicateKeyException.class, () -> productService.createProduct(mockRequest));
  }

  @Test
  void testShouldReturnProduct_whenUpdateProduct() {
    when(productRepository.findById(anyString())).thenReturn(Optional.of(mockProduct));
    mockProduct.setCategory(new Category("2", "Eletrônicos", Instant.now(), Instant.now()));

    when(productRepository.save(mockProduct)).thenReturn(mockProduct);
    ProductResponseDTO responseDTO = productService.updateProduct("1", mockRequest);

    assertNotNull(responseDTO);
    assertEquals("Eletrônicos", responseDTO.category());
  }

  @Test
  void testShouldThrowNoSuchElementException_whenDeleteById() {
    doThrow(NoSuchElementException.class)
        .when(mockService).deleteProductById(anyString());

    assertThrows(NoSuchElementException.class, () -> mockService.deleteProductById(anyString()));
  }
}
