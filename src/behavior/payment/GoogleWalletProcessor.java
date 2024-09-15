package src.behavior.payment;

//step two. Book money from sender to receiver
public class GoogleWalletProcessor extends PaymentProcessor {
    @Override
    protected void transferAmount(Account senderAccount, Account receiverAccount, PayAmount amount, String bookingId) {
        System.out.println("Processing PayPal payment for booking ID: " + bookingId);
        senderAccount.debit(amount);
        receiverAccount.credit(amount);
    }
}