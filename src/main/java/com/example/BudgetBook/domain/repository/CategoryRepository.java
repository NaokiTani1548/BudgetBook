package com.example.BudgetBook.domain.repository;

import com.example.BudgetBook.domain.model.category.Category;
import com.example.BudgetBook.domain.model.category.CategoryId;
import com.example.BudgetBook.domain.model.shared.UserId;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * カテゴリリポジトリ（インターフェース）
 *
 */
public interface CategoryRepository {

    void save(Category category);

    Optional<Category> findById(CategoryId id);

    List<Category> findByIds(Set<CategoryId> ids);

    List<Category> findByUserId(UserId userId);

    void delete(CategoryId id);

    boolean existsById(CategoryId id);
}
