package src.CarReservationService;

import src.Resource.*;
import src.content.behaviour.ContentService;
import src.person.behaviour.*;
import src.person.structure.*;
import src.Booking.BookingService;
import src.behavior.payment.PaymentService;
import src.behavior.statistics.StatisticsService;
import src.authentication.behaviour.*;
import src.authentication.structure.*;
import java.util.Scanner;
import src.Resource.Car;
import src.Resource.SetTopBox;
import src.Resource.ChildSeat;


import javax.naming.AuthenticationException;

public class CarReservationService {
    private AuthenticationService authenticationService;
    private StatisticsService statisticsService;
    private PaymentService paymentService;
    private BookingService bookingService;
    private PersonService personService;
    private ResourceService resourceService;
    private ContentService contentService;
    private Scanner scanner;
    private Subject subject;

    public CarReservationService(PersonService personService, ResourceService resourceService, BookingService bookingService, AuthenticationService authenticationService, StatisticsService statisticsService, PaymentService paymentService, ContentService contentService) {
        this.personService = personService;
        this.resourceService = resourceService;
        this.bookingService = bookingService;
        this.authenticationService = authenticationService;
        this.statisticsService = statisticsService;
        this.paymentService = paymentService;
        this.contentService = contentService;
        scanner = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;

        while (running) {
            System.out.println("Please choose a Person option:");
            System.out.println("1. Create Person");
            System.out.println("2. Delete Person");
            System.out.println("3. List Persons");
            System.out.println("4. Next");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createPersonMenu();
                    break;
                case 2:
                    deletePersonMenu();
                    break;
                case 3:
                    personService.listPersons();
                    break;
                case 4:
                    menuResources();
                    break; // Add this break statement
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void menuResources() {
        while (true) {
            System.out.println("Resource Menu:");
            System.out.println("1. Daten eingeben");
            System.out.println("2. Daten löschen");
            System.out.println("3. Daten ausgeben");
            System.out.println("4. Beenden");
            System.out.print("Wählen Sie eine Option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Geben Sie den Ressourcentyp (Car, SetTopBox, ChildSeat) ein: ");
                    String type = scanner.nextLine();
                    System.out.print("Geben Sie den Namen der Ressource ein: ");
                    String name = scanner.nextLine();
                    Resource resource = null;

                    if (type.equalsIgnoreCase("Car")) {
                        resource = new Car(name);
                    } else if (type.equalsIgnoreCase("SetTopBox")) {
                        resource = new SetTopBox(name);
                    } else if (type.equalsIgnoreCase("ChildSeat")) {
                        resource = new ChildSeat(name);
                    }

                    if (resource != null) {
                        resourceService.addResource(resource);
                        System.out.println(type + " erfolgreich hinzugefügt.");
                    } else {
                        System.out.println("Ungültiger Ressourcentyp.");
                        continue; // Add this line
                    }
                    break;
                case 2:
                    System.out.print("Geben Sie den Namen der zu löschenden Ressource ein: ");
                    String nameToDelete = scanner.nextLine();
                    resourceService.removeResource(nameToDelete);
                    System.out.println("Ressource gelöscht.");
                    break;
                case 3:
                    resourceService.displayResources();
                    break;
                case 4:
                    menuAutehnitcation();
                default:
                    System.out.println("Ungültige Auswahl.");
            }
        }
    }
    private void menuAutehnitcation() {
        selectSubject();
        int choice;
        do {
            System.out.println("\nAuthentication Menu:");
            System.out.println("1. Set authentication strategy");
            System.out.println("2. Enter credentials");
            System.out.println("3. Authenticate");
            System.out.println("4. Exit");
            System.out.print("Please select an option: ");
            choice = scanner.nextInt();
            scanner.nextLine();  // Zeilenumbruch verarbeiten

            switch (choice) {
                case 1:
                    setStrategy(scanner);
                    break;
                case 2:
                    enterCredentials();  // Neue Methode zum Erfassen der Credentials
                    break;
                case 3:
                    authenticate();
                    break;
                case 4:
                    System.out.println("Exiting Authentication Menu.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);
    }
    private void createPersonMenu() {
        System.out.println("Enter type of person (natural/legal): ");
        String type = scanner.nextLine();

        System.out.println("Enter name: ");
        String name = scanner.nextLine();

        try {
            personService.createPerson(type, name);
        } catch (IllegalArgumentException e) {
            System.out.println("Failed to create person: " + e.getMessage());
        }
    }

    private void deletePersonMenu() {
        System.out.println("Enter name of the person to delete: ");
        String name = scanner.nextLine();
        personService.deletePerson(name);
    }

    private void enterCredentials() {
        Credential credential = new Credential();

        System.out.println("Enter Username:");
        String username = scanner.nextLine();
        credential.setUsername(username);

        System.out.println("Enter Password:");
        String password = scanner.nextLine();
        credential.setPassword(password);

        // Optional: Fingerprint-Daten abfragen
        System.out.println("Do you want to enter Fingerprint data? (yes/no)");
        String fingerprintInput = scanner.nextLine();
        if (fingerprintInput.equalsIgnoreCase("yes")) {
            System.out.println("Enter Fingerprint:");
            String fingerprint = scanner.nextLine();
            credential.setFingerprint(fingerprint);
        }

        // Optional: EyeScan-Daten abfragen
        System.out.println("Do you want to enter EyeScan data? (yes/no)");
        String eyeScanInput = scanner.nextLine();
        if (eyeScanInput.equalsIgnoreCase("yes")) {
            System.out.println("Enter EyeScan data:");
            String eyeScanData = scanner.nextLine();
            credential.setEyeScanData(eyeScanData);
        }

        // Setze die erfassten Credentials im AuthenticationService
        authenticationService.setCredential(credential);
        System.out.println("Credentials have been set.");
    }

    private void setStrategy(Scanner scanner) {
        System.out.println("Select authentication strategy:");
        System.out.println("1. Username/Password");
        System.out.println("2. Fingerprint");
        System.out.println("3. Eye Scan");
        System.out.print("Please select an option: ");
        int strategyChoice = scanner.nextInt();
        scanner.nextLine();  // Zeilenumbruch verarbeiten

        switch (strategyChoice) {
            case 1:
                authenticationService.setStrategy(new UserNamePasswordStrategy());
                System.out.println("Username/Password strategy selected.");
                break;
            case 2:
                authenticationService.setStrategy(new FingerPrintStrategy());
                System.out.println("Fingerprint strategy selected.");
                break;
            case 3:
                authenticationService.setStrategy(new EyeScanStrategy());
                System.out.println("Eye Scan strategy selected.");
                break;
            default:
                System.out.println("Invalid strategy. Please try again.");
        }
    }

    private void authenticate() {
        try {
            boolean isAuthenticated = authenticationService.authenticate(subject, authenticationService.getCredential());
            if (isAuthenticated) {
                System.out.println("Authentication successful!");
            } else {
                System.out.println("Authentication failed.");
            }
        } catch (AuthenticationException e) {
            System.out.println("Error during authentication: " + e.getMessage());
        }
    }
    private void selectSubject() {
        System.out.println("Select subject type:");
        System.out.println("1. Natural Person");
        System.out.println("2. Software System");
        System.out.print("Please select an option: ");
        int subjectChoice = scanner.nextInt();
        scanner.nextLine();  // Zeilenumbruch verarbeiten

        switch (subjectChoice) {
            case 1:
                System.out.print("Enter the name of the Natural Person: ");
                String personName = scanner.nextLine();
                subject = new NaturalPerson(personName);
                System.out.println("Selected Natural Person: " + personName);
                break;
            case 2:
                System.out.print("Enter the name of the Software System: ");
                String systemName = scanner.nextLine();
                subject = new SoftwareSystem(systemName);
                System.out.println("Selected Software System: " + systemName);
                break;
            default:
                System.out.println("Invalid choice. Defaulting to Natural Person.");
                subject = new NaturalPerson("Default Person");
                break;
        }
    }

    public static void main(String[] args) {
        PersonService personService = new PersonService();
        ResourceService resourceService = new ResourceService();
        BookingService bookingService = new BookingService(personService, resourceService);
        AuthenticationService authenticationService = new AuthenticationService();
        StatisticsService statisticsService = new StatisticsService();
        PaymentService paymentService = new PaymentService();
        ContentService contentService = new ContentService();
        CarReservationService menu = new CarReservationService(personService, resourceService, bookingService, authenticationService, statisticsService, paymentService, contentService);
        menu.start();
    }
}
