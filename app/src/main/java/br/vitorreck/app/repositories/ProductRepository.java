package br.vitorreck.app.repositories;

import br.vitorreck.app.domain.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

  Optional<Product> findByName(String name);
}
