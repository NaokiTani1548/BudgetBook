package com.example.BudgetBook.domain.repository;

import com.example.BudgetBook.domain.model.income.Income;
import com.example.BudgetBook.domain.model.income.IncomeId;
import com.example.BudgetBook.domain.model.shared.UserId;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * 収入リポジトリ（インターフェース）
 */
public interface IncomeRepository {

    void save(Income income);

    Optional<Income> findById(IncomeId id);

    List<Income> findByUserId(UserId userId);

    List<Income> findByUserIdAndDateRange(UserId userId, LocalDate from, LocalDate to);

    void delete(IncomeId id);

    boolean existsById(IncomeId id);
}
