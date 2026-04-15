package com.example.BudgetBook.application.dto.expense;

import com.example.BudgetBook.domain.model.expense.Expense;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record ExpenseResult(
        UUID id,
        UUID userId,
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

    public static ExpenseResult from(Expense expense) {
        return from(expense, null);
    }

    public static ExpenseResult from(Expense expense, String categoryName) {
        return new ExpenseResult(
                expense.getId().value(),
                expense.getUserId().value(),
                expense.getAmount().value(),
                expense.getExpenseDate(),
                expense.getCategoryId().value(),
                categoryName,
                expense.getDescription(),
                expense.getPaymentMethod().name(),
                expense.getMemo(),
                expense.getCreatedAt(),
                expense.getUpdatedAt()
        );
    }
}
