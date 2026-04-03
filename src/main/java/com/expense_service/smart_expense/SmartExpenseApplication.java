package com.expense_service.smart_expense;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication(scanBasePackages = "com.expense_service.smart_expense")
@EnableCaching
public class SmartExpenseApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartExpenseApplication.class, args);
	}

}
