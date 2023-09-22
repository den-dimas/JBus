package dimasDermawanJBusIO;

public class Renter extends Serializable {
    public String address;
    public String companyName;
    public int phoneNumber;
    
    public Renter(int id, String companyName) {
        super(id);
        
        this.companyName = companyName;
        this.phoneNumber = 0;
        this.address = "";
    }
    
    public Renter(int id, String companyName, String address) {
        super(id);
        
        this.companyName = companyName;
        this.phoneNumber = 0;
        this.address = address;
    }
    
    public Renter(int id, String companyName, int phoneNumber) {
        super(id);
        
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.address = "";
    }
    
    public Renter(int id, String companyName, int phoneNumber, String address) {
        super(id);
        
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
}
