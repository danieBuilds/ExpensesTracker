package com.nielExpendex.expensesTracker.controller;

import com.nielExpendex.expensesTracker.dto.TransactionRequest;
import com.nielExpendex.expensesTracker.dto.TransactionResponse;
import com.nielExpendex.expensesTracker.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("transaction")
@RequiredArgsConstructor
public class TransactionsController {
    private final TransactionService transactionService;

    @PostMapping("transactions")
    public ResponseEntity<String> addTransactions(@RequestBody TransactionRequest transactions){
        String res = transactionService.addTransaction(transactions);

        if (res.equals("Transaction added successfully")){
            return new ResponseEntity<>(res,HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>("Not accepted",HttpStatus.FORBIDDEN);
        }
    }
    @GetMapping("transactions/search")
    public ResponseEntity<List<TransactionResponse>> getTransactions(
            @RequestParam(required = false) String type,
            @RequestParam(required = false) int category,
            @RequestParam(required = false) LocalDate date,
            @RequestParam(required = false) String keyword) {

        return new ResponseEntity<>(transactionService.getTransactions(type, category, date, keyword),HttpStatus.OK);
    }
    @GetMapping("transactions")
    public ResponseEntity<List<TransactionResponse>> getTransactions(){
        List<TransactionResponse> tr = transactionService.getTransactions();
        if (tr.isEmpty()){
            return new ResponseEntity<>(tr, HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(tr, HttpStatus.OK);
        }
    }
    @GetMapping("transactions/{id}")
    public ResponseEntity<TransactionResponse> getTransaction(@PathVariable int id){
        TransactionResponse tr = transactionService.getTransaction(id);

        if (tr == null){
            return new ResponseEntity<>(tr, HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(tr, HttpStatus.OK);
        }
    }
    @PutMapping("transactions/{id}")
    public ResponseEntity<String> updateTransaction(@PathVariable int id, @RequestBody TransactionRequest tr){
        String ut = transactionService.updateTransaction(id, tr);
        if (ut.equals("transaction not found ")){
            return new ResponseEntity<>(ut,HttpStatus.NOT_FOUND);
        }else if (ut.equals("update successfully")) {
            return new ResponseEntity<>(ut,HttpStatus.ACCEPTED);
        }else {
            return new ResponseEntity<>(ut,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("transactions/{id}")
    public ResponseEntity<String> deleteTransaction(@PathVariable int id){
        String del = transactionService.deleteTransaction(id);

        if (del.equals("transaction not found")){
            return new ResponseEntity<>(del,HttpStatus.NO_CONTENT);
        } else if (del.equals("deleted")) {
            return new ResponseEntity<>(del,HttpStatus.OK);
        }else {
            return new ResponseEntity<>(del,HttpStatus.FORBIDDEN);
        }
    }
}
