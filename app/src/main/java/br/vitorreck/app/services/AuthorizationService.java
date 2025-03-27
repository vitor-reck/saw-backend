package br.vitorreck.app.services;

import br.vitorreck.app.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AuthorizationService implements UserDetailsService {

  private static final String USER_NOT_FOUND = "User doesn't exit";
  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) {
    return userRepository.findByEmail(username)
        .orElseThrow(() -> new NoSuchElementException(USER_NOT_FOUND));
  }
}
