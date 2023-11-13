package com.dimasDermawanJBusIO;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import com.dimasDermawanJBusIO.dbjson.JsonDBEngine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JBus {
    public static void main(String[] args) throws InterruptedException {
        JsonDBEngine.Run(JBus.class);
        SpringApplication.run(JBus.class, args);
        Runtime.getRuntime().addShutdownHook(new Thread(JsonDBEngine::join));
    }

    public static Bus createBus() {
        Price price = new Price(750000, 5);
        List<Facility> facilities = Arrays.asList(Facility.AC, Facility.WIFI);

        Bus bus = new Bus("Netlab Bus", facilities, price, 25, BusType.REGULER, new Station("Depok Terminal", City.DEPOK, "Jl. Margonda Raya"), new Station("Halte UI", City.JAKARTA, "Universitas Indonesia"));
        Timestamp timestamp = Timestamp.valueOf("2023-07-27 19:00:00");

        try {
            bus.addSchedule(timestamp);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bus;
    }
}
