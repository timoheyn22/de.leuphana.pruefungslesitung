package src.behavior.statistics;


public class EnglishBookingStatistics implements Statistics {
    //saves the payment method
    private String paymentMethod;

    // Constructor for English Booking
    public EnglishBookingStatistics(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    // getter
    public String getPaymentMethod() {
        return paymentMethod;
    }

    // accept a visitor
    @Override
    public void accept(BookingVisitor visitor) {
        visitor.visitEnglishBooking(this);
    }
}
