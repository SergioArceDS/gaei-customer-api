package com.gaei.customer.exception;

public class BusinessException extends RuntimeException {

    private final String idTx;

    public BusinessException(String idTx, String message){
        super(message);
        this.idTx = idTx;
    }

    public String getIdTx(){
        return idTx;
    }
}
