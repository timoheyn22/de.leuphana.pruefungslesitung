package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.Booking.BookingService;
import src.Booking.GermanBooking;
import src.Booking.EnglishBooking;
import src.person.behaviour.PersonService;
import src.person.structure.Person;
import src.Resource.ResourceService;
import src.Resource.Resource;

public class BookingServiceTest {
    private BookingService bookingService;
    private PersonService personService;
    private ResourceService resourceService;

    @BeforeEach
    public void setUp() {
        personService = new PersonService();
        resourceService = new ResourceService();
        bookingService = new BookingService(personService, resourceService);

        // Add some test data to PersonService
        Resource person1 = new Resource("Person1");
        Resource person2 = new Resource("Person2");
        PersonService.createPerson(person1);
        PersonService.createPerson(person2);


        // Add some test data to ResourceService
        Resource resource1 = new Resource("Resource1");
        Resource resource2 = new Resource("Resource2");
        resourceService.addResource(resource1);
        resourceService.addResource(resource2);
    }

    @Test
    public void testCreateAndListBooking() {
        bookingService.createBooking("german", "1", "John Doe", "Resource1");
        bookingService.createBooking("english", "2", "Jane Smith", "Resource2");

        // Verify that bookings are created
        String expectedBody1 = "Person: John Doe, Resource: Resource1";
        assertEquals(expectedBody1, bookingService.getBookingBodyById("1"));
        String expectedBody2 = "Person: Jane Smith, Resource: Resource2";
        assertEquals(expectedBody2, bookingService.getBookingBodyById("2"));
    }

    @Test
    public void testDeleteBooking() {
        bookingService.createBooking("german", "3", "John Doe", "Resource1");
        bookingService.createBooking("english", "4", "Jane Smith", "Resource2");

        // Delete one booking
        bookingService.deleteBooking("3");

        // Verify that the booking was deleted
        assertEquals("Booking with ID: 3 not found.",bookingService.getBookingBodyById("3"));
    }

    @Test
    public void testGetBookingBodyById() {
        bookingService.createBooking("german", "5", "John Doe", "Resource1");

        // Verify the body of the booking with ID "5"
        String expectedBody = "Person: John Doe, Resource: Resource1";
        assertEquals(expectedBody, bookingService.getBookingBodyById("5"));
    }
}