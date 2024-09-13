package src.Resource;

import src.Booking.Booking;

public class SetTopBox extends Resource {

    public SetTopBox(Booking booking) {
        super(booking);
    }

    @Override
    public String getDetails() {
        return booking.toString() + " + SetTopBox Resource";
    }
}
