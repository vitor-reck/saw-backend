package br.vitorreck.app.repositories;

import br.vitorreck.app.domain.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

  Optional<UserDetails> findByEmail(String email);
}
