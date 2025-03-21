package br.vitorreck.app.controllers;

import br.vitorreck.app.domain.model.Product;
import br.vitorreck.app.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.String;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

  private final ProductService productService;

  @GetMapping
  public ResponseEntity<List<Product>> getAllProducts(Pageable pageable) {
    return ResponseEntity.status(HttpStatus.OK).body(productService.listProducts(pageable));
  }

  @GetMapping("{id}")
  public ResponseEntity<Product> getProductByString(@PathVariable String id) {
    return ResponseEntity.status(HttpStatus.OK).body(productService.retrieveProductByString(id));
  }

  @PostMapping
  public ResponseEntity<Product> postProduct(@RequestBody Product product) throws NoSuchFieldException {
    return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(product));
  }

  @PutMapping("{id}")
  public ResponseEntity<Product> putProduct(@PathVariable String id, @RequestBody Product updatedProduct) {
    return ResponseEntity.status(HttpStatus.OK).body(productService.updateProduct(id, updatedProduct));
  }

  @DeleteMapping("{id}")
  public ResponseEntity<String> deleteProductByString(@PathVariable String id) {
    productService.deleteProduct(id);
    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
