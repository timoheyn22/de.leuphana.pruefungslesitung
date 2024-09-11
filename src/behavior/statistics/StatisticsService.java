package src.behavior.statistics;

import java.util.HashMap;
import java.util.Map;
// Service that applies visitors to count the number of bookings by payment method
public class StatisticsService {
    private Map<String, Statistics> bookings;

    public StatisticsService() {
        bookings = new HashMap<>();
    }

    // Add a booking with account ID as the key
    public void addBooking(String accountId, Statistics booking) {
        bookings.put(accountId, booking);
    }

    // Remove a booking by account ID
    public void removeBooking(String accountId) {
        if (bookings.containsKey(accountId)) {
            bookings.remove(accountId);
            System.out.println("Booking with account ID " + accountId + " removed.");
        } else {
            System.out.println("Booking with account ID " + accountId + "not found.");
        }
    }

    // Returns the number of German bookings paid via PayPal
    public int getGermanBookingsPaidByPayPal() {
        PayPalVisitor visitor = new PayPalVisitor();
        for (Statistics booking : bookings.values()) {
            booking.accept(visitor);
        }
        return visitor.getGermanBookings();
    }

    // Returns the number of English bookings paid via PayPal
    public int getEnglishBookingsPaidByPayPal() {
        PayPalVisitor visitor = new PayPalVisitor();
        for (Statistics booking : bookings.values()) {
            booking.accept(visitor);
        }
        return visitor.getEnglishBookings();
    }

    // Returns the number of German bookings paid via Google Wallet
    public int getGermanBookingsPaidByGoogleWallet() {
        GoogleWalletVisitor visitor = new GoogleWalletVisitor();
        for (Statistics booking : bookings.values()) {
            booking.accept(visitor);
        }
        return visitor.getGermanBookings();
    }

    // Returns the number of English bookings paid via Google Wallet
    public int getEnglishBookingsPaidByGoogleWallet() {
        GoogleWalletVisitor visitor = new GoogleWalletVisitor();
        for (Statistics booking : bookings.values()) {
            booking.accept(visitor);
        }
        return visitor.getEnglishBookings();
    }

    // Returns the number of German bookings paid via Money Wallet
    public int getGermanBookingsPaidByMoneyWallet() {
        MoneyWalletVisitor visitor = new MoneyWalletVisitor();
        for (Statistics booking : bookings.values()) {
            booking.accept(visitor);
        }
        return visitor.getGermanBookings();
    }

    // Returns the number of English bookings paid via Money Wallet
    public int getEnglishBookingsPaidByMoneyWallet() {
        MoneyWalletVisitor visitor = new MoneyWalletVisitor();
        for (Statistics booking : bookings.values()) {
            booking.accept(visitor);
        }
        return visitor.getEnglishBookings();
    }
}
