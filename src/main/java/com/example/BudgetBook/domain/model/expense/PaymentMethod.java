package com.example.BudgetBook.domain.model.expense;

public enum PaymentMethod {
    CASH("現金"),
    CREDIT_CARD("クレジットカード");

    private final String displayName;

    PaymentMethod(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}