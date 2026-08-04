package com.nielExpendex.expensesTracker.service;

import com.nielExpendex.expensesTracker.dto.BudgetRequest;
import com.nielExpendex.expensesTracker.dto.BudgetResponse;
import com.nielExpendex.expensesTracker.dto.TransactionResponse;
import com.nielExpendex.expensesTracker.model.Budget;
import com.nielExpendex.expensesTracker.model.Category;
import com.nielExpendex.expensesTracker.model.Users;
import com.nielExpendex.expensesTracker.repository.BudgetRepo;
import com.nielExpendex.expensesTracker.repository.CategoryRepo;
import com.nielExpendex.expensesTracker.repository.TransactionRepo;
import com.nielExpendex.expensesTracker.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BudgetService {
    private final UserRepo userRepo;
    private final TransactionService transactionService;
    private final BudgetRepo budgetRepo;
    private final CategoryRepo categoryRepo;

    public Users getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        assert authentication != null;

        return userRepo.findByUsername(authentication.getName());
    }

    public List<BudgetResponse> getBudgets() {
        Users user = getCurrentUser();
        List<Budget> budgets = budgetRepo.findAllByUser(user);
        List<BudgetResponse> budgetResponses = new ArrayList<>();

        for (Budget budget : budgets){
            BudgetResponse budgetResponse = new BudgetResponse();
            budgetResponse.setAmount(budget.getAmount());
            budgetResponse.setDate(budget.getDate());
            budgetResponse.setCategory(budget.getCategory().getName());
            budgetResponse.setDescription(budget.getDescription());
            budgetResponses.add(budgetResponse);
        }
        return budgetResponses;
    }

    public BudgetResponse getBudget(int id) {
        Users user = getCurrentUser();
        Budget budgets = budgetRepo.findByUserAndId(user,id);
        BigDecimal totalSpent = BigDecimal.valueOf(0);
        //BudgetResponse budgetResponses = new ArrayList<>();
        List<TransactionResponse> transactionResponses = transactionService.getTransactions();
        List<TransactionResponse> tr = new ArrayList<>();
        for (TransactionResponse t : transactionResponses){
            if (t.getCategory().equals(budgets.getCategory().getName())){
                TransactionResponse trs = new TransactionResponse();
                trs.setCategory(t.getCategory());
                trs.setDescription(t.getDescription());
                trs.setType(t.getType());
                trs.setDate(t.getDate());
                trs.setAmount(t.getAmount());
                tr.add(trs);
                totalSpent = totalSpent.add(t.getAmount());
            }
        }
        BudgetResponse budgetResponse = new BudgetResponse();
        budgetResponse.setAmount(budgets.getAmount());
        budgetResponse.setDate(budgets.getDate());
        budgetResponse.setCategory(budgets.getCategory().getName());
        budgetResponse.setDescription(budgets.getDescription());
        budgetResponse.setSpent(totalSpent);
        //budgetResponses.add(budgetResponse);
        budgetResponse.setTransactionResponses(tr);

        return budgetResponse;
    }

    public void addBudget(BudgetRequest budgetRequest) {
        Budget budget = new Budget();
        Optional<Category> category = categoryRepo.findById(budgetRequest.getCategoryId());
        budget.setAmount(budgetRequest.getAmount());
        budget.setCategory(category.get());
        budget.setDescription(budgetRequest.getDescription());
        budget.setDate(budgetRequest.getDate());
        budget.setUser(getCurrentUser());

        budgetRepo.save(budget);
    }

    public void editBudget(BudgetRequest budgetRequest, int id) {
        Users user = getCurrentUser();
        Budget budget = budgetRepo.findByUserAndId(user,id);
        Optional<Category> category = categoryRepo.findById(budgetRequest.getCategoryId());
        budget.setAmount(budgetRequest.getAmount());
        budget.setCategory(category.get());
        budget.setDescription(budgetRequest.getDescription());
        budget.setDate(budgetRequest.getDate());
        budgetRepo.save(budget);
    }

    public void deleteBudget(int id) {
        Users user = getCurrentUser();
        Budget budget = budgetRepo.findByUserAndId(user,id);
        budgetRepo.delete(budget);
    }
}
