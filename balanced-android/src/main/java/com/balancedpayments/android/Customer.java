package com.balancedpayments.android;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

public class Customer {
    public static String OptionalFieldKeyName = "name";
    public static String OptionalFieldKeyEmail = "email";
    public static String OptionalFieldKeyMeta = "meta";
    public static String OptionalFieldKeySsn = "ssn_last4"; // Last 4 digits of Social Security Number of customer or business representative
    public static String OptionalFieldKeyBusinessName = "business_name";
    //TODO Implement address as object
    public static String OptionalFieldKeyPhone = "phone";
    public static String OptionalFieldKeyDob = "dob"; // Date of birth. Format is YYYY-MM e.g. "1980-05"
    public static String OptionalFieldKeyEin = "ein"; // Employee Identification Number (for businesses)
    public static String OptionalFieldKeyFacebook = "facebook"; // Facebook ID or username
    public static String OptionalFieldKeyTwitter = "twitter"; // Twitter ID or username
    
    private HashMap<String,String> optionalFields;
    private boolean valid = false;
    private ArrayList<String> errors;
    
    public Customer(HashMap<String,String> optionalFields) {        
        this.optionalFields = optionalFields;
        errors = new ArrayList<String>();
        
        validate();
    }

    private void validate() {
        // TODO validate email, SSN, dob, etc.
        boolean isValid = true;
        
        if (optionalFields.size() == 0) {
            errors.add("Minimum one field required");
        } 
        
        if (optionalFields.containsKey(OptionalFieldKeyDob)) {
            String dob = optionalFields.get(OptionalFieldKeyDob);
            
            if (!Pattern.matches("\\d\\d\\d\\d-\\d\\d", dob)) 
                errors.add("DOB must follow format YYYY-MM");
            else {
                int month = Integer.parseInt(dob.substring(5));
                if (month > 12 || month < 1) 
                    errors.add("DOB month invalid");
            }
            
        }
        
        if (!errors.isEmpty()) {
            isValid = false;
        }
         
        valid = isValid;
    }
    
    public boolean isValid() {
        return valid;
    }
    
    public ArrayList<String> getErrors() {
        return errors;
    }
}
