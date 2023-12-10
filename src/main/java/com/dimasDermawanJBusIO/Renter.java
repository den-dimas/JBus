package com.dimasDermawanJBusIO;

import com.dimasDermawanJBusIO.dbjson.Serializable;

import java.util.regex.Pattern;

/**
 * Represents a renting company in the JBus system.
 * Extends the Serializable class.
 *
 * @author Dimas Dermawan
 */
public class Renter extends Serializable {
    /**
     * The address of the renting company.
     */
    public String address;

    /**
     * The name of the renting company.
     */
    public String companyName;

    /**
     * The phone number of the renting company.
     */
    public String phoneNumber;

    /**
     * The regular expression pattern for validating phone numbers.
     */
    private static final String REGEX_PHONE = "^\\d{9,12}$";

    /**
     * The regular expression pattern for validating company names.
     */
    private static final String REGEX_NAME = "^[A-Z][a-zA-Z]*(?: [A-Z][a-zA-Z]*)?$";

    /**
     * Creates a new Renter instance with the given company name.
     *
     * @param companyName The name of the renting company.
     */
    public Renter(String companyName) {
        this.companyName = companyName;
        this.phoneNumber = "";
        this.address = "";
    }

    /**
     * Creates a new Renter instance with the given company name and address.
     *
     * @param companyName The name of the renting company.
     * @param address     The address of the renting company.
     */
    public Renter(String companyName, String address) {
        this.companyName = companyName;
        this.phoneNumber = "";
        this.address = address;
    }

    /**
     * Creates a new Renter instance with the given company name, address, and phone number.
     *
     * @param companyName The name of the renting company.
     * @param address     The address of the renting company.
     * @param phoneNumber The phone number of the renting company.
     */
    public Renter(String companyName, String address, String phoneNumber) {
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    /**
     * Validates the Renter instance by checking the company name and phone number against defined regex patterns.
     *
     * @return True if the Renter instance is valid, false otherwise.
     */
    public boolean validate() {
        Pattern patName = Pattern.compile(REGEX_NAME);
        Pattern patPhone = Pattern.compile(REGEX_PHONE);

        return patName.matcher(companyName).find() && patPhone.matcher(phoneNumber).find();
    }
}
