package src.behavior.statistics;

import java.util.Scanner;

public class StatisticsMenu {
    private StatisticsService statisticsService;
    private Scanner scanner;

    public StatisticsMenu() {
        statisticsService = new StatisticsService();
        scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println("\nStatistics Menu");
            System.out.println("1. Add booking");
            System.out.println("2. Remove booking");
            System.out.println("3. Display statistics");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    addBooking();
                    break;
                case 2:
                    removeBooking();
                    break;
                case 3:
                    displayStatistics();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void addBooking() {
        System.out.print("Enter account ID: ");
        String accountId = scanner.nextLine();

        System.out.print("Enter booking type (German/English): ");
        String bookingType = scanner.nextLine();

        System.out.print("Enter payment method (PayPal/GoogleWallet/MoneyWallet): ");
        String paymentMethod = scanner.nextLine();

        if ("German".equalsIgnoreCase(bookingType)) {
            statisticsService.addBooking(accountId, new GermanBookingStatistics(paymentMethod));
        } else if ("English".equalsIgnoreCase(bookingType)) {
            statisticsService.addBooking(accountId, new EnglishBookingStatistics(paymentMethod));
        } else {
            System.out.println("Invalid booking type.");
        }

        System.out.println("Booking added successfully.");
    }

    private void removeBooking() {
        System.out.print("Enter account ID to remove: ");
        String accountId = scanner.nextLine();

        statisticsService.removeBooking(accountId);
    }

    private void displayStatistics() {
        System.out.println("\n--- Statistics ---");

        System.out.println("German bookings paid via PayPal: " + statisticsService.getGermanBookingsPaidByPayPal());
        System.out.println("English bookings paid via PayPal: " + statisticsService.getEnglishBookingsPaidByPayPal());
        System.out.println("German bookings paid via Google Wallet: " + statisticsService.getGermanBookingsPaidByGoogleWallet());
        System.out.println("English bookings paid via Google Wallet: " + statisticsService.getEnglishBookingsPaidByGoogleWallet());
        System.out.println("German bookings paid via Money Wallet: " + statisticsService.getGermanBookingsPaidByMoneyWallet());
        System.out.println("English bookings paid via Money Wallet: " + statisticsService.getEnglishBookingsPaidByMoneyWallet());
    }

    public static void main(String[] args) {
        StatisticsMenu menu = new StatisticsMenu();
        menu.start();
    }
}
