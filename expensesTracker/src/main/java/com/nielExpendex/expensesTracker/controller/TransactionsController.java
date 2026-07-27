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
        String res = transactionService.addTransaction(transactions);

        if (res.equals("Transaction added successfully")){
            return new ResponseEntity<>(res,HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>("Not accepted",HttpStatus.FORBIDDEN);
        }
    }
    @GetMapping("getTransactions")
    public ResponseEntity<List<TransactionResponse>> getTransactions(){
        List<TransactionResponse> tr = transactionService.getTransactions();
        if (tr.isEmpty()){
            return new ResponseEntity<>(tr, HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(tr, HttpStatus.OK);
        }
    }
    @GetMapping("getTransaction/{id}")
    public ResponseEntity<TransactionResponse> getTransaction(@PathVariable int id){
        TransactionResponse tr = transactionService.getTransaction(id);

        if (tr == null){
            return new ResponseEntity<>(tr, HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(tr, HttpStatus.OK);
        }
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
