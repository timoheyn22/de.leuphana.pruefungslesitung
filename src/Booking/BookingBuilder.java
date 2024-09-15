package src.Booking;

import src.person.structure.Person;
import src.Resource.Resource;

public class BookingBuilder {
    protected String header;
    protected String body;
    protected String footer;
    protected String bookingId;
    protected Person person;  // Add person field
    protected Resource resource;  // Add resource field
    private String bookingType;
    protected double price;

    public BookingBuilder setHeader(String header) {
        this.header = header;
        return this;
    }

    public BookingBuilder setBody(String body) {
        this.body = body;
        return this;
    }

    public BookingBuilder setFooter(String footer) {
        this.footer = footer;
        return this;
    }

    public BookingBuilder setPrice(double price) {  // New method for setting price
        this.price = price;
        return this;
    }

    public BookingBuilder setBookingId(String bookingId) {
        this.bookingId = bookingId;
        return this;
    }

    public BookingBuilder setPerson(Person person) {
        this.person = person;
        return this;
    }

    public BookingBuilder setResource(Resource resource) {
        this.resource = resource;
        return this;
    }

    public BookingBuilder setBookingType(String bookingType) {
        this.bookingType = bookingType;
        return this;
    }

    public Booking build() {
        if ("german".equalsIgnoreCase(bookingType)) {
            return new GermanBooking(this);
        } else if ("english".equalsIgnoreCase(bookingType)) {
            return new EnglishBooking(this);
        } else {
            throw new IllegalArgumentException("Unknown booking type: " + bookingType);
        }
    }
}
