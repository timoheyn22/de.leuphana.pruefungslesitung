package src.Booking;
import src.behavior.statistics.StatisticsVisitor;
import src.behavior.payment.PaymentType;

public class GermanBooking extends Booking {
    public GermanBooking(BookingBuilder builder) {
        this.header = "Buchungsnummer: " + builder.bookingId;
        this.price = builder.price;
        this.body = "Person: " + builder.person.getName() + ", Resource: " + builder.resource.getName();  // Use person and resource
        this.footer = "Preis:" + this.price + " EUR";
        this.bookingId = builder.bookingId;
        this.person = builder.person; // Assign person
        this.resource = builder.resource; // Assign resource
    }
    @Override
    public void accept(StatisticsVisitor visitor, PaymentType paymentType) {
        visitor.visit(this, paymentType);
    }
}