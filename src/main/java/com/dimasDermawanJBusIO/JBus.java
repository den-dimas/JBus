package com.dimasDermawanJBusIO;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JBus {
    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(JBus.class, args);

//        JsonTable<Account> akuns = null;
//
//        try {
//            String filepath = "C:\\Users\\Den\\Documents\\_perkuliahan\\Semester 3\\Pemrograman Berorientasi Objek dan Praktikum\\Praktikum\\JBus\\data\\accountDatabase.json";
//
//            akuns = new JsonTable<Account>(Account.class, filepath);
//
//            akuns.add(new Account("Dimas", "den@gmail.com", "NgikNgok"));
//
//            JsonTable.writeJson(akuns, filepath);
//        } catch (Throwable t) {
//            t.printStackTrace();
//        }
//
//        Bus bus = createBus();
//        bus.schedules.forEach(Schedule::printSchedule);
//
//        for (int i = 0; i < 10; i++) {
//            BookingThread thread = new BookingThread("Thread " + i,
//                    bus, Timestamp.valueOf("2023-07-27 19:00:00"));
//        }
//
//        try {
//            Thread.sleep(1000);
//        } catch (Throwable t) {
//            t.printStackTrace();
//        }
//
//        bus.schedules.forEach(Schedule::printSchedule);
    }

    public static Bus createBus() {
        Price price = new Price(750000, 5);
        Bus bus = new Bus("Netlab Bus", Facility.LUNCH, price, 25, BusType.REGULER, City.BANDUNG, new Station("Depok Terminal", City.DEPOK, "Jl. Margonda Raya"), new Station("Halte UI", City.JAKARTA, "Universitas Indonesia"));
        Timestamp timestamp = Timestamp.valueOf("2023-07-27 19:00:00");

        try {
            bus.addSchedule(timestamp);
        } catch (Throwable t) {
            t.printStackTrace();
        }

        return bus;
    }

//    public static List<Bus> filterByDeparture(List<Bus> buses, City departure, int page, int pageSize) {
//        return Algorithm.paginate(Algorithm.collect(buses.toArray(new Bus[0]), bus -> bus.city == departure), page, pageSize, t -> true);
//    }
//
//    public static List<Bus> filterByPrice(List<Bus> buses, int min, int max) {
//        return Algorithm.collect(buses.toArray(new Bus[0]), bus -> bus.price.price >= min && bus.price.price <= max);
//    }
//
//    public static Bus filterBusId(List<Bus> buses, int id) {
//        return Algorithm.find(buses.toArray(new Bus[0]), bus -> bus.id == id);
//    }
//
//    public static List<Bus> filterByDepartureAndArrival(List<Bus> buses, City departure, City arrival, int page, int pageSize) {
//        return Algorithm.paginate(Algorithm.collect(buses.toArray(new Bus[0]), bus -> bus.departure.city == departure && bus.arrival.city == arrival), page, pageSize, t -> true);
//    }
}
