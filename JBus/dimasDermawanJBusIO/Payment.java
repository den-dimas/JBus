package dimasDermawanJBusIO;

public class Payment extends Serializable {
    private int busId;
    
    public String departureDate;
    public String busSeat;
    
    public Payment(int id, int buyerId, int renterId, String time, int busId, String departureDate, String busSeat) {
        super(id);
        
        this.busId = busId;
        this.departureDate = departureDate;
        this.busSeat = busSeat;
    }
    
    public Payment(int id, Account buyer, Renter renter, String time, int busId, String departureDate, String busSeat) {
        super(id);
        
        this.busId = busId;
        this.departureDate = departureDate;
        this.busSeat = busSeat;
    }
    
    public String print() {
        return "Bus ID : " + this.getBusId() + "\nTanggal Berangkat : " + this.departureDate + "\nKode Bangku : " + this.busSeat + "\n";
    }
    
    public int getBusId() {
        return this.busId;
    }
}
