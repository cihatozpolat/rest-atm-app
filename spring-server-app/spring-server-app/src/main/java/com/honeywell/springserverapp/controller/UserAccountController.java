package com.honeywell.springserverapp.controller;

import com.honeywell.springserverapp.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserAccountController {
    @Autowired
    UserAccountRepository userAccountRepository;

}
