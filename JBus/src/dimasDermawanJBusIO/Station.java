package dimasDermawanJBusIO;

public class Station extends Serializable {
    public String address;
    public String stationName;
    public City city;
    
    public Station(int id, String stationName, City city, String address) {
        super(id);
        
        this.address = address;
        this.stationName = stationName;
        this.city = city;
    }
    
    public String toString() {
        return "ID Stasiun : " + super.id + "\nNama Stasiun : " + stationName + "\nNama Kota : " + city + "\nNama Jalan : " + address + "\n";
    }
}
