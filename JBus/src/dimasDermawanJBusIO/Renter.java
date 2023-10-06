package dimasDermawanJBusIO;

public class Renter extends Serializable {
    public String address;
    public String companyName;
    public int phoneNumber;
    
    public Renter(String companyName) {
        this.companyName = companyName;
        this.phoneNumber = 0;
        this.address = "";
    }
    
    public Renter(String companyName, String address) {
        this.companyName = companyName;
        this.phoneNumber = 0;
        this.address = address;
    }
    
    public Renter(String companyName, int phoneNumber) {
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.address = "";
    }
    
    public Renter(int id, String companyName, int phoneNumber, String address) {
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
}
