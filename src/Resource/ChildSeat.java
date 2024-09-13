package src.Resource;

import src.Booking.Booking;

public class ChildSeat extends Resource {

    public ChildSeat(Booking booking) {
        super(booking);
    }

    @Override
    public String getDetails() {
        return booking.toString() + " + ChildSeat Resource";
    }
}
