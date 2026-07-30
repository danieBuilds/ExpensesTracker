package com.nielExpendex.expensesTracker.dto;

import com.nielExpendex.expensesTracker.model.Category;
import lombok.Data;

import java.util.Date;

@Data
public class TransactionRequest {
    private double amount;
    private String description;
    private Date date;
    private String type;
    private Integer categoryId;
//    private Integer userId;
}
