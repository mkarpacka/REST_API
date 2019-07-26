package com.example.comarch.services;

import com.example.comarch.entities.enums.Currency;
import com.example.comarch.entities.Account;
import com.example.comarch.entities.Transfer;
import com.example.comarch.entities.enums.TransferStatus;
import com.example.comarch.exception.AccountDoesNotExistException;
import com.example.comarch.exception.CurrencyDoesNotExistException;
import com.example.comarch.repository.AccountRepository;
import com.example.comarch.repository.TransferRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@NoArgsConstructor
public class TransferServiceImpl implements TransferService {

    private AccountRepository accountRepository;
    private TransferRepository transferRepository;
    private JavaMailSender javaMailSender;
    private SendEmailService sendEmailService;

    private HashMap<String, Double> currencies = hashMapWithCurrencies();

    private HashMap<String, Double> hashMapWithCurrencies() {
        currencies = new HashMap<>();
        double eurToGbp = 0.898452137;
        currencies.put("EURGBP", eurToGbp);
        double eurToPln = 4.24076919;
        currencies.put("EURPLN", eurToPln);
        double eurToUsd = 1.129126;
        currencies.put("EURUSD", eurToUsd);
        double gbpToPln = 4.72178926;
        currencies.put("GBPPLN", gbpToPln);
        double gbpToUsd = 1.2572;
        currencies.put("GBPUSD", gbpToUsd);
        double gbpToEur = 1.11424014;
        currencies.put("GBPEUR", gbpToEur);
        double usdToPln = 4.71894451;
        currencies.put("USDPLN", usdToPln);
        double usdToEur = 0.886132034;
        currencies.put("USDEUR", usdToEur);
        double usdToGpb = 0.795279224;
        currencies.put("USDGBP", usdToGpb);
        double plnToEur = 0.234189743;
        currencies.put("PLNEUR", plnToEur);
        double plnToGbp = 0.210383879;
        currencies.put("PLNGBP", plnToGbp);
        double plnToUsd = 0.26377;
        currencies.put("PLNUSD", plnToUsd);
        return currencies;
    }

    @Autowired
    public TransferServiceImpl(AccountRepository accountRepository, TransferRepository transferRepository, JavaMailSender javaMailSender, SendEmailService sendEmailService) {
        this.accountRepository = accountRepository;
        this.transferRepository = transferRepository;
        this.javaMailSender = javaMailSender;
        this.sendEmailService = sendEmailService;
    }

    @Override
    public List<Account> makeTransfer(String firstAccountNumber, String secondAccountNumber, Double valueOfTransfer, String email) throws AccountDoesNotExistException, CurrencyDoesNotExistException {
        Account firstAccount = accountRepository.findByNumber(firstAccountNumber);
        Account secondAccount = accountRepository.findByNumber(secondAccountNumber);

        if (firstAccount == null || secondAccount == null)
            throw new AccountDoesNotExistException("account to transfer does not exist");
        if (!firstAccount.equals(secondAccount)) {

            Transfer moneyTransfer;
            Double newMoneyAmountToFirstAccount = firstAccount.getMoney() - valueOfTransfer;
            Double money = currencyConverter(firstAccount, secondAccount, valueOfTransfer);
            List<Account> updatedAccountsList = new ArrayList<>();

            moneyTransfer = new Transfer(firstAccount, secondAccount, money, secondAccount.getCurrency(), TransferStatus.valueOf("OPEN"), LocalDateTime.now(), null);

            addTransfer(moneyTransfer);

            firstAccount.setMoney(newMoneyAmountToFirstAccount);

            updatedAccountsList.add(firstAccount);
            updatedAccountsList.add(secondAccount);
            accountRepository.save(firstAccount);
            accountRepository.save(secondAccount);

            System.out.println("Sending Email...");
            if (!email.equals("email")) {
                sendEmailService.sendConfirmingTransferEmail(firstAccount.getNumber(), secondAccount.getNumber(), email);
            }

            return updatedAccountsList;
        } else return Collections.emptyList();

    }

    @Override
    public Double currencyConverter(Account firstAccount, Account secondAccount, Double valueOfTransfer) throws CurrencyDoesNotExistException {
        Currency firstAccountCurrency = firstAccount.getCurrency();
        Currency secondAccountCurrency = secondAccount.getCurrency();

        List<Currency> currencyTypes = Arrays.asList(Currency.values());

        if (currencyTypes.contains(firstAccountCurrency) && currencyTypes.contains(secondAccountCurrency)) {

            if (firstAccountCurrency == secondAccountCurrency) {
                return valueOfTransfer;
            } else {
                String currencyStringKey = firstAccountCurrency.toString() + secondAccountCurrency.toString();
                valueOfTransfer = valueOfTransfer * currencies.get(currencyStringKey);
                return valueOfTransfer;
            }
        } else {
            throw new CurrencyDoesNotExistException("currency does not exist");
        }
    }

    @Override
    public void updateTransferWithNewMoneyOnSecondAccount(Double newMoneyToUpdateOnSecondAccount, Account account) {

        Double money = account.getMoney() + newMoneyToUpdateOnSecondAccount;
        account.setMoney(money);
        accountRepository.save(account);
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
    public List<Transfer> getIncomingAccountTransfers(String number) throws AccountDoesNotExistException {
        Account account = accountRepository.findByNumber(number);

        if (account == null) throw new AccountDoesNotExistException("404");
        List<Transfer> transfersTo = transferRepository.findBySecondAccountNumber(account.getNumber());

        return new ArrayList<>(transfersTo);
    }

    @Override
    public List<Transfer> getOutgoingAccountTransfers(String number) throws AccountDoesNotExistException {
        Account account = accountRepository.findByNumber(number);

        if (account == null) throw new AccountDoesNotExistException("404");
        List<Transfer> transfersFrom = transferRepository.findByFirstAccountNumber(account.getNumber());

        return new ArrayList<>(transfersFrom);
    }

    @Override
    public Transfer addTransfer(Transfer transfer) {
        return transferRepository.save(transfer);
    }

    @Override
    public void changeTransferStatus() {
        List<Transfer> openedTransfers = transferRepository.findByTransferStatus(TransferStatus.OPEN);

        for (Transfer openedTransfer : openedTransfers) {
            openedTransfer.setTransferStatus(TransferStatus.FINISHED);
            openedTransfer.setTransferReceivedDate(LocalDateTime.now());
            String secondAccountNumber = openedTransfer.getSecondAccount().getNumber();
            Account account = accountRepository.findByNumber(secondAccountNumber);
            updateTransferWithNewMoneyOnSecondAccount(openedTransfer.getMoney(), account);
            transferRepository.save(openedTransfer);
        }

    }

}
