package com.nielExpendex.expensesTracker.service;

import com.nielExpendex.expensesTracker.dto.TransactionRequest;
import com.nielExpendex.expensesTracker.dto.TransactionResponse;
import com.nielExpendex.expensesTracker.model.Transactions;
import com.nielExpendex.expensesTracker.model.Users;
import com.nielExpendex.expensesTracker.repository.TransactionRepo;
import com.nielExpendex.expensesTracker.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionService {
    private final TransactionRepo transactionRepo;
    private final UserRepo userRepo;

    public Users getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        assert authentication != null;

        return userRepo.findByUsername(authentication.getName());
    }

    public String addTransaction(TransactionRequest transactions) {
        //Optional<Users> user = userRepo.findById(transactions.getUserId());
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        String username = authentication.getName();
//        Users user = userRepo.findByUsername(username);

        Users user = getCurrentUser();

        log.info("Here with user: {}", user);

            Transactions transaction = new Transactions();
            transaction.setUser(user);
            transaction.setDate(transactions.getDate());
            transaction.setAmount(transactions.getAmount());
            transaction.setType(transactions.getType());
            transaction.setDescription(transactions.getDescription());

            transactionRepo.save(transaction);
        System.out.println("saved");
        log.info("returning!!!!!!!!!!!!!");
            return "Transaction added successfully";
    }

    public List<TransactionResponse> getTransactions() {
        List<TransactionResponse> response = new ArrayList<>();
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String username = authentication.getName();
//        Users currentUser = userRepo.findByUsername(username);
        List<Transactions> transactions = transactionRepo.findAllByUser(getCurrentUser());

        for (Transactions t : transactions){
            TransactionResponse tr = new TransactionResponse();
            tr.setAmount(t.getAmount());
            tr.setDate(t.getDate());
            tr.setType(t.getType());
            tr.setDescription(t.getDescription());
            response.add(tr);
        }
        return response;
    }

    public TransactionResponse getTransaction(int id) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String username = authentication.getName();
//        Users currentUser = userRepo.findByUsername(username);
//        List<Transactions> transactions = transactionRepo.findByUser(getCurrentUser());
        Optional<Transactions> transaction = transactionRepo.findByIdAndUser(id,getCurrentUser());
        TransactionResponse tr = new TransactionResponse();

        if (transaction.isPresent()){
            tr.setDescription(transaction.get().getDescription());
            tr.setDate(transaction.get().getDate());
            tr.setAmount(transaction.get().getAmount());
            tr.setType(transaction.get().getType());
            return tr;
        }
        return tr;
    }

    public String updateTransaction(int id, TransactionRequest tr) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        String username = authentication.getName();
//
//        Users currentUser = userRepo.findByUsername(username);
        Optional<Transactions> transaction = transactionRepo.findById(id);


        if (transaction.isEmpty()){
            return "transaction not found ";
        }
        if(transaction.get().getUser().getId() == getCurrentUser().getId()){
            transaction.get().setDescription(tr.getDescription());
            transaction.get().setDate(tr.getDate());
            transaction.get().setAmount(tr.getAmount());
            transaction.get().setType(tr.getType());
            Transactions t1 = transaction.get();
            transactionRepo.save(t1);
            return "update successfully";
        }else{
            return "A problem occurred during the update";
        }
    }

    public String deleteTransaction(int id) {
        Optional<Transactions> t1 = transactionRepo.findById(id);
//
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        assert authentication != null;
//        String username = authentication.getName();
//        Users currentUser = userRepo.findByUsername(username);

        if (t1.isEmpty()) {
            return "transaction not found";
        }
        if (t1.get().getUser().getId() == getCurrentUser().getId()) {
            transactionRepo.delete(t1.get());
            return "deleted";
        }

        return "failed to delete";

    }
}
