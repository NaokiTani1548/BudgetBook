package com.example.BudgetBook.infrastructure.persistence.repository;

import com.example.BudgetBook.domain.model.category.Category;
import com.example.BudgetBook.domain.model.category.CategoryId;
import com.example.BudgetBook.domain.model.shared.UserId;
import com.example.BudgetBook.domain.repository.CategoryRepository;
import com.example.BudgetBook.infrastructure.persistence.mapper.CategoryCustomMapper;
import com.example.BudgetBook.infrastructure.persistence.repository.Converter;
import com.example.BudgetBook.infrastructure.persistence.entity.CategoryEntityExample;
import com.example.BudgetBook.infrastructure.persistence.mapper.CategoryEntityMapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {

    private final CategoryEntityMapper mapper;
    private final CategoryCustomMapper customMapper;

    public CategoryRepositoryImpl(
            CategoryEntityMapper mapper,
            CategoryCustomMapper customMapper
    ) {
        this.mapper = mapper;
        this.customMapper = customMapper;
    }

    @Override
    public void save(Category category) {
        var entity = Converter.toEntity(category);

        if (customMapper.selectById(category.getId().value()) == null) {
            mapper.insert(entity);
        } else {
            mapper.updateByPrimaryKey(entity);
        }
    }

    @Override
    public Optional<Category> findById(CategoryId id) {
        var entity = customMapper.selectById(id.value());
        return Optional.ofNullable(entity).map(Converter::toDomain);
    }

    @Override
    public List<Category> findByIds(Set<CategoryId> ids) {
        if (ids == null || ids.isEmpty()) {
            return List.of();
        }

        List<UUID> uuids = ids.stream()
                .map(CategoryId::value)
                .toList();

        return customMapper.selectByIds(uuids)
                .stream()
                .map(Converter::toDomain)
                .toList();
    }

    @Override
    public List<Category> findByUserId(UserId userId) {
        var example = new CategoryEntityExample();

        // ユーザー固有のカテゴリ + デフォルトカテゴリ（user_id = null）
        example.or().andUserIdEqualTo(userId.value());
        example.or().andUserIdIsNull();
        example.setOrderByClause("sort_order ASC");

        return mapper.selectByExample(example)
                .stream()
                .map(Converter::toDomain)
                .toList();
    }

    @Override
    public void delete(CategoryId id) {
        mapper.deleteByPrimaryKey(id.value());
    }

    @Override
    public boolean existsById(CategoryId id) {
        return mapper.selectByPrimaryKey(id.value()) != null;
    }
}