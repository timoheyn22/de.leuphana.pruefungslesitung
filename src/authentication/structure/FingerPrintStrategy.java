package src.authentication.structure;

import javax.naming.AuthenticationException;

public class FingerPrintStrategy implements AuthenticationStrategy {
    @Override
    public boolean authenticate(Subject subject, Credential credential) throws AuthenticationException {
        if (credential.getFingerprint() == null) {
            throw new AuthenticationException("Fingerprint is missing.");
        }

        // Simulierte Fingerabdruck-Überprüfung
        if (credential.getFingerprint().equals("valid_fingerprint")) {
            return true; // Authentifizierung erfolgreich
        } else {
            throw new AuthenticationException("Invalid fingerprint.");
        }
    }
}
