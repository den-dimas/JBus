package dimasDermawanJBusIO;

import java.text.*;
import java.util.Calendar;

public class Payment extends Invoice {
    private int busId;
    
    public String busSeat;
    public Calendar departureDate;
    
    public Payment(int id, int buyerId, int renterId, int busId, String busSeat) {
        super(id, buyerId, renterId);
        
        this.busId = busId;        
        this.busSeat = busSeat;
        
        this.departureDate = Calendar.getInstance();
        this.departureDate.add(Calendar.DATE, 2);
    }
    
    public Payment(int id, Account buyer, Renter renter, int busId, String busSeat) {
        super(id, buyer, renter);
        
        this.busId = busId;
        this.busSeat = busSeat;
        
        this.departureDate = Calendar.getInstance();
        this.departureDate.add(Calendar.DAY_OF_MONTH, 2);
    }
    
    public int getBusId() {
        return this.busId;
    }
    
    public String getDepartureInfo() {
        SimpleDateFormat SDFormat = new SimpleDateFormat("MMMM dd, yyyy hh:mm:ss");
        
        String current = SDFormat.format(this.departureDate.getTime());
        
        return "Payment ID: " + super.id + "\nBuyer ID: " + super.buyerId + "\nRenter ID: " + super.renterId + "\nBus ID: " + busId + "\nSeat: " + busSeat + "\nDeparture Date: " + current + "\n";
    }
    
    public String getTime() {
        SimpleDateFormat SDFormat = new SimpleDateFormat("MMMM dd, yyyy hh:mm:ss");
        
        String current = SDFormat.format(this.time.getTime());
        
        return current;
    }
}
