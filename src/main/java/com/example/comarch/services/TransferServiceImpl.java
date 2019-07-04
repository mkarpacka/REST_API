package com.example.comarch.services;

import com.example.comarch.Currency;
import com.example.comarch.entities.Account;
import com.example.comarch.entities.Transfer;
import com.example.comarch.exception.AccountDoesNotExistException;
import com.example.comarch.repository.AccountRepository;
import com.example.comarch.repository.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Service
public class TransferServiceImpl implements TransferService {

    private AccountRepository accountRepository;
    private TransferRepository transferRepository;

    private double eurToGbp = 0.898452137;
    private double eurToPln = 4.24076919;
    private double eurToUsd = 1.129126;

    private double gbpToPln = 4.72178926;
    private double gbpToUsd = 1.2572;
    private double gbpToEur = 1.11424014;

    private double usdToPln = 4.71894451;
    private double usdToEur = 0.886132034;
    private double usdToGpb = 0.795279223 ;

    private HashMap<String, Double> currencies = hashmapWithCurrencies();

    public HashMap<String, Double>  hashmapWithCurrencies(){
        currencies = new HashMap<String, Double>();
        currencies.put("EURGBP", eurToGbp);
        currencies.put("EURPLN", eurToPln);
        currencies.put("EURUSD", eurToUsd);
        currencies.put("GBPPLN", gbpToPln);
        currencies.put("GBPUSD", gbpToUsd);
        currencies.put("GBPEUR", gbpToEur);
        currencies.put("USDPLN", usdToPln);
        currencies.put("USDEUR", usdToEur);
        currencies.put("USDGBP", usdToGpb);
        return currencies;
    }

    @Autowired
    public TransferServiceImpl(AccountRepository accountRepository, TransferRepository transferRepository) {
        this.accountRepository = accountRepository;
        this.transferRepository = transferRepository;
    }

    @Override
    public List<Account> makeTransfer(Account firstAccount, Account secondAccount, Double valueOfTransfer) throws AccountDoesNotExistException {

        if (firstAccount == null || secondAccount == null)
            throw new AccountDoesNotExistException("account to transfer do not exist");
        if (!firstAccount.equals(secondAccount)) {

            Double newMoneyAmountToFirstAccount = firstAccount.getMoney() - valueOfTransfer;
            Double newMoneyAmountToSecondAccount = secondAccount.getMoney() + valueOfTransfer;
            List<Account> updatedAccountsList = new ArrayList<>();

            if (newMoneyAmountToFirstAccount >= 0) {
                firstAccount.setMoney(newMoneyAmountToFirstAccount);
                secondAccount.setMoney(newMoneyAmountToSecondAccount);
                addTransfer(new Transfer(firstAccount.getNumber(), secondAccount.getNumber(), valueOfTransfer, secondAccount.getCurrency()));
            }
            updatedAccountsList.add(firstAccount);
            updatedAccountsList.add(secondAccount);
            accountRepository.save(firstAccount);
            accountRepository.save(secondAccount);
            return updatedAccountsList;
        } else return Collections.emptyList();

    }

    @Override
    public List<Transfer> getAllTransfers() {
        return transferRepository.findAll();
    }

    @Override
    public List<Transfer> getAccountTransfers(String number) throws AccountDoesNotExistException {
        Account account = accountRepository.findByNumber(number);

        if (account == null) throw new AccountDoesNotExistException("404");
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

    @Override
    public Double currencyConverter(Account firstAccount, Account secondAccount, Double valueOfTransfer) {
        Currency enumCurrency;
        Currency firstAccountCurrency = firstAccount.getCurrency();
        Currency secondAccountCurrency = secondAccount.getCurrency();

        if (firstAccountCurrency == secondAccountCurrency) {
            return valueOfTransfer;
        } else {
            String currencyStringKey = firstAccountCurrency.toString() + secondAccountCurrency.toString();
            System.out.println(currencyStringKey);
            valueOfTransfer = valueOfTransfer * currencies.get(currencyStringKey);
            return valueOfTransfer;
        }
    }

}
