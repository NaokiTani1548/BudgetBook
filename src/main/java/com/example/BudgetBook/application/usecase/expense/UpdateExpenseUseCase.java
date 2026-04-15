package com.example.BudgetBook.application.usecase.expense;

import com.example.BudgetBook.application.dto.expense.ExpenseResult;
import com.example.BudgetBook.application.dto.expense.UpdateExpenseCommand;
import com.example.BudgetBook.domain.exception.CategoryNotFoundException;
import com.example.BudgetBook.domain.exception.ExpenseNotFoundException;
import com.example.BudgetBook.domain.model.category.Category;
import com.example.BudgetBook.domain.model.category.CategoryId;
import com.example.BudgetBook.domain.model.expense.Expense;
import com.example.BudgetBook.domain.model.expense.ExpenseId;
import com.example.BudgetBook.domain.model.expense.PaymentMethod;
import com.example.BudgetBook.domain.model.shared.Money;
import com.example.BudgetBook.domain.repository.CategoryRepository;
import com.example.BudgetBook.domain.repository.ExpenseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UpdateExpenseUseCase {
    private final ExpenseRepository expenseRepository;
    private final CategoryRepository categoryRepository;

    public UpdateExpenseUseCase(
            ExpenseRepository expenseRepository,
            CategoryRepository categoryRepository
    ) {
        this.expenseRepository = expenseRepository;
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public ExpenseResult execute(UpdateExpenseCommand command) {
        // 1. 既存の支出を取得
        Expense expense = expenseRepository.findById(ExpenseId.of(command.expenseId()))
                .orElseThrow(() -> new ExpenseNotFoundException(command.expenseId()));

        // 2. カテゴリの存在確認
        CategoryId categoryId = CategoryId.of(command.categoryId());
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(command.categoryId()));

        // 3. エンティティを更新（新しいインスタンスを生成）
        Expense updatedExpense = expense.update(
                Money.of(command.amount()),
                CategoryId.of(command.categoryId()),
                command.description(),
                command.expenseDate(),
                PaymentMethod.valueOf(command.paymentMethod()),
                command.memo()
        );

        // 4. 永続化
        expenseRepository.save(updatedExpense);

        // 5. 結果を返す
        return ExpenseResult.from(updatedExpense, category.getName());
    }
}
