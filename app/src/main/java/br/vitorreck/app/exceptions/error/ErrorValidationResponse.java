package br.vitorreck.app.exceptions.error;

import java.util.List;

public record ErrorValidationResponse(
    String title,
    Integer status,
    String detail,
    List<Error> errors
) {}
