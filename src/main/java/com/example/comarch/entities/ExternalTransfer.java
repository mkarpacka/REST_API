package com.example.comarch.entities;

import com.example.comarch.entities.enums.Currency;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;


@Data
@Entity
@Table(name = "external-transfers")
@NoArgsConstructor
public class ExternalTransfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String externalAccount;
    private String toAccount;
    private BigDecimal amount;
    private Currency currency;
    private String bankName;

    public ExternalTransfer(String externalAccount, String toAccount, BigDecimal amount, Currency currency, String bankName) {
        this.externalAccount = externalAccount;
        this.toAccount = toAccount;
        this.amount = amount;
        this.currency = currency;
        this.bankName = bankName;
    }

}
