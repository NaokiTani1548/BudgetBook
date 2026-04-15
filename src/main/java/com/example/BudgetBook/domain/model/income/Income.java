package com.example.BudgetBook.domain.model.income;

import com.example.BudgetBook.domain.model.category.CategoryId;
import com.example.BudgetBook.domain.model.shared.Money;
import com.example.BudgetBook.domain.model.shared.UserId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 収入エンティティ
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Income {

    private final IncomeId id;
    private final UserId userId;
    private final Money amount;
    private final CategoryId categoryId;
    private final String description;
    private final LocalDate incomeDate;
    private final String memo;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    /**
     * 新規作成
     */
    public static Income create(
            UserId userId,
            Money amount,
            CategoryId categoryId,
            String description,
            LocalDate incomeDate,
            String memo
    ) {
        LocalDateTime now = LocalDateTime.now();
        return new Income(
                IncomeId.generate(),
                userId,
                amount,
                categoryId,
                description,
                incomeDate,
                memo,
                now,
                now
        );
    }

    /**
     * DBから復元
     */
    public static Income reconstruct(
            IncomeId id,
            UserId userId,
            Money amount,
            CategoryId categoryId,
            String description,
            LocalDate incomeDate,
            String memo,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        return new Income(
                id, userId, amount, categoryId, description,
                incomeDate, memo, createdAt, updatedAt
        );
    }

    /**
     * 更新
     */
    public Income update(
            Money amount,
            CategoryId categoryId,
            String description,
            LocalDate incomeDate,
            String memo
    ) {
        return new Income(
                this.id,
                this.userId,
                amount,
                categoryId,
                description,
                incomeDate,
                memo,
                this.createdAt,
                LocalDateTime.now()
        );
    }
}