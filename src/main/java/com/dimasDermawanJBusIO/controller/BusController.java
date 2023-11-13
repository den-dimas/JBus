package com.dimasDermawanJBusIO.controller;

import com.dimasDermawanJBusIO.*;
import com.dimasDermawanJBusIO.dbjson.JsonAutowired;
import com.dimasDermawanJBusIO.dbjson.JsonTable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/bus")
public class BusController implements BasicGetController<Bus> {
    public static @JsonAutowired(value = Bus.class, filepath = "data/busDatabase.json") JsonTable<Bus> busTable;
    @Override
    public JsonTable<Bus> getJsonTable() {
        return busTable;
    }

    @PostMapping("/create")
    public BaseResponse<Bus> createBus(
            @RequestParam int accountId,
            @RequestParam String name,
            @RequestParam int capacity,
            @RequestParam List<Facility> facilities,
            @RequestParam BusType busType,
            @RequestParam int price,
            @RequestParam int stationDepartureId,
            @RequestParam int stationArrivalId
    ) {
        Account akun = Algorithm.<Account>find(AccountController.accountTable, a -> a.id == accountId);
        Station departure = Algorithm.<Station>find(StationController.stationTable, s -> s.id == stationDepartureId);
        Station arrival = Algorithm.<Station>find(StationController.stationTable, s -> s.id == stationArrivalId);

        if (akun == null) return new BaseResponse<>(false, "Akun tidak ditemukan!", null);
        if (akun.company == null) return new BaseResponse<>(false, "Akun bukan merupakan renter!", null);
        if (departure == null) return new BaseResponse<>(false, "Stasiun keberangkatan tidak ditemukan!", null);
        if (arrival == null) return new BaseResponse<>(false, "Stasiun tujuan tidak ditemukan!", null);
        if (name.isBlank()) return new BaseResponse<>(false, "Nama bus tidak boleh kosong!", null);
        if (capacity < 2) return new BaseResponse<>(false, "Kapasitas bus minimal 2!", null);
        if (facilities.isEmpty()) return new BaseResponse<>(false, "Fasilitas tidak boleh kosong!", null);
        if (price < 1) return new BaseResponse<>(false, "Harga bus tidak benar!", null);

        Bus newBus = new Bus(name, facilities, new Price(price), capacity, busType, departure, arrival);

        busTable.add(newBus);

        return new BaseResponse<>(true, "Bus berhasil dibuat", newBus);
    }

    @PostMapping("/addSchedule")
    public BaseResponse<Bus> addSchedule(@RequestParam int busId, @RequestParam String time) {
        Bus bus = Algorithm.<Bus>find(getJsonTable(), b -> b.id == busId);
        int indexBus = busTable.indexOf(bus);

        if (bus == null) return new BaseResponse<>(false, "Bus tidak ditemukan!", null);
        if (time.isBlank()) return new BaseResponse<>(false, "Waktu yang diberikan tidak valid!", null);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd, MMMM yyyy HH:mm:ss");
        LocalDateTime date = LocalDateTime.from(formatter.parse(time));
        Timestamp timestamp = Timestamp.valueOf(date);

        try {
            bus.addSchedule(timestamp);
            busTable.set(indexBus, bus);
        } catch (IOException error) {
            return new BaseResponse<>(false, "Gagal menambahkan Schedule: " + error.getMessage(), null);
        }


        return new BaseResponse<>(true, "Schedule berhasil dibuat!", bus);
    }
}
