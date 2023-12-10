package com.dimasDermawanJBusIO;

import com.dimasDermawanJBusIO.dbjson.Serializable;

import java.util.regex.Pattern;

/**
 * <p>Represents a user account with essential information like name, email, and password.
 * This class extends the Serializable class, ensuring unique IDs for each instance.</p>
 *
 * <br />
 *
 * <p>The account includes additional details such as the associated company ({@link Renter}),
 * account balance, and validation methods for email and password.</p>
 *
 * @author Dimas Dermawan
 */
public class Account extends Serializable {

    /**
     * The email associated with the account.
     */
    public String email;

    /**
     * The name associated with the account.
     */
    public String name;

    /**
     * The password associated with the account.
     */
    public String password;

    /**
     * The company {@link Renter} associated with the account.
     */
    public Renter company;

    /**
     * The account balance.
     */
    public double balance;

    /**
     * Regular expression pattern for validating email format.
     */
    public static final String REGEX_EMAIL = "^[a-zA-Z0-9]+@[a-zA-Z]+(\\.[a-zA-Z]+)+$";

    /**
     * Regular expression pattern for validating password complexity.
     */
    public static final String REGEX_PASSWORD = "^(?!.*_)(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])\\w{8,}$";

    /**
     * Constructs an Account object with the specified name, email, and password.
     * Initializes the balance to 0.0.
     *
     * @param name The name associated with the account.
     * @param email The email associated with the account.
     * @param password The password associated with the account.
     */
    public Account(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.balance = 0.0;
    }

    /**
     * Returns a string representation of the Account, including ID, name, email, and password.
     *
     * @return A formatted string representing the Account.
     */
    public String toString() {
        return "ID Akun : " + super.id + "<br/>Nama : " + name + "<br/>Email : " + email + "<br/>Password : " + password + "<br/>";
    }

    /**
     * Validates the email and password based on predefined regular expression patterns.
     *
     * @return True if both email and password are valid, false otherwise.
     */
    public boolean validate() {
        Pattern patEmail = Pattern.compile(REGEX_EMAIL);
        Pattern patPassword = Pattern.compile(REGEX_PASSWORD);

        return patEmail.matcher(email).find() && patPassword.matcher(password).find();
    }

    /**
     * Validates the email and password based on predefined regular expression patterns.
     *
     * @param email The email to validate.
     * @param password The password to validate.
     * @return True if both email and password are valid, false otherwise.
     */
    public static boolean validate(String email, String password) {
        Pattern patEmail = Pattern.compile(REGEX_EMAIL);
        Pattern patPassword = Pattern.compile(REGEX_PASSWORD);

        return patEmail.matcher(email).find() && patPassword.matcher(password).find();
    }

    /**
     * Validates the email based on the predefined regular expression pattern.
     *
     * @param email The email to validate.
     * @return True if the email is valid, false otherwise.
     */
    public static boolean validateEmail(String email) {
        Pattern patEmail = Pattern.compile(REGEX_EMAIL);

        return patEmail.matcher(email).find();
    }

    /**
     * Validates the password based on the predefined regular expression pattern.
     *
     * @param password The password to validate.
     * @return True if the password is valid, false otherwise.
     */
    public static boolean validatePassword(String password) {
        Pattern patPassword = Pattern.compile(REGEX_PASSWORD);

        return patPassword.matcher(password).find();
    }
}
