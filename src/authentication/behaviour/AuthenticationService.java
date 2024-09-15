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

    // Methode zum Authentifizieren mit gegebenen Credentials
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

    // Getter und Setter für Credentials
    public Credential getCredential() {
        return credential;
    }

    public void setCredential(Credential credential) {
        this.credential = credential;
    }
}

