package src.authentication.behaviour;
import javax.naming.AuthenticationException;
import java.util.Scanner;

import src.authentication.structure.AuthenticationStrategy;
import src.authentication.structure.Credential;
import src.authentication.structure.Subject;

public class AuthenticationService {
    private AuthenticationStrategy strategy;
    private Credential credential;

    public AuthenticationService() {
        credential = new Credential();
    }

    // Methode zum Setzen der Strategie
    public void setStrategy(AuthenticationStrategy strategy) {
        this.strategy = strategy;
    }

    // Methode zur Authentifizierung
    public boolean authenticate(Subject subject, Credential credential) throws AuthenticationException {
        if (strategy == null) {
            throw new AuthenticationException("No authentication strategy set");
        }

        boolean isAuthenticated = strategy.authenticate(subject, credential);

        if (!isAuthenticated) {
            throw new AuthenticationException("Authentication failed");
        }

        return isAuthenticated;
    }

    // Interaktive Eingabe der Credentials
    public void enterCredentials() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Username:");
        String username = scanner.nextLine();

        System.out.println("Enter Password:");
        String password = scanner.nextLine();

        credential.setUsername(username);
        credential.setPassword(password);

        // Zusätzliche Eingabeoptionen für Fingerabdruck und EyeScan
        System.out.println("Do you want to enter Fingerprint data? (yes/no)");
        String fingerprintInput = scanner.nextLine();
        if (fingerprintInput.equalsIgnoreCase("yes")) {
            System.out.println("Enter Fingerprint:");
            String fingerprint = scanner.nextLine();
            credential.setFingerprint(fingerprint);
        }

        System.out.println("Do you want to enter EyeScan data? (yes/no)");
        String eyeScanInput = scanner.nextLine();
        if (eyeScanInput.equalsIgnoreCase("yes")) {
            System.out.println("Enter EyeScan data:");
            String eyeScanData = scanner.nextLine();
            credential.setEyeScanData(eyeScanData);
        }
    }
}


