package src.Booking;

public class GermanBookingBuilder {

    public Booking buildGermanBooking() {
        return new Booking.Builder()
                .setHead("German Booking Header")
                .setBody("German Booking Body")
                .setFooter("German Booking Footer")
                .build();
    }
}
