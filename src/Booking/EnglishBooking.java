package src.Booking;

public class EnglishBooking extends Booking {
    public EnglishBooking(BookingBuilder builder) {
        this.header = "Booking ID: " + builder.bookingId;
        this.body = "Person: " + builder.person.getName() + ", Resource: " + builder.resource.getName();  // Use person and resource
        this.footer = "End of Booking";
        this.bookingId = builder.bookingId;
        this.person = builder.person; // Assign person
        this.resource = builder.resource; // Assign resource
    }
}