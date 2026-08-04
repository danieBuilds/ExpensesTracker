package com.nielExpendex.expensesTracker.service;

import com.nielExpendex.expensesTracker.dto.AnalyticsResponse;
import com.nielExpendex.expensesTracker.dto.TransactionResponse;
import com.nielExpendex.expensesTracker.model.Transactions;
import com.nielExpendex.expensesTracker.model.Users;
import com.nielExpendex.expensesTracker.repository.TransactionRepo;
import com.nielExpendex.expensesTracker.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AnalyticsService {
    private final UserRepo userRepo;
    private final TransactionRepo transactionRepo;

    public Users getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        assert authentication != null;

        return userRepo.findByUsername(authentication.getName());
    }

    public AnalyticsResponse getTotal() {
        List<Transactions> t1 = transactionRepo.findAllByUser(getCurrentUser());

        AnalyticsResponse ar = new AnalyticsResponse();
        List<TransactionResponse> incomeTransactionResponses = new ArrayList<>();
        List<TransactionResponse> expenseTransactionResponses = new ArrayList<>();

        BigDecimal totalIncome = BigDecimal.valueOf(0);
        BigDecimal totalExpense = BigDecimal.valueOf(0);
        BigDecimal balance = BigDecimal.valueOf(0);

//        LocalDate today = LocalDate.now();
//
//        // Option 1: Get month number (1-12)
//        int monthValue = today.getMonthValue();
//
//        // Option 2: Get month name (enum)
//        Month monthEnum = today.getMonth();
//
//        // Option 3: Get month name as string
//        String monthName = monthEnum.name(); // e.g., "JULY"

        log.info("before for loop");
        for (Transactions t : t1){
            log.info(t.getType().name());
            if (t.getType().name().equals("INCOME")){
                TransactionResponse tr = new TransactionResponse();
                BigDecimal thisAmount = t.getAmount();

                totalIncome = totalIncome.add(thisAmount);

                tr.setAmount(t.getAmount());
                tr.setDate(t.getDate());
                tr.setCategory(t.getCategory().getName());
                tr.setType(String.valueOf(t.getType()));
                tr.setDescription(t.getDescription());

                incomeTransactionResponses.add(tr);

            } else if (t.getType().name().equals("EXPENSE")) {
                TransactionResponse tr = new TransactionResponse();

                BigDecimal thisAmount = t.getAmount();

                totalExpense = totalExpense.add(thisAmount);

                tr.setAmount(t.getAmount());
                tr.setDate(t.getDate());
                tr.setCategory(t.getCategory().getName());
                tr.setType(String.valueOf(t.getType()));
                tr.setDescription(t.getDescription());

                expenseTransactionResponses.add(tr);
            }
        }
        balance = totalIncome.subtract(totalExpense);
        log.info(String.valueOf(totalIncome));
        log.info(String.valueOf(expenseTransactionResponses));
        log.info(String.valueOf(totalExpense));
        ar.setTotal_income(totalIncome);
        ar.setTotal_expenses(totalExpense);
        ar.setIncomeTransactionResponseList(incomeTransactionResponses);
        ar.setExpenseTransactionResponseList(expenseTransactionResponses);
        ar.setBalance(balance);

        return ar;
    }
}
