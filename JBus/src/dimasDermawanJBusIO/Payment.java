package dimasDermawanJBusIO;

import java.text.*;
import java.sql.Timestamp;

public class Payment extends Invoice {
    private int busId;
    
    public String busSeat;
    public Timestamp departureDate;
    
    public Payment(int id, int buyerId, int renterId, int busId, String busSeat, Timestamp departureDate) {
        super(id, buyerId, renterId);
        
        this.busId = busId;        
        this.busSeat = busSeat;
        
        this.departureDate = departureDate;
    }
    
    public Payment(int id, Account buyer, Renter renter, int busId, String busSeat, Timestamp departureDate) {
        super(id, buyer, renter);
        
        this.busId = busId;
        this.busSeat = busSeat;
        
        this.departureDate = departureDate;
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
    
    public static boolean isAvailable(Timestamp departureSchedule, String seat, Bus bus) {
        for (Schedule schedule : bus.schedules) {
            if (departureSchedule.compareTo(schedule.departureSchedule) == 0 && schedule.isSeatAvailable(seat)) {
                return true;
            }
        }
        
        return false;
    }
    
    public static boolean makeBooking(Timestamp departureSchedule, String seat, Bus bus) {
        for (Schedule schedule : bus.schedules) {
            if (departureSchedule.compareTo(schedule.departureSchedule) == 0 && schedule.isSeatAvailable(seat)) {
                schedule.bookSeat(seat);
                
                return true;
            }
        }
        
        return false;
    }
}
