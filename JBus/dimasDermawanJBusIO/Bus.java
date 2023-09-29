package dimasDermawanJBusIO;

import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;
import java.text.*;

public class Bus extends Serializable implements FileParser {
    public String name;
    public Station departure;
    public Station arrival;
    public Price price;
    public Facility facility;    
    public int capacity;
    public BusType busType;
    public City city;
    public List<Schedule> schedules;
    
    public Bus(int id, String name, Facility facility, Price price, int capacity, BusType busType, City city, Station departure, Station arrival) {
        super(id);
        
        this.name = name;
        this.facility = facility;
        this.price = price;
        this.capacity = capacity;
        this.busType = busType;
        this.city = city;
        this.departure = departure;
        this.arrival = arrival;
        
        this.schedules = new ArrayList<Schedule>();
    }
    
    public String toString() {
        return "Bus ID : " + super.id + "\nNama Bus : " + name + "\nBerangkat : " + departure.city + "\nTujuan : " + arrival.city + "\nHarga : " + price.price + "\nFasilitas : " + facility + "\nKapasitas: " + capacity + "\nTipe Bus : " + busType + "\nKota : " + city + "\n";
    }
    
    public Object write() {
        return new int[10];
    }
    
    public boolean read(String string) {
        return true;
    }
    
    public void addSchedule(Calendar calendar) {
        Schedule schedule = new Schedule(calendar, capacity);
        
        schedules.add(schedule);
    }
    
    public void printSchedule(Schedule schedule) {
        SimpleDateFormat SDFormat = new SimpleDateFormat("MMMM dd, yyyy hh:mm:ss");
        
        String current = SDFormat.format(schedule.departureSchedule.getTime());
        
        System.out.println("Tanggal keberangkatan: " + current + "\nDaftar kursi:");
        
        int i = 0;
        for (Map.Entry<String, Boolean> map : schedule.seatAvailability.entrySet()) {        
            System.out.print(map.getKey() + " : " + map.getValue() + " ");
            i++;
            if (i == 4) {
                System.out.print("\n");
                i = 0;
            }
        }
        
        System.out.print("\n");
    }
}
