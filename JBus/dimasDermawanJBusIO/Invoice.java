package dimasDermawanJBusIO;

import java.util.Calendar;

public class Invoice extends Serializable {
    public Calendar time;
    public int buyerId;
    public int renterId;
    
    public BusRating rating;
    public PaymentStatus status;
    
    public enum BusRating { NONE, NEUTRAL, GOOD, BAD }
    public enum PaymentStatus { FAILED, WAITING, SUCCESS }
    
    protected Invoice(int id, int buyerId, int renterId) {
        super(id);
        
        // Stamp time
        time = Calendar.getInstance();
        time.getTime();
        
        this.buyerId = buyerId;
        this.renterId = renterId;
        this.time = time;
        
        this.rating = BusRating.NONE;
        this.status = PaymentStatus.WAITING;
    }
    
    public Invoice(int id, Account buyer, Renter renter) {
        super(id);
        
        // Stamp time
        time = Calendar.getInstance();
        time.getTime();
        
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
