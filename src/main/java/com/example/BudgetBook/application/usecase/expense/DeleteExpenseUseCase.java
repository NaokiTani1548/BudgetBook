package com.example.BudgetBook.application.usecase.expense;

import com.example.BudgetBook.domain.exception.ExpenseNotFoundException;
import com.example.BudgetBook.domain.model.expense.ExpenseId;
import com.example.BudgetBook.domain.repository.ExpenseRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteExpenseUseCase {

    private final ExpenseRepository expenseRepository;

    public DeleteExpenseUseCase (
           ExpenseRepository expenseRepository
    ) {
        this.expenseRepository = expenseRepository;
    }

    @Transactional
    public void execute (UUID expenseId) {
        ExpenseId id = ExpenseId.of(expenseId);

        if (!expenseRepository.existsById(id)) {
            throw new ExpenseNotFoundException(expenseId);
        }

        expenseRepository.delete(id);
    }
}
