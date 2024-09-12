package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.behavior.payment.*;
import src.behavior.statistics.*;


import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class PaymentServiceTest {

    private PaymentService paymentService;
    private StatisticsService statisticsService;

    @BeforeEach
    public void setUp() {
        statisticsService = new StatisticsService();
        paymentService = new PaymentService(statisticsService);
    }

    @Test
    public void testAddAccount() {
        Account account = new Account("A123", new PayAmount(BigDecimal.valueOf(1000)));
        paymentService.addAccount(account, "German", "PayPal");

        assertNotNull(paymentService.getAccount("A123"));
        assertEquals(BigDecimal.valueOf(1000), paymentService.getAccount("A123").getBalance().getAmount());
    }

    @Test
    public void testDeleteAccount() {
        Account account = new Account("A123", new PayAmount(BigDecimal.valueOf(500)));
        paymentService.addAccount(account, "German", "PayPal");

        paymentService.deleteAccount("A123");
        assertNull(paymentService.getAccount("A123"));
    }

    @Test
    public void testPayAmountWithPayPal() {
        Account sender = new Account("S123", new PayAmount(BigDecimal.valueOf(1000)));
        Account receiver = new Account("R123", new PayAmount(BigDecimal.valueOf(500)));
        PayAmount amount = new PayAmount(BigDecimal.valueOf(200));

        paymentService.payAmount(sender, receiver, amount, PaymentType.PAYPAL, "German", "PayPal");

        assertEquals(BigDecimal.valueOf(800), sender.getBalance().getAmount()); // 1000 - 200
        assertEquals(BigDecimal.valueOf(700), receiver.getBalance().getAmount()); // 500 + 200

        // Check if accounts are added after payment
        assertNotNull(paymentService.getAccount("S123"));
        assertNotNull(paymentService.getAccount("R123"));
    }
    @Test
    public void testPayAmountWithMobileMoneyWallet() {
        Account sender = new Account("S789", new PayAmount(BigDecimal.valueOf(1000)));
        Account receiver = new Account("R789", new PayAmount(BigDecimal.valueOf(500)));
        PayAmount amount = new PayAmount(BigDecimal.valueOf(200));

        paymentService.payAmount(sender, receiver, amount, PaymentType.MOBILE_MONEY_WALLET, "German", "MobileMoneyWallet");

        assertEquals(BigDecimal.valueOf(800), sender.getBalance().getAmount()); // 1000 - 200
        assertEquals(BigDecimal.valueOf(700), receiver.getBalance().getAmount()); // 500 + 200

        // Check if accounts are added after payment
        assertNotNull(paymentService.getAccount("S789"));
        assertNotNull(paymentService.getAccount("R789"));
    }


    @Test
    public void testPayAmountWithGoogleWallet() {
        Account sender = new Account("S456", new PayAmount(BigDecimal.valueOf(2000)));
        Account receiver = new Account("R456", new PayAmount(BigDecimal.valueOf(300)));
        PayAmount amount = new PayAmount(BigDecimal.valueOf(500));

        paymentService.payAmount(sender, receiver, amount, PaymentType.GOOGLE_WALLET, "English", "GoogleWallet");

        assertEquals(BigDecimal.valueOf(1500), sender.getBalance().getAmount()); // 2000 - 500
        assertEquals(BigDecimal.valueOf(800), receiver.getBalance().getAmount()); // 300 + 500

        // Check if accounts are added after payment
        assertNotNull(paymentService.getAccount("S456"));
        assertNotNull(paymentService.getAccount("R456"));
    }

    @Test
    public void testUpdateStatisticsOnAddAndDelete() {
        Account account1 = new Account("A1", new PayAmount(BigDecimal.valueOf(1500)));
        Account account2 = new Account("A2", new PayAmount(BigDecimal.valueOf(800)));

        paymentService.addAccount(account1, "German", "PayPal");
        paymentService.addAccount(account2, "English", "GoogleWallet");

        assertEquals(1, statisticsService.getGermanBookingsPaidByPayPal());
        assertEquals(1, statisticsService.getEnglishBookingsPaidByGoogleWallet());

        paymentService.deleteAccount("A1");
        assertEquals(0, statisticsService.getGermanBookingsPaidByPayPal());
    }

    @Test
    public void testInvalidAccountDeletion() {
        paymentService.deleteAccount("NonExistentID"); // Should not throw an exception
        assertNull(paymentService.getAccount("NonExistentID"));
    }
}
