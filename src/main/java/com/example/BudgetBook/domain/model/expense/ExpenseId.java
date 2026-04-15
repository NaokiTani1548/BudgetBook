package com.example.BudgetBook.domain.model.expense;

import java.util.UUID;

public record ExpenseId(UUID value) {

    public ExpenseId {
        if (value == null) {
            throw new IllegalArgumentException("支出IDはnullにできません");
        }
    }

    public static ExpenseId of(String value) {
        return new ExpenseId(UUID.fromString(value));
    }

    public static ExpenseId of(UUID value) {
        return new ExpenseId(value);
    }

    public static ExpenseId generate() {
        return new ExpenseId(UUID.randomUUID());
    }
}