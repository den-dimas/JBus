package dimasDermawanJBusIO;

import java.io.*;
import java.sql.Timestamp;

public class JBus {   
    public static void main(String[] args) {
        Bus b = createBus();
        
        Timestamp schedule1 = Timestamp.valueOf("2023-7-18 15:00:00");
        Timestamp schedule2 = Timestamp.valueOf("2023-7-20 12:00:00");
        
        b.addSchedule(schedule1);
        b.addSchedule(schedule2);
        
        b.schedules.forEach(Schedule::printSchedule);
        
        // Invalid date
        Timestamp t1 = Timestamp.valueOf("2023-7-19 15:00:00");
        
        System.out.println("Make booking at July 19, 2023 15:00:00 Seat IO12");
        System.out.println(Payment.makeBooking(t1, "IO12", b));
        
        // Valid Date, Invalid Seat
        Timestamp t2 = Timestamp.valueOf("2023-7-18 15:00:00");
        System.out.println("Make booking at July 18, 2023 15:00:00 Seat IO20");
        System.out.println(Payment.makeBooking(t2, "IO20", b));
        
        // Valid Date, Valid Seat
        System.out.println("Make booking at July 18, 2023 15:00:00 Seat IO07");
        System.out.println(Payment.makeBooking(t2, "IO07", b));
        
        Timestamp t3 = Timestamp.valueOf("2023-7-20 12:00:00");
        System.out.println("Make booking at July 20, 2023 12:00:00 Seat IO01");
        System.out.println(Payment.makeBooking(t3, "IO01", b));
        
        System.out.println("Make booking at July 20, 2023 12:00:00 Seat IO01 again");
        System.out.println(Payment.makeBooking(t3, "IO01", b));
        
        System.out.println("\nUpdated Schedules\n");
        b.schedules.forEach(Schedule::printSchedule);
    }
    
    public static Bus createBus() {
        Price price = new Price(10000, 0);
        Station depok = new Station(2, "DEPOK", City.DEPOK, "DEPOK");
        Station jakarta = new Station(3, "JAKARTA", City.JAKARTA, "JAKARTA");
        
        Bus bus = new Bus(1, "Dermawan's Bus", Facility.AC, price, 12, BusType.REGULER, City.DEPOK, depok, jakarta);

        return bus;
    }
}
