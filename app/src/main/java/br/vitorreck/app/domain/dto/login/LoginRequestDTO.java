package br.vitorreck.app.domain.dto.login;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record LoginRequestDTO(

    @NotNull
    @Pattern(regexp = "[^@ \t\r\n]+@[^@ \t\r\n]+\\.[^@ \t\r\n]+")
    String username,

    @NotNull
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,}$")
    String password
) {}
