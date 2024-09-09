package de.leuphana.pruefungslesitung.behavior.payment;
import java.math.BigDecimal;
import java.util.Scanner;

public class PaymentMenu {
    private PaymentService paymentService;

    public PaymentMenu(StatisticsService statisticsService) {
        paymentService = new PaymentService(statisticsService);
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nPayment Menu:");
            System.out.println("1. Add Payment Data");
            System.out.println("2. Delete Payment Data");
            System.out.println("3. Display Payment Data");
            System.out.println("4. Exit");
            System.out.print("Please select an option: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    enterPaymentData(scanner);
                    break;
                case 2:
                    deletePaymentData(scanner);
                    break;
                case 3:
                    displayPaymentData(scanner);
                    break;
                case 4:
                    System.out.println("Exiting Payment Menu.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);
    }

    private void enterPaymentData(Scanner scanner) {
        // Enter sender and receiver account data
        System.out.print("Enter sender account ID: ");
        String senderId = scanner.next();
        System.out.print("Enter sender balance: ");
        BigDecimal senderBalance = scanner.nextBigDecimal();

        System.out.print("Enter receiver account ID: ");
        String receiverId = scanner.next();
        System.out.print("Enter receiver balance: ");
        BigDecimal receiverBalance = scanner.nextBigDecimal();

        System.out.print("Enter payment amount: ");
        BigDecimal paymentAmount = scanner.nextBigDecimal();

        // Select payment type
        System.out.println("Select Payment Type:");
        System.out.println("1. PayPal");
        System.out.println("2. Google Wallet");
        System.out.println("3. Mobile Money Wallet");
        int paymentTypeChoice = scanner.nextInt();
        PaymentType paymentType = PaymentType.values()[paymentTypeChoice - 1];

        // Enter booking type and payment method
        System.out.print("Enter booking type (German/English): ");
        String bookingType = scanner.next();
        System.out.print("Enter payment method: ");
        String paymentMethod = scanner.next();

        // Create sender and receiver accounts
        Account sender = new Account(senderId, new PayAmount(senderBalance));
        Account receiver = new Account(receiverId, new PayAmount(receiverBalance));

        // Process payment and update statistics
        paymentService.payAmount(sender, receiver, new PayAmount(paymentAmount), paymentType, bookingType, paymentMethod);

        System.out.println("Payment processed successfully.");
    }

    private void deletePaymentData(Scanner scanner) {
        System.out.print("Enter the account ID to delete: ");
        String accountId = scanner.next();
        paymentService.deleteAccount(accountId);
    }

    private void displayPaymentData(Scanner scanner) {
        System.out.print("Enter the account ID to display: ");
        String accountId = scanner.next();
        Account account = paymentService.getAccount(accountId);
        if (account != null) {
            System.out.println("Account ID: " + account.getAccountId());
            System.out.println("Balance: " + account.getBalance().getAmount());
        } else {
            System.out.println("Account not found.");
        }
    }

    public static void main(String[] args) {
        StatisticsService statisticsService = new StatisticsService();
        PaymentMenu menu = new PaymentMenu(statisticsService);
        menu.start();
    }
}
