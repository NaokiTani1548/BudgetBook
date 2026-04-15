package com.example.BudgetBook.presentation.response;

import com.example.BudgetBook.application.dto.expense.ExpenseResult;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record ExpenseResponse(
        UUID id,
        BigDecimal amount,
        LocalDate expenseDate,
        UUID categoryId,
        String categoryName,
        String description,
        String paymentMethod,
        String memo,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static ExpenseResponse from(ExpenseResult result) {
        return new ExpenseResponse(
                result.id(),
                result.amount(),
                result.expenseDate(),
                result.categoryId(),
                result.categoryName(),
                result.description(),
                result.paymentMethod(),
                result.memo(),
                result.createdAt(),
                result.updatedAt()
        );
    }
}