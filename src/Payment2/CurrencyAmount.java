package src.Payment2;

public class CurrencyAmount {
    private double amount;
    private String currency;

    public CurrencyAmount(double amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public double getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }
}
