package src.Payment2;

import src.Booking.Booking;
import src.behavior.payment.Account;

public class GoogleWalletPayment extends PaymentService {

    @Override
    protected void processPayment(Account senderAccount, Account receiverAccount, CurrencyAmount amount, Booking booking) {
        System.out.println("Verarbeite Google Wallet-Zahlung von " + amount.getAmount() + " " + amount.getCurrency() +
                " von " + senderAccount.getAccountName() + " an " + receiverAccount.getAccountName() +
                " für die Buchung: " + booking.getBody());
    }
}
