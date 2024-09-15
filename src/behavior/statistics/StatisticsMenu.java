package src.behavior.statistics;

import src.Booking.*;
import src.Resource.*;
import src.person.behaviour.*;
import src.behavior.payment.PaymentType;

import java.util.Scanner;

public class StatisticsMenu {
    private StatisticsService statisticsService;
    private ResourceService resourceService;
    private PersonService personService;

    public StatisticsMenu(StatisticsService statisticsService, ResourceService resourceService, PersonService personService) {
        this.statisticsService = statisticsService;
        this.resourceService = resourceService;
        this.personService = personService;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nStatistics Menu:");
            System.out.println("1. Enter data");
            System.out.println("2. Delete data");
            System.out.println("3. Display data");
            System.out.println("4. Exit");
            System.out.print("Please select an option: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    enterData(scanner);
                    break;
                case 2:
                    deleteData(scanner);
                    break;
                case 3:
                    displayData(scanner);
                    break;
                case 4:
                    System.out.println("Exiting Statistics Menu.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);
    }

    private void enterData(Scanner scanner) {
        System.out.print("Enter booking type (German/English): ");
        String bookingType = scanner.next();
        System.out.print("Enter booking ID: ");
        String bookingId = scanner.next();
        System.out.print("Enter person name: ");
        String personName = scanner.next();
        System.out.print("Enter person Type(legal/natural): ");
        String personType = scanner.next();
        System.out.print("Enter resource name: ");
        String resourceName = scanner.next();
        System.out.print("Enter resource Type(Car/SetTopBox/ChildSeat): ");
        String resourceType = scanner.next();
        System.out.print("Enter price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter payment type (1 for PayPal, 2 for Google Wallet, 3 for Mobile Money Wallet): ");
        PaymentType paymentType = PaymentType.values()[scanner.nextInt() - 1];
        personService.createPerson(personType, personName);

        Resource resource = null;

        if (resourceType.equalsIgnoreCase("Car")) {
            resource = new Car(resourceName);
        } else if (resourceType.equalsIgnoreCase("SetTopBox")) {
            resource = new SetTopBox(resourceName);
        } else if (resourceType.equalsIgnoreCase("ChildSeat")) {
            resource = new ChildSeat(resourceName);
        }
        resourceService.addResource(resource);
        BookingService bookingService = new BookingService(personService,resourceService);
        bookingService.createBooking(bookingType, bookingId, personName, resourceName, price);
        Booking booking = bookingService.getBookingById(bookingId);
        statisticsService.addBooking(booking, paymentType);
    }

    private void deleteData(Scanner scanner) {
        System.out.print("Enter booking ID to delete: ");
        String bookingId = scanner.next();
        statisticsService.removeBooking(bookingId);
    }

    private void displayData(Scanner scanner) {
        System.out.print("Enter payment type (1 for PayPal, 2 for Google Wallet, 3 for Mobile Money Wallet): ");
        PaymentType paymentType = PaymentType.values()[scanner.nextInt() - 1];
        System.out.println("German bookings paid by " + paymentType + ": " +
                statisticsService.getGermanBookingsPaidBy(paymentType));
        System.out.println("English bookings paid by " + paymentType + ": " +
                statisticsService.getEnglishBookingsPaidBy(paymentType));
    }

    public static void main(String[] args) {
        StatisticsService statisticsService = new StatisticsService();
        ResourceService resourceService = new ResourceService();
        PersonService personService = new PersonService();
        StatisticsMenu menu = new StatisticsMenu(statisticsService, resourceService, personService);
        menu.start();
    }
}
