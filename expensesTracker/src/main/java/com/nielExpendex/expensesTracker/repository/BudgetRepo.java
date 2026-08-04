package com.nielExpendex.expensesTracker.repository;

import com.nielExpendex.expensesTracker.model.Budget;
import com.nielExpendex.expensesTracker.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BudgetRepo extends JpaRepository<Budget,Integer> {
    List<Budget> findAllByUser(Users user);

    Budget findByUserAndId(Users user, int id);
}
