package src.behavior.payment;


import src.behavior.statistics.*;
import java.util.HashMap;
import java.util.Map;

public class PaymentService {
    // Store accounts in a Map
    private Map<String, Account> accounts = new HashMap<>();
    private StatisticsService statisticsService;

    public PaymentService(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    // Adding account to the storage and updating statistics
    public void addAccount(Account account, String bookingType, String paymentMethod) {
        accounts.put(account.getAccountId(), account);

        // Print account owner details
        System.out.println("Account with ID " + account.getAccountId() + " owned by " + account.getOwner().getName() + " has been added.");

        // Update statistics based on bookingType and paymentMethod
        if ("German".equalsIgnoreCase(bookingType)) {
            statisticsService.addBooking(account.getAccountId(), new GermanBookingStatistics(paymentMethod));
        } else if ("English".equalsIgnoreCase(bookingType)) {
            statisticsService.addBooking(account.getAccountId(), new EnglishBookingStatistics(paymentMethod));
        }
    }

    // Deleting an account by accountId and updating statistics
    public void deleteAccount(String accountId) {
        if (accounts.containsKey(accountId)) {
            accounts.remove(accountId);
            statisticsService.removeBooking(accountId); // Assuming you have a method to remove bookings by accountId
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

        // After payment, add accounts if they don't exist already and update statistics
        addAccount(senderAccount, bookingType, paymentMethod);
        addAccount(receiverAccount, bookingType, paymentMethod);
    }
}
