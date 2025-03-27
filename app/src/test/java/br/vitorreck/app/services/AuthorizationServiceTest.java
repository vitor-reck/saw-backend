package br.vitorreck.app.services;

import br.vitorreck.app.domain.model.User;
import br.vitorreck.app.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthorizationServiceTest {

  @InjectMocks private AuthorizationService authorizationService;
  @Mock private UserRepository userRepository;

  private User mockUser;

  @BeforeEach
  void setUp() {
    mockUser = User.builder()
        .id("1")
        .name("John Doe")
        .email("john@gmail.com")
        .password("P@ssw0rd")
        .role("admin")
        .isActive(true)
        .createdAt(Instant.now())
        .updatedAt(Instant.now())
        .build();
  }

  @Test
  void testShouldReturnUser_whenRetrievedByUsername() {
    when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(mockUser));

    UserDetails user = authorizationService.loadUserByUsername("john@gmail.com");

    assertNotNull(user);
    assertEquals("john@gmail.com", user.getUsername());
  }
}
