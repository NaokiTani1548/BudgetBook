package com.example.BudgetBook.domain.model.category;

import com.example.BudgetBook.domain.model.shared.UserId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * カテゴリエンティティ
 * - 不変オブジェクト
 * - 更新時は新しいインスタンスを返す
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Category {

    private final CategoryId id;
    private final UserId userId;
    private final String name;
    private final CategoryType type;
    private final String color;
    private final int sortOrder;
    private final boolean isDefault;
    private final LocalDateTime createdAt;

    /**
     * 新規作成
     */
    public static Category create(
            UserId userId,
            String name,
            CategoryType type,
            String color,
            int sortOrder,
            boolean isDefault
    ) {
        validate(name, type);

        return new Category(
                CategoryId.generate(),
                userId,
                name,
                type,
                color,
                sortOrder,
                isDefault,
                LocalDateTime.now()
        );
    }

    /**
     * DBから復元
     */
    public static Category reconstruct(
            CategoryId id,
            UserId userId,
            String name,
            CategoryType type,
            String color,
            int sortOrder,
            boolean isDefault,
            LocalDateTime createdAt
    ) {
        return new Category(
                id,
                userId,
                name,
                type,
                color,
                sortOrder,
                isDefault,
                createdAt
        );
    }

    /**
     * 更新
     */
    public Category update(
            String name,
            String color,
            int sortOrder
    ) {
        validate(name, this.type);

        return new Category(
                this.id,
                this.userId,
                name,
                this.type,
                color,
                sortOrder,
                this.isDefault,
                this.createdAt
        );
    }

    /**
     * バリデーション
     */
    private static void validate(String name, CategoryType type) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("カテゴリ名は必須です");
        }
        if (type == null) {
            throw new IllegalArgumentException("カテゴリ種別は必須です");
        }
    }
}