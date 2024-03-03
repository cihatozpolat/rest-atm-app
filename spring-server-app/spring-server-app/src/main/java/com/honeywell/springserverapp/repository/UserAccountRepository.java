package com.honeywell.springserverapp.repository;

import com.honeywell.springserverapp.model.UserAccount;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserAccountRepository extends MongoRepository<UserAccount, Integer> {
}
