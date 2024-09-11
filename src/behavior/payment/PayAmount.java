package src.behavior.payment;

import java.math.BigDecimal;
//creates money balance for an Account which can be added or subtracted
public class PayAmount {
    private BigDecimal amount;

    public PayAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void subtract(PayAmount other) {
        this.amount = this.amount.subtract(other.getAmount());
    }

    public void add(PayAmount other) {
        this.amount = this.amount.add(other.getAmount());
    }
}