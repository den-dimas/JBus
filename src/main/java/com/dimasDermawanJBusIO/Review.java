package com.dimasDermawanJBusIO;

import com.dimasDermawanJBusIO.dbjson.Serializable;

public class Review extends Serializable {
    public String date;
    public String desc;
    
    public Review(String date, String desc) {
        this.date = date;
        this.desc = desc;
    }
    
    public String toString() {
        return "Review ID : " + super.id + "\nDate : " + date + "\nDescription : " + desc + "\n";
    }
}
