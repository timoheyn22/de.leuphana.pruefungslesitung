package src.Booking;

import static org.junit.jupiter.api.Assertions.*;

import src.Payment2.Account;
import src.Payment2.CurrencyAmount;
import src.Payment2.PayPalPayment;
import src.Payment2.GoogleWalletPayment;
import org.junit.jupiter.api.Test;

public class BookingPaymentTest {

    @Test
    public void testGermanBookingWithPayPal() {
        // German booking
        Booking germanBooking = new GermanBookingBuilder().buildGermanBooking();
        assertEquals("German Booking Body", germanBooking.getBody());

        // Payment details
        Account sender = new Account("SenderAccount");
        Account receiver = new Account("ReceiverAccount");
        CurrencyAmount amount = new CurrencyAmount(100.0, "EUR");

        // PayPal payment
        PayPalPayment payPalPayment = new PayPalPayment();
        payPalPayment.payAmount(sender, receiver, amount, germanBooking);

        // Example assertion (for more complex tests you can verify additional outcomes)
        assertEquals("SenderAccount", sender.getAccountName());
        assertEquals(100.0, amount.getAmount());
        assertEquals("EUR", amount.getCurrency());
    }

    @Test
    public void testEnglishBookingWithGoogleWallet() {
        // English booking
        Booking englishBooking = new EnglishBookingBuilder().buildEnglishBooking();
        assertEquals("English Booking Body", englishBooking.getBody());

        // Payment details
        Account sender = new Account("SenderAccount");
        Account receiver = new Account("ReceiverAccount");
        CurrencyAmount amount = new CurrencyAmount(150.0, "USD");

        // Google Wallet payment
        GoogleWalletPayment googleWalletPayment = new GoogleWalletPayment();
        googleWalletPayment.payAmount(sender, receiver, amount, englishBooking);

        // Example assertion
        assertEquals("SenderAccount", sender.getAccountName());
        assertEquals(150.0, amount.getAmount());
        assertEquals("USD", amount.getCurrency());
    }
}
