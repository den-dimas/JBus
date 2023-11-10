package com.dimasDermawanJBusIO.controller;

import com.dimasDermawanJBusIO.Account;
import com.dimasDermawanJBusIO.Algorithm;
import com.dimasDermawanJBusIO.Renter;
import com.dimasDermawanJBusIO.dbjson.JsonAutowired;
import com.dimasDermawanJBusIO.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@RestController
@RequestMapping("/account")
public class AccountController implements BasicGetController<Account> {
    @JsonAutowired(value = Account.class, filepath = "../../../data/accountDatabase.json")
    public static JsonTable<Account> accountTable;

    public JsonTable<Account> getJsonTable() {
        return accountTable;
    }

    @GetMapping
    String index() { return "account page"; }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    BaseResponse<Account> register (@RequestParam String name, @RequestParam String email, @RequestParam String password) {
        Account akun = new Account(name, email, password);

        if (akun.name.isBlank() || !akun.validate() || Algorithm.<Account>exists(accountTable, t -> Objects.equals(t.email, akun.email)))
            return new BaseResponse<>(false, "Gagal register", null);
        else {
//        try {
//            MessageDigest md = MessageDigest.getInstance("MD5");
//
//            md.update(akun.password.getBytes());
//
//            byte[] bytes = md.digest();
//
//            StringBuilder sb= new StringBuilder();
//
//            for (byte aByte : bytes) {
//                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
//            }
//
//            akun.password = sb.toString();
//        } catch (NoSuchAlgorithmException e) {
//            throw new RuntimeException(e);
//        }

            return new BaseResponse<>(true, "Berhasil register", akun);
        }
    }

    @PostMapping("/login")
    BaseResponse<Account> login(@RequestParam String email, @RequestParam String password) {
        Account akun = Algorithm.<Account>find(accountTable, t -> Objects.equals(t.email, email));

        String hashedPass = password;

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");

            md.update(hashedPass.getBytes());

            byte[] bytes = md.digest();

            StringBuilder sb= new StringBuilder();

            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }

            hashedPass = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        if (Objects.equals(akun.password, hashedPass))
            return new BaseResponse<>(true, "Berhasil Login", akun);
        else
            return new BaseResponse<>(false, "Gagal login", null);
    }

    @PostMapping("/{id}/registerRenter")
    BaseResponse<Renter> registerRenter(@PathVariable int id, @RequestParam String companyName, @RequestParam String address, @RequestParam String phoneNumber) {
        Account akun = Algorithm.<Account>find(getJsonTable(), t -> t.id == id);

        if (akun.company == null) {
            Renter renter = new Renter(companyName, address, phoneNumber);
            akun.company = renter;

            return new BaseResponse<>(true, "Berhasil mendaftarkan renter baru", renter);
        } else {
            return new BaseResponse<>(false, "Gagal mendaftarkan renter baru", null);
        }
    }

    @PostMapping("/{id}/topUp")
    BaseResponse<Double> topUp(@PathVariable int id, @RequestParam double amount) {
        Account akun = Algorithm.<Account>find(getJsonTable(), t -> t.id == id);

        if (akun != null && amount > 0.0) {
            akun.balance += amount;

            return new BaseResponse<>(true, "Berhasil top up", amount);
        } else {
            return new BaseResponse<>(false, "Gagal top up", 0.0);
        }
    }

//    @GetMapping("/{id}")
//    String getById(@PathVariable int id) { return "account id " + id + " not found!"; }
}
