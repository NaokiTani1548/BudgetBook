package com.example.BudgetBook.application.usecase.expense;

import com.example.BudgetBook.application.dto.expense.CreateExpenseCommand;
import com.example.BudgetBook.application.dto.expense.ExpenseResult;
import com.example.BudgetBook.domain.exception.CategoryNotFoundException;
import com.example.BudgetBook.domain.model.expense.Expense;
import com.example.BudgetBook.domain.model.expense.PaymentMethod;
import com.example.BudgetBook.domain.model.category.CategoryId;
import com.example.BudgetBook.domain.model.shared.Money;
import com.example.BudgetBook.domain.model.shared.UserId;
import com.example.BudgetBook.domain.repository.ExpenseRepository;
import com.example.BudgetBook.domain.repository.CategoryRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateExpenseUseCase {

    private final ExpenseRepository expenseRepository;
    private final CategoryRepository categoryRepository;

    public CreateExpenseUseCase(
            ExpenseRepository expenseRepository,
            CategoryRepository categoryRepository
    ) {
        this.expenseRepository = expenseRepository;
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public ExpenseResult execute(CreateExpenseCommand command) {
        // 1. カテゴリの存在確認
        CategoryId categoryId = CategoryId.of(command.categoryId());
        String categoryName = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(command.categoryId()))
                .getName();

        // 2. ドメインオブジェクトを生成
        Expense expense = Expense.create(
                UserId.of(command.userId()),
                Money.of(command.amount()),
                categoryId,
                command.description(),
                command.expenseDate(),
                PaymentMethod.valueOf(command.paymentMethod()),
                command.memo()
                );

        // 3. 永続化
        expenseRepository.save(expense);

        // 4. 結果を返す
        return ExpenseResult.from(expense, categoryName);
    }

}