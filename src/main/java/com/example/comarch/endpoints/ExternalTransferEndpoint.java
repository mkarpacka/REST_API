package com.example.comarch.endpoints;

import com.example.comarch.dto.ExternalTransferDto;
import com.example.comarch.services.ExternalTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(maxAge = 3600,origins = "*")
@RestController
@RequestMapping("/api/")
public class ExternalTransferEndpoint {

    private ExternalTransferService externalTransferService;

    @Autowired
    public ExternalTransferEndpoint(ExternalTransferService externalTransferService) {
        this.externalTransferService = externalTransferService;
    }

    @PostMapping("transfer/external-transfer/{email}")
    public ResponseEntity<?> makeExternalTransfer(@RequestBody ExternalTransferDto externalTransferDto, @PathVariable String email){
        externalTransferService.addExternalTransfer(externalTransferDto, email);
        return new ResponseEntity<>(externalTransferDto, HttpStatus.OK);
    }

}
