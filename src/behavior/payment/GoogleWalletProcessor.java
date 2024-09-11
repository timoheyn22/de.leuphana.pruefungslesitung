package src.behavior.payment;

//step two. Book money from sender to receiver
public class GoogleWalletProcessor extends PaymentProcessor {
    @Override
    protected void transferAmount(Account senderAccount, Account receiverAccount, PayAmount amount) {
        System.out.println("Processing Google Wallet payment...");
        senderAccount.debit(amount);
        receiverAccount.credit(amount);
    }
}