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
        Price price = new Price(0, 0);
        Bus bus = new Bus("Dermawan's Bus", Facility.AC, price, 56);
        
        return bus;
    }
    
    public static int getBusId() {
        return 0;
    }
    
    public static String getBusName() {
        return "Bus";
    }
    
    public static boolean isDiscount() {
        return true;
    }
    
    public static float getDiscountPercentage(int beforeDiscount, int afterDiscount) {
        if (beforeDiscount < afterDiscount) {
            return 0.0f;
        }
        else if (beforeDiscount == 0) {
            return 0.0f;
        }
        else {
            return ((beforeDiscount - afterDiscount) / (float) beforeDiscount) * 100;
        }
    }
    
    public static int getDiscountedPrice(int price, float discountPercentage) {
        if (discountPercentage > 100) {
            return 0;
        } else {
            return (int) ((1 - (discountPercentage / 100)) * price);   
        }
    }
    
    public static int getOriginalPrice(int discountedPrice, float discountPercentage) {
        return (int) (discountedPrice / (1 - (discountPercentage / 100)));
    }
    
    public static float getAdminFeePercentage() {
        return 0.05f;
    }
    
    public static int getAdminFee(int price) {
        return (int) (price * getAdminFeePercentage());
    }
    
    public static int getTotalPrice(int price, int numberOfSeat) {
        return price * numberOfSeat + getAdminFee(price * numberOfSeat);
    }
}
