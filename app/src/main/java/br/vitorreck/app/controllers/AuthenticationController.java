package br.vitorreck.app.controllers;

import br.vitorreck.app.domain.dto.login.LoginRequestDTO;
import br.vitorreck.app.domain.dto.login.LoginResponseDTO;
import br.vitorreck.app.domain.model.User;
import br.vitorreck.app.utils.JwtUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Log4j2
public class AuthenticationController {

  private final AuthenticationManager authenticationManager;
  private final JwtUtils jwtUtils;

  @PostMapping
  public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
    log.info("POST /auth - retrieving user with username: {}", loginRequestDTO.username());
    var usernamePassword = new UsernamePasswordAuthenticationToken(loginRequestDTO.username(), loginRequestDTO.password());
    var auth = authenticationManager.authenticate(usernamePassword);

    var token = jwtUtils.generateToken((User) auth.getPrincipal());
    return ResponseEntity.status(HttpStatus.OK).body(new LoginResponseDTO(token));
  }
}
