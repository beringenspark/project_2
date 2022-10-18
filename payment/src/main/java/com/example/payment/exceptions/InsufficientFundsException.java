package com.example.payment.exceptions;

public class InsufficientFundsException extends Exception {

    public InsufficientFundsException(){
        super("Unable to make withdrawal due to insufficient  funds.");
    }
}
