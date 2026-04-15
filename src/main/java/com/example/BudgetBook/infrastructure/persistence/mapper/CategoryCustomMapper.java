package com.example.BudgetBook.infrastructure.persistence.mapper;

import com.example.BudgetBook.infrastructure.persistence.entity.CategoryEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.UUID;

@Mapper
public interface CategoryCustomMapper {

    @Select("SELECT * FROM categories WHERE id = #{id}::uuid")
    CategoryEntity selectById(@Param("id") UUID id);

    List<CategoryEntity> selectByIds(@Param("ids") List<UUID> ids);
}