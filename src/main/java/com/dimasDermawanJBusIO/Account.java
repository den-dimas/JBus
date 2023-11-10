package com.dimasDermawanJBusIO;

import com.dimasDermawanJBusIO.dbjson.Serializable;

import java.util.regex.Pattern;

public class Account extends Serializable {
    public String email;
    public String name;
    public String password;

    public Renter company;
    public double balance;

    public static final String REGEX_EMAIL = "^(^[a-zA-Z0-9])([a-zA-Z0-9\\.]+)([a-zA-Z0-9]+)(\\@)([a-zA-Z])+(\\.)(com)$";
    public static final String REGEX_PASSWORD = "^(?!.*\\_)(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])\\w{8,}$";
    
    /**
     * <i>Class</i> untuk membuat suatu akun.
     * 
     * @param name Nama akun
     * @param email Email akun
     * @param password Password akun
     */
    public Account(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.balance = 0.0;
    }
    
    public String toString() {
        return "ID Akun : " + super.id + "\nNama : " + name + "\nEmail : " + email + "\nPassword : " + password + "\n";
    }

    public boolean validate() {
        Pattern patEmail = Pattern.compile(REGEX_EMAIL);
        Pattern patPassword = Pattern.compile(REGEX_PASSWORD);

        if (patEmail.matcher(email).find() && patPassword.matcher(password).find()) {
            return true;
        } else {
            return false;
        }
    }
}
