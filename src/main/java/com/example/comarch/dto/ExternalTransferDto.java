package com.example.comarch.dto;

import com.example.comarch.entities.enums.Currency;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ExternalTransferDto {

    private String externalAccount;
    private String toAccount;
    private BigDecimal amount;
    private Currency currency;
    private String bankName;

    public ExternalTransferDto(String externalAccount, String toAccount, BigDecimal amount, Currency currency, String bankName) {
        this.externalAccount = externalAccount;
        this.toAccount = toAccount;
        this.amount = amount;
        this.currency = currency;
        this.bankName = bankName;
    }
}