package com.nielExpendex.expensesTracker.service;

import com.nielExpendex.expensesTracker.model.Users;
import com.nielExpendex.expensesTracker.repository.UserRepo;
import com.nielExpendex.expensesTracker.security.Jwtservice;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;

    private final Jwtservice jwtservice;

    final AuthenticationManager authenticationManager;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public String registerUser(Users user) {
        try {
            user.setPassword(encoder.encode(user.getPassword()));
            userRepo.save(user);
            return "registration successful";
        } catch (RuntimeException e) {
            return " ";
        }
    }

    public ResponseEntity<String> login(Users user) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                                user.getUsername(), user.getPassword()
                        )
                );
        if (authentication.isAuthenticated())
            return new ResponseEntity<>(jwtservice.generateToken(user.getUsername()),HttpStatus.ACCEPTED);
        else
            return new ResponseEntity<>("login failed",HttpStatus.FORBIDDEN);

//        Optional<Users> dbuser = userRepo.findByEmail(user.getEmail());

//        if(dbuser.isPresent()) {
//            if (user.getPassword().equals(dbuser.get().getPassword())) {
//                return new ResponseEntity<>("credentials correct", HttpStatus.ACCEPTED);
//            }else {
//                return new ResponseEntity<>("invalid email or password", HttpStatus.NOT_FOUND);
//            }
//        } else {
//            return new ResponseEntity<>("invalid email or password", HttpStatus.NOT_FOUND);
//        }
    }
}
