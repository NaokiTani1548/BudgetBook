package com.example.BudgetBook.infrastructure.persistence.mapper;

import com.example.BudgetBook.infrastructure.persistence.entity.ExpenseEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.UUID;

@Mapper
public interface ExpenseCustomMapper {

    @Select("SELECT * FROM expenses WHERE id = #{id}::uuid")
    ExpenseEntity selectById(@Param("id") UUID id);

    @Select("SELECT COUNT(*) > 0 FROM expenses WHERE id = #{id}::uuid")
    boolean existsById(@Param("id") UUID id);
}