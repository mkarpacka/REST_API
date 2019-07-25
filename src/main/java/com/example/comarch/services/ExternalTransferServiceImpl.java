package com.example.comarch.services;

import com.example.comarch.dto.ExternalTransferDto;
import com.example.comarch.entities.Account;
import com.example.comarch.entities.ExternalTransfer;
import com.example.comarch.repository.AccountRepository;
import com.example.comarch.repository.ExternalTransferRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@NoArgsConstructor
public class ExternalTransferServiceImpl implements ExternalTransferService{

    private ExternalTransferRepository externalTransferRepository;
    private AccountRepository accountRepository;

    @Autowired
    public ExternalTransferServiceImpl(ExternalTransferRepository externalTransferRepository, AccountRepository accountRepository) {
        this.externalTransferRepository = externalTransferRepository;
        this.accountRepository = accountRepository;
    }




    @Override
    public ResponseEntity<Object> addExternalTransfer(ExternalTransferDto externalTransferDto) {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Object> objectResponseEntity = restTemplate.postForEntity("https://comarch.herokuapp.com/transfer/external-transfer", externalTransferDto, null);
        ExternalTransfer externalTransfer = new ExternalTransfer(externalTransferDto.getExternalAccount(), externalTransferDto.getToAccount(), externalTransferDto.getAmount(), externalTransferDto.getCurrency(), externalTransferDto.getBankName());

        Account account = accountRepository.findByNumber(externalTransferDto.getExternalAccount());

        account.setMoney(account.getMoney() - externalTransfer.getAmount());

        accountRepository.save(account);
        externalTransferRepository.save(externalTransfer);
        return objectResponseEntity;
    }
}
