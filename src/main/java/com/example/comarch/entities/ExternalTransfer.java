package com.example.comarch.entities;

import com.example.comarch.entities.enums.Currency;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;


@Data
@Entity
@Table(name = "external_transfers")
@NoArgsConstructor
public class ExternalTransfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String externalAccount;
    @Column(name="recieving_account")
    private String toAccount;
    private Double amount;
    private Currency currency;
    private String bankName;

    public ExternalTransfer(String externalAccount, String toAccount, Double amount, Currency currency, String bankName) {
        this.externalAccount = externalAccount;
        this.toAccount = toAccount;
        this.amount = amount;
        this.currency = currency;
        this.bankName = bankName;
    }


}
