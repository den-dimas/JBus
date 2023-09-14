package dimasDermawanJBusIO;

public class Price
{
    // Fields
    public double rebate;
    public double price;
    public int discount;
    
    public Price(double price) {
        price = price;
        rebate = 0.0;
        discount = 0;
    }
    
    public Price(double price, int discount) {
        price = price;
        discount = discount;
        rebate = 0.0;
    }
    
    public Price(double price, double rebate) {
        price = price;
        rebate = rebate;
        discount = 0;
    }
}
