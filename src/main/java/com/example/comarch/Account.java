package com.example.comarch;


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
    private Long number;
    private Double money;
    private String currency;
    private String owner;


    public Account(Long number, Double money, String currency, String owner) {
        this.number = number;
        this.money = money;
        this.currency = currency;
        this.owner = owner;
    }

}
