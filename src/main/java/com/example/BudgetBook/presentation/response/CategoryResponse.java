package com.example.BudgetBook.presentation.response;

import com.example.BudgetBook.application.dto.category.CategoryResult;

import java.util.UUID;

public record CategoryResponse(
        UUID id,
        String name,
        String type,
        String color,
        Integer sortOrder,
        boolean isDefault
) {
    public static CategoryResponse from(CategoryResult result) {
        return new CategoryResponse(
                result.id(),
                result.name(),
                result.type(),
                result.color(),
                result.sortOrder(),
                result.isDefault()
        );
    }
}