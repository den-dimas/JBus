package com.dimasDermawanJBusIO;

import com.dimasDermawanJBusIO.dbjson.Serializable;

import java.util.regex.Pattern;

public class Renter extends Serializable {
    public String address;
    public String companyName;
    public String phoneNumber;

    private static final String REGEX_PHONE = "([0-9])\\d{9,12}";
    private static final String REGEX_NAME = "^[A-Z][a-zA-Z0-9_]\\w{4,20}";
    
    public Renter(String companyName) {
        this.companyName = companyName;
        this.phoneNumber = "";
        this.address = "";
    }
    
    public Renter(String companyName, String address) {
        this.companyName = companyName;
        this.phoneNumber = "";
        this.address = address;
    }
    
    public Renter(String companyName, String phoneNumber, String address) {
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public boolean validate() {
        Pattern patName = Pattern.compile(REGEX_NAME);
        Pattern patPhone = Pattern.compile(REGEX_PHONE);

        if (patName.matcher(companyName).find() && patPhone.matcher(phoneNumber).find()) {
            return true;
        } else {
            return false;
        }
    }
}
