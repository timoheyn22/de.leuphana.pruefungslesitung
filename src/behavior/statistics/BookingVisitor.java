package src.behavior.statistics;


// interface for the Visitors
public interface BookingVisitor {
    void visitGermanBooking(GermanBookingStatistics booking);
    void visitEnglishBooking(EnglishBookingStatistics booking);


}
