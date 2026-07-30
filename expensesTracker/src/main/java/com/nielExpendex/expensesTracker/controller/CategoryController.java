package com.nielExpendex.expensesTracker.controller;

import com.nielExpendex.expensesTracker.model.Category;
import com.nielExpendex.expensesTracker.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("categories")
    public List<Category> allCategory(){
        return categoryService.allCategory();
    }
}
