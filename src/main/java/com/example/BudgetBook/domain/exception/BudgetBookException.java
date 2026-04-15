package com.example.BudgetBook.domain.exception;

public abstract class BudgetBookException extends RuntimeException {

    public BudgetBookException(String message) {
        super(message);
    }

    public BudgetBookException(String message, Throwable cause) {
        super(message, cause);
    }
}
