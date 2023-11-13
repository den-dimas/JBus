package com.dimasDermawanJBusIO;

import com.dimasDermawanJBusIO.dbjson.Serializable;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.sql.Timestamp;

public class Bus extends Serializable {
    public String name;
    public Station departure;
    public Station arrival;
    public Price price;
    public List<Facility> facility;
    public int capacity;
    public int accountId;
    public BusType busType;
    public List<Schedule> schedules;
    
    public Bus(String name, List<Facility> facility, Price price, int capacity, BusType busType, Station departure, Station arrival) {
        this.name = name;
        this.facility = facility;
        this.price = price;
        this.capacity = capacity;
        this.busType = busType;
        this.departure = departure;
        this.arrival = arrival;
        
        this.schedules = new ArrayList<Schedule>();
    }
    
    public String toString() {
        return
            "Bus ID : " + super.id +
            "\nNama Bus : " + name +
            "\nBerangkat : " + departure.city +
            "\nTujuan : " + arrival.city +
            "\nHarga : " + price.price +
            "\nFasilitas : " + facility +
            "\nKapasitas: " + capacity +
            "\nTipe Bus : " + busType;
    }

    public void addSchedule(Timestamp time) throws IOException {
        if (!schedules.contains(new Schedule(time, this.capacity)))
            schedules.add(new Schedule(time, this.capacity));
        else
            throw new IOException();
    }
}
