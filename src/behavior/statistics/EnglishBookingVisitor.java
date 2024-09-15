package src.behavior.statistics;

import src.Booking.*;
import src.behavior.payment.PaymentType;

import java.util.HashMap;
import java.util.Map;

public class EnglishBookingVisitor implements StatisticsVisitor {
    private Map<PaymentType, Integer> paymentStats = new HashMap<>();

    @Override
    public void visit(EnglishBooking booking, PaymentType paymentType) {
        paymentStats.put(paymentType, paymentStats.getOrDefault(paymentType, 0) + 1);
    }

    @Override
    public void visit(GermanBooking booking, PaymentType paymentType) {
        // No action for German bookings
    }

    public Map<PaymentType, Integer> getPaymentStats() {
        return paymentStats;
    }
}
