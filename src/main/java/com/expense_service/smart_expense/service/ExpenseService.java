package com.expense_service.smart_expense.service;

import com.expense_service.smart_expense.entity.Expense;
import com.expense_service.smart_expense.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {


    private final ExpenseRepository repo;
    private final CurrencyService currencyService;

    public ExpenseService(ExpenseRepository repo, CurrencyService currencyService) {
        this.repo = repo;
        this.currencyService = currencyService;
    }

    public Expense save(Expense expense) {
        return repo.save(expense);
    }

    public List<Expense> getAll() {
        return repo.findAll();
    }

    public double convertToUSD(double amount) {
        return amount * currencyService.getUsdRate();
    }

    public double getMonthlyTotal(int month, int year) {
        return repo.findAll().stream()
                .filter(e -> e.getDate().getMonthValue() == month &&
                        e.getDate().getYear() == year)
                .mapToDouble(Expense::getAmount)
                .sum();
    }
}
