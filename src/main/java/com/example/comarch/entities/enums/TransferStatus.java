package com.example.comarch.entities.enums;

public enum TransferStatus {
    OPEN (0),
    FINISHED (1);

    private int transferStatus;

    TransferStatus(int transferStatus) {

        this.transferStatus = transferStatus;
    }

    public int getValue(){
        return this.transferStatus;
    }
}
