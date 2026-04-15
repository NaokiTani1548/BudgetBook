package com.example.BudgetBook.infrastructure.persistence.repository;

import com.example.BudgetBook.domain.model.category.Category;
import com.example.BudgetBook.domain.model.category.CategoryId;
import com.example.BudgetBook.domain.model.category.CategoryType;
import com.example.BudgetBook.domain.model.expense.Expense;
import com.example.BudgetBook.domain.model.expense.ExpenseId;
import com.example.BudgetBook.domain.model.expense.PaymentMethod;
import com.example.BudgetBook.domain.model.shared.Money;
import com.example.BudgetBook.domain.model.shared.UserId;
import com.example.BudgetBook.infrastructure.persistence.entity.CategoryEntity;
import com.example.BudgetBook.infrastructure.persistence.entity.ExpenseEntity;

import java.util.UUID;

public class Converter {

    private Converter() {
        // インスタンス化禁止
    }

    /**
     * ドメインモデル → DBエンティティ
     */
    public static ExpenseEntity toEntity(Expense expense) {
        ExpenseEntity entity = new ExpenseEntity();
        entity.setId(expense.getId().value());
        entity.setUserId(expense.getUserId().value());
        entity.setAmount(expense.getAmount().value());
        entity.setExpenseDate(expense.getExpenseDate());
        entity.setCategoryId(expense.getCategoryId().value());
        entity.setDescription(expense.getDescription());
        entity.setPaymentMethod(expense.getPaymentMethod().name());
        entity.setMemo(expense.getMemo());
        entity.setCreatedAt(expense.getCreatedAt());
        entity.setUpdatedAt(expense.getUpdatedAt());
        return entity;
    }

    /**
     * DBエンティティ → ドメインモデル
     */
    public static Expense toDomain(ExpenseEntity entity) {
        return Expense.reconstruct(
                ExpenseId.of(toUUID(entity.getId())),
                UserId.of(toUUID(entity.getUserId())),
                Money.of(entity.getAmount()),
                CategoryId.of(toUUID(entity.getCategoryId())),
                entity.getDescription(),
                entity.getExpenseDate(),
                PaymentMethod.valueOf(entity.getPaymentMethod()),
                entity.getMemo(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    public static CategoryEntity toEntity(Category category) {
        CategoryEntity entity = new CategoryEntity();
        entity.setId(category.getId().value());
        entity.setUserId(category.getUserId() != null ? category.getUserId().value() : null);
        entity.setName(category.getName());
        entity.setType(category.getType().name());
        entity.setColor(category.getColor());
        entity.setSortOrder(category.getSortOrder());
        entity.setIsDefault(category.isDefault());
        entity.setCreatedAt(category.getCreatedAt());
        return entity;
    }

    public static Category toDomain(CategoryEntity entity) {
        return Category.reconstruct(
                CategoryId.of(toUUID(entity.getId())),
                entity.getUserId() != null ? UserId.of(toUUID(entity.getUserId())) : null,
                entity.getName(),
                CategoryType.valueOf(entity.getType()),
                entity.getColor(),
                entity.getSortOrder(),
                entity.getIsDefault(),
                entity.getCreatedAt()
        );
    }

    /**
     * Object → UUID の変換
     */
    private static UUID toUUID(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof UUID) {
            return (UUID) value;
        }
        return UUID.fromString(value.toString());
    }
}