package test;

import src.authentication.behaviour.AuthenticationService;
import src.authentication.structure.Credential;
import src.authentication.structure.Subject;
import src.authentication.structure.UserNamePasswordStrategy;
import src.authentication.structure.FingerPrintStrategy;
import src.authentication.structure.EyeScanStrategy;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AuthenticationServiceTest {
    private AuthenticationService authService;
    private Subject subject;
    private Credential credential;

    @Before
    public void setUp() {
        authService = new AuthenticationService();
        subject = new Subject("John Doe"); // geht nicht wenn Subject abstrakte Klasse ist
        credential = new Credential();
    }

    @Test
    public void testUserNamePasswordStrategy() {
        try {
            credential.setUsername("admin");
            credential.setPassword("password123");

            authService.setStrategy(new UserNamePasswordStrategy());
            boolean result = authService.authenticate(subject, credential);

            assertTrue("Username/Password authentication should succeed", result);
        } catch (javax.naming.AuthenticationException e) {
            fail("Authentication failed with exception: " + e.getMessage());
        }
    }

    @Test
    public void testFingerPrintStrategy() {
        try {
            credential.setFingerprint("valid_fingerprint");

            authService.setStrategy(new FingerPrintStrategy());
            boolean result = authService.authenticate(subject, credential);

            assertTrue("Fingerprint authentication should succeed", result);
        }catch (javax.naming.AuthenticationException e) {
            fail("Authentication failed with exception: " + e.getMessage());
        }
    }

    @Test
    public void testEyeScanStrategy() {
        try {
            credential.setEyeScanData("valid_eye_scan");

            authService.setStrategy(new EyeScanStrategy());
            boolean result = authService.authenticate(subject, credential);

            assertTrue("Eye scan authentication should succeed", result);
        }catch (javax.naming.AuthenticationException e) {
            fail("Authentication failed with exception: " + e.getMessage());
        }
    }

    @Test
    public void testInvalidUserNamePassword() {
        try {
            credential.setUsername("admin");
            credential.setPassword("wrongPassword");

            authService.setStrategy(new UserNamePasswordStrategy());
            authService.authenticate(subject, credential);

            fail("Authentication with wrong password should throw AuthenticationException");
        } catch (javax.naming.AuthenticationException e) {
            assertEquals("Invalid username or password.", e.getMessage());
        }
    }
}
