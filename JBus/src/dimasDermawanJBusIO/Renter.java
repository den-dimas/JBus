package dimasDermawanJBusIO;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Renter extends Serializable {
    public String address;
    public String companyName;
    public int phoneNumber;

    private final String REGEX_PHONE = "([0-9])\\d{9,12}";
    private final String REGEX_NAME = "^[A-Z][a-zA-Z0-9_]\\w{4,20}";
    
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

    public boolean validate() {
        Pattern patName = Pattern.compile(REGEX_NAME);
        Pattern patPhone = Pattern.compile(REGEX_PHONE);

        if (patName.matcher(companyName).find() && patPhone.matcher(Integer.toString(phoneNumber)).find()) {
            return true;
        } else {
            return false;
        }
    }
}
