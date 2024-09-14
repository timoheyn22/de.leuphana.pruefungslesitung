package src.Booking;

public class GermanBooking extends Booking {
    public GermanBooking(BookingBuilder builder) {
        this.header = "Buchungsnummer: " + builder.bookingId;
        this.body = "Person: " + builder.person.getName() + ", Resource: " + builder.resource.getName();  // Use person and resource
        this.footer = "Ende der Buchung";
        this.bookingId = builder.bookingId;
        this.person = builder.person; // Assign person
        this.resource = builder.resource; // Assign resource
    }
}