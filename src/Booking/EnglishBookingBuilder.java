package src.Booking;

public class EnglishBookingBuilder {

    public Booking buildEnglishBooking() {
        return new Booking.Builder()
                .setHead("English Booking Header")
                .setBody("English Booking Body")
                .setFooter("English Booking Footer")
                .build();
    }
}
