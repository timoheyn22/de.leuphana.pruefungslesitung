package src.Resource;

import src.Booking.Booking;

public class ResourceService {

    public Resource getSelectedResource(String resourceType, Booking booking) {
        switch (resourceType) {
            case "Car":
                return new Car(booking);
            case "SetTopBox":
                return new SetTopBox(booking);
            case "ChildSeat":
                return new ChildSeat(booking);
            default:
                throw new IllegalArgumentException("Unknown resource type: " + resourceType);
        }
    }
}
