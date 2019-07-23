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
    @OneToOne
    private Account firstAccount;
    @OneToOne
    private Account secondAccount;
    private Double money;
    private Currency currency;
    private TransferStatus transferStatus;
    private LocalDateTime transferStartedDate;
    private LocalDateTime transferReceivedDate;

    public Transfer(Account firstAccount, Account secondAccount, Double money, Currency currency, TransferStatus transferStatus, LocalDateTime transferStartedDate, LocalDateTime transferReceivedDate) {
        this.firstAccount = firstAccount;
        this.secondAccount = secondAccount;
        this.money = money;
        this.currency = currency;
        this.transferStatus = transferStatus;
        this.transferStartedDate = transferStartedDate;
        this.transferReceivedDate = transferReceivedDate;
    }
}
