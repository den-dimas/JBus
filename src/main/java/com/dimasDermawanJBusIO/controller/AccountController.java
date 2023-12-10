package com.dimasDermawanJBusIO.controller;

import com.dimasDermawanJBusIO.Account;
import com.dimasDermawanJBusIO.Algorithm;
import com.dimasDermawanJBusIO.Bus;
import com.dimasDermawanJBusIO.Renter;
import com.dimasDermawanJBusIO.dbjson.JsonAutowired;
import com.dimasDermawanJBusIO.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/account")
public class AccountController implements BasicGetController<Account> {
    public static @JsonAutowired(value = Account.class, filepath = "./data/accountDatabase.json")
    JsonTable<Account> accountTable;

    public JsonTable<Account> getJsonTable() {
        return accountTable;
    }

    @GetMapping
    String index() {
        return "account page";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    BaseResponse<Account> register(@RequestParam String name, @RequestParam String email, @RequestParam String password) {
        boolean valid = Account.validate(email, password);

        boolean emailValid = Account.validateEmail(email);
        boolean passwordValid = Account.validatePassword(password);

        if (!emailValid)
            return new BaseResponse<>(false, "Format email salah!", null);

        if (!passwordValid)
            return new BaseResponse<>(false, "Format password salah!", null);

        if (name.isBlank())
            return new BaseResponse<>(false, "Nama tidak boleh kosong!", null);

        if (Algorithm.<Account>exists(accountTable, t -> Objects.equals(t.email, email)))
            return new BaseResponse<>(false, "Terdapat akun dengan email yang sama!", null);

        Account akun = new Account(name, email, Algorithm.hashPassword(password));

        accountTable.add(akun);

        return new BaseResponse<>(true, "Berhasil register", akun);
    }

    @PostMapping("/login")
    BaseResponse<Account> login(@RequestParam String email, @RequestParam String password) {
        Account akun = Algorithm.<Account>find(accountTable, t -> Objects.equals(t.email, email));

        String hashed = Algorithm.hashPassword(password);

        if (akun == null)
            return new BaseResponse<>(false, "Akun tidak ditemukan!", null);

        if (!Objects.equals(akun.password, hashed))
            return new BaseResponse<>(false, "Password salah!", null);

        return new BaseResponse<>(true, "Berhasil Login", akun);
    }

    @PostMapping("/{id}/registerRenter")
    BaseResponse<Renter> registerRenter(@PathVariable int id, @RequestParam String companyName, @RequestParam String address, @RequestParam String phoneNumber) {
        Account akun = Algorithm.<Account>find(getJsonTable(), t -> t.id == id);
        int akunIndex = getJsonTable().indexOf(akun);

        if (akun.company == null) {
            Renter renter = new Renter(companyName, address, phoneNumber);

            if (!renter.validate()) return new BaseResponse<>(false, "Masukkan nama dan nomor telpon yang benar!", null);

            akun.company = renter;

            accountTable.set(akunIndex, akun);

            return new BaseResponse<>(true, "Berhasil mendaftarkan renter baru", renter);
        } else {
            return new BaseResponse<>(false, "Gagal mendaftarkan renter baru", null);
        }
    }

    @PostMapping("/{id}/topUp")
    BaseResponse<Double> topUp(@PathVariable int id, @RequestParam double amount) {
        Account akun = Algorithm.<Account>find(getJsonTable(), t -> t.id == id);
        int akunIndex = getJsonTable().indexOf(akun);

        if (akun == null)
            return new BaseResponse<>(false, "Akun tidak ditemukan untuk top up!", null);


        if (amount <= 0.0)
            return new BaseResponse<>(false, "Nominal Top Up tidak valid!", null);


        akun.balance += amount;

        accountTable.set(akunIndex, akun);

        return new BaseResponse<>(true, "Berhasil top up sebanyak " + amount, amount);
    }


    /**
     * @param id Account ID that needs to be edited.
     * @param name New name for the account.
     * @param email New email for the account.
     * @return Returns the payload and HTTP status for the request
     */
    @PostMapping("/{id}/editAccount")
    BaseResponse<Account> editAccount(@PathVariable int id, @RequestParam String name, @RequestParam String email) {
        Account akun = Algorithm.<Account>find(getJsonTable(), t -> t.id == id);
        int akunIndex = getJsonTable().indexOf(akun);

        boolean emailValid = Account.validateEmail(email);

        if (!emailValid)
            return new BaseResponse<>(false, "Format email salah!", null);

        if (name.isBlank())
            return new BaseResponse<>(false, "Nama tidak boleh kosong!", null);

        if (Algorithm.<Account>exists(accountTable, t -> Objects.equals(t.email, email) && !Objects.equals(email, akun.email)))
            return new BaseResponse<>(false, "Terdapat akun dengan email yang sama!", null);

        akun.name = name;
        akun.email = email;

        getJsonTable().set(akunIndex, akun);

        return new BaseResponse<>(true, "Berhasil mengubah akun", akun);
    }

    @PostMapping("/{id}/deleteAccount")
    BaseResponse<Boolean> deleteAccount(@PathVariable int id, @RequestParam String password) {
        Account akun = Algorithm.<Account>find(getJsonTable(), t -> t.id == id);

        if (akun == null) return new BaseResponse<>(false, "Akun tidak ditemukan!", false);
        if (!akun.password.equals(Algorithm.hashPassword(password))) return new BaseResponse<>(false, "Password tidak tepat!", false);
        if (Algorithm.<Bus>count(BusController.busTable, t -> t.accountId == akun.id) > 0) return new BaseResponse<>(false, "Anda masih memiliki bus!", false);

        getJsonTable().remove(akun);

        return new BaseResponse<>(true, "Berhasil menghapus akun", true);
    }
}
