package com.dimasDermawanJBusIO;

import com.dimasDermawanJBusIO.dbjson.Serializable;

import java.sql.Timestamp;

/**
 * Represents an invoice for a transaction between an {@link Account} buyer and {@link Renter} renter.
 * Extends {@link Serializable} so the ID is assigned automatically.
 *
 * @author Dimas Dermawan
 */
public class Invoice extends Serializable {

    /** Time of when invoice was created */
    public Timestamp time;

    /** ID of the {@link Account} buyer for this invoice */
    public int buyerId;

    /** ID of the {@link Renter} renter for this invoice */
    public int renterId;

    /** Rating given to transaction by renter */
    public BusRating rating;

    /** Status of payment for this invoice */
    public PaymentStatus status;

    /**
     * Enum representing possible ratings a renter can give the transaction.
     */
    public enum BusRating {
        /** No rating provided */
        NONE,

        /** Neutral rating */
        NEUTRAL,

        /** Good rating */
        GOOD,

        /** Bad rating */
        BAD
    }

    /**
     * Enum representing possible payment statuses for the invoice.
     */
    public enum PaymentStatus {
        /** Payment failed */
        FAILED,

        /** Payment is waiting or pending */
        WAITING,

        /** Payment was successful */
        SUCCESS
    }

    /**
     * Constructs an Invoice with buyer and renter IDs.
     * Time is automatically set. Rating and status initialized to defaults.
     *
     * @param buyerId ID of the {@link Account} buyer
     * @param renterId ID of the {@link Renter} renter
     */
    protected Invoice(int buyerId, int renterId) {
        // Set time to current timestamp
        time = new Timestamp(System.currentTimeMillis());

        this.buyerId = buyerId;
        this.renterId = renterId;

        this.rating = BusRating.NONE;
        this.status = PaymentStatus.WAITING;
    }

    /**
     * Overloaded constructor allowing {@link Account} and {@link Account} objects.
     * Sets IDs and default values.
     */
    public Invoice(Account buyer, Account renter) {
        this(buyer.id, renter.id);
    }

    /**
     * Returns a string representation of key invoice details.
     */
    public String toString() {
        return "Nomor Faktur : " + super.id + "\\nID Pembeli : "
                + buyerId + "\\nID Penyewa : " + renterId + "\\n";
    }
}
