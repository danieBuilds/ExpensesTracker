package com.nielExpendex.expensesTracker.controller;

import com.nielExpendex.expensesTracker.model.Users;
import com.nielExpendex.expensesTracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("register")
    public ResponseEntity<String> registerUser(@RequestBody Users user){
        String res = userService.registerUser(user);
        if (res.isBlank()){
            return new ResponseEntity<>("registration failed",HttpStatus.INTERNAL_SERVER_ERROR);
        }else {
            return new ResponseEntity<>(res,HttpStatus.CREATED);
        }

    }

    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody Users user){
        return userService.login(user);
    }
}
