package com.example.BudgetBook.application.dto.expense;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record CreateExpenseCommand(
        UUID userId,
        BigDecimal amount,
        LocalDate expenseDate,
        UUID categoryId,
        String description,
        String paymentMethod,  // "CASH" or "CREDIT_CARD"
        String memo
) {
    // デフォルト値を適用するファクトリメソッド
    public static CreateExpenseCommand of(
            UUID userId,
            BigDecimal amount,
            LocalDate expenseDate,
            UUID categoryId,
            String description,
            String paymentMethod,
            String memo
    ) {
        return new CreateExpenseCommand(
                userId,
                amount,
                expenseDate,
                categoryId,
                description,
                paymentMethod != null ? paymentMethod : "CASH",  // デフォルト: 現金
                memo
        );
    }
}