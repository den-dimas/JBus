package com.dimasDermawanJBusIO;

public class Station extends Serializable {
    public String address;
    public String stationName;
    public City city;
    
    public Station(String stationName, City city, String address) {
        this.address = address;
        this.stationName = stationName;
        this.city = city;
    }
    
    public String toString() {
        return "ID Stasiun : " + super.id + "\nNama Stasiun : " + stationName + "\nNama Kota : " + city + "\nNama Jalan : " + address + "\n";
    }
}
