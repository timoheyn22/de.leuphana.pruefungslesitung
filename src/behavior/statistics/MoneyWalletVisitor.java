package src.behavior.statistics;

//Visitor that counts bookings made using MoneyWallet as the payment method.

public class MoneyWalletVisitor implements BookingVisitor {
    private int germanBookings = 0;
    private int englishBookings = 0;

    // Increment the count if the payment method is MoneyWallet
    @Override
    public void visitGermanBooking(GermanBookingStatistics booking) {
        if ("MoneyWallet".equals(booking.getPaymentMethod())) {
            germanBookings++;
        }
    }

    @Override
    public void visitEnglishBooking(EnglishBookingStatistics booking) {
        if ("MoneyWallet".equals(booking.getPaymentMethod())) {
            englishBookings++;
        }
    }
    // Returns the number of German bookings that used Money Wallet
    public int getGermanBookings() {
        return germanBookings;
    }
    // Returns the number of English bookings that used Money Wallet.
    public int getEnglishBookings() {
        return englishBookings;
    }
}
