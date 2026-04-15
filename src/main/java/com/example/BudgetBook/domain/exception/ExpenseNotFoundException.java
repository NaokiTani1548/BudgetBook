package com.example.BudgetBook.domain.exception;

import java.util.UUID;

public class ExpenseNotFoundException extends NotFoundException {

    public ExpenseNotFoundException(UUID expenseId) {
        super("支出が見つかりません: " + expenseId);
    }
}