package br.vitorreck.app.services;

import br.vitorreck.app.domain.dto.product.ProductRequestDTO;
import br.vitorreck.app.domain.dto.product.ProductResponseDTO;
import br.vitorreck.app.domain.model.Category;
import br.vitorreck.app.domain.model.Product;
import br.vitorreck.app.mappers.ProductMapper;
import br.vitorreck.app.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Log4j2
public class ProductService {

  private static final String PRODUCT_NOT_FOUND = "Product doesn't exist";
  private static final String PRODUCT_ALREADY_EXISTS = "Product previously created, please update with new fields";
  private final ProductRepository productRepository;
  private final CategoryService categoryService;
  private final ProductMapper productMapper;

  public List<ProductResponseDTO> listProducts(Pageable pageable) {
    List<ProductResponseDTO> products = productRepository.findAll(pageable).getContent()
        .stream()
        .map(productMapper::toDTO)
        .toList();

    if (products.isEmpty())
      throw new NoSuchElementException(PRODUCT_NOT_FOUND);
    else
      return products;
  }

  public ProductResponseDTO retrieveProductById(String id) {
    log.info("GET /products/{id} - retrieving product with ID: {}", id);
    return productRepository.findById(id)
        .map(productMapper::toDTO)
        .orElseThrow(() -> new NoSuchElementException(PRODUCT_NOT_FOUND));
  }

  public ProductResponseDTO createProduct(ProductRequestDTO productDTO) {
    productRepository.findByName(productDTO.name())
        .ifPresent(p -> {throw new DuplicateKeyException(PRODUCT_ALREADY_EXISTS);});

    Category category = categoryService.retrieveCategoryByName(productDTO.category());

    Product product = productMapper.toEntity(productDTO);
    product.setId(UUID.randomUUID().toString());
    product.setCategory(category);
    product.setCreatedAt(Instant.now());
    product.setUpdatedAt(Instant.now());

    log.info("POST /products - creating product with name: {}", product.getName());
    return productMapper.toDTO(productRepository.insert(product));
  }

  public ProductResponseDTO updateProduct(String id, ProductRequestDTO updatedProduct) {
    Product product = productRepository.findById(id)
        .orElseThrow(() -> new NoSuchElementException(PRODUCT_NOT_FOUND));

    product.setName(updatedProduct.name());
    product.setDescription(updatedProduct.description());
    product.setPrice(updatedProduct.price());
    product.setStock(updatedProduct.stock());
    product.setUpdatedAt(Instant.now());

    log.info("PUT /products/{id} - updating product with ID: {}", id);
    return productMapper.toDTO(productRepository.save(product));
  }

  public void deleteProductById(String id) {
    log.info("DELETE /products/{id} - removing product with ID: {}", id);
    productRepository.findById(id)
        .ifPresentOrElse(p -> {
          productRepository.deleteById(id);
        },
            () -> {throw new NoSuchElementException(PRODUCT_NOT_FOUND);});
  }
}
