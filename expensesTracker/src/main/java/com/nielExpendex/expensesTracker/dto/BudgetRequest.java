package com.nielExpendex.expensesTracker.dto;

import com.nielExpendex.expensesTracker.model.Category;
import com.nielExpendex.expensesTracker.model.Transactions;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Month;
import java.util.List;

@Data
public class BudgetRequest {
    private Integer categoryId;
    private BigDecimal amount;
    private Month date;
    private String description;
}
