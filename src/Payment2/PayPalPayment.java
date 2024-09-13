package src.Payment2;

import src.Booking.Booking;
import src.behavior.payment.Account;

public class PayPalPayment extends PaymentService {

    @Override
    protected void processPayment(Account senderAccount, Account receiverAccount, CurrencyAmount amount, Booking booking) {
        System.out.println("Verarbeite PayPal-Zahlung von " + amount.getAmount() + " " + amount.getCurrency() +
                " von " + senderAccount.getAccountName() + " an " + receiverAccount.getAccountName() +
                " für die Buchung: " + booking.getBody());
    }
}
