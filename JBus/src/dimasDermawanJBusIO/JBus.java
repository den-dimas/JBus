package dimasDermawanJBusIO;

import java.util.List;

public class JBus {
    public static void main(String[] args) {
        try {
            String filepath = "C:\\Users\\Den\\Documents\\_perkuliahan\\Semester 3\\Pemrograman Berorientasi Objek dan Praktikum\\Praktikum\\JBus\\data\\buses.json";
            JsonTable<Bus> busList = new JsonTable<>(Bus.class, filepath);
            List<Bus> filteredBus = filterByDeparture(busList, City.JAKARTA, 1, 10);
            filteredBus.forEach(bus -> System.out.println(bus.toString()));
        } catch (Throwable t) {
            t.printStackTrace();
        }
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
}
