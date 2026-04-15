package com.example.BudgetBook.domain.exception;

import java.util.UUID;

public class UserNotFoundException extends NotFoundException {

    public UserNotFoundException(UUID userId) {
        super("ユーザーが見つかりません: " + userId);
    }
}