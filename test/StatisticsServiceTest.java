package test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.Booking.*;
import src.behavior.payment.*;
import src.Resource.*;
import src.behavior.statistics.*;
import src.person.behaviour.*;
import src.person.structure.*;
import src.person.creational.*;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class StatisticsServiceTest {

    private StatisticsService statisticsService;
    private BookingService bookingService;
    private PersonService personService;
    private ResourceService resourceService;

    @BeforeEach
    public void setUp() {
        statisticsService = new StatisticsService();
        personService = new PersonService();
        resourceService = new ResourceService();
        bookingService = new BookingService(personService, resourceService);

        // Add some test data to PersonService
        personService.createPerson("legal", "person1");
        personService.createPerson("natural", "person2");

        // Add some test data to ResourceService
        Resource resource1 = new Resource("Resource1");
        Resource resource2 = new Resource("Resource2");
        resourceService.addResource(resource1);
        resourceService.addResource(resource2);
    }

    @Test
    public void testAddAndRemoveBooking() {
        Booking booking = new BookingBuilder()
                .setBookingId("1")
                .setBody("Test Body")
                .setPrice(100.0)
                .setBookingType("German")
                .setPerson(personService.findPersonByName("person1"))
                .setResource(new Resource("Resource1"))
                .build();

        statisticsService.addBooking(booking, PaymentType.PAYPAL);
        assertEquals(1, statisticsService.getGermanBookingsPaidBy(PaymentType.PAYPAL).size());

        statisticsService.removeBooking("1");
        assertEquals(0, statisticsService.getGermanBookingsPaidBy(PaymentType.PAYPAL).size());
    }

    @Test
    public void testGetGermanBookingsPaidBy() {
        Booking booking = new BookingBuilder()
                .setBookingId("2")
                .setBody("Test Body")
                .setPrice(200.0)
                .setBookingType("German")
                .setPerson(personService.findPersonByName("person2"))
                .setResource(new Resource("Resource2"))
                .build();

        statisticsService.addBooking(booking, PaymentType.GOOGLE_WALLET);
        Map<PaymentType, Integer> stats = statisticsService.getGermanBookingsPaidBy(PaymentType.GOOGLE_WALLET);
        assertEquals(1, stats.get(PaymentType.GOOGLE_WALLET));
    }

    @Test
    public void testGetEnglishBookingsPaidBy() {
        Booking booking = new BookingBuilder()
                .setBookingId("3")
                .setBody("Test Body")
                .setPrice(300.0)
                .setBookingType("English")
                .setPerson(personService.findPersonByName("person1"))
                .setResource(new Resource("Resource3"))
                .build();

        statisticsService.addBooking(booking, PaymentType.MOBILE_MONEY_WALLET);
        Map<PaymentType, Integer> stats = statisticsService.getEnglishBookingsPaidBy(PaymentType.MOBILE_MONEY_WALLET);
        assertEquals(1, stats.get(PaymentType.MOBILE_MONEY_WALLET));
    }
}