package src.behavior.payment;


import java.util.HashMap;
import java.util.Map;

public class PaymentService {
    // Store accounts in a Map
    private Map<String, Account> accounts = new HashMap<>();

    public PaymentService() {
    }

    // Adding account to the storage
    public void addAccount(Account account, String bookingType, String paymentMethod) {
        accounts.put(account.getAccountId(), account);

        // Print account owner details
        System.out.println("Account with ID " + account.getAccountId() + " owned by " + account.getOwner().getName() + " has been added.");
    }

    // Deleting an account by accountId
    public void deleteAccount(String accountId) {
        if (accounts.containsKey(accountId)) {
            accounts.remove(accountId);
            System.out.println("Account with ID " + accountId + " has been deleted.");
        } else {
            System.out.println("Account with ID " + accountId + " does not exist.");
        }
    }

    // Retrieve an account by accountId
    public Account getAccount(String accountId) {
        return accounts.get(accountId);
    }

    public void payAmount(Account senderAccount, Account receiverAccount, PayAmount amount, PaymentType paymentType, String bookingType, String paymentMethod, String bookingId) {
        PaymentProcessor processor;

        switch (paymentType) {
            case PAYPAL:
                processor = new PayPalProcessor();
                break;
            case GOOGLE_WALLET:
                processor = new GoogleWalletProcessor();
                break;
            case MOBILE_MONEY_WALLET:
                processor = new MobileMoneyWalletProcessor();
                break;
            default:
                throw new IllegalArgumentException("Unsupported payment type");
        }

        // Process the payment with the booking ID
        processor.processPayment(senderAccount, receiverAccount, amount, bookingId);

        // After payment, add accounts if they don't exist already
        addAccount(senderAccount, bookingType, paymentMethod);
        addAccount(receiverAccount, bookingType, paymentMethod);
    }
}
