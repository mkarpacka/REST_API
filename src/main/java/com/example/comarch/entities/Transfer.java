package com.example.comarch.entities;


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
    private Long firstAccountNumber;
    private Long secondAccountNumber;
    private Double money;
    private String currency;

    public Transfer(Long firstAccountNumber, Long secondAccountNumber, Double money) {
        this.firstAccountNumber = firstAccountNumber;
        this.secondAccountNumber = secondAccountNumber;
        this.money = money;
    }




}
