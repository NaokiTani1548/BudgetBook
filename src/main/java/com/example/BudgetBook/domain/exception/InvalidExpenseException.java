package com.example.BudgetBook.domain.exception;

public class InvalidExpenseException extends ValidationException {

    public InvalidExpenseException(String message) {
        super(message);
    }

    public static InvalidExpenseException negativeAmount() {
        return new InvalidExpenseException("金額は0より大きい必要があります");
    }

    public static InvalidExpenseException futureDate() {
        return new InvalidExpenseException("未来の日付は指定できません");
    }
}