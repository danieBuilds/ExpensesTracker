package com.nielExpendex.expensesTracker.dto;

import com.nielExpendex.expensesTracker.model.Category;
import lombok.Data;

import java.util.Date;

@Data
public class TransactionResponse {
    private double amount;
    private String description;
    private Date date;
    private String type;
    private String category;
}
