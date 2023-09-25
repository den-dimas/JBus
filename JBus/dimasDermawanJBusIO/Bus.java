package dimasDermawanJBusIO;

public class Bus extends Serializable {
    public String name;
    public Station departure;
    public Station arrival;
    public Price price;
    public Facility facility;    
    public int capacity;
    public BusType busType;
    public City city;
    
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
    }
    
    public String toString() {
        return "Bus ID : " + super.id + "\nNama Bus : " + name + "\nBerangkat : " + departure.city + "\nTujuan : " + arrival.city + "\nHarga : " + price.price + "\nFasilitas : " + facility + "\nKapasitas: " + capacity + "\nTipe Bus : " + busType + "\nKota : " + city + "\n";
    }
}
