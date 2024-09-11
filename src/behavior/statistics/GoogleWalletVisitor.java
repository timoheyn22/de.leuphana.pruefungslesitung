package src.behavior.statistics;

//Visitor that counts bookings made using GoogleWallet as the payment method.

public class GoogleWalletVisitor implements BookingVisitor {
    private int germanBookings = 0;
    private int englishBookings = 0;

    // Increment the count if the payment method is GoogleWallet.
    @Override
    public void visitGermanBooking(GermanBookingStatistics booking) {
        if ("GoogleWallet".equals(booking.getPaymentMethod())) {
            germanBookings++;
        }
    }

    @Override
    public void visitEnglishBooking(EnglishBookingStatistics booking) {
        if ("GoogleWallet".equals(booking.getPaymentMethod())) {
            englishBookings++;
        }
    }

    // Returns the number of German bookings that used GoogelWallet.
    public int getGermanBookings() {
        return germanBookings;
    }
    // Returns the number of English bookings that used GoogelWallet
    public int getEnglishBookings() {
        return englishBookings;
    }

}
