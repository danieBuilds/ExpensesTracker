package com.nielExpendex.expensesTracker.specification;

import com.nielExpendex.expensesTracker.model.Transactions;
import com.nielExpendex.expensesTracker.model.Users;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class TransactionSpecification {
    public static Specification<Transactions> hasUser(Users user) {

        return (root, query, cb) ->
                cb.equal(root.get("user"), user);

    }
    public static Specification<Transactions> hasType(String type) {

        return (root, query, cb) ->
                cb.equal(root.get("type"), type);

    }
    public static Specification<Transactions> hasDate(LocalDate date) {

        return (root, query, cb) ->
                cb.equal(root.get("date"), date);

    }
    public static Specification<Transactions> hasCategory(Integer id) {

        return (root, query, cb) ->
                cb.equal(root.get("category").get("id"), id);

    }
    public static Specification<Transactions> hasKeyword(String keyword) {

        return (root, query, cb) -> {

            String pattern = "%" + keyword.toLowerCase() + "%";

            return cb.or(

                    cb.like(cb.lower(root.get("description")), pattern),

                    cb.like(cb.lower(root.get("type")), pattern),

                    cb.like(cb.lower(root.get("category").get("name")), pattern)

            );

        };

    }
}
