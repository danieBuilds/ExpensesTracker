package com.nielExpendex.expensesTracker.dto;

import com.nielExpendex.expensesTracker.model.Category;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Data
public class TransactionRequest {
    private BigDecimal amount;
    private String description;
    private LocalDate date;
    private String type;
    private Integer categoryId;
//    private Integer userId;
}
