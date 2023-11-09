package com.dimasDermawanJBusIO;

import java.sql.Timestamp;

public class BookingThread extends Thread {
    private Timestamp timestamp;
    private Bus bus;

    public BookingThread(String name, Bus bus, Timestamp timestamp) {
        super(name);
        this.bus = bus;
        this.timestamp = timestamp;
        this.start();
    }

    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " ID: " + Thread.currentThread().getId() + " is running");

            synchronized (bus) {
                if (Payment.makeBooking(timestamp, "IO01", bus)) {
                    System.out.println(Thread.currentThread().getName() + " berhasil melakukan booking");
                } else {
                    System.out.println(Thread.currentThread().getName() + " gagal melakukan booking");
                }
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
