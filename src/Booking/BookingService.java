package src.Booking;

import src.person.behaviour.PersonService;
import src.person.structure.Person;
import src.Resource.ResourceService;
import src.Resource.Resource;

import java.util.ArrayList;
import java.util.List;

public class BookingService {
    private List<Booking> bookings = new ArrayList<>();
    private PersonService personService;
    private ResourceService resourceService;

    public BookingService(PersonService personService, ResourceService resourceService) {
        this.personService = personService;
        this.resourceService = resourceService;
    }

    public void createBooking(String language, String bookingId, String personName, String resourceName) {
        Person person = personService.findPersonByName(personName);
        if (person == null) {
            System.out.println("Person not found: " + personName);
            return;
        }

        Resource resource = resourceService.getSelectedResource(resourceName);
        if (resource == null) {
            System.out.println("Resource not found: " + resourceName);
            return;
        }

        BookingBuilder builder = new BookingBuilder()
                .setBookingId(bookingId)
                .setPerson(person)
                .setResource(resource)
                .setBookingType(language);

        Booking booking = builder.build();
        bookings.add(booking);
        System.out.println("Booking created with ID: " + bookingId);
    }

    public void deleteBooking(String bookingId) {
        bookings.removeIf(booking -> booking.getBookingId().equals(bookingId));
        System.out.println("Booking with ID: " + bookingId + " has been removed.");
    }

    public String getBookingBodyById(String bookingId) {
        for (Booking booking : bookings) {
            if (booking.getBookingId().equals(bookingId)) {
                return booking.getBody();
            }
        }
        return "Booking with ID: " + bookingId + " not found.";
    }

    public void listBookings() {
        if (bookings.isEmpty()) {
            System.out.println("No bookings available.");
            return;
        }
        for (Booking booking : bookings) {
            System.out.println("Booking ID: " + booking.getBookingId() + "\n" +
                    "Header: " + booking.getHeader() + "\n" +
                    "Body: " + booking.getBody() + "\n" +
                    "Footer: " + booking.getFooter() + "\n");
        }
    }
}
