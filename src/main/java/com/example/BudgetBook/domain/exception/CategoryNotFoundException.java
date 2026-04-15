package com.example.BudgetBook.domain.exception;

import java.util.UUID;

public class CategoryNotFoundException extends NotFoundException {

    public CategoryNotFoundException(UUID categoryId) {
        super("カテゴリが見つかりません: " + categoryId);
    }
}