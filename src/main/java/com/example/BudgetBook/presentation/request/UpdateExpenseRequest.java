package com.example.BudgetBook.presentation.request;

import com.example.BudgetBook.application.dto.expense.UpdateExpenseCommand;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record UpdateExpenseRequest(
        @NotNull(message = "金額は必須です")
        @Positive(message = "金額は0より大きい必要があります")
        BigDecimal amount,

        @NotNull(message = "日付は必須です")
        LocalDate expenseDate,

        @NotNull(message = "カテゴリは必須です")
        UUID categoryId,

        String description,

        @NotNull(message = "支払い方法は必須です")
        String paymentMethod,

        String memo
) {
    public UpdateExpenseCommand toCommand(UUID expenseId) {
        return new UpdateExpenseCommand(
                expenseId,
                amount,
                expenseDate,
                categoryId,
                description,
                paymentMethod,
                memo
        );
    }
}