package com.example.BudgetBook.application.dto.category;

import com.example.BudgetBook.domain.model.category.Category;

import java.util.UUID;

public record CategoryResult (
        UUID id,
        UUID userId,
        String name,
        String type,
        String color,
        Integer sortOrder,
        boolean isDefault
){
    public static CategoryResult from(Category category) {
        return new CategoryResult(
                category.getId().value(),
                category.getUserId() != null ? category.getUserId().value() : null,
                category.getName(),
                category.getType().name(),
                category.getColor(),
                category.getSortOrder(),
                category.isDefault()
        );
    }
}
