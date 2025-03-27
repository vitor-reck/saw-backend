package br.vitorreck.app.utils;

import br.vitorreck.app.domain.model.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class JwtUtils {

  @Value("${jwt.secret.key}")
  private String secretKey;

  private static final String ISSUER = "br.vitorreck";

  public String generateToken(User user) {
    Algorithm algorithm = Algorithm.HMAC256(secretKey);
    return JWT.create()
        .withIssuer(ISSUER)
        .withSubject(user.getEmail())
        .withIssuedAt(Instant.now())
        .withExpiresAt(Instant.now().plusSeconds(1800)) // 30 minutes
        .sign(algorithm);
  }

  public String getEmailFromJWT(String token) {
    Algorithm algorithm = Algorithm.HMAC256(secretKey);
    return JWT.require(algorithm)
        .withIssuer(ISSUER)
        .build()
        .verify(token)
        .getSubject();
  }
}
