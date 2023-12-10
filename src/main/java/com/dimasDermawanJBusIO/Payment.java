package com.dimasDermawanJBusIO;
import java.sql.Time;
import java.text.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * Represents a payment for a bus booking, extending the functionality of an Invoice.
 * Includes details such as the bus ID, booked seats, and departure date.
 *
 * @author Dimas Dermawan
 */
public class Payment extends Invoice {

    /**
     * The ID of the bus associated with the payment.
     */
    public int busId;

    /**
     * The list of booked bus seats.
     */
    public List<String> busSeat;

    /**
     * The timestamp of the departure date.
     */
    public Timestamp departureDate;

    /**
     * Constructs a Payment instance with buyer ID, renter ID, bus ID, booked seats, and departure date.
     *
     * @param buyerId        The ID of the buyer.
     * @param renterId       The ID of the renter.
     * @param busId          The ID of the bus.
     * @param busSeat        The list of booked seats.
     * @param departureDate  The timestamp of the departure date.
     */
    public Payment(int buyerId, int renterId, int busId, List<String> busSeat, Timestamp departureDate) {
        super(buyerId, renterId);

        this.busId = busId;
        this.busSeat = busSeat;
        this.departureDate = departureDate;
    }

    /**
     * Constructs a Payment instance with buyer account, renter account, bus ID, booked seats, and departure date.
     *
     * @param buyer          The buyer's account.
     * @param renter         The renter's account.
     * @param busId          The ID of the bus.
     * @param busSeat        The list of booked seats.
     * @param departureDate  The timestamp of the departure date.
     */
    public Payment(Account buyer, Account renter, int busId, List<String> busSeat, Timestamp departureDate) {
        super(buyer, renter);

        this.busId = busId;
        this.busSeat = busSeat;
        this.departureDate = departureDate;
    }

    /**
     * Finds an available schedule for a specific departure schedule and seat in a given bus.
     *
     * @param departureSchedule The timestamp of the departure schedule.
     * @param seat              The seat number.
     * @param bus               The bus instance.
     * @return The available schedule or null if not found.
     */
    public static Schedule availableSchedule(Timestamp departureSchedule, String seat, Bus bus) {
        for (Schedule s : bus.schedules) {
            if (departureSchedule.compareTo(s.departureSchedule) == 0 && s.isSeatAvailable(seat)) {
                return s;
            }
        }

        return null;
    }

    /**
     * Finds an available schedule for a specific departure schedule and list of seats in a given bus.
     *
     * @param departureSchedule The timestamp of the departure schedule.
     * @param seats             The list of seat numbers.
     * @param bus               The bus instance.
     * @return The available schedule or null if not found.
     */
    public static Schedule availableSchedule(Timestamp departureSchedule, List<String> seats, Bus bus) {
        for (Schedule s : bus.schedules) {
            if (departureSchedule.compareTo(s.departureSchedule) == 0 && s.isSeatAvailable(seats)) {
                return s;
            }
        }

        return null;
    }

    /**
     * Finds an available schedule for a specific departure schedule in a given bus.
     *
     * @param departureSchedule The timestamp of the departure schedule.
     * @param bus               The bus instance.
     * @return The available schedule or null if not found.
     */
    public static Schedule availableSchedule(Timestamp departureSchedule, Bus bus) {
        for (Schedule s : bus.schedules) {
            if (departureSchedule.equals(s.departureSchedule)) return s;
        }

        return null;
    }

    /**
     * Books a seat for a specific departure schedule in a given bus.
     *
     * @param departureSchedule The timestamp of the departure schedule.
     * @param seat              The seat number to book.
     * @param bus               The bus instance.
     * @return True if booking is successful, false otherwise.
     */
    public static boolean makeBooking(Timestamp departureSchedule, String seat, Bus bus) {
        for (Schedule schedule : bus.schedules) {
            if (departureSchedule.compareTo(schedule.departureSchedule) == 0 && schedule.isSeatAvailable(seat)) {
                schedule.bookSeat(seat);

                return true;
            }
        }

        return false;
    }

    /**
     * Books a list of seats for a specific departure schedule in a given bus.
     *
     * @param departureSchedule The timestamp of the departure schedule.
     * @param seats             The list of seat numbers to book.
     * @param bus               The bus instance.
     * @return True if booking is successful, false otherwise.
     */
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

