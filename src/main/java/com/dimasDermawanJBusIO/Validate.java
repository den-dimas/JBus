package com.dimasDermawanJBusIO;

import java.util.ArrayList;

public class Validate {
    public static ArrayList filter(Price[] list, int value, boolean less) {
        ArrayList<Double> temp = new ArrayList<Double>();
        
        for (Price price : list) {
            if (less && price.price <= value) {
                temp.add(price.price);
            } 
            
            if (!less && price.price > value) {
                temp.add(price.price);
            }
        }
        
        return temp;
    }
}
