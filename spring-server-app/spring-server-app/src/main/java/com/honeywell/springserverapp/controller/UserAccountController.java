package com.honeywell.springserverapp.controller;

import com.honeywell.springserverapp.model.UserAccount;
import com.honeywell.springserverapp.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class UserAccountController {
    @Autowired
    UserAccountService userAccountService;

    @GetMapping("/accounts/login/{cardNumber}/{PIN}")
    public ResponseEntity<UserAccount> getAccount(@PathVariable String cardNumber, @PathVariable String PIN) throws IOException {
        return userAccountService.getAccount(cardNumber, PIN);
    }

    @GetMapping("/accounts/balance/{cardNumber}")
    public ResponseEntity<Double> getBalance(@PathVariable String cardNumber) throws IOException {
        return userAccountService.getBalance(cardNumber);
    }

    @PutMapping("/accounts/changePin/{cardNumber}/{newPIN}")
    public ResponseEntity<String> changeCardPin(@PathVariable String cardNumber, @PathVariable String newPIN) throws IOException {
        return userAccountService.changeCardPin(cardNumber, newPIN);
    }

    @PutMapping("/accounts/deposit/{cardNumber}/{amount}")
    public ResponseEntity<String> deposit(@PathVariable String cardNumber, @PathVariable double amount) throws IOException {
        return userAccountService.deposit(cardNumber, amount);
    }

    @PutMapping("/accounts/withdraw/{cardNumber}/{amount}")
    public ResponseEntity<String> withdraw(@PathVariable String cardNumber, @PathVariable double amount) throws IOException {
        return userAccountService.withdraw(cardNumber, amount);
    }

    @PutMapping("/accounts/new/{cardNumber}/{PIN}")
    public ResponseEntity<UserAccount> newAccount(@PathVariable String cardNumber, @PathVariable String PIN) throws IOException {
        return userAccountService.save(cardNumber, PIN);
    }
}
