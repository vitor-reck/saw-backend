package br.vitorreck.app.services;

import br.vitorreck.app.domain.dto.category.CategoryResponseDTO;
import br.vitorreck.app.domain.model.Category;
import br.vitorreck.app.mappers.CategoryMapper;
import br.vitorreck.app.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CategoryService {

  private static final String CATEGORY_NOT_FOUND = "Category doesn't exist";
  private final CategoryRepository categoryRepository;
  private final CategoryMapper categoryMapper;

  public List<CategoryResponseDTO> listCategories() {
    List<CategoryResponseDTO> categories = categoryRepository.findAll()
        .stream()
        .map(categoryMapper::toDTO)
        .toList();

    if (categories.isEmpty())
      throw new NoSuchElementException();
    else
      return categories;
  }

  public Category retrieveCategoryByName(String name) {
    return categoryRepository.findByName(name)
        .orElseThrow(() -> new NoSuchElementException(CATEGORY_NOT_FOUND));
  }
}
