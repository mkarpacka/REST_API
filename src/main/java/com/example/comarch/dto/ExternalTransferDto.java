package com.example.comarch.dto;

import com.example.comarch.entities.enums.Currency;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ExternalTransferDto {

    private String externalAccount;
    private String toAccount;
    private Double amount;
    private Currency currency;
    private String bankName;

    public ExternalTransferDto(String externalAccount, String toAccount, Double amount, Currency currency, String bankName) {
        this.externalAccount = externalAccount;
        this.toAccount = toAccount;
        this.amount = amount;
        this.currency = currency;
        this.bankName = bankName;
    }
}
