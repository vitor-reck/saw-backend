package br.vitorreck.app.exceptions.error;

public record Error(
    String field,
    String message
) {}
