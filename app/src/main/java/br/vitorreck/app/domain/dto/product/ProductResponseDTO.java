package br.vitorreck.app.domain.dto.product;

import java.math.BigDecimal;
import java.time.Instant;

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
