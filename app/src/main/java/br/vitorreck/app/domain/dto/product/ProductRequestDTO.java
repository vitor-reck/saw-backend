package br.vitorreck.app.domain.dto.product;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ProductRequestDTO(

    @NotNull
    @Size(min = 3, max = 100, message = "Name should be 3 characters minimum and 100 characters maximum")
    String name,

    @NotNull
    @Size(min = 10, max = 500, message = "Description should be 10 characters minimum and 500 characters maximum")
    String description,

    @NotNull
    @DecimalMin(value = "0.01", message = "Price should be minimum value of 0.01")
    @DecimalMax(value = "999999.99", message = "Price should be maximum value of 999999.99")
    BigDecimal price,

    @NotNull
    @Size(min = 3, max = 50, message = "Category should be 3 characters minimum and 50 characters maximum")
    String category,

    @NotNull
    @Min(value = 0, message = "Stock should be minimum value of 0")
    @Max(value = 10000, message = "Stock should be minimum value of 0")
    Integer stock
) {}
