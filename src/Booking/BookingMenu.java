package src.Booking;

import src.person.behaviour.PersonService;
import src.Resource.ResourceService;
import src.person.structure.Person;
import src.Resource.Resource;

import java.util.Scanner;

public class BookingMenu {
    private BookingService bookingService;
    private PersonService personService;
    private ResourceService resourceService;
    private Scanner scanner;

    public BookingMenu(PersonService personService, ResourceService resourceService) {
        this.personService = personService;
        this.resourceService = resourceService;
        this.bookingService = new BookingService(personService, resourceService);
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println("1. Daten eingeben");
            System.out.println("2. Daten löschen");
            System.out.println("3. Daten ausgeben");
            System.out.println("4. Person verwalten");
            System.out.println("5. Resource verwalten");
            System.out.println("6. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();  // consume newline

            switch (choice) {
                case 1:
                    createBooking();
                    break;
                case 2:
                    deleteBooking();
                    break;
                case 3:
                    bookingService.listBookings();
                    break;
                case 4:
                    managePersons();
                    break;
                case 5:
                    manageResources();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void createBooking() {
        System.out.println("Enter language (german/english): ");
        String language = scanner.nextLine();

        System.out.println("Enter booking ID: ");
        String bookingId = scanner.nextLine();

        System.out.println("Enter person name: ");
        String personName = scanner.nextLine();

        System.out.println("Enter resource name: ");
        String resourceName = scanner.nextLine();

        System.out.println("Enter price: ");
        double price = scanner.nextDouble();

        bookingService.createBooking(language, bookingId, personName, resourceName, price);
    }

    private void deleteBooking() {
        System.out.println("Enter booking ID to delete: ");
        String bookingId = scanner.nextLine();
        bookingService.deleteBooking(bookingId);
    }

    private void managePersons() {
        while (true) {
            System.out.println("1. Add Person");
            System.out.println("2. List Persons");
            System.out.println("3. Delete Person");
            System.out.println("4. Back to Main Menu");

            int choice = scanner.nextInt();
            scanner.nextLine();  // consume newline

            switch (choice) {
                case 1:
                    addPerson();
                    break;
                case 2:
                    listPersons();
                    break;
                case 3:
                    deletePerson();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addPerson() {
        System.out.println("Enter type of person (natural/legal): ");
        String type = scanner.nextLine();

        System.out.println("Enter name: ");
        String name = scanner.nextLine();

        personService.createPerson(type, name);
    }

    private void listPersons() {
        personService.listPersons();
    }

    private void deletePerson() {
        System.out.println("Enter person name to delete: ");
        String name = scanner.nextLine();
        personService.deletePerson(name);
    }

    private void manageResources() {
        while (true) {
            System.out.println("1. Add Resource");
            System.out.println("2. List Resources");
            System.out.println("3. Delete Resource");
            System.out.println("4. Back to Main Menu");

            int choice = scanner.nextInt();
            scanner.nextLine();  // consume newline

            switch (choice) {
                case 1:
                    addResource();
                    break;
                case 2:
                    listResources();
                    break;
                case 3:
                    deleteResource();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addResource() {
        System.out.println("Enter resource name: ");
        String name = scanner.nextLine();
        Resource resource = new Resource(name);
        resourceService.addResource(resource);
        System.out.println("Resource added: " + name);
    }

    private void listResources() {
        resourceService.displayResources();
    }

    private void deleteResource() {
        System.out.println("Enter resource name to delete: ");
        String name = scanner.nextLine();
        resourceService.removeResource(name);
        System.out.println("Resource deleted: " + name);
    }

    public static void main(String[] args) {
        PersonService personService = new PersonService();
        ResourceService resourceService = new ResourceService();

        BookingMenu bookingMenu = new BookingMenu(personService, resourceService);
        bookingMenu.start();
    }
}
