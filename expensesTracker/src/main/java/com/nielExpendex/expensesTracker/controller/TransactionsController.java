package com.nielExpendex.expensesTracker.controller;

import com.nielExpendex.expensesTracker.dto.TransactionRequest;
import com.nielExpendex.expensesTracker.dto.TransactionResponse;
import com.nielExpendex.expensesTracker.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("transaction")
@RequiredArgsConstructor
public class TransactionsController {
    private final TransactionService transactionService;

    @PostMapping("transaction")
    public ResponseEntity<String> addTransactions(@RequestBody TransactionRequest transactions){
        return transactionService.addTransaction(transactions);
    }
    @GetMapping("getTransactions")
    public ResponseEntity<List<TransactionResponse>> getTransactions(){
        return transactionService.getTransactions();
    }
    @GetMapping("getTransaction/{id}")
    public ResponseEntity<TransactionResponse> getTransaction(@PathVariable int id){
        return transactionService.getTransaction(id);
    }
    @PutMapping("updateTransaction/{id}")
    public ResponseEntity<String> updateTransaction(@PathVariable int id, @RequestBody TransactionRequest tr){
        return transactionService.updateTransaction(id, tr);
    }

    @DeleteMapping("transaction/{id}")
    public ResponseEntity<String> deleteTransaction(@PathVariable int id){
        return transactionService.deleteTransaction(id);
    }
}
