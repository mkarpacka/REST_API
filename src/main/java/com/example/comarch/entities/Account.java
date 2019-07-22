package com.example.comarch.entities;


import com.example.comarch.entities.enums.Currency;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "account")
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String number;
    private Double money;
    @Enumerated
    private Currency currency;
    private String owner;

    public Account(String accountNumber, Double money, Currency currency, String owner) {
        this.number = accountNumber;
        this.money = money;
        this.currency = currency;
        this.owner = owner;
    }
}
