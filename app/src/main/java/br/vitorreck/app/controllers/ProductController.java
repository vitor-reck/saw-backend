package br.vitorreck.app.controllers;

import br.vitorreck.app.domain.dto.product.ProductRequestDTO;
import br.vitorreck.app.domain.dto.product.ProductResponseDTO;
import br.vitorreck.app.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

  private final ProductService productService;

  @GetMapping
  public ResponseEntity<List<ProductResponseDTO>> getAllProducts(Pageable pageable) {
    return ResponseEntity.status(HttpStatus.OK).body(productService.listProducts(pageable));
  }

  @GetMapping("{id}")
  public ResponseEntity<ProductResponseDTO> getProductByString(@PathVariable String id) {
    return ResponseEntity.status(HttpStatus.OK).body(productService.retrieveProductById(id));
  }

  @PostMapping
  public ResponseEntity<ProductResponseDTO> postProduct(@Valid @RequestBody ProductRequestDTO productDTO) {
    return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(productDTO));
  }

  @PutMapping("{id}")
  public ResponseEntity<ProductResponseDTO> putProduct(@PathVariable String id, @Valid @RequestBody ProductRequestDTO productDTO) {
    return ResponseEntity.status(HttpStatus.OK).body(productService.updateProduct(id, productDTO));
  }

  @DeleteMapping("{id}")
  public ResponseEntity<String> deleteProductByString(@PathVariable String id) {
    productService.deleteProductById(id);
    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
