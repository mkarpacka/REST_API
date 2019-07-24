package com.example.comarch.entities.dto;

import com.example.comarch.entities.enums.Currency;

import java.math.BigDecimal;

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
