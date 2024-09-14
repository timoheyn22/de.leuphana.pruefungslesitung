package src.Booking;

import src.person.structure.Person;
import src.Resource.Resource;

public abstract class Booking {
    protected String header;
    protected String body;
    protected String footer;
    protected String bookingId;
    protected Person person; // Add person field
    protected Resource resource; // Add resource field

    public String getHeader() {
        return header;
    }

    public String getBody() {
        return body;
    }

    public String getFooter() {
        return footer;
    }

    public String getBookingId() {
        return bookingId;
    }

    public Person getPerson() {
        return person;
    }

    public Resource getResource() {
        return resource;
    }
}
