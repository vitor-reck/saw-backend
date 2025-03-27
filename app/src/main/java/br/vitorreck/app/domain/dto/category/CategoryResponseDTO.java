package br.vitorreck.app.domain.dto.category;

import lombok.Builder;

@Builder
public record CategoryResponseDTO(
    String name
) {}
