package dimasDermawanJBusIO;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JBus {
    public static void main(String[] args) {
        // PT Modul 5
        // Tes Pagination
        Bus b = createBus();
        List<Timestamp> listOfSchedules = new ArrayList<>();
        listOfSchedules.add(Timestamp.valueOf("2023-7-18 15:00:00"));
        listOfSchedules.add(Timestamp.valueOf("2023-7-20 12:00:00"));
        listOfSchedules.add(Timestamp.valueOf("2023-7-22 10:00:00"));
        listOfSchedules.add(Timestamp.valueOf("2023-7-26 12:00:00"));

        listOfSchedules.add(Timestamp.valueOf("2023-7-19 15:00:00"));
        listOfSchedules.add(Timestamp.valueOf("2023-7-21 12:00:00"));
        listOfSchedules.add(Timestamp.valueOf("2023-7-23 10:00:00"));
        listOfSchedules.add(Timestamp.valueOf("2023-7-27 12:00:00"));

        listOfSchedules.add(Timestamp.valueOf("2023-7-28 15:00:00"));
        listOfSchedules.add(Timestamp.valueOf("2023-7-29 12:00:00"));
        listOfSchedules.add(Timestamp.valueOf("2023-7-30 10:00:00"));
        listOfSchedules.add(Timestamp.valueOf("2023-7-31 12:00:00"));

        listOfSchedules.forEach(b::addSchedule);
        // 1 - 3
        System.out.println("Page 1");
        Algorithm.paginate(b.schedules, 0, 3, t -> true).forEach(System.out::println);
        System.out.println("=====================================================");

        // 6 - 10
        System.out.println("Page 2");
        Algorithm.paginate(b.schedules, 1, 5, t -> true).forEach(System.out::println);
        System.out.println("=====================================================");

        // Tes Booking
        String msgSuccess = "Booking Success!";
        String msgFailed = "Booking Failed";

        // valid date, invalid seat = Booking Failed
        Timestamp t1 = Timestamp.valueOf("2023-7-19 15:00:00");
        System.out.println("\nMake booking at July 19, 2023 15:00:00 Seats: S17 S18");
        System.out.println(Payment.makeBooking(t1, List.of("IO17", "IO18"), b)? msgSuccess : msgFailed);

        // valid date, invalid seat = Booking Failed
        Timestamp t2 = Timestamp.valueOf("2023-7-18 15:00:00");
        System.out.println("Make booking at July 18, 2023 15:00:00 Seat IO26");
        System.out.println(Payment.makeBooking(t2, "IO26", b)? msgSuccess : msgFailed);

        // valid date, valid seat = Booking Success
        System.out.println("Make booking at July 18, 2023 15:00:00 Seats: IO07 IO08");
        System.out.println(Payment.makeBooking(t2, List.of("IO07", "IO08"), b)? msgSuccess : msgFailed);

        // valid date, valid seat = Booking Success
        Timestamp t3 = Timestamp.valueOf("2023-7-20 12:00:00");
        System.out.println("Make booking at July 20, 2023 12:00:00 Seats: IO01 IO02");
        System.out.println(Payment.makeBooking(t3, List.of("IO01", "IO02"), b)? msgSuccess : msgFailed);

        // valid date, book the same seat = Booking Failed
        System.out.println("Make booking at July 20, 2023 12:00:00 Seat IO01");
        System.out.println(Payment.makeBooking(t3, "IO01", b)? msgSuccess : msgFailed);

        // check if the data changed
        System.out.println("\nUpdated Schedule");
        Algorithm.paginate(b.schedules, 0, 4, t-> true).forEach(System.out::println);

        /*Integer[] numbers = {18, 10, 22, 43, 18, 67, 12, 11, 88, 22, 18};
        System.out.println("Number "+Arrays.toString(numbers));

        // Tes Algorithm
        System.out.print("1. ");
        testCount(numbers);
        System.out.print("2. ");
        testFind(numbers);
        System.out.print("3. ");
        testExist(numbers);
        System.out.println("4. Filtering");
        testCollect(numbers);*/
    }

    /*private static void testExist(Integer[] t) {
        int valueToCheck = 67;
        boolean result3 = Algorithm.exists(t, valueToCheck);
        if (result3) {
            System.out.println(valueToCheck + " exist in the array.");
        } else {
            System.out.println(valueToCheck + " doesn't exists in the array.");
        }
    }
    public static void testCount(Integer[] t) {
        int valueToCount = 18;
        int result = Algorithm.count(t, valueToCount);
        System.out.println("Number " + valueToCount + " appears " + result + " times");
    }
    public static void testFind(Integer[] t) {
        Integer valueToFind = 69;
        Integer result2 = Algorithm.find(t, valueToFind);
        System.out.print("Finding " + valueToFind + " inside the array : ");
        if (result2 != null) {
            System.out.println("Found!" + result2);
        } else {
            System.out.println("Not Found");
        }
    }
    private static void testCollect(Integer[] t) {
        Predicate<Integer> below = (val)->val<=22;
        Predicate<Integer> above = (val)->val>43;

        List<Integer> integerBelow = Algorithm.paginate(t, below);
        List<Integer> integerAbove = Algorithm.paginate(t, above);

        System.out.println("Below 22");
        System.out.println(integerBelow);
        System.out.println("Above 43");
        System.out.println(integerAbove);
    }*/
    
    public static Bus createBus() {
        Price price = new Price(10000, 0);
        Station depok = new Station("DEPOK", City.DEPOK, "DEPOK");
        Station jakarta = new Station( "JAKARTA", City.JAKARTA, "JAKARTA");

        return new Bus("Dermawan's Bus", Facility.AC, price, 25, BusType.REGULER, City.DEPOK, depok, jakarta);
    }
}
