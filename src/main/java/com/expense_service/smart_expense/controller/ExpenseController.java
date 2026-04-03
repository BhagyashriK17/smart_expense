package com.expense_service.smart_expense.controller;

import com.expense_service.smart_expense.entity.Expense;
import com.expense_service.smart_expense.service.CurrencyService;
import com.expense_service.smart_expense.service.ExpenseService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    private final ExpenseService service;
    private final CurrencyService currencyService;

    public ExpenseController(ExpenseService service, CurrencyService currencyService) {
        this.service = service;
        this.currencyService = currencyService;
    }

    @PostMapping("/create")
    public Expense create(@RequestBody Expense expense) {
        return service.save(expense);
    }

    @GetMapping
    @Cacheable("expenses")
    public List<Expense> getAll() {
        return service.getAll();
    }

    @GetMapping("/convert/{amount}")
    public double convert(@PathVariable double amount) {
        return service.convertToUSD(amount);
    }

    @PostMapping("/refresh-cache")
    public String refreshCache() {
        currencyService.clearCache();
        return "Cache cleared";
    }

    @GetMapping("/monthly-total")
    public double getMonthlyTotal(@RequestParam int month,
                                  @RequestParam int year) {
        return service.getMonthlyTotal(month, year);
    }

}