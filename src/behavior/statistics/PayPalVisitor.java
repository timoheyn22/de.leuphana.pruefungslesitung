package src.behavior.statistics;

//Visitor that counts bookings made using PayPal as the payment method.

public class PayPalVisitor implements BookingVisitor {
    private int germanBookings = 0;
    private int englishBookings = 0;

    // Increment the count if the payment method is PayPal.
    @Override
    public void visitGermanBooking(GermanBookingStatistics booking) {
        if ("PayPal".equals(booking.getPaymentMethod())) {
            germanBookings++;
        }
    }

    @Override
    public void visitEnglishBooking(EnglishBookingStatistics booking) {
        if ("PayPal".equals(booking.getPaymentMethod())) {
            englishBookings++;
        }
    }
    // Returns the number of German bookings that used PayPal.
    public int getGermanBookings() {
        return germanBookings;
    }
    // Returns the number of English bookings that used PayPal.
    public int getEnglishBookings() {
        return englishBookings;
    }
}
