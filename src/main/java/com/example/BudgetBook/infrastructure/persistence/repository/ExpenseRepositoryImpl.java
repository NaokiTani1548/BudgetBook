package com.example.BudgetBook.infrastructure.persistence.repository;

import com.example.BudgetBook.domain.model.expense.Expense;
import com.example.BudgetBook.domain.model.expense.ExpenseId;
import com.example.BudgetBook.domain.model.shared.UserId;
import com.example.BudgetBook.domain.repository.ExpenseRepository;
import com.example.BudgetBook.infrastructure.persistence.entity.ExpenseEntity;
import com.example.BudgetBook.infrastructure.persistence.entity.ExpenseEntityExample;
import com.example.BudgetBook.infrastructure.persistence.mapper.ExpenseCustomMapper;
import com.example.BudgetBook.infrastructure.persistence.mapper.ExpenseEntityMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ExpenseRepositoryImpl implements ExpenseRepository {
    private final ExpenseEntityMapper mapper;
    private final ExpenseCustomMapper customMapper;

    public ExpenseRepositoryImpl(ExpenseEntityMapper mapper, ExpenseCustomMapper customMapper) {
        this.mapper = mapper;
        this.customMapper = customMapper;
    }

    @Override
    public void save(Expense expense) {
        ExpenseEntity entity = Converter.toEntity(expense);

        if (!customMapper.existsById(expense.getId().value())) {
           mapper.insert(entity);
        } else {
            mapper.updateByPrimaryKey(entity);
        }
    }

    @Override
    public Optional<Expense> findById(ExpenseId id) {
        var entity = customMapper.selectById(id.value());
        return Optional.ofNullable(entity).map(Converter::toDomain);
    }

    @Override
    public List<Expense> findByUserId(UserId userId) {
        var example = new ExpenseEntityExample();
        example.createCriteria().andUserIdEqualTo(userId.value());
        example.setOrderByClause("expense_date DESC");

        return mapper.selectByExample(example)
                .stream()
                .map(Converter::toDomain)
                .toList();
    }

    @Override
    public List<Expense> findByUserIdAndDateRange(UserId userId, LocalDate from, LocalDate to) {
        var example = new ExpenseEntityExample();
        example.createCriteria()
                .andUserIdEqualTo(userId.value())
                .andExpenseDateGreaterThanOrEqualTo(from)
                .andExpenseDateLessThanOrEqualTo(to);
        example.setOrderByClause("expense_date DESC");

        return mapper.selectByExample(example)
                .stream()
                .map(Converter::toDomain)
                .toList();
    }

    @Override
    public void delete(ExpenseId id) {
        mapper.deleteByPrimaryKey(id.value());
    }

    @Override
    public boolean existsById(ExpenseId id) {
        return mapper.selectByPrimaryKey(id.value()) != null;
    }

}
