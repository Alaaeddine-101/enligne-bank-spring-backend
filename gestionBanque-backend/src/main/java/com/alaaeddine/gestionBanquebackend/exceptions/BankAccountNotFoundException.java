package com.alaaeddine.gestionBanquebackend.exceptions;

public class BankAccountNotFoundException extends Exception {
    public BankAccountNotFoundException(String msg){
        super(msg);
    }
}
