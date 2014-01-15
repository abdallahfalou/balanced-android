package com.balancedpayments.android.exception;

/**
 * Custom exception type for insufficient customer information 
 * (at least one optional field is required) 
 * 
 * @author Abdallah El-Falou
 */
public class CustomerInsufficientInfoException extends Exception {
    public CustomerInsufficientInfoException(String message) {
        super(message);
    }
}
