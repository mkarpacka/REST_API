package com.example.comarch.entities;


import com.example.comarch.Currency;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "transfer")
@NoArgsConstructor
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstAccountNumber;
    private String secondAccountNumber;
    private Double money;
    private Currency currency;

    public Transfer(String firstAccountNumber, String secondAccountNumber, Double money, Currency currency) {
        this.firstAccountNumber = firstAccountNumber;
        this.secondAccountNumber = secondAccountNumber;
        this.money = money;
        this.currency = currency;
    }




}
