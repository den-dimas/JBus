package com.dimasDermawanJBusIO;

public class Price {
    // Fields
    public double rebate;
    public double price;
    
    public Price(double price) {
        this.price = price;
        this.rebate = 0.0;
    }
    
    public Price(double price, double rebate) {
        this.price = price;
        this.rebate = rebate;
    }
    
    public String toString() {
        return "Harga : " + price + "\nPotongan : " + rebate + "\n";
    }
}
