package com.expense_service.smart_expense.repository;

import com.expense_service.smart_expense.entity.Expense;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends MongoRepository<Expense,String> {

    List<Expense> findByCategory(String category);
}
