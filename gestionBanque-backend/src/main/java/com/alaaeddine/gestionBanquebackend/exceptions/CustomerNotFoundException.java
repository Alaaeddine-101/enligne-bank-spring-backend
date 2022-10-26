package com.alaaeddine.gestionBanquebackend.exceptions;

import com.alaaeddine.gestionBanquebackend.entities.Customer;

public class CustomerNotFoundException extends Exception{
    public CustomerNotFoundException(String msg){
        super(msg);
    }
}
