package com.example.comarch.services;

import com.example.comarch.entities.enums.Currency;
import com.example.comarch.entities.Account;
import com.example.comarch.entities.Transfer;
import com.example.comarch.entities.enums.TransferStatus;
import com.example.comarch.exception.AccountDoesNotExistException;
import com.example.comarch.repository.AccountRepository;
import com.example.comarch.repository.TransferRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Service
@NoArgsConstructor
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
    private double usdToGpb = 0.795279223;

    private HashMap<String, Double> currencies = hashmapWithCurrencies();

    public HashMap<String, Double> hashmapWithCurrencies() {
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

    Double newMoneyAmountToFirstAccount;
    Double newMoneyAmountToSecondAccount;

    @Override
    public List<Account> makeTransfer(Account firstAccount, Account secondAccount, Double valueOfTransfer) throws AccountDoesNotExistException {

        if (firstAccount == null || secondAccount == null)
            throw new AccountDoesNotExistException("account to transfer do not exist");
        if (!firstAccount.equals(secondAccount)) {

            Transfer moneyTransfer;
            newMoneyAmountToFirstAccount = firstAccount.getMoney() - valueOfTransfer;
            newMoneyAmountToSecondAccount = secondAccount.getMoney() + valueOfTransfer;
            List<Account> updatedAccountsList = new ArrayList<>();

            moneyTransfer = new Transfer(firstAccount.getNumber(), secondAccount.getNumber(), valueOfTransfer, secondAccount.getCurrency(), TransferStatus.OPEN, LocalDateTime.now(), null);

            addTransfer(moneyTransfer);

            firstAccount.setMoney(newMoneyAmountToFirstAccount);
            if (newMoneyAmountToFirstAccount >= 0 && moneyTransfer.getTransferStatus() == TransferStatus.FINISHED) {
                updateTransferWithNewMoneyOnSecondAccount(newMoneyAmountToSecondAccount, secondAccount);
            }
            updatedAccountsList.add(firstAccount);
            updatedAccountsList.add(secondAccount);
            accountRepository.save(firstAccount);
            accountRepository.save(secondAccount);

            return updatedAccountsList;
        } else return Collections.emptyList();

    }

    public void updateTransferWithNewMoneyOnSecondAccount(Double newMoneyToUpdateOnSecondAccount, Account account) {

        Double money = account.getMoney();
        account.setMoney(money + newMoneyToUpdateOnSecondAccount);
        accountRepository.save(account);


    }


//    public void addMoneyToAccount() {
//        List<Transfer> openedTransfers = transferRepository.findByTransferStatus(TransferStatus.OPEN);
//
//
//        Account account = accountRepository.findByNumber(openedTransfers.get(openedTransfers.size() - 1).getSecondAccountNumber());
//        account.setMoney(newMoneyAmountToSecondAccount);
//    }

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
        Currency firstAccountCurrency = firstAccount.getCurrency();
        Currency secondAccountCurrency = secondAccount.getCurrency();

        if (firstAccountCurrency == secondAccountCurrency) {
            return valueOfTransfer;
        } else {
            String currencyStringKey = firstAccountCurrency.toString() + secondAccountCurrency.toString();
            valueOfTransfer = valueOfTransfer * currencies.get(currencyStringKey);
            return valueOfTransfer;
        }
    }

    @Override
    public void changeTransferStatus() {
        List<Transfer> openedTransfers = transferRepository.findByTransferStatus(TransferStatus.OPEN);

        for (Transfer openedTransfer : openedTransfers) {
            openedTransfer.setTransferStatus(TransferStatus.FINISHED);
            openedTransfer.setTransferRecievedDate(LocalDateTime.now());
            String secondaccontNum = openedTransfer.getSecondAccountNumber();
            Account account = accountRepository.findByNumber(secondaccontNum);
            updateTransferWithNewMoneyOnSecondAccount(openedTransfer.getMoney(), account);
            transferRepository.save(openedTransfer);
        }

    }

    @Override
    public void addMoneyToAccount() {

    }

}
