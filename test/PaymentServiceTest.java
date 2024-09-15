package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.behavior.payment.*;
import src.person.creational.PersonFactory;
import src.person.structure.Person;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class PaymentServiceTest {

    private PaymentService paymentService;


    @BeforeEach
    public void setUp() {
        paymentService = new PaymentService();
    }

    @Test
    public void testAddAccount() {
        Person owner = PersonFactory.createPerson("natural", "John Doe");
        Account account = new Account("A123", new PayAmount(BigDecimal.valueOf(1000)), owner);
        paymentService.addAccount(account, "German", "PayPal");

        assertNotNull(paymentService.getAccount("A123"));
        assertEquals(BigDecimal.valueOf(1000), paymentService.getAccount("A123").getBalance().getAmount());
        assertEquals("John Doe", paymentService.getAccount("A123").getOwner().getName());
    }

    @Test
    public void testDeleteAccount() {
        Person owner = PersonFactory.createPerson("legal", "Acme Corp");
        Account account = new Account("A123", new PayAmount(BigDecimal.valueOf(500)), owner);
        paymentService.addAccount(account, "German", "PayPal");

        paymentService.deleteAccount("A123");
        assertNull(paymentService.getAccount("A123"));
    }

    @Test
    public void testPayAmountWithPayPal() {
        Person senderPerson = PersonFactory.createPerson("natural", "Alice");
        Person receiverPerson = PersonFactory.createPerson("legal", "Bob Inc.");

        Account sender = new Account("S123", new PayAmount(BigDecimal.valueOf(1000)), senderPerson);
        Account receiver = new Account("R123", new PayAmount(BigDecimal.valueOf(500)), receiverPerson);
        PayAmount amount = new PayAmount(BigDecimal.valueOf(200));
        String bookingId = "B001";

        paymentService.payAmount(sender, receiver, amount, PaymentType.PAYPAL, "German", "PayPal", bookingId);

        assertEquals(BigDecimal.valueOf(800), sender.getBalance().getAmount()); // 1000 - 200
        assertEquals(BigDecimal.valueOf(700), receiver.getBalance().getAmount()); // 500 + 200

        // Check if accounts are added after payment
        assertNotNull(paymentService.getAccount("S123"));
        assertNotNull(paymentService.getAccount("R123"));
    }

    @Test
    public void testPayAmountWithMobileMoneyWallet() {
        Person senderPerson = PersonFactory.createPerson("natural", "Charlie");
        Person receiverPerson = PersonFactory.createPerson("legal", "Delta Inc.");

        Account sender = new Account("S789", new PayAmount(BigDecimal.valueOf(1000)), senderPerson);
        Account receiver = new Account("R789", new PayAmount(BigDecimal.valueOf(500)), receiverPerson);
        PayAmount amount = new PayAmount(BigDecimal.valueOf(200));
        String bookingId = "B002";

        paymentService.payAmount(sender, receiver, amount, PaymentType.MOBILE_MONEY_WALLET, "German", "MobileMoneyWallet", bookingId);

        assertEquals(BigDecimal.valueOf(800), sender.getBalance().getAmount()); // 1000 - 200
        assertEquals(BigDecimal.valueOf(700), receiver.getBalance().getAmount()); // 500 + 200

        // Check if accounts are added after payment
        assertNotNull(paymentService.getAccount("S789"));
        assertNotNull(paymentService.getAccount("R789"));
    }

    @Test
    public void testPayAmountWithGoogleWallet() {
        Person senderPerson = PersonFactory.createPerson("natural", "Eve");
        Person receiverPerson = PersonFactory.createPerson("legal", "Foxtrot Ltd.");

        Account sender = new Account("S456", new PayAmount(BigDecimal.valueOf(2000)), senderPerson);
        Account receiver = new Account("R456", new PayAmount(BigDecimal.valueOf(300)), receiverPerson);
        PayAmount amount = new PayAmount(BigDecimal.valueOf(500));
        String bookingId = "B003";

        paymentService.payAmount(sender, receiver, amount, PaymentType.GOOGLE_WALLET, "English", "GoogleWallet", bookingId);

        assertEquals(BigDecimal.valueOf(1500), sender.getBalance().getAmount()); // 2000 - 500
        assertEquals(BigDecimal.valueOf(800), receiver.getBalance().getAmount()); // 300 + 500

        // Check if accounts are added after payment
        assertNotNull(paymentService.getAccount("S456"));
        assertNotNull(paymentService.getAccount("R456"));
    }

    @Test
    public void testInvalidAccountDeletion() {
        paymentService.deleteAccount("NonExistentID"); // Should not throw an exception
        assertNull(paymentService.getAccount("NonExistentID"));
    }
}
