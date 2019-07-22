package com.example.comarch.entities;


import com.example.comarch.entities.enums.Currency;
import com.example.comarch.entities.enums.TransferStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    private TransferStatus transferStatus;
    private LocalDateTime transferStartedDate;
    private LocalDateTime transferRecievedDate;

    public Transfer(String firstAccountNumber, String secondAccountNumber, Double money, Currency currency, TransferStatus transferStatus, LocalDateTime transferStartedDate, LocalDateTime transferRecievedDate) {
        this.firstAccountNumber = firstAccountNumber;
        this.secondAccountNumber = secondAccountNumber;
        this.money = money;
        this.currency = currency;
        this.transferStatus = transferStatus;
        this.transferStartedDate = transferStartedDate;
        this.transferRecievedDate = transferRecievedDate;
    }
}
