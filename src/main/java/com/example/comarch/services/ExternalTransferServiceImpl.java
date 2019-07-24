package com.example.comarch.services;

import com.example.comarch.dto.ExternalTransferDto;
import com.example.comarch.repository.ExternalTransferRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@NoArgsConstructor
public class ExternalTransferServiceImpl implements ExternalTransferService{

    @Autowired
    public ExternalTransferServiceImpl(ExternalTransferRepository externalTransferRepository) {
        this.externalTransferRepository = externalTransferRepository;
    }

    private ExternalTransferRepository externalTransferRepository;


    @Override
    public ResponseEntity<Object> addExternalTransfer(ExternalTransferDto externalTransferDto) {
        RestTemplate restTemplate = new RestTemplate();
//        externalTransferRepository.save();
        ResponseEntity<Object> objectResponseEntity = restTemplate.postForEntity("https://comarch.herokuapp.com/transfer/external-transfer", externalTransferDto, null);
        return objectResponseEntity;
    }
}
