package dimasDermawanJBusIO;

public class Account extends Serializable implements FileParser {
    public String email;
    public String name;
    public String password;
    
    /**
     * Untuk membuat akun.
     * 
     * @param id ID akun
     * @param name Nama akun
     * @param email Email akun
     * @param password Password akun
     */
    public Account(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
    
    public String toString() {
        return "ID Akun : " + super.id + "\nNama : " + name + "\nEmail : " + email + "\nPassword : " + password + "\n";
    }
    
    public Object write() {
        return new int[10];
    }
    
    public boolean read(String string) {
        return true;
    }
}
