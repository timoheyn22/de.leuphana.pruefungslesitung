package src.behavior.statistics;

import src.behavior.payment.PaymentType;
import src.Booking.*;

public interface StatisticsVisitor {
    void visit(GermanBooking booking, PaymentType paymentType);
    void visit(EnglishBooking booking, PaymentType paymentType);
}
