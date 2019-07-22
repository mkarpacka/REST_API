package com.example.comarch.components;

import com.example.comarch.services.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTransfers {

    private TransferService transferService;

    @Autowired
    public ScheduledTransfers(TransferService transferService) {
        this.transferService = transferService;
    }

    @Scheduled(fixedDelay = 30000)
    public void scheduleFixedDelayTask() {
        transferService.changeTransferStatus();
    }
}
