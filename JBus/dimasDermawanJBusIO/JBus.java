package dimasDermawanJBusIO;

import java.io.*;

public class JBus
{   
    public static void main(String[] args) {
        Bus testBus = createBus();
        
        System.out.println(testBus.name);
        System.out.println(testBus.facility);
        System.out.println(testBus.price.price);
        System.out.println(testBus.capacity);
    }

    public static Bus createBus() {
        Price price = new Price(100000, 50000);
        Bus bus = new Bus("Dermawan's Bus", Facility.AC, price, 56);
        
        return bus;
    }
}
