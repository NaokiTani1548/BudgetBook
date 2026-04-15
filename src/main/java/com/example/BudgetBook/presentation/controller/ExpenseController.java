package com.example.BudgetBook.presentation.controller;

import com.example.BudgetBook.application.usecase.expense.CreateExpenseUseCase;
import com.example.BudgetBook.application.usecase.expense.DeleteExpenseUseCase;
import com.example.BudgetBook.application.usecase.expense.GetExpenseUseCase;
import com.example.BudgetBook.application.usecase.expense.UpdateExpenseUseCase;
import com.example.BudgetBook.presentation.request.CreateExpenseRequest;
import com.example.BudgetBook.presentation.request.UpdateExpenseRequest;
import com.example.BudgetBook.presentation.response.ExpenseResponse;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final CreateExpenseUseCase createExpenseUseCase;
    private final GetExpenseUseCase getExpenseUseCase;
    private final UpdateExpenseUseCase updateExpenseUseCase;
    private final DeleteExpenseUseCase deleteExpenseUseCase;

    public ExpenseController(
            CreateExpenseUseCase createExpenseUseCase,
            GetExpenseUseCase getExpenseUseCase,
            UpdateExpenseUseCase updateExpenseUseCase,
            DeleteExpenseUseCase deleteExpenseUseCase
    ) {
        this.createExpenseUseCase = createExpenseUseCase;
        this.getExpenseUseCase = getExpenseUseCase;
        this.updateExpenseUseCase = updateExpenseUseCase;
        this.deleteExpenseUseCase = deleteExpenseUseCase;
    }

    /**
     * 支出を作成
     */
    @PostMapping
    public ResponseEntity<ExpenseResponse> create(
            @RequestBody @Valid CreateExpenseRequest request,
            @RequestHeader("X-User-Id") UUID userId  // 仮実装：本来はJWTから取得
    ) {
        var command = request.toCommand(userId);
        var result = createExpenseUseCase.execute(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(ExpenseResponse.from(result));
    }

    /**
     * 支出を1件取得
     */
    @GetMapping("/{id}")
    public ResponseEntity<ExpenseResponse> findById(@PathVariable UUID id) {
        var result = getExpenseUseCase.findById(id);
        return ResponseEntity.ok(ExpenseResponse.from(result));
    }

    /**
     * ユーザーの支出一覧を取得
     */
    @GetMapping
    public ResponseEntity<List<ExpenseResponse>> findByUserId(
            @RequestHeader("X-User-Id") UUID userId
    ) {
        var results = getExpenseUseCase.findByUserId(userId);
        var responses = results.stream()
                .map(ExpenseResponse::from)
                .toList();
        return ResponseEntity.ok(responses);
    }

    /**
     * 期間指定で支出一覧を取得
     */
    @GetMapping("/search")
    public ResponseEntity<List<ExpenseResponse>> findByDateRange(
            @RequestHeader("X-User-Id") UUID userId,
            @RequestParam LocalDate from,
            @RequestParam LocalDate to
    ) {
        var results = getExpenseUseCase.findByUserIdAndDateRange(userId, from, to);
        var responses = results.stream()
                .map(ExpenseResponse::from)
                .toList();
        return ResponseEntity.ok(responses);
    }

    /**
     * 支出を更新
     */
    @PutMapping("/{id}")
    public ResponseEntity<ExpenseResponse> update(
            @PathVariable UUID id,
            @RequestBody @Valid UpdateExpenseRequest request
    ) {
        var command = request.toCommand(id);
        var result = updateExpenseUseCase.execute(command);
        return ResponseEntity.ok(ExpenseResponse.from(result));
    }

    /**
     * 支出を削除
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteExpenseUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}