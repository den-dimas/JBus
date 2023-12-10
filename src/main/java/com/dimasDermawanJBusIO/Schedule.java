package com.dimasDermawanJBusIO;

import java.util.*;
import java.sql.Timestamp;
import java.text.*;

/**
 * Represents a bus schedule with seat availability information.
 *
 * @author Dimas Dermawan
 */
public class Schedule {
    /**
     * The timestamp of the departure schedule.
     */
    public Timestamp departureSchedule;
    /**
     * Map representing seat availability. Key is the seat number, and value is true if the seat is available, false otherwise.
     */
    public Map<String, Boolean> seatAvailability;

    /**
     * Initializes a new Schedule instance.
     *
     * @param departureSchedule The timestamp of the departure schedule.
     * @param numberOfSeats     The number of seats in the bus.
     */
    public Schedule(Timestamp departureSchedule, int numberOfSeats) {
        this.departureSchedule = departureSchedule;
        this.initializeSeatAvailability(numberOfSeats);
    }

    /**
     * Initializes seat availability based on the total number of seats.
     *
     * @param numberOfSeats The total number of seats.
     */
    private void initializeSeatAvailability(int numberOfSeats) {
        seatAvailability = new LinkedHashMap<>();
        
        for (int i = 1; i <= numberOfSeats; i++) {
            String sn = i < 10 ? "0"+i : ""+i;
            seatAvailability.put("IO" + sn, true);
        }
    }

    /**
     * Checks if all seats are occupied.
     *
     * @return True if all seats are occupied, false otherwise.
     */
    public boolean isFull() {
        return !seatAvailability.containsValue(true);
    }

    /**
     * Checks if a specific seat is available.
     *
     * @param seat The seat number.
     * @return True if the seat is available, false otherwise.
     */
    public boolean isSeatAvailable(String seat) {
        return seatAvailability.containsKey(seat) && seatAvailability.get(seat);
    }

    /**
     * Checks if a list of seats are available.
     *
     * @param seats The list of seat numbers.
     * @return True if all seats are available, false if any seat is not available.
     */
    public boolean isSeatAvailable(List<String> seats) {
        for (String seat : seats) {
            if (!isSeatAvailable(seat))
                return false;
        }

        return true;
    }

    /**
     * Books a specific seat if it is available.
     *
     * @param seat The seat number to book.
     */
    public void bookSeat(String seat) {
        if (isSeatAvailable(seat)) {
            seatAvailability.put(seat, false);
        }
    }

    /**
     * Books a list of seats if they are available.
     *
     * @param seats The list of seat numbers to book.
     */
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
