package com.example.BudgetBook.domain.repository;

import com.example.BudgetBook.domain.model.expense.Expense;
import com.example.BudgetBook.domain.model.expense.ExpenseId;
import com.example.BudgetBook.domain.model.shared.UserId;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * 支出リポジトリ（インターフェース）
 *
 */
public interface ExpenseRepository {

    void save(Expense expense);

    Optional<Expense> findById(ExpenseId id);

    List<Expense> findByUserId(UserId userId);

    List<Expense> findByUserIdAndDateRange(UserId userId, LocalDate from, LocalDate to);

    void delete(ExpenseId id);

    boolean existsById(ExpenseId id);
}
