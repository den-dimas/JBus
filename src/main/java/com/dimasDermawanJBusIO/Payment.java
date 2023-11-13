package com.dimasDermawanJBusIO;

import java.sql.Time;
import java.text.*;
import java.sql.Timestamp;
import java.util.List;

public class Payment extends Invoice {
    private int busId;
    
    public List<String> busSeat;
    public Timestamp departureDate;
    
    public Payment(int buyerId, int renterId, int busId, List<String> busSeat, Timestamp departureDate) {
        super(buyerId, renterId);
        
        this.busId = busId;        
        this.busSeat = busSeat;
        
        this.departureDate = departureDate;
    }
    
    public Payment(Account buyer, Renter renter, int busId, List<String> busSeat, Timestamp departureDate) {
        super(buyer, renter);
        
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

    public static Schedule availableSchedule(Timestamp departureSchedule, String seat, Bus bus) {
        for (Schedule s : bus.schedules) {
            if (departureSchedule.compareTo(s.departureSchedule) == 0 && s.isSeatAvailable(seat)) {
                return s;
            }
        }

        return null;
    }

    public static Schedule availableSchedule(Timestamp departureSchedule, List<String> seats, Bus bus) {
        for (Schedule s : bus.schedules) {
            if (departureSchedule.compareTo(s.departureSchedule) == 0 && s.isSeatAvailable(seats)) {
                return s;
            }
        }

        return null;
    }

    public static Schedule availableSchedule(Timestamp departureSchedule, Bus bus) {
        for (Schedule s : bus.schedules) {
            if (departureSchedule.equals(departureSchedule)) return s;
        }

        return null;
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

    public static boolean makeBooking(Timestamp departureSchedule, List<String> seats, Bus bus) {
        for (Schedule schedule : bus.schedules) {
            if (departureSchedule.compareTo(schedule.departureSchedule) == 0 && schedule.isSeatAvailable(seats)) {
                schedule.bookSeat(seats);

                return true;
            }
        }

        return false;
    }
}
