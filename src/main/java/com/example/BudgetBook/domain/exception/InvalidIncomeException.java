package com.example.BudgetBook.domain.exception;

public class InvalidIncomeException extends ValidationException {

    public InvalidIncomeException(String message) {
        super(message);
    }

    public static InvalidIncomeException negativeAmount() {
        return new InvalidIncomeException("金額は0より大きい必要があります");
    }
}