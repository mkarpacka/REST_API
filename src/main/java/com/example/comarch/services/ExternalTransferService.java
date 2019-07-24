package com.example.comarch.services;

import com.example.comarch.dto.ExternalTransferDto;
import org.springframework.http.ResponseEntity;

public interface ExternalTransferService {

    ResponseEntity<Object> addExternalTransfer(ExternalTransferDto externalTransferDto);

}
