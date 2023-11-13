package com.dimasDermawanJBusIO;

import com.dimasDermawanJBusIO.dbjson.Serializable;

import java.sql.Timestamp;

public class Invoice extends Serializable {
    public Timestamp time;
    public int buyerId;
    public int renterId;
    
    public BusRating rating;
    public PaymentStatus status;
    
    public enum BusRating { NONE, NEUTRAL, GOOD, BAD }
    public enum PaymentStatus { FAILED, WAITING, SUCCESS }
    
    protected Invoice(int buyerId, int renterId) {
        // Stamp time
        time = new Timestamp(System.currentTimeMillis());
        
        this.buyerId = buyerId;
        this.renterId = renterId;
        this.time = time;
        
        this.rating = BusRating.NONE;
        this.status = PaymentStatus.WAITING;
    }
    
    public Invoice(Account buyer, Renter renter) {
        // Stamp time
        time = new Timestamp(System.currentTimeMillis());
        
        this.buyerId = buyer.id;
        this.renterId = renter.id;
        this.time = time;
        
        this.rating = BusRating.NONE;
        this.status = PaymentStatus.WAITING;
    }
    
    public String toString() {
        return "Nomor Faktur : " + super.id + "\nID Pembeli : " + buyerId + "\nID Penyewa : " + renterId + "\n";
    }
}
