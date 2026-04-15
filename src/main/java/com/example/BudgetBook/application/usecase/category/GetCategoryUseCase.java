package com.example.BudgetBook.application.usecase.category;


import com.example.BudgetBook.application.dto.category.CategoryResult;
import com.example.BudgetBook.domain.model.shared.UserId;
import com.example.BudgetBook.domain.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class GetCategoryUseCase {

    private final CategoryRepository categoryRepository;

    public GetCategoryUseCase(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryResult> findByUserId(UUID userId){
        return categoryRepository.findByUserId(UserId.of(userId))
                .stream()
                .map(CategoryResult::from)
                .toList();
    }
}
