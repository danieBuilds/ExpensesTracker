package com.nielExpendex.expensesTracker.controller;

import com.nielExpendex.expensesTracker.dto.BudgetRequest;
import com.nielExpendex.expensesTracker.dto.BudgetResponse;
import com.nielExpendex.expensesTracker.service.BudgetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("budget")
@RequiredArgsConstructor
public class BudgetController {
    private final BudgetService budgetService;

    @GetMapping("budgets")
    public ResponseEntity<?> getBudgets(){
        List<BudgetResponse> budgetResponses = budgetService.getBudgets();

        if (budgetResponses.isEmpty()){
            return new ResponseEntity<>("no budget yet, create one.", HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(budgetResponses,HttpStatus.OK);
        }
    }

    @GetMapping("budgets/{id}")
    public ResponseEntity<?> getBudget(@PathVariable int id){
        BudgetResponse budgetResponses = budgetService.getBudget(id);
        return new ResponseEntity<>(budgetResponses,HttpStatus.OK);
    }
    @PostMapping("budgets")
    public ResponseEntity<String> addBudget(@RequestBody BudgetRequest budgetRequest){
        budgetService.addBudget(budgetRequest);
        return new ResponseEntity<>("created",HttpStatus.CREATED);
    }
    @PutMapping("budgets/{id}")
    public ResponseEntity<String> editBudget(BudgetRequest budgetRequest, @PathVariable int id){
        budgetService.editBudget(budgetRequest, id);
        return new ResponseEntity<>("edited successfully",HttpStatus.CREATED);
    }

    @DeleteMapping("budgets/{id}")
    public ResponseEntity<?> deleteBudget(@PathVariable int id){
        budgetService.deleteBudget(id);
        return new ResponseEntity<>("delete",HttpStatus.OK);
    }
}
