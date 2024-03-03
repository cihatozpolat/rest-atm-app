package com.honeywell.springserverapp.repository;

import com.honeywell.springserverapp.model.UserAccount;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAccountRepository extends MongoRepository<UserAccount, String> {
    Optional<UserAccount> findByCardNumber(String cardNumber);
    Optional<UserAccount> findByCardNumberAndPin(String cardNumber, String pin);
}
