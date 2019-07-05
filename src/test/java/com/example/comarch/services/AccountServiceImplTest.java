package com.example.comarch.services;

import com.example.comarch.entities.Account;
import com.example.comarch.entities.enums.Currency;
import com.example.comarch.exception.AccountDoesNotExistException;
import com.example.comarch.repository.AccountRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class AccountServiceImplTest {

    private AccountService accountService;
    private AccountRepository accountRepository;
    private Account account;
    private String owner;
    private Currency currency;
    private Double money;
    private String accountNumber;

    @Before
    public void setup() {
        accountNumber = "12345";
        money = 123.0;
        currency = Currency.EUR;
        owner = "Kowalsky";

        accountRepository = Mockito.mock(AccountRepository.class);
        accountService = new AccountServiceImpl(accountRepository);
        account = new Account(accountNumber, money, currency, owner);

    }

    @Test(expected = AccountDoesNotExistException.class)
    public void whenAccountDoesNotExistShouldThrowAccountDoesNotExistException() throws AccountDoesNotExistException {
        accountService.updateAccount("123", new Account());
    }

    @Test
    public void whenAccountCurrencyIsNullShouldNotChangeCurrencyInUpdatedAccount() throws AccountDoesNotExistException {
        when(accountRepository.findByNumber(accountNumber)).thenReturn(account);
        Account updatedAccount = accountService.updateAccount(accountNumber, new Account(accountNumber, money, null, owner));

        Assert.assertEquals(account.getCurrency(), updatedAccount.getCurrency());
    }

    @Test
    public void whenAccountMoneyIsNullShouldNotChangeMoneyInUpdatedAccount() throws AccountDoesNotExistException {
        when(accountRepository.findByNumber(accountNumber)).thenReturn(account);
        Account updatedAccount = accountService.updateAccount(accountNumber, new Account(accountNumber, null, currency, owner));

        Assert.assertEquals(account.getMoney(), updatedAccount.getMoney());
    }

    @Test
    public void whenAccountOwnerIsNullShouldNotChangeOwnerInUpdatedAccount() throws AccountDoesNotExistException {
        when(accountRepository.findByNumber(accountNumber)).thenReturn(account);
        Account updatedAccount = accountService.updateAccount(accountNumber, new Account(null, money, currency, owner));

        Assert.assertEquals(account.getOwner(), updatedAccount.getOwner());
    }
}