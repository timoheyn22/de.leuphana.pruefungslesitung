package src.behavior.payment;

public abstract class PaymentProcessor {
    // abstract pattern for all Payment Processors
    public final void processPayment(Account senderAccount, Account receiverAccount, PayAmount amount) {
        authenticateSender(senderAccount);
        transferAmount(senderAccount, receiverAccount, amount);
        sendConfirmation();
    }
    //not changeable
    protected void authenticateSender(Account senderAccount) {
        System.out.println("Authenticating sender: " + senderAccount.getAccountId());
    }
    //changeable
    protected abstract void transferAmount(Account senderAccount, Account receiverAccount, PayAmount amount);
    //not changeable
    protected void sendConfirmation() {
        System.out.println("Payment confirmation sent.");
    }
}