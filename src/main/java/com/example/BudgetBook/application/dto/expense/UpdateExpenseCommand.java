package com.example.BudgetBook.application.dto.expense;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record UpdateExpenseCommand(
        UUID expenseId,
        BigDecimal amount,
        LocalDate expenseDate,
        UUID categoryId,
        String description,
        String paymentMethod,
        String memo
) {}
