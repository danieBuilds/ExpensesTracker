package com.nielExpendex.expensesTracker.repository;

import com.nielExpendex.expensesTracker.model.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepo extends JpaRepository<Transactions, Integer> {

}
