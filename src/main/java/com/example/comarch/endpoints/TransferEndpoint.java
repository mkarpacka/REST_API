package com.example.comarch.endpoints;

import com.example.comarch.entities.Account;
import com.example.comarch.entities.Transfer;
import com.example.comarch.exception.AccountDoesNotExistException;
import com.example.comarch.services.AccountService;
import com.example.comarch.services.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(allowCredentials = "true", maxAge = 3600)
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

    @PutMapping("accounts/transfer/{firstAccountNumber}/{secondAccountNumber}/{money}")
    public ResponseEntity<?> makeTransfer(@PathVariable String firstAccountNumber, @PathVariable String secondAccountNumber, @PathVariable Double money) throws AccountDoesNotExistException {
        List<Account> updatedAccounts = transferService.makeTransfer(firstAccountNumber, secondAccountNumber, money);
        return new ResponseEntity<>(updatedAccounts, HttpStatus.OK);
    }

    @GetMapping("accounts/transfers")
    public List<Transfer> getAllTransfers() {
        return transferService.getAllTransfers();
    }

    @GetMapping("accounts/transfers/{number}")
    public ResponseEntity<?> getAccountTransfers(@PathVariable String number) throws AccountDoesNotExistException {
        return new ResponseEntity<>(transferService.getAccountTransfers(number), HttpStatus.OK);
    }

    @GetMapping("accounts/transfers/incoming/{number}")
    public ResponseEntity<?> getIncomingAccountTransfers(@PathVariable String number) throws AccountDoesNotExistException {
        return new ResponseEntity<>(transferService.getIncomingAccountTransfers(number), HttpStatus.OK);
    }

    @GetMapping("accounts/transfers/outgoing/{number}")
    public ResponseEntity<?> getOutgoingAccountTransfers(@PathVariable String number) throws AccountDoesNotExistException {
        return new ResponseEntity<>(transferService.getOutgoingAccountTransfers(number), HttpStatus.OK);
    }
}
