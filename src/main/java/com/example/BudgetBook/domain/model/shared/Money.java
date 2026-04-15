package com.example.BudgetBook.domain.model.shared;

import java.math.BigDecimal;
import java.math.RoundingMode;

public record Money(BigDecimal value) {

    // コンパクトコンストラクタでバリデーション
    public Money {
        if (value == null) {
            throw new IllegalArgumentException("金額はnullにできません");
        }
        if (value.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("金額は0以上である必要があります");
        }
        value = value.setScale(2, RoundingMode.HALF_UP);
    }

    // ファクトリメソッド
    public static Money of(BigDecimal amount) {
        return new Money(amount);
    }

    public static Money of(long amount) {
        return new Money(BigDecimal.valueOf(amount));
    }

    public static Money of(String amount) {
        return new Money(new BigDecimal(amount));
    }

    public static Money zero() {
        return new Money(BigDecimal.ZERO);
    }

    // ビジネスロジック
    public Money add(Money other) {
        return new Money(this.value.add(other.value));
    }

    public Money subtract(Money other) {
        return new Money(this.value.subtract(other.value));
    }

    public boolean isGreaterThan(Money other) {
        return this.value.compareTo(other.value) > 0;
    }
}
