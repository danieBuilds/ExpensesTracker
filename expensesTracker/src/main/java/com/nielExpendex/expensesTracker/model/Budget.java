package com.nielExpendex.expensesTracker.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Month;
import java.util.List;

@Entity
@Data
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @ManyToOne
    private Category category;
    private BigDecimal amount;
    private Month date;
    private String description;
    @ManyToOne
    private Users user;
    @ManyToMany
    private List<Transactions> transactions;
}
