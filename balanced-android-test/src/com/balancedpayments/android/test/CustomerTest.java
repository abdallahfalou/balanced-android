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
    
    public void testIsValidWithInvalidSSN() {
        HashMap<String,String> map = new HashMap<String,String>();
        String[] ssn = {    "abcde",
                            "38992",
                            "a324"};
        
        for (int i = 0; i < ssn.length; i++) {
            map.put(Customer.OptionalFieldKeySsn, ssn[i]);
            Customer customer = new Customer(map);
            
            assertFalse(customer.isValid());
            assertFalse(customer.getErrors().isEmpty());
            assertTrue(customer.getErrors().contains("Invalid SSN (last 4 digits)"));
        }
    }
    
    public void testIsValidWithValidSSN() {
        HashMap<String,String> map = new HashMap<String,String>();
        String ssn = "1234";
        
        map.put(Customer.OptionalFieldKeySsn, ssn);
        Customer customer = new Customer(map);
        
        assertTrue(customer.isValid());
        assertTrue(customer.getErrors().isEmpty());
    }
    
    public void testIsValidWithInvalidEmail() {
        HashMap<String,String> map = new HashMap<String,String>();
        String[] email = {  "someone.com",
                            "email@domain",
                            "with/slash@domain.com"};
        
        for (int i = 0; i < email.length; i++) {
            map.put(Customer.OptionalFieldKeyEmail, email[i]);
            Customer customer = new Customer(map);
            
            assertFalse(customer.isValid());
            assertFalse(customer.getErrors().isEmpty());
            assertTrue(customer.getErrors().contains("Invalid email address"));
        }
    }
    
    public void testIsValidWithValidEmail() {
        HashMap<String,String> map = new HashMap<String,String>();
        String[] email = {  "someone@email.com",
                            "another@another.email.com",
                            "canada@email.ca",
                            "org@domain.org",
                            "with_underscore@domain.com",
                            "with.period@domain.com",
                            "with-dash@domain.com",
                            "with+plus@domain.com"};
        
        for (int i = 0; i < email.length; i++) {
            map.put(Customer.OptionalFieldKeyEmail, email[i]);
            Customer customer = new Customer(map);
            
            assertTrue(customer.isValid());
            assertTrue(customer.getErrors().isEmpty());
        }
    }
    
    public void testIsValidWithValidDob() {
        HashMap<String,String> map = new HashMap<String,String>();
        String[] dob = {"1969-01",
                        "1988-02",
                        "1983-03",
                        "1992-04",
                        "1972-11",
                        "1983-12" };
        
        for (int i = 0; i < dob.length; i++) {
            map.put(Customer.OptionalFieldKeyDob, dob[i]);
            Customer customer = new Customer(map);
            
            assertTrue(customer.isValid());
            assertTrue(customer.getErrors().isEmpty());
        }
    }
    
    public void testIsValidWithInvalidDob() {
        HashMap<String,String> map = new HashMap<String,String>();
        String[] dob = {"2013/10",
                        "10/2013",
                        "1983-2",
                        "2-1983",
                        "Jan 1990"};
        
        for (int i = 0; i < dob.length; i++) {
            map.put(Customer.OptionalFieldKeyDob, dob[i]);
            Customer customer = new Customer(map);
            
            assertFalse(customer.isValid());
            assertFalse(customer.getErrors().isEmpty());
            assertTrue(customer.getErrors().contains("DOB must follow format YYYY-MM"));
        }
        
        map.put(Customer.OptionalFieldKeyDob, "1959-13");
        Customer customer = new Customer(map);
        
        assertFalse(customer.isValid());
        assertFalse(customer.getErrors().isEmpty());
        assertTrue(customer.getErrors().contains("DOB month invalid"));
    }
    
//    public void testIsValidWithOneField() {
//        String name = "Jean-Claud Jake";
//        HashMap<String,String> param = new Map<String,String>();
//        
//        param.put("name", name);
//        Customer customer = new Customer(param);
//        
//        
//    }
}
