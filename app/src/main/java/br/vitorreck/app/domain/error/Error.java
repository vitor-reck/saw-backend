package br.vitorreck.app.domain.error;

public record Error(
    String field,
    String message
) {}
