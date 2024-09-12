package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream; // Zum Testen der Scanner Klasse
import java.io.InputStream;

import src.person.behaviour.PersonService
public class PersonServiceTest {

    private PersonService personService;

    @BeforeEach // Annonation Before Each heißt: Wird vor jedem Test gemacht, damit jeder Test mit einer frischen Instanz arbeitet
    public void setUp() throws Exception { // Throws Exception um sich nicht um nicht jede Exception einzeln behandeln zu müssen
        personService = new PersonService();
    }

    @AfterEach
    void tearDown() throws Exception {
        personService = null;
    }

    @Test
    public void testCreatePerson() {
        // Simuliere Benutzereingaben: Typ und Name
        String simulatedInput = "natural\nJohn Doe\n";
        InputStream originalIn = System.in;
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        try {
            personService.createPerson();  // Testet die Methode mit Scanner
            assertEquals(1, personService.getPersons().size());
            assertEquals("John Doe", personService.getPersons().get(0).getName());
        } finally {
            System.setIn(originalIn);  // Stelle den Original-InputStream wieder her
        }
    }

    @Test
    public void testDeletePerson() {
        // Simuliere Benutzereingaben für die Erstellung von Personen
        String simulatedCreateInput = "natural\nJohn Doe\nlegal\nAcme Corp\n";
        InputStream originalIn = System.in;
        System.setIn(new ByteArrayInputStream(simulatedCreateInput.getBytes()));

        try {
            // Erstelle zwei Personen
            personService.createPerson();  // John Doe
            personService.createPerson();  // Acme Corp

            // Simuliere Benutzereingaben für das Löschen einer Person
            String simulatedDeleteInput = "John Doe\n";
            System.setIn(new ByteArrayInputStream(simulatedDeleteInput.getBytes()));
            personService.deletePerson("John Doe");
            assertEquals(1, personService.getPersons().size());
            assertNull(personService.findPersonByName("John Doe"));

            // Simuliere Benutzereingaben für das Löschen der zweiten Person
            simulatedDeleteInput = "Acme Corp\n";
            System.setIn(new ByteArrayInputStream(simulatedDeleteInput.getBytes()));
            personService.deletePerson("Acme Corp");
            assertTrue(personService.getPersons().isEmpty());
        } finally {
            System.setIn(originalIn);
        }
    }
}