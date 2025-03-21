package br.vitorreck.app.services;

import br.vitorreck.app.domain.model.Category;
import br.vitorreck.app.domain.model.Product;
import br.vitorreck.app.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ProductService {

  private static final String PRODUCT_NOT_FOUND = "Product doesn't exist";
  private static final String PRODUCT_ALREADY_EXISTS = "Product previously created, please update with new fields";
  private final ProductRepository productRepository;
  private final CategoryService categoryService;

  public List<Product> listProducts(Pageable pageable) {
    List<Product> products = productRepository.findAll(pageable).getContent();

    if (products.isEmpty())
      throw new NoSuchElementException(PRODUCT_NOT_FOUND);
    else
      return products;
  }

  public Product retrieveProductByString(String id) {
    return productRepository.findById(id)
        .orElseThrow(() -> new NoSuchElementException(PRODUCT_NOT_FOUND));
  }

  public Product createProduct(Product product) {
    productRepository.findByName(product.getName())
        .ifPresent(p -> {throw new DuplicateKeyException(PRODUCT_ALREADY_EXISTS);});

    Category category = categoryService.retrieveCategoryByName(product.getCategory().getName());

    product.setId(UUID.randomUUID().toString());
    product.setCategory(category);
    product.setCreatedAt(Instant.now());
    product.setUpdatedAt(Instant.now());

    return productRepository.insert(product);
  }

  public Product updateProduct(String id, Product updatedProduct) {
    Product product = productRepository.findById(id)
        .orElseThrow(() -> new NoSuchElementException(PRODUCT_NOT_FOUND));

    product.setName(updatedProduct.getName());
    product.setDescription(updatedProduct.getDescription());
    product.setPrice(updatedProduct.getPrice());
    product.setStock(updatedProduct.getStock());
    product.setUpdatedAt(Instant.now());

    return productRepository.save(product);
  }

  public void deleteProduct(String id) {
    productRepository.findById(id)
        .ifPresentOrElse(p -> {
          productRepository.deleteById(id);
        },
            () -> {throw new NoSuchElementException(PRODUCT_NOT_FOUND);});
  }
}
