package com.example.BudgetBook.domain.model.income;

import java.util.UUID;

public record IncomeId(UUID value) {

    public IncomeId {
        if (value == null) {
            throw new IllegalArgumentException("収入IDはnullにできません");
        }
    }

    public static IncomeId of(String value) {
        return new IncomeId(UUID.fromString(value));
    }

    public static IncomeId generate() {
        return new IncomeId(UUID.randomUUID());
    }
}