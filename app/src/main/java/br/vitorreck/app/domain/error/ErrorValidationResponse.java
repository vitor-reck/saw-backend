package br.vitorreck.app.domain.error;

import java.util.List;

public record ErrorValidationResponse(
    String Title,
    Integer status,
    String detail,
    List<Error> errors
) {}
