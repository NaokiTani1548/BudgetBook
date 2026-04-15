package com.example.BudgetBook.domain.model.category;

import java.util.UUID;

public record CategoryId(UUID value) {

    public CategoryId {
        if (value == null) {
            throw new IllegalArgumentException("カテゴリIDはnullにできません");
        }
    }

    public static CategoryId of(UUID value) {
        return new CategoryId(value);
    }

    public static CategoryId of(String value) {
        return new CategoryId(UUID.fromString(value));
    }

    public static CategoryId generate() {
        return new CategoryId(UUID.randomUUID());
    }
}
