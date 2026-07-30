package com.nielExpendex.expensesTracker.service;

import com.nielExpendex.expensesTracker.model.Category;
import com.nielExpendex.expensesTracker.repository.CategoryRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepo categoryRepo;

    public List<Category> allCategory() {
        Category c = new Category();
        c.setName("food");
        categoryRepo.save(c);

        Category a = new Category();
        a.setName("transport");
        categoryRepo.save(a);

        Category b = new Category();
        b.setName("shopping");
        categoryRepo.save(b);

        Category d = new Category();
        d.setName("gifts");
        categoryRepo.save(d);

        Category e = new Category();
        e.setName("rents");
        categoryRepo.save(e);

        Category f = new Category();
        f.setName("subscriptions");
        categoryRepo.save(f);

        return categoryRepo.findAll();
    }
}
