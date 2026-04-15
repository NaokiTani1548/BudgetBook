package com.example.BudgetBook.domain.exception;

public abstract class NotFoundException extends BudgetBookException {

    public NotFoundException(String message) {
        super(message);
    }
}
