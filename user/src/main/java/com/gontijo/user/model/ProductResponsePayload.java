package com.gontijo.user.model;

import java.math.BigDecimal;

public record ProductResponsePayload(
    String name,
    String description,
    BigDecimal price
) {
}
