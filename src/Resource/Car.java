package src.Resource;

import src.Booking.Booking;

public class Car extends Resource {

    public Car(Booking booking) {
        super(booking);
    }

    @Override
    public String getDetails() {
        return booking.toString() + " + Car Resource";
    }
}

