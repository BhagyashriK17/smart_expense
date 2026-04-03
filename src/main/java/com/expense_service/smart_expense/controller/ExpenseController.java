package com.expense_service.smart_expense.controller;

import com.expense_service.smart_expense.entity.Expense;
import com.expense_service.smart_expense.service.CurrencyService;
import com.expense_service.smart_expense.service.ExpenseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expenses")
@Tag(name = "Expense APIs", description = "Operations related to expense management")
public class ExpenseController {

    private final ExpenseService service;
    private final CurrencyService currencyService;

    public ExpenseController(ExpenseService service, CurrencyService currencyService) {
        this.service = service;
        this.currencyService = currencyService;
    }

    @Operation(summary = "Get all expenses")
    @PostMapping("/create")
    public Expense create(@RequestBody Expense expense) {
        return service.save(expense);
    }


    @Operation(summary = "Get all expenses")
    @GetMapping
    @Cacheable("expenses")
    public List<Expense> getAll() {
        return service.getAll();
    }


    @Operation(summary = "Convert INR to USD using cached exchange rate")
    @GetMapping("/convert/{amount}")
    public double convert(@PathVariable double amount) {
        return service.convertToUSD(amount);
    }


    @Operation(summary = "Refresh the Cache")
    @PostMapping("/refresh-cache")
    public String refreshCache() {
        currencyService.clearCache();
        return "Cache cleared";
    }


    @Operation(summary = "Get the monthly total of expense")
    @GetMapping("/monthly-total")
    public double getMonthlyTotal(@RequestParam int month,
                                  @RequestParam int year) {
        return service.getMonthlyTotal(month, year);
    }

}