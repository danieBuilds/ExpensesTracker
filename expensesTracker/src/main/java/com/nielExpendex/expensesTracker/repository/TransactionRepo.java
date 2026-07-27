package com.nielExpendex.expensesTracker.repository;

import com.nielExpendex.expensesTracker.model.Transactions;
import com.nielExpendex.expensesTracker.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepo extends JpaRepository<Transactions, Integer> {

    List<Transactions> findByUser(Users currentUser);

    Optional<Transactions> findByIdAndUser(int id, Users currentUser);

    List<Transactions> findAllByUser(Users currentUser);
}
