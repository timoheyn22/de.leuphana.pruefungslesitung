package src.behavior.payment;

public class Account {
    private String accountId;
    private PayAmount balance;
    //constructor
    public Account(String accountId, PayAmount balance) {
        this.accountId = accountId;
        this.balance = balance;
    }

    public String getAccountId() {
        return accountId;
    }

    public PayAmount getBalance() {
        return balance;
    }
    //call to subtract money from the Account
    public void debit(PayAmount amount) {
        balance.subtract(amount);
    }
    // call to add money to the Account
    public void credit(PayAmount amount) {
        balance.add(amount);
    }

}