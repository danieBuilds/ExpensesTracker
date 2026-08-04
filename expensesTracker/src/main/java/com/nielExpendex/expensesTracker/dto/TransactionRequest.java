package com.nielExpendex.expensesTracker.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;


@Data
public class TransactionRequest {
    private BigDecimal amount;
    private String description;
    private LocalDate date;
    private String type;
    private Integer categoryId;
//    private Integer userId;
}
