package dimasDermawanJBusIO;

import java.util.List;

public class JBus {
    public static void main(String[] args) {
        JsonTable<Bus> busList = null;

        try {
            String filepath = "C:\\Users\\Den\\Documents\\_perkuliahan\\Semester 3\\Pemrograman Berorientasi Objek dan Praktikum\\Praktikum\\JBus\\data\\buses_CS.json";
            busList = new JsonTable<>(Bus.class, filepath);
        } catch (Throwable t) {
            t.printStackTrace();
        }

//        List<Bus> filteredBus = filterByDeparture(busList, City.JAKARTA, 0, 3);
//        filteredBus.forEach(bus -> System.out.println(bus.toString()));

//        List<Bus> priceBus = filterByPrice(busList, 100000, 500000);
//        priceBus.forEach(bus -> System.out.println(bus.toString()));

//        Bus busId = filterBusId(busList, 155);
//        System.out.println(busId.toString());

        List<Bus> depArrBus = filterByDepartureAndArrival(busList, City.JAKARTA, City.SURABAYA, 0, 3);
        depArrBus.forEach(bus -> System.out.println(bus.toString()));
    }

    public static Bus createBus() {
        Price price = new Price(10000, 0);
        Station depok = new Station("DEPOK", City.DEPOK, "DEPOK");
        Station jakarta = new Station( "JAKARTA", City.JAKARTA, "JAKARTA");

        return new Bus("Dermawan's Bus", Facility.AC, price, 25, BusType.REGULER, City.DEPOK, depok, jakarta);
    }

    public static List<Bus> filterByDeparture(List<Bus> buses, City departure, int page, int pageSize) {
        return Algorithm.paginate(Algorithm.collect(buses.toArray(new Bus[0]), bus -> bus.city == departure), page, pageSize, t -> true);
    }

    public static List<Bus> filterByPrice(List<Bus> buses, int min, int max) {
        return Algorithm.collect(buses.toArray(new Bus[0]), bus -> bus.price.price >= min && bus.price.price <= max);
    }

    public static Bus filterBusId(List<Bus> buses, int id) {
        return Algorithm.find(buses.toArray(new Bus[0]), bus -> bus.id == id);
    }

    public static List<Bus> filterByDepartureAndArrival(List<Bus> buses, City departure, City arrival, int page, int pageSize) {
        return Algorithm.paginate(Algorithm.collect(buses.toArray(new Bus[0]), bus -> bus.departure.city == departure && bus.arrival.city == arrival), page, pageSize, t -> true);
    }
}
