package com.example.BudgetBook.presentation.controller;

import com.example.BudgetBook.application.usecase.category.GetCategoryUseCase;
import com.example.BudgetBook.presentation.response.CategoryResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final GetCategoryUseCase getCategoryUseCase;

    public CategoryController(GetCategoryUseCase getCategoryUseCase) {
        this.getCategoryUseCase = getCategoryUseCase;
    }

    /**
     * カテゴリ一覧を取得
     */
    @GetMapping
    public ResponseEntity<List<CategoryResponse>> findAll(
            @RequestHeader("X-User-Id") UUID userId
    ) {
        var results = getCategoryUseCase.findByUserId(userId);
        var responses = results.stream()
                .map(CategoryResponse::from)
                .toList();
        return ResponseEntity.ok(responses);
    }
}