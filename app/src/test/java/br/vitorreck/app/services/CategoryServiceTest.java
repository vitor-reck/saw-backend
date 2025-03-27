package br.vitorreck.app.services;

import br.vitorreck.app.domain.model.Category;
import br.vitorreck.app.repositories.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

  @InjectMocks private CategoryService categoryService;
  @Mock private CategoryRepository categoryRepository;

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
  void testShouldThrowNullPointerException_whenRetrievedByListCategories() {
    when(categoryRepository.findAll()).thenReturn(null);

    assertThrows(NullPointerException.class, () -> categoryService.listCategories());
  }

  @Test
  void testShouldReturnUser_whenRetrievedByName() {
    when(categoryRepository.findByName(anyString())).thenReturn(Optional.of(mockCategory));

    Category category = categoryService.retrieveCategoryByName("Higiene");

    assertNotNull(category);
    assertEquals("Higiene", category.getName());
  }
}
