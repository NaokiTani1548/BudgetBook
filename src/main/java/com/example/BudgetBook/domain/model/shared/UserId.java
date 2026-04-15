package com.example.BudgetBook.domain.model.shared;

import java.util.UUID;

public record UserId(UUID value) {

    public UserId {
        if (value == null) {
            throw new IllegalArgumentException("ユーザーIDはnullにできません");
        }
    }

    public static UserId of(UUID value) {  // 追加
        return new UserId(value);
    }

    public static UserId of(String value) {
        return new UserId(UUID.fromString(value));
    }

    public static UserId generate() {
        return new UserId(UUID.randomUUID());
    }
}
