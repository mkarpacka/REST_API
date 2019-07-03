package com.example.comarch.endpoints;


import com.example.comarch.entities.Account;
import com.example.comarch.services.AccountService;
import com.example.comarch.services.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/")
public class TransferEndpoint {


    private AccountService accountService;
    private TransferService transferService;

    @Autowired
    public TransferEndpoint(AccountService accountService, TransferService transferService) {
        this.accountService = accountService;
        this.transferService = transferService;
    }


    @PutMapping("accounts/transfer/{number1}/{number2}/{money}")
    public ResponseEntity<?> makeTransfer(@PathVariable Long number1, @PathVariable Long number2, @PathVariable Double money) {

        Account firstAccount = accountService.getOneAccount(number1);
        Account secondAccount = accountService.getOneAccount(number2);

        List<Account> updatedAccounts = transferService.makeTransfer(firstAccount, secondAccount, money);

        return new ResponseEntity<>(updatedAccounts, HttpStatus.OK);
    }
}
