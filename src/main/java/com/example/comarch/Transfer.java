package com.example.comarch;

import com.example.comarch.entities.Account;

public class Transfer {


    public boolean makeTransfer(Account firstAccount, Account secondAccount, Double valueOfTransfer){
        Double newMoneyAmountToFirstAccount = firstAccount.getMoney() - valueOfTransfer;
        Double newMoneyAmountToSecondAccount = secondAccount.getMoney() + valueOfTransfer;

        if(newMoneyAmountToFirstAccount>0 && newMoneyAmountToSecondAccount >0){
            firstAccount.setMoney(newMoneyAmountToFirstAccount);
            secondAccount.setMoney(newMoneyAmountToSecondAccount);
            return true;
        }

        return false;
    }
}
