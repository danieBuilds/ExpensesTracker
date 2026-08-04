package com.nielExpendex.expensesTracker.dto;

import com.nielExpendex.expensesTracker.model.Category;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Data
public class TransactionResponse {
    private BigDecimal amount;
    private String description;
    private LocalDate date;
    private String type;
    private String category;
}
