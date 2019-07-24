package com.example.comarch.services;

import com.example.comarch.entities.ExternalTransfer;
import org.springframework.http.ResponseEntity;

public interface ExternalTransferService {

    ResponseEntity<Object> addExternalTransfer(ExternalTransfer externalTransfer);

}
