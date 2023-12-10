package com.dimasDermawanJBusIO.controller;

import com.dimasDermawanJBusIO.*;
import com.dimasDermawanJBusIO.dbjson.JsonAutowired;
import com.dimasDermawanJBusIO.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Controller for managing Bus-related operations in the system.
 * Implements BasicGetController interface for basic CRUD operations.
 */
@RestController
@RequestMapping("/bus")
public class BusController implements BasicGetController<Bus> {
    /**
     * JsonTable to store and manage Bus data.
     */
    public static @JsonAutowired(value = Bus.class, filepath = "./data/busDatabase.json") JsonTable<Bus> busTable;

    /**
     * Get the JsonTable instance associated with BusController.
     *
     * @return The JsonTable instance for Bus.
     */
    @Override
    public JsonTable<Bus> getJsonTable() {
        return busTable;
    }

    /**
     * Create a new bus and add it to the system.
     *
     * @param accountId           The ID of the account associated with the bus.
     * @param name                The name of the bus.
     * @param capacity            The seating capacity of the bus.
     * @param facilities          The list of facilities available on the bus.
     * @param busType             The type of the bus.
     * @param price               The price of the bus.
     * @param stationDepartureId  The ID of the departure station.
     * @param stationArrivalId    The ID of the arrival station.
     * @return A {@link BaseResponse} containing information about the success of the operation.
     */
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

        Bus newBus = new Bus(accountId, name, facilities, new Price(price), capacity, busType, departure, arrival);

        busTable.add(newBus);

        return new BaseResponse<>(true, "Bus berhasil dibuat", newBus);
    }

    /**
     * Add a new schedule to an existing bus.
     *
     * @param busId The ID of the bus to which the schedule will be added.
     * @param time  The time of the new schedule.
     * @return A BaseResponse containing information about the success of the operation.
     */
    @PostMapping("/addSchedule")
    public BaseResponse<Bus> addSchedule(@RequestParam int busId, @RequestParam String time) {
        Bus bus = Algorithm.<Bus>find(getJsonTable(), b -> b.id == busId);
        int indexBus = busTable.indexOf(bus);

        if (bus == null) return new BaseResponse<>(false, "Bus tidak ditemukan!", null);
        if (time.isBlank()) return new BaseResponse<>(false, "Waktu yang diberikan tidak valid!", null);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime date = LocalDateTime.from(formatter.parse(time));
        Timestamp timestamp = Timestamp.valueOf(date);

        try {
            bus.addSchedule(timestamp);
            bus.schedules.sort(Comparator.comparing(s -> s.departureSchedule));
            busTable.set(indexBus, bus);
        } catch (IOException error) {
            return new BaseResponse<>(false, "Schedule sudah ada!"  , null);
        }

        return new BaseResponse<>(true, "Schedule berhasil dibuat!", bus);
    }

    /**
     * Get a list of buses associated with a specific account.
     *
     * @param accountId The ID of the account for which to retrieve buses.
     * @return A list of Bus instances associated with the specified account.
     */
    @GetMapping("/getMyBus")
    public List<Bus> getMyBus(@RequestParam int accountId) {
        return Algorithm.<Bus>collect(getJsonTable(),
                b -> b.accountId == accountId);
    }

    @GetMapping("/getAll")
    public BaseResponse<List<Bus>> getAllBus() {
        return new BaseResponse<>(true, "All bus retrieved", getJsonTable());
    }
}
