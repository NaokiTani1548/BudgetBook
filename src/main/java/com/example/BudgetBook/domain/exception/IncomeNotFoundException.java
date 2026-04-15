package com.example.BudgetBook.domain.exception;

import java.util.UUID;

public class IncomeNotFoundException extends NotFoundException {

    public IncomeNotFoundException(UUID incomeId) {
        super("収入が見つかりません: " + incomeId);
    }
}