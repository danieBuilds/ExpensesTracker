package com.nielExpendex.expensesTracker.repository;

import com.nielExpendex.expensesTracker.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<Users,Integer> {
    Optional<Users> findByEmail(String email);
    Users findByUsername(String username);
}
