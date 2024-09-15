package src.behavior.statistics;

import java.util.Map;
import src.Booking.*;
import src.behavior.payment.*;

import java.util.ArrayList;
import java.util.List;

public class StatisticsService {
    private List<BookingWithPayment> bookings = new ArrayList<>();

    public void addBooking(Booking booking, PaymentType paymentType) {
        bookings.add(new BookingWithPayment(booking, paymentType));
    }

    public void removeBooking(String bookingId) {
        bookings.removeIf(bookingWithPayment -> bookingWithPayment.getBooking().getBookingId().equals(bookingId));
    }

    public Map<PaymentType, Integer> getGermanBookingsPaidBy(PaymentType paymentType) {
        GermanBookingVisitor visitor = new GermanBookingVisitor();
        for (BookingWithPayment bookingWithPayment : bookings) {
            if (bookingWithPayment.getBooking() instanceof GermanBooking && bookingWithPayment.getPaymentType() == paymentType) {
                bookingWithPayment.getBooking().accept(visitor, paymentType);
            }
        }
        return visitor.getPaymentStats();
    }

    public Map<PaymentType, Integer> getEnglishBookingsPaidBy(PaymentType paymentType) {
        EnglishBookingVisitor visitor = new EnglishBookingVisitor();
        for (BookingWithPayment bookingWithPayment : bookings) {
            if (bookingWithPayment.getBooking() instanceof EnglishBooking && bookingWithPayment.getPaymentType() == paymentType) {
                bookingWithPayment.getBooking().accept(visitor, paymentType);
            }
        }
        return visitor.getPaymentStats();
    }
}