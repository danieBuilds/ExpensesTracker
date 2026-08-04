package com.nielExpendex.expensesTracker.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class AnalyticsResponse {
    private BigDecimal total_income;
    private BigDecimal total_expenses;
    private BigDecimal balance;
    private List<TransactionResponse> incomeTransactionResponseList;
    private List<TransactionResponse> expenseTransactionResponseList;
}
