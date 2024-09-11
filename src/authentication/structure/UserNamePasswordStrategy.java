package src.authentication.structure;

import javax.naming.AuthenticationException;

public class UserNamePasswordStrategy implements AuthenticationStrategy {
    @Override
    public boolean authenticate(Subject subject, Credential credential) throws AuthenticationException {
        // Überprüfe, ob Username und Passwort in den Credentials vorhanden sind
        if (credential.getUsername() == null || credential.getPassword() == null) {
            throw new AuthenticationException("Username or password is missing.");
        }

        // Führe die Authentifizierung durch
        if (credential.getUsername().equals("admin") && credential.getPassword().equals("password123")) {
            return true; // Authentifizierung erfolgreich
        } else {
            throw new AuthenticationException("Invalid username or password.");
        }
    }
}
