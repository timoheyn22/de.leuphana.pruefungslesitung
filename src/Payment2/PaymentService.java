/*package src.Payment2;

import src.Booking.Booking;
import src.behavior.payment.Account;

public abstract class PaymentService {

    // Template method
    public final void payAmount(Account senderAccount, Account receiverAccount, CurrencyAmount amount, Booking booking) {
        authenticateSender(senderAccount);
        processPayment(senderAccount, receiverAccount, amount, booking);
        sendConfirmation();
    }

    // Step 1: Authentifizierung
    private void authenticateSender(Account senderAccount) {
        System.out.println("Authentifizierung von: " + senderAccount.getAccountName());
    }

    // Step 3: Bestätigung
    private void sendConfirmation() {
        System.out.println("Zahlungsbestätigung gesendet.");
    }

    // Step 2: Muss in den Subklassen implementiert werden
    protected abstract void processPayment(Account senderAccount, Account receiverAccount, CurrencyAmount amount, Booking booking);
}
*/