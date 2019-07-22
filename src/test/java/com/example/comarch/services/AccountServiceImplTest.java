package com.example.comarch.services;

import com.example.comarch.entities.Account;
import com.example.comarch.entities.enums.Currency;
import com.example.comarch.exception.AccountDoesNotExistException;
import com.example.comarch.repository.AccountRepository;
import org.apache.catalina.core.ApplicationContext;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@SpringJUnitConfig
@RunWith(SpringJUnit4ClassRunner.class)
public class AccountServiceImplTest {

    private AccountService accountService;
    private AccountRepository accountRepository;
    private Account account;
    private String owner;
    private Currency currency;
    private Double money;
    private String accountNumber;
    private boolean isDeleted;


    @Before
    public void setup() {
        accountNumber = "12345";
        money = 123.0;
        currency = Currency.EUR;
        owner = "Kowalsky";
        isDeleted = false;

        accountRepository = Mockito.mock(AccountRepository.class);
        accountService = new AccountServiceImpl(accountRepository);
        account = new Account(accountNumber, money, currency, owner, isDeleted);

    }

    @Test(expected = AccountDoesNotExistException.class)
    public void whenAccountDoesNotExistShouldThrowAccountDoesNotExistException() throws AccountDoesNotExistException {
        accountService.updateAccount("123", new Account());
    }

    @Test
    public void whenAccountCurrencyIsNullShouldNotChangeCurrencyInUpdatedAccount() throws AccountDoesNotExistException {
        when(accountRepository.findByNumber(accountNumber)).thenReturn(account);
        Account updatedAccount = accountService.updateAccount(accountNumber, new Account(accountNumber, money, null, owner, isDeleted));

        Assert.assertEquals(account.getCurrency(), updatedAccount.getCurrency());
    }

    @Test
    public void whenAccountMoneyIsNullShouldNotChangeMoneyInUpdatedAccount() throws AccountDoesNotExistException {
        when(accountRepository.findByNumber(accountNumber)).thenReturn(account);
        Account updatedAccount = accountService.updateAccount(accountNumber, new Account(accountNumber, null, currency, owner, isDeleted));

        Assert.assertEquals(account.getMoney(), updatedAccount.getMoney());
    }

    @Test
    public void whenAccountOwnerIsNullShouldNotChangeOwnerInUpdatedAccount() throws AccountDoesNotExistException {
        when(accountRepository.findByNumber(accountNumber)).thenReturn(account);
        Account updatedAccount = accountService.updateAccount(accountNumber, new Account(null, money, currency, owner, isDeleted));

        Assert.assertEquals(account.getOwner(), updatedAccount.getOwner());
    }
}