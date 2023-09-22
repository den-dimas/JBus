package dimasDermawanJBusIO;

public class Account extends Serializable {
    public String email;
    public String name;
    public String password;
    
    /**
     * Melakukan pembuatan akun.
     * 
     * @param id ID akun
     * @param name Nama akun
     * @param email Email akun
     * @param password Password akun
     */
    public Account(int id, String name, String email, String password) {
        super(id);
        
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
