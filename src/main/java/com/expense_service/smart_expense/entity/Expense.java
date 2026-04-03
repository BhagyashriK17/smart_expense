package com.expense_service.smart_expense.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;


@Document(collection = "expenses")
@Data
public class Expense {

    @Id
    private String id;

    private String description;
    private double amount;
    private String category;
    //@JsonFormat(pattern = "M-d-yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate date;
}
