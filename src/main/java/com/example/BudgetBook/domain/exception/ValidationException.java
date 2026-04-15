package com.example.BudgetBook.domain.exception;

public abstract class ValidationException extends BudgetBookException {

    public ValidationException(String message) {
        super(message);
    }
}