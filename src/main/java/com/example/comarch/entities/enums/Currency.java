package com.example.comarch.entities.enums;

public enum Currency {
    EUR(0),
    USD(1),
    PLN(2),
    GBP(3);

    private int valueOfCurrencyInTable;

    Currency(int valueOfCurrencyInTable) {
        this.valueOfCurrencyInTable = valueOfCurrencyInTable;
    }

    public int getValue(){
        return this.valueOfCurrencyInTable;
    }
}
