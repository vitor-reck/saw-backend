package br.vitorreck.app.domain.dto.product;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.Instant;

@Builder
public record ProductResponseDTO(
    String id,
    String name,
    String description,
    BigDecimal price,
    String category,
    Integer stock,
    Instant createdAt,
    Instant updatedAt
) {}
