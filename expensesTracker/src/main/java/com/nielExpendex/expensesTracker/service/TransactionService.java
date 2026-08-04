package com.nielExpendex.expensesTracker.service;

import com.nielExpendex.expensesTracker.dto.TransactionRequest;
import com.nielExpendex.expensesTracker.dto.TransactionResponse;
import com.nielExpendex.expensesTracker.model.Category;
import com.nielExpendex.expensesTracker.model.TransactionType;
import com.nielExpendex.expensesTracker.model.Transactions;
import com.nielExpendex.expensesTracker.model.Users;
import com.nielExpendex.expensesTracker.repository.CategoryRepo;
import com.nielExpendex.expensesTracker.repository.TransactionRepo;
import com.nielExpendex.expensesTracker.repository.UserRepo;
import com.nielExpendex.expensesTracker.specification.TransactionSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionService {
    private final TransactionRepo transactionRepo;
    private final UserRepo userRepo;
    private final CategoryRepo categoryRepo;

    public Users getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        assert authentication != null;

        return userRepo.findByUsername(authentication.getName());
    }

    public String addTransaction(TransactionRequest transactions) {

        Users user = getCurrentUser();

        log.info("Here with user: {}", user);
        Category c = categoryRepo.findById(transactions.getCategoryId()).get();
            Transactions transaction = new Transactions();
            transaction.setUser(user);
            transaction.setDate(transactions.getDate());
            transaction.setAmount(transactions.getAmount());
            transaction.setType(TransactionType.valueOf(transactions.getType()));
            transaction.setDescription(transactions.getDescription());
            transaction.setCategory(c);

            transactionRepo.save(transaction);
        System.out.println("saved");
        log.info("returning!!!!!!!!!!!!!");
            return "Transaction added successfully";
    }

    public List<TransactionResponse> getTransactions() {
        List<TransactionResponse> response = new ArrayList<>();
        List<Transactions> transactions = transactionRepo.findAllByUser(getCurrentUser());

        for (Transactions t : transactions){
            TransactionResponse tr = new TransactionResponse();
            tr.setAmount(t.getAmount());
            tr.setDate(t.getDate());
            tr.setType(String.valueOf(t.getType()));
            tr.setDescription(t.getDescription());
            tr.setCategory(t.getCategory().getName());
            response.add(tr);
        }
        return response;
    }

    public TransactionResponse getTransaction(int id) {
        Optional<Transactions> transaction = transactionRepo.findByIdAndUser(id,getCurrentUser());
        TransactionResponse tr = new TransactionResponse();

        if (transaction.isPresent()){
            tr.setDescription(transaction.get().getDescription());
            tr.setDate(transaction.get().getDate());
            tr.setAmount(transaction.get().getAmount());
            tr.setType(String.valueOf(transaction.get().getType()));
            return tr;
        }
        return tr;
    }

    public String updateTransaction(int id, TransactionRequest tr) {
        Optional<Transactions> transaction = transactionRepo.findById(id);


        if (transaction.isEmpty()){
            return "transaction not found ";
        }
        if(transaction.get().getUser().getId() == getCurrentUser().getId()){
            transaction.get().setDescription(tr.getDescription());
            transaction.get().setDate(tr.getDate());
            transaction.get().setAmount(tr.getAmount());
            transaction.get().setType(TransactionType.valueOf(tr.getType()));
            Transactions t1 = transaction.get();
            transactionRepo.save(t1);
            return "update successfully";
        }else{
            return "A problem occurred during the update";
        }
    }

    public String deleteTransaction(int id) {
        Optional<Transactions> t1 = transactionRepo.findById(id);

        if (t1.isEmpty()) {
            return "transaction not found";
        }
        if (t1.get().getUser().getId() == getCurrentUser().getId()) {
            transactionRepo.delete(t1.get());
            return "deleted";
        }

        return "failed to delete";

    }

    public List<TransactionResponse> getTransactions(String type, Integer category, LocalDate date, String keyword) {
        List<TransactionResponse> transactionResponses = new ArrayList<>();
        Specification<Transactions> spec =
                Specification.where(TransactionSpecification.hasUser(getCurrentUser()));
        if(type != null){

            spec = spec.and(
                    TransactionSpecification.hasType(type)
            );

        }
        if(category != null){

            spec = spec.and(
                    TransactionSpecification.hasCategory(category)
            );

        }
        if(date != null){

            spec = spec.and(
                    TransactionSpecification.hasDate(date)
            );

        }
        if(keyword != null){

            spec = spec.and(
                    TransactionSpecification.hasKeyword(keyword)
            );

        }
        List<Transactions> transactions =
                transactionRepo.findAll(spec);

        for (Transactions t1 : transactions){

            if (t1.getUser().getUsername().equals(getCurrentUser().getUsername())) {

                TransactionResponse tr = new TransactionResponse();

                tr.setType(String.valueOf(t1.getType()));
                tr.setDescription(t1.getDescription());
                tr.setDate(t1.getDate());
                tr.setAmount(t1.getAmount());
                tr.setCategory(t1.getCategory().getName());

                transactionResponses.add(tr);
            }else {
                return transactionResponses;
            }
        }
        return transactionResponses;
    }
}
