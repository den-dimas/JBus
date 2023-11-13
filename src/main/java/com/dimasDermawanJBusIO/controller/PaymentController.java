package com.dimasDermawanJBusIO.controller;

import com.dimasDermawanJBusIO.*;
import com.dimasDermawanJBusIO.dbjson.JsonAutowired;
import com.dimasDermawanJBusIO.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController implements BasicGetController<Payment> {
    public static @JsonAutowired(value = Payment.class, filepath = "data/paymentDatabase.json") JsonTable<Payment> paymentTable;
    @Override
    public JsonTable<Payment> getJsonTable() {
        return paymentTable;
    }

    @PostMapping("/makeBooking")
    public BaseResponse<Payment> makeBooking(
            @RequestParam int buyerId,
            @RequestParam int renterId,
            @RequestParam int busId,
            @RequestParam List<String> busSeats,
            @RequestParam String departureDate
    ) {
        Account buyer = Algorithm.<Account>find(AccountController.accountTable, a -> a.id == buyerId);

        List<Account> isRenter = Algorithm.<Account>collect(AccountController.accountTable, a -> a.company != null);
        Account renter = Algorithm.<Account>find(isRenter, a -> a.id == renterId);

        Bus bus = Algorithm.<Bus>find(BusController.busTable, b -> b.id == busId);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd, MMMM yyyy HH:mm:ss");
        LocalDateTime date = LocalDateTime.from(formatter.parse(departureDate));
        Timestamp departureTime = Timestamp.valueOf(date);

        if (buyer == null) return new BaseResponse<>(false, "Akun buyer tidak ditemukan!", null);
        if (renter == null) return new BaseResponse<>(false, "Akun renter tidak ditemukan!", null);
        if (bus == null) return new BaseResponse<>(false, "Bus tidak ditemukan!", null);
        if (busSeats.isEmpty()) return new BaseResponse<>(false, "Tempat duduk minimal 1!", null);
        if (departureDate.isBlank()) return new BaseResponse<>(false, "Waktu keberangkatan tidak boleh kosong!", null);

        if (buyer.balance < bus.price.price) return new BaseResponse<>(false, "Saldo anda tidak cukup!", null);

        Schedule busSchedule = Payment.availableSchedule(departureTime, bus);

        if (busSchedule == null) return new BaseResponse<>(false, "Waktu keberangkatan tidak ditemukan!", null);
        if (!busSchedule.isSeatAvailable(busSeats)) return new BaseResponse<>(false, "Tempat duduk tidak tersedia!", null);

        boolean booking = Payment.makeBooking(departureTime, busSeats, bus);

        if (!booking) return new BaseResponse<>(false, "Booking gagal dilakukan!", null);

        Payment payment = new Payment(buyer, renter.company, busId, busSeats, departureTime);

        payment.status = Invoice.PaymentStatus.WAITING;

        paymentTable.add(payment);

        return new BaseResponse<>(true, "Menunggu verifikasi pembayaran!", payment);
    }

    @PostMapping("/{id}/accept")
    public BaseResponse<Payment> acceptPayment(@PathVariable int id) {
        Payment payment = Algorithm.<Payment>find(getJsonTable(), p -> p.id == id);
        int paymentIndex = getJsonTable().indexOf(payment);

        if (payment == null) return new BaseResponse<>(false, "Payment tidak ditemukan!", null);

        payment.status = Invoice.PaymentStatus.SUCCESS;

        paymentTable.set(paymentIndex, payment);

        return new BaseResponse<>(true, "Berhasil melakukan pembayaran!", payment);
    }

    @PostMapping("/{id}/cancel")
    public BaseResponse<Payment> cancelPayment(@PathVariable int id) {
        Payment payment = Algorithm.<Payment>find(getJsonTable(), p -> p.id == id);
        int paymentIndex = getJsonTable().indexOf(payment);

        if (payment == null) return new BaseResponse<>(false, "Payment tidak ditemukan!", null);

        payment.status = Invoice.PaymentStatus.FAILED;

        paymentTable.set(paymentIndex, payment);

        return new BaseResponse<>(true, "Berhasil melakukan pembayaran!", payment);
    }
}
