package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import src.Booking.Booking;
import src.Booking.GermanBookingBuilder;
import src.Resource.Car;
import src.Resource.Resource;
import src.Resource.ResourceService;

public class ResourceServiceTest {

    @Test
    public void testGetSelectedResource() {
        // Buchung erstellen
        Booking booking = new GermanBookingBuilder().buildGermanBooking();

        // ResourceService verwenden
        ResourceService resourceService = new ResourceService();

        // Test der Car Resource
        Car carResource = (Car) resourceService.getSelectedResource("Car", booking);
        assertNotNull(carResource);
        assertTrue(carResource.getDetails().contains("Car Resource"));

        // Test der SetTopBox Resource
        Resource setTopBoxResource = resourceService.getSelectedResource("SetTopBox", booking);
        assertNotNull(setTopBoxResource);
        assertTrue(setTopBoxResource.getDetails().contains("SetTopBox Resource"));

        // Test der ChildSeat Resource
        Resource childSeatResource = resourceService.getSelectedResource("ChildSeat", booking);
        assertNotNull(childSeatResource);
        assertTrue(childSeatResource.getDetails().contains("ChildSeat Resource"));
    }
}
