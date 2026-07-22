package com.nielExpendex.expensesTracker.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column(unique = true, nullable = false)
    private String email;
    private String password;
}
