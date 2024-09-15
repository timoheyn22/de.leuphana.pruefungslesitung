package src.behavior.statistics;

import src.Booking.*;
import src.behavior.payment.*;

public class BookingWithPayment {
    private Booking booking;
    private PaymentType paymentType;

    public BookingWithPayment(Booking booking, PaymentType paymentType) {
        this.booking = booking;
        this.paymentType = paymentType;
    }

    public Booking getBooking() {
        return booking;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }
}