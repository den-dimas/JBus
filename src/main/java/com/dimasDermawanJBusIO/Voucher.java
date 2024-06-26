package com.dimasDermawanJBusIO;

import com.dimasDermawanJBusIO.dbjson.Serializable;

public class Voucher extends Serializable {
    private boolean used;
    
    public String name;
    public double minimum;
    public double cut;
    public int code;
    public Type type;
    
    public Voucher(String name, int code, Type type, double minimum, double cut) {
        this.name = name;
        this.code = code;
        this.type = type;
        this.minimum = minimum;
        this.cut = cut;
        
        this.used = false;
    }
    
    public boolean isUsed() {
        return this.used;
    }
    
    public boolean canApply(Price price) {
        return price.price > this.minimum && this.used == false;
    }
    
    public double apply(Price price) {
        if (this.cut < 0.0) {
            return price.price;
        }
        
        this.used = true;
        
        switch(type) {
            case DISCOUNT:
                if (this.cut > 100.0) {
                    return 0.0;
                } else {
                    return price.price * (1 - (this.cut / 100.0));   
                }
            case REBATE:
                if (this.cut > price.price) {
                    return 0.0;
                } else {
                    return price.price - this.cut;   
                }
            default:
                return price.price;
        }
    }

}
