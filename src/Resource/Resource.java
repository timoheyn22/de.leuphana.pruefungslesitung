package src.Resource;

import src.Booking.Booking;

public abstract class Resource {
    protected Booking booking;

    public Resource(Booking booking) {
        this.booking = booking;
    }

    public abstract String getDetails();
}
