package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.person.behaviour.*;
import src.person.structure.*;
import src.person.creational.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonServiceTest {
    private PersonService personService;

    @BeforeEach
    void setUp() {
        personService = new PersonService();
    }

    @Test
    void testCreatePerson() {
        // Test creating a natural person
        personService.createPerson("natural", "John Doe");
        List<Person> persons = personService.getPersons();
        assertEquals(1, persons.size());
        assertEquals("John Doe", persons.get(0).getName());

        // Test creating a legal person
        personService.createPerson("legal", "ABC Corp");
        persons = personService.getPersons();
        assertEquals(2, persons.size());
        assertEquals("ABC Corp", persons.get(1).getName());
    }

    @Test
    void testListPersons() {
        // Create persons
        personService.createPerson("natural", "Alice");
        personService.createPerson("legal", "XYZ Inc.");

        // Check if persons are listed correctly
        List<Person> persons = personService.getPersons();
        assertEquals(2, persons.size());
        assertEquals("Alice", persons.get(0).getName());
        assertEquals("XYZ Inc.", persons.get(1).getName());
    }

    @Test
    void testFindPersonByName() {
        // Create persons
        personService.createPerson("natural", "Bob");
        personService.createPerson("legal", "Tech Ltd.");

        // Find existing person
        Person person = personService.findPersonByName("Bob");
        assertNotNull(person);
        assertEquals("Bob", person.getName());

        // Find non-existent person
        Person nonExistentPerson = personService.findPersonByName("NonExistent");
        assertNull(nonExistentPerson);
    }

    @Test
    void testDeletePerson() {
        // Create persons
        personService.createPerson("natural", "Charlie");
        personService.createPerson("legal", "Company Inc.");

        // Delete an existing person
        personService.deletePerson("Charlie");
        List<Person> persons = personService.getPersons();
        assertEquals(1, persons.size());
        assertNull(personService.findPersonByName("Charlie"));

        // Try deleting a non-existent person
        personService.deletePerson("NonExistent");
        assertEquals(1, persons.size()); // No change in list size
    }

    @Test
    void testCreatePersonInvalidInput() {
        // Test invalid inputs
        assertThrows(IllegalArgumentException.class, () -> personService.createPerson(null, "Invalid"));
        assertThrows(IllegalArgumentException.class, () -> personService.createPerson("natural", null));
        assertThrows(IllegalArgumentException.class, () -> personService.createPerson("", "Invalid"));
        assertThrows(IllegalArgumentException.class, () -> personService.createPerson("natural", ""));
    }
}
