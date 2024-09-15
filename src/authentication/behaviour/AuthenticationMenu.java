package src.authentication.behaviour;


/*
import authentication.structure.Credential;
import authentication.structure.FingerPrintStrategy;
import authentication.structure.UserNamePasswordStrategy;
import authentication.structure.EyeScanStrategy;
import authentication.structure.Subject;
import person.structure.NaturalPerson;
import authentication.structure.SoftwareSystem;
*/

import src.authentication.structure.Credential;
import src.authentication.structure.FingerPrintStrategy;
import src.authentication.structure.UserNamePasswordStrategy;
import src.authentication.structure.EyeScanStrategy;
import src.authentication.structure.Subject;
import src.person.structure.NaturalPerson;
import src.authentication.structure.SoftwareSystem;

import javax.naming.AuthenticationException;
import java.util.Scanner;

public class AuthenticationMenu {
    private AuthenticationService authService;
    private Subject subject;  // Subjekt, das authentifiziert werden soll
    private Scanner scanner;

    public AuthenticationMenu(AuthenticationService authService) {
        this.authService = authService;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        selectSubject();  // Ermögliche dem Benutzer die Auswahl des Subjekts

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
        authService.setCredential(credential);
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
                authService.setStrategy(new UserNamePasswordStrategy());
                System.out.println("Username/Password strategy selected.");
                break;
            case 2:
                authService.setStrategy(new FingerPrintStrategy());
                System.out.println("Fingerprint strategy selected.");
                break;
            case 3:
                authService.setStrategy(new EyeScanStrategy());
                System.out.println("Eye Scan strategy selected.");
                break;
            default:
                System.out.println("Invalid strategy. Please try again.");
        }
    }

    private void authenticate() {
        try {
            boolean isAuthenticated = authService.authenticate(subject, authService.getCredential());
            if (isAuthenticated) {
                System.out.println("Authentication successful!");
            } else {
                System.out.println("Authentication failed.");
            }
        } catch (AuthenticationException e) {
            System.out.println("Error during authentication: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        AuthenticationService authService = new AuthenticationService();
        AuthenticationMenu authMenu = new AuthenticationMenu(authService);
        authMenu.start();
    }
}
