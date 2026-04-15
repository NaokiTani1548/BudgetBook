package com.example.BudgetBook.application.usecase.expense;

import com.example.BudgetBook.application.dto.expense.ExpenseResult;
import com.example.BudgetBook.domain.exception.CategoryNotFoundException;
import com.example.BudgetBook.domain.exception.ExpenseNotFoundException;
import com.example.BudgetBook.domain.model.category.Category;
import com.example.BudgetBook.domain.model.category.CategoryId;
import com.example.BudgetBook.domain.model.expense.Expense;
import com.example.BudgetBook.domain.model.expense.ExpenseId;
import com.example.BudgetBook.domain.model.shared.UserId;
import com.example.BudgetBook.domain.repository.CategoryRepository;
import com.example.BudgetBook.domain.repository.ExpenseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class GetExpenseUseCase {
    private final ExpenseRepository expenseRepository;
    private final CategoryRepository categoryRepository;

    public GetExpenseUseCase(ExpenseRepository expenseRepository, CategoryRepository categoryRepository) {
        this.expenseRepository = expenseRepository;
        this.categoryRepository = categoryRepository;
    }

    public ExpenseResult findById (UUID expenseId) {
        var expense = expenseRepository.findById(ExpenseId.of(expenseId)).orElseThrow(() -> new ExpenseNotFoundException(expenseId));
        String categoryName = categoryRepository.findById(expense.getCategoryId()).map(Category::getName).orElseThrow(() -> new CategoryNotFoundException(expense.getCategoryId().value()));

        return ExpenseResult.from(expense, categoryName);
    }

    public List<ExpenseResult> findByUserId (UUID userId) {
        var expense = expenseRepository.findByUserId(UserId.of(userId));
        return toResultsWithCategoryNames(expense);
    }

    public List<ExpenseResult> findByUserIdAndDateRange (UUID userId, LocalDate from, LocalDate to) {
        var expenses = expenseRepository.findByUserIdAndDateRange(
                UserId.of(userId), from, to
        );

        return toResultsWithCategoryNames(expenses);
    }

    private List<ExpenseResult> toResultsWithCategoryNames(List<Expense> expenses) {
        if (expenses.isEmpty()) {
            return List.of();
        }

        // 1. カテゴリIDを集める
        Set<CategoryId> categoryIds = expenses.stream()
                .map(Expense::getCategoryId)
                .collect(Collectors.toSet());

        // 2. カテゴリを一括取得してMapに変換
        Map<CategoryId, String> categoryNameMap = categoryRepository.findByIds(categoryIds)
                .stream()
                .collect(Collectors.toMap(
                        Category::getId,
                        Category::getName
                ));

        // 3. 支出にカテゴリ名を紐付けて返す
        return expenses.stream()
                .map(expense -> ExpenseResult.from(
                        expense,
                        categoryNameMap.get(expense.getCategoryId())
                ))
                .toList();
    }
}
