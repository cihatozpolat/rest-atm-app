package com.honeywell.springserverapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
@AllArgsConstructor
public class UserAccount {
    @Id
    private int id;
    private String cardNumber;
    private String pin;
}
