package com.example.comarch.services;

import com.example.comarch.entities.Account;
import com.example.comarch.entities.Transfer;
import com.example.comarch.repository.AccountRepository;
import com.example.comarch.repository.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class TransferServiceImpl implements TransferService {

    private AccountRepository accountRepository;
    private TransferRepository transferRepository;

    @Autowired
    public TransferServiceImpl(AccountRepository accountRepository, TransferRepository transferRepository) {
        this.accountRepository = accountRepository;
        this.transferRepository = transferRepository;
    }

    @Override
    public List<Account> makeTransfer(Account firstAccount, Account secondAccount, Double valueOfTransfer) {

        if(!firstAccount.equals(secondAccount)) {

            Double newMoneyAmountToFirstAccount = firstAccount.getMoney() - valueOfTransfer;
            Double newMoneyAmountToSecondAccount = secondAccount.getMoney() + valueOfTransfer;
            List<Account> updatedAccountsList = new ArrayList<>();

            if (newMoneyAmountToFirstAccount > 0) {
                firstAccount.setMoney(newMoneyAmountToFirstAccount);
                secondAccount.setMoney(newMoneyAmountToSecondAccount);
            }
            updatedAccountsList.add(firstAccount);
            updatedAccountsList.add(secondAccount);
            addTransfer(new Transfer(firstAccount.getNumber(), secondAccount.getNumber(), valueOfTransfer));
            accountRepository.save(firstAccount);
            accountRepository.save(secondAccount);
            return updatedAccountsList;
        }
        else return Collections.emptyList();

    }

    @Override
    public List<Transfer> getAllTransfers() {
        return transferRepository.findAll();
    }

    @Override
    public List<Transfer> getAccountTransfers(Long number) {
        Account account = accountRepository.findByNumber(number);
        List<Transfer> allAccountTransfers = new ArrayList<>();
        List<Transfer> transfersFrom = transferRepository.findByFirstAccountNumber(account.getNumber());
        List<Transfer> transfersTo = transferRepository.findBySecondAccountNumber(account.getNumber());

        allAccountTransfers.addAll(transfersFrom);
        allAccountTransfers.addAll(transfersTo);

        return allAccountTransfers;
    }

    @Override
    public Transfer addTransfer(Transfer transfer) {
        return transferRepository.save(transfer);
    }
}
