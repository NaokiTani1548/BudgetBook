package com.example.BudgetBook.infrastructure.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.example.BudgetBook.infrastructure.persistence.mapper")
public class MyBatisConfig {
}