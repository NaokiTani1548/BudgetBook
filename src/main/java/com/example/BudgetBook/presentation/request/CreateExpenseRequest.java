package com.example.BudgetBook.presentation.request;

import com.example.BudgetBook.application.dto.expense.CreateExpenseCommand;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record CreateExpenseRequest(
        @NotNull(message = "金額は必須です")
        @Positive(message = "金額は0より大きい必要があります")
        BigDecimal amount,

        @NotNull(message = "日付は必須です")
        LocalDate expenseDate,

        @NotNull(message = "カテゴリは必須です")
        UUID categoryId,

        String description,

        String paymentMethod,  // null の場合はデフォルトで CASH

        String memo
) {
    public CreateExpenseCommand toCommand(UUID userId) {
        return CreateExpenseCommand.of(
                userId,
                amount,
                expenseDate,
                categoryId,
                description,
                paymentMethod,
                memo
        );
    }
}