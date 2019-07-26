package com.example.comarch.services;

import com.example.comarch.dto.ExternalTransferDto;
import com.example.comarch.entities.Account;
import com.example.comarch.entities.ExternalTransfer;
import com.example.comarch.repository.AccountRepository;
import com.example.comarch.repository.ExternalTransferRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@NoArgsConstructor
public class ExternalTransferServiceImpl implements ExternalTransferService {

    private ExternalTransferRepository externalTransferRepository;
    private AccountRepository accountRepository;
    private SendEmailService sendEmailService;

    @Autowired
    public ExternalTransferServiceImpl(ExternalTransferRepository externalTransferRepository, AccountRepository accountRepository, SendEmailService sendEmailService) {
        this.externalTransferRepository = externalTransferRepository;
        this.accountRepository = accountRepository;
        this.sendEmailService = sendEmailService;
    }


    @Override
    public ResponseEntity<Object> addExternalTransfer(ExternalTransferDto externalTransferDto, String email) {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Object> objectResponseEntity = restTemplate.postForEntity("https://comarch.herokuapp.com/transfer/external-transfer", externalTransferDto, null);
        ExternalTransfer externalTransfer = new ExternalTransfer(externalTransferDto.getExternalAccount(), externalTransferDto.getToAccount(), externalTransferDto.getAmount(), externalTransferDto.getCurrency(), externalTransferDto.getBankName());

        if (objectResponseEntity.getStatusCode() == HttpStatus.OK) {
            if(!email.isEmpty()){
                sendEmailService.sendConfirmingTransferEmail(externalTransferDto.getExternalAccount(), externalTransferDto.getToAccount(), email);

            }
        }
        Account account = accountRepository.findByNumber(externalTransferDto.getExternalAccount());

        account.setMoney(account.getMoney() - externalTransfer.getAmount());

        accountRepository.save(account);
        externalTransferRepository.save(externalTransfer);
        return objectResponseEntity;
    }
}
