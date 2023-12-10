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
    public static @JsonAutowired(value = Payment.class, filepath = "./data/paymentDatabase.json") JsonTable<Payment> paymentTable;

    @Override
    public JsonTable<Payment> getJsonTable() {
        return paymentTable;
    }

    @GetMapping("/{id}/getMyPayment")
    public BaseResponse<List<Payment>> getMyPayment(@PathVariable int id) {
        Account account = Algorithm.<Account>find(AccountController.accountTable, t -> t.id == id);

        List<Payment> payments;

        if (account.company == null)
            payments = Algorithm.<Payment>collect(getJsonTable(), payment -> payment.buyerId == id && payment.status != Invoice.PaymentStatus.FAILED);
        else
            payments = Algorithm.<Payment>collect(getJsonTable(), payment -> payment.renterId == id && payment.status == Invoice.PaymentStatus.WAITING);

        return new BaseResponse<>(true, "Retrieved", payments);
    }

    @PostMapping("/makeBooking")
    public BaseResponse<Payment> makeBooking(@RequestParam int buyerId, @RequestParam int renterId, @RequestParam int busId, @RequestParam List<String> busSeats, @RequestParam String departureDate) {
        Account buyer = Algorithm.<Account>find(AccountController.accountTable, a -> a.id == buyerId);
        int buyerIndex = AccountController.accountTable.indexOf(buyer);

        List<Account> isRenter = Algorithm.<Account>collect(AccountController.accountTable, a -> a.company != null);
        Account renter = Algorithm.<Account>find(isRenter, a -> a.id == renterId);

        Bus bus = Algorithm.<Bus>find(BusController.busTable, b -> b.id == busId);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime date = LocalDateTime.from(formatter.parse(departureDate));
        Timestamp departureTime = Timestamp.valueOf(date);

        if (buyer == null) return new BaseResponse<>(false, "Akun buyer tidak ditemukan!", null);
        if (renter == null) return new BaseResponse<>(false, "Akun renter tidak ditemukan!", null);
        if (bus == null) return new BaseResponse<>(false, "Bus tidak ditemukan!", null);
        if (busSeats.isEmpty()) return new BaseResponse<>(false, "Tempat duduk minimal 1!", null);
        if (departureDate.isBlank()) return new BaseResponse<>(false, "Waktu keberangkatan tidak boleh kosong!", null);

        if (buyer.balance < bus.price.price * busSeats.size() + 1)
            return new BaseResponse<>(false, "Saldo anda tidak cukup!", null);

        Schedule busSchedule = Payment.availableSchedule(departureTime, bus);

        if (busSchedule == null) return new BaseResponse<>(false, "Waktu keberangkatan tidak ditemukan!", null);
        if (!busSchedule.isSeatAvailable(busSeats))
            return new BaseResponse<>(false, "Tempat duduk tidak tersedia!", null);

        boolean booking = Payment.makeBooking(departureTime, busSeats, bus);

        if (!booking) return new BaseResponse<>(false, "Booking gagal dilakukan!", null);

        Payment payment = new Payment(buyer, renter, busId, busSeats, departureTime);

        payment.status = Invoice.PaymentStatus.WAITING;

        paymentTable.add(payment);

        buyer.balance -= bus.price.price * busSeats.size();
        AccountController.accountTable.set(buyerIndex, buyer);

        return new BaseResponse<>(true, "Menunggu verifikasi pembayaran!", payment);
    }

    @PostMapping("/{id}/accept")
    public BaseResponse<Payment> acceptPayment(@PathVariable int id, @RequestParam int renterId) {
        Payment payment = Algorithm.<Payment>find(getJsonTable(), p -> p.id == id);
        int paymentIndex = getJsonTable().indexOf(payment);

        Account renter = Algorithm.<Account>find(AccountController.accountTable, a -> a.id == renterId);

        if (renter.company == null) return new BaseResponse<>(false, "Akun bukan renter!", null);
        if (payment == null) return new BaseResponse<>(false, "Payment tidak ditemukan!", null);
        if (payment.status == Invoice.PaymentStatus.SUCCESS) return new BaseResponse<>(false, "Pembayaran sudah berhasil di-accept!", null);

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

        Account buyer = Algorithm.<Account>find(AccountController.accountTable, t -> payment.buyerId == t.id);
        int buyerIndex = AccountController.accountTable.indexOf(buyer);

        Bus bus = Algorithm.<Bus>find(BusController.busTable, b -> b.id == payment.busId);
        int busIndex = BusController.busTable.indexOf(bus);

        buyer.balance += bus.price.price + payment.busSeat.size();
        AccountController.accountTable.set(buyerIndex, buyer);

        Schedule schedule = Algorithm.<Schedule>find(bus.schedules, t -> payment.departureDate.equals(t.departureSchedule));
        int scheduleIndex = bus.schedules.indexOf(schedule);

        for (String seat : payment.busSeat) {
            schedule.seatAvailability.put(seat, true);
        }
        bus.schedules.set(scheduleIndex, schedule);

        BusController.busTable.set(busIndex, bus);

        paymentTable.set(paymentIndex, payment);

        return new BaseResponse<>(true, "Berhasil menggagalkan pembayaran!", payment);
    }
}
