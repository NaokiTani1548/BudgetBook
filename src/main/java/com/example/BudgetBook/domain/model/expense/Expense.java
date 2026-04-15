package com.example.BudgetBook.domain.model.expense;

import com.example.BudgetBook.domain.model.category.CategoryId;
import com.example.BudgetBook.domain.model.shared.Money;
import com.example.BudgetBook.domain.model.shared.UserId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 支出エンティティ
 * - 不変性を重視（setterを持たない）
 * - 変更時は新しいインスタンスを返す
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Expense {

    private final ExpenseId id;
    private final UserId userId;
    private final Money amount;
    private final CategoryId categoryId;
    private final String description;
    private final LocalDate expenseDate;
    private final PaymentMethod paymentMethod;
    private final String memo;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    /**
     * 新規作成
     */
    public static Expense create(
            UserId userId,
            Money amount,
            CategoryId categoryId,
            String description,
            LocalDate expenseDate,
            PaymentMethod paymentMethod,
            String memo
    ) {
        LocalDateTime now = LocalDateTime.now();
        return new Expense(
                ExpenseId.generate(),
                userId,
                amount,
                categoryId,
                description,
                expenseDate,
                paymentMethod,
                memo,
                now,
                now
        );
    }

    /**
     * DBから復元
     */
    public static Expense reconstruct(
            ExpenseId id,
            UserId userId,
            Money amount,
            CategoryId categoryId,
            String description,
            LocalDate expenseDate,
            PaymentMethod paymentMethod,
            String memo,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        return new Expense(
                id, userId, amount, categoryId, description,
                expenseDate, paymentMethod, memo, createdAt, updatedAt
        );
    }

    /**
     * 更新（新しいインスタンスを返す）
     */
    public Expense update(
            Money amount,
            CategoryId categoryId,
            String description,
            LocalDate expenseDate,
            PaymentMethod paymentMethod,
            String memo
    ) {
        return new Expense(
                this.id,
                this.userId,
                amount,
                categoryId,
                description,
                expenseDate,
                paymentMethod,
                memo,
                this.createdAt,
                LocalDateTime.now()
        );
    }
}