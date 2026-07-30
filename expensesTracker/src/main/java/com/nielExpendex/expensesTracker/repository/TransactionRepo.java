package com.nielExpendex.expensesTracker.repository;

import com.nielExpendex.expensesTracker.model.Transactions;
import com.nielExpendex.expensesTracker.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepo extends JpaRepository<Transactions, Integer>, JpaSpecificationExecutor<Transactions> {

    List<Transactions> findByUser(Users currentUser);

    Optional<Transactions> findByIdAndUser(int id, Users currentUser);

    List<Transactions> findAllByUser(Users currentUser);
//    @Query("""
//        SELECT t
//        FROM Transactions t
//        WHERE LOWER(t.type) LIKE LOWER(CONCAT('%', :keyword, '%'))
//           OR LOWER(t.description) LIKE LOWER(CONCAT('%', :keyword, '%'))
//           OR LOWER(t.category.name) LIKE LOWER(CONCAT('%', :keyword, '%'))
//        """)
//    List<Transactions> findAllByUserAndKeyword(Users currentUser,String keyword);
//
//    List<Transactions> findAllByUserAndCategory(Users currentUser,String keyword);
//    @Query("""
//    SELECT t
//    FROM Transactions t
//    WHERE t.user = :currentUser
//    AND (:type IS NULL OR LOWER(t.type) = LOWER(:type))
//    AND (:category IS NULL OR t.category.id = :category)
//    AND (:date IS NULL OR t.date = :date)
//
//    ORDER BY t.date DESC
//    """)
//    List<Transactions> findAllByUserAndKeyword(Users currentUser,String type, int category, Date date, String keyword);
}
