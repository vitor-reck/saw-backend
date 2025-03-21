package br.vitorreck.app.repositories;

import br.vitorreck.app.domain.model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {

  Optional<Category> findByName(String Name);
}
