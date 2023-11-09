package com.dimasDermawanJBusIO;

public class Rating {
    // Fields
    private long count;
    private long total;
    
    public Rating() {
        this.count = 0;
        this.total = 0;
    }
    
    public void insert(int rating) {
        this.total += rating;
        this.count++;
    }
    
    public long getTotal() {
        return this.total;
    }
    
    public long getCount() {
        return this.count;
    }
    
    public double getAverage() {
        if (this.count == 0) {
            return 0.0;
        } else {
            return (double) (this.total / this.count);
        }
    }
    
    public String toString() {
        return "Rating's Count : " + getCount() + "\nTotal Ratings : " + getTotal() + "\n";
    }
}
