package src.authentication.structure;

import javax.naming.AuthenticationException;

public class EyeScanStrategy implements AuthenticationStrategy {
    @Override
    public boolean authenticate(Subject subject, Credential credential) throws AuthenticationException {
        if (credential.getEyeScanData() == null) {
            throw new AuthenticationException("Eye scan data is missing.");
        }

        // Simulierte Eye-Scan-Überprüfung
        if (credential.getEyeScanData().equals("valid_eye_scan")) {
            return true; // Authentifizierung erfolgreich
        } else {
            throw new AuthenticationException("Invalid eye scan data.");
        }
    }
}
