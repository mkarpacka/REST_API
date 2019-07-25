package com.example.comarch.endpoints;

import com.example.comarch.entities.Account;
import com.example.comarch.exception.AccountDoesNotExistException;
import com.example.comarch.exception.CurrencyDoesNotExistException;
import com.example.comarch.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(maxAge = 3600, origins = "*")
@RestController
@RequestMapping("/api/")
public class AccountEndpoint {

    private AccountService accountService;

    @Autowired
    public AccountEndpoint(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("accounts")
    public List<Account> getAccounts() {
        return accountService.getAccounts();
    }

    @GetMapping("accounts/get-account/{number}")
    public ResponseEntity<?> getOneAccount(@PathVariable String number) throws AccountDoesNotExistException {
        Account account = accountService.getOneAccount(number);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @GetMapping("accounts/get-account-byid/{id}")
    public ResponseEntity<?> getOneAccountById(@PathVariable Long id) throws AccountDoesNotExistException {
        Account account = accountService.getOneAccountById(id);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @PostMapping("accounts/add")
    public ResponseEntity<?> addAccount(@RequestBody Account account) throws CurrencyDoesNotExistException {
        accountService.addAccount(account);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @PutMapping("accounts/update/{number}")
    public ResponseEntity<Account> updateAccount(@PathVariable String number, @RequestBody Account account) throws AccountDoesNotExistException {
        accountService.updateAccount(number, account);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }


    @DeleteMapping("accounts/delete/{number}")
    public ResponseEntity<Account> deleteAccount(@PathVariable String number) throws AccountDoesNotExistException {
        accountService.deleteAccount(number);
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
