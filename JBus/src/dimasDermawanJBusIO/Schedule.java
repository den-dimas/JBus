package dimasDermawanJBusIO;

import java.util.*;
import java.sql.Timestamp;
import java.text.*;

public class Schedule {
    public Timestamp departureSchedule;
    public Map<String, Boolean> seatAvailability;
    
    public Schedule(Timestamp departureSchedule, int numberOfSeats) {
        this.departureSchedule = departureSchedule;
        this.initializeSeatAvailability(numberOfSeats);
    }
    
    private void initializeSeatAvailability(int numberOfSeats) {
        seatAvailability = new LinkedHashMap<String, Boolean>();
        
        for (int i = 1; i <= numberOfSeats; i++) {
            String sn = i < 10 ? "0"+i : ""+i;
            seatAvailability.put("IO" + sn, true);
        }
    }
    
     public void printSchedule() {
        SimpleDateFormat SDFormat = new SimpleDateFormat("MMMM dd, yyyy HH:mm:ss");
        
        String current = SDFormat.format(departureSchedule.getTime());
        
        System.out.println("Tanggal keberangkatan: " + current + "\nDaftar kursi:");
        
        int maxSeatsPerRow = 4; // Assuming there are 4 seats per row
        int currentSeat = 1;
        
        for (String seat : this.seatAvailability.keySet()) {
            String symbol = this.seatAvailability.get(seat) ? "O" : "X";
            
            System.out.print(seat + " : " + symbol + "\t");
            if (currentSeat % maxSeatsPerRow == 0) {
                System.out.println();
            }
            
            currentSeat++;
        }
        System.out.println("\n");
    }
    
    public boolean isSeatAvailable(String seat) {
        return seatAvailability.containsKey(seat) && seatAvailability.get(seat);
    }

    public boolean isSeatAvailable(List<String> seats) {
        for (String seat : seats) {
            if (!isSeatAvailable(seat))
                return false;
        }

        return true;
    }
    
    public void bookSeat(String seat) {
        if (isSeatAvailable(seat)) {
            seatAvailability.put(seat, false);
        }
    }

    public void bookSeat(List<String> seats) {
        for (String seat : seats) {
            if (isSeatAvailable(seat))
                seatAvailability.put(seat, false);
        }
    }

    public String toString() {
        int occupied = Collections.frequency(seatAvailability.values(), false);

        return "Schedule: " + departureSchedule + "\nOccupied: " + occupied + "/" + seatAvailability.size() + "\n";
    }
}
