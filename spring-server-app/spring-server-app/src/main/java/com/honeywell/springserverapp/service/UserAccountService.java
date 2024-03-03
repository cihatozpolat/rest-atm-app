package com.honeywell.springserverapp.service;

import com.honeywell.springserverapp.model.UserAccount;
import com.honeywell.springserverapp.repository.UserAccountRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class UserAccountService {
    @Autowired
    private UserAccountRepository userAccountRepository;

    public ResponseEntity<UserAccount> getAccount(String cardNumber, String PIN) throws NoSuchElementException {
        Optional<UserAccount> optionalAccount = userAccountRepository.findByCardNumberAndPin(cardNumber, PIN);
        UserAccount userAccount = optionalAccount.orElseThrow(NoSuchElementException::new);
        return ResponseEntity.ok(userAccount);
    }

    public ResponseEntity<Double> getBalance(String cardNumber) throws NoSuchElementException {
        Optional<UserAccount> optionalAccount = userAccountRepository.findByCardNumber(cardNumber);
        UserAccount userAccount = optionalAccount.orElseThrow(NoSuchElementException::new);
        return ResponseEntity.ok(userAccount.getBalance());
    }

    public ResponseEntity<String> changeCardPin(String cardNumber, String newPIN) throws NoSuchElementException {
        Optional<UserAccount> optionalAccount = userAccountRepository.findByCardNumber(cardNumber);
        UserAccount userAccount = optionalAccount.orElseThrow(NoSuchElementException::new);
        userAccount.setPin(newPIN);
        userAccountRepository.save(userAccount);
        return ResponseEntity.ok("success");
    }

    public ResponseEntity<String> deposit(String cardNumber, double amount) throws NoSuchElementException {
        Optional<UserAccount> optionalAccount = userAccountRepository.findByCardNumber(cardNumber);
        UserAccount userAccount = optionalAccount.orElseThrow(NoSuchElementException::new);
        userAccount.deposit(amount);
        userAccountRepository.save(userAccount);
        return ResponseEntity.ok("success");
    }

    public ResponseEntity<String> withdraw(String cardNumber, double amount) throws NoSuchElementException {
        Optional<UserAccount> optionalAccount = userAccountRepository.findByCardNumber(cardNumber);
        UserAccount userAccount = optionalAccount.orElseThrow(NoSuchElementException::new);

        if (userAccount.getBalance() < amount) {
            throw new IllegalStateException("Insufficient funds");
        }
        userAccount.withdraw(amount);
        userAccountRepository.save(userAccount);
        return ResponseEntity.ok("success");
    }

    public ResponseEntity<UserAccount> save(String cardNumber, String PIN) {
        UserAccount newAccount = new UserAccount(cardNumber, PIN, 0);
        UserAccount savedAccount = userAccountRepository.save(newAccount);
        return ResponseEntity.ok(savedAccount);
    }
}
