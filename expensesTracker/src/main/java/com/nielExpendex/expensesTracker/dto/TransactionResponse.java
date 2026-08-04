package com.nielExpendex.expensesTracker.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class TransactionResponse {
    private BigDecimal amount;
    private String description;
    private LocalDate date;
    private String type;
    private String category;
}
