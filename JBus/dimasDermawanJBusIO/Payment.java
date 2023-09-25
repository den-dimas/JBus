package dimasDermawanJBusIO;

public class Payment extends Invoice {
    private int busId;
    
    public String departureDate;
    public String busSeat;
    
    public Payment(int id, int buyerId, int renterId, String time, int busId, String departureDate, String busSeat) {
        super(id, buyerId, renterId, time);
        
        this.busId = busId;
        this.departureDate = departureDate;
        this.busSeat = busSeat;
    }
    
    public Payment(int id, Account buyer, Renter renter, String time, int busId, String departureDate, String busSeat) {
        super(id, buyer, renter, time);
        
        this.busId = busId;
        this.departureDate = departureDate;
        this.busSeat = busSeat;
    }
    
    public String toString() {
        return "Payment ID : " + this.id + "\nBuyer ID : " + this.buyerId + "\nRenter ID : " + this.renterId + "\nTime : " + this.time + "\nBus ID : " + this.getBusId() + "\nTanggal Berangkat : " + this.departureDate + "\nKode Bangku : " + this.busSeat + "\n";
    }
    
    public int getBusId() {
        return this.busId;
    }
}
