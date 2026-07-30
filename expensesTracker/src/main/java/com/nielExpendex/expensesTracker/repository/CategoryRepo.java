package com.nielExpendex.expensesTracker.repository;

import com.nielExpendex.expensesTracker.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Integer> {
}
