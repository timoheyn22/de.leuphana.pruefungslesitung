package src.behavior.statistics;


public class GermanBookingStatistics implements Statistics{
    //saves the payment method
    private String paymentMethod;

    // Constructor for German Booking
    public GermanBookingStatistics(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    // getter
    public String getPaymentMethod() {
        return paymentMethod;
    }

    // accept a visitor
    @Override
    public void accept(BookingVisitor visitor) {
        visitor.visitGermanBooking(this);
    }
}
