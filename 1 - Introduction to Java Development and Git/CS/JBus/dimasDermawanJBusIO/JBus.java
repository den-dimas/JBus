package dimasDermawanJBusIO;

import java.io.*;

public class JBus
{   
    public static void main(String[] args) {
        System.out.println("Bus ID : " + getBusId());
        System.out.println("Nama Bus : " + getBusName());
        System.out.println("Discount? : " + isDiscount());
        System.out.println("Besar diskon : " + getDiscountPercentage(1000, 0));
        System.out.println("Harga setelah diskon : " + getDiscountedPrice(1000, 100.0f));
        System.out.println("Harga asli : " + getOriginalPrice(1000, 0.0f));
        System.out.println("Biaya admin (%): " + getAdminFeePercentage());
        System.out.println("Biaya admin : " + getAdminFee(500));
        System.out.println("Total harga : " + getTotalPrice(5000, 1));
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
        return price * numberOfSeat + getAdminFee(price);
    }
}
