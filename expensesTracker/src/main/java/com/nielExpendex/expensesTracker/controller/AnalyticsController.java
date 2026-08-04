package com.nielExpendex.expensesTracker.controller;

import com.nielExpendex.expensesTracker.dto.AnalyticsResponse;
import com.nielExpendex.expensesTracker.service.AnalyticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("analytics")
@RequiredArgsConstructor
public class AnalyticsController {
    private final AnalyticsService analyticsService;

    @GetMapping("total")
    public ResponseEntity<AnalyticsResponse> getTotal(){
        return new ResponseEntity<>(analyticsService.getTotal(), HttpStatus.OK);
    }
}
