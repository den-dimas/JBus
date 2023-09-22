package dimasDermawanJBusIO;

public class Invoice extends Serializable {
    public String time;
    public int buyerId;
    public int renterId;
    
    protected Invoice(int id, int buyerId, int renterId, String time) {
        super(id);
        
        this.buyerId = buyerId;
        this.renterId = renterId;
        this.time = time;
    }
    
    public Invoice(int id, Account buyer, Renter renter, String time) {
        super(id);
        
        this.buyerId = buyer.id;
        this.renterId = renter.id;
        this.time = time;
    }
    
    public String print() {
        return "Nomor Faktur : " + this.id + "\nID Pembeli : " + this.buyerId + "\nID Penyewa : " + this.renterId + "\nWaktu Sewa: " + this.time + "\n";
    }
}
