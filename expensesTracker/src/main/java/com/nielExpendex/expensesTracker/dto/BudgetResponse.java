package com.nielExpendex.expensesTracker.dto;

import com.nielExpendex.expensesTracker.model.Category;
import com.nielExpendex.expensesTracker.model.Transactions;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Month;
import java.util.List;

@Data
public class BudgetResponse {
    private String category;
    private BigDecimal amount;
    private Month date;
    private String description;
    private List<TransactionResponse> transactionResponses;
    private BigDecimal spent;
}
