package test;

import src.behavior.payment.*;
import src.behavior.statistics.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentServiceTest {

    private Account sender;
    private Account receiver;
    private List<Statistics> bookings;
    private PaymentService paymentService;
    private StatisticsService statisticsService;


    @BeforeEach
    public void setup() {
        // Initialize sender, receiver and payment service
        sender = new Account("S123", new PayAmount(BigDecimal.valueOf(100)));
        receiver = new Account("R456", new PayAmount(BigDecimal.valueOf(50)));
        bookings = new ArrayList<>();
        statisticsService = new StatisticsService();
        paymentService = new PaymentService(statisticsService);
    }

    @Test
    public void testPayAmount_withPayPal_createsGermanBooking() {
        // Act: Make a payment using PayPal and expect a German booking
        paymentService.payAmount(sender, receiver, new PayAmount(BigDecimal.valueOf(30)), PaymentType.PAYPAL, "German", "PAYPAL");

        // Assert: Check that the booking was recorded correctly
        assertEquals(1, bookings.size(), "There should be one booking recorded.");
        assertEquals("PAYPAL", ((GermanBookingStatistics) bookings.get(0)).getPaymentMethod());
    }

    @Test
    public void testPayAmount_withGoogleWallet_createsEnglishBooking() {
        // Act: Make a payment using Google Wallet and expect an English booking
        paymentService.payAmount(sender, receiver, new PayAmount(BigDecimal.valueOf(20)), PaymentType.GOOGLE_WALLET, "English", "GOOGLE_WALLET");

        // Assert: Check that the booking was recorded correctly
        assertEquals(1, bookings.size(), "There should be one booking recorded.");
        assertEquals("GOOGLE_WALLET", ((EnglishBookingStatistics) bookings.get(0)).getPaymentMethod());
    }

    @Test
    public void testPayAmount_withMultiplePayments_createsMultipleBookings() {
        // Act: Make multiple payments
        paymentService.payAmount(sender, receiver, new PayAmount(BigDecimal.valueOf(30)), PaymentType.PAYPAL, "German", "PAYPAL");
        paymentService.payAmount(sender, receiver, new PayAmount(BigDecimal.valueOf(20)), PaymentType.GOOGLE_WALLET, "English", "GOOGLE_WALLET");

        // Assert: Check that both bookings were recorded
        assertEquals(2, bookings.size(), "There should be two bookings recorded.");
        assertEquals("MOBILE_MONEY_WALLET", ((GermanBookingStatistics) bookings.get(0)).getPaymentMethod());
        assertEquals("PAYPAL", ((EnglishBookingStatistics) bookings.get(1)).getPaymentMethod());
    }
}
