package com.nielExpendex.expensesTracker.dto;

import lombok.Data;

import java.util.Date;

@Data
public class TransactionRequest {
    private double amount;
    private String description;
    private Date date;
    private String type;
//    private Integer userId;
}
