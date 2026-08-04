package com.nielExpendex.expensesTracker.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.Month;


@Data
public class BudgetRequest {
    private Integer categoryId;
    private BigDecimal amount;
    private Month date;
    private String description;
}
