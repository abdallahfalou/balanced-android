package com.balancedpayments.android.test;

import java.util.HashMap;

import junit.framework.TestCase;

import com.balancedpayments.android.Customer;

public class CustomerTest extends TestCase {
    
    public void testIsValidWithNoFields() {
        Customer customer = new Customer(new HashMap<String,String>());
        
        assertFalse(customer.isValid());
        assertFalse(customer.getErrors().isEmpty());
        assertTrue(customer.getErrors().contains("Minimum one field required"));
    }
//    public void testIsValidWithNameOnly() {
//        String name = "Jean-Claud Jake";
//        HashMap<String,String> param = new Map<String,String>();
//        
//        param.put("name", name);
//        Customer customer = new Customer(param);
//        
//        
//    }
}
