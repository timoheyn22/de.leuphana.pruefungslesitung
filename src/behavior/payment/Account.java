package src.behavior.payment;

import src.person.structure.Person;

public class Account {
    private String accountId;
    private PayAmount balance;
    private Person owner;  // New field to store the owner of the account

    // Constructor updated to include the owner (person)
    public Account(String accountId, PayAmount balance, Person owner) {
        this.accountId = accountId;
        this.balance = balance;
        this.owner = owner;
    }

    public String getAccountId() {
        return accountId;
    }

    public PayAmount getBalance() {
        return balance;
    }

    public Person getOwner() {
        return owner;
    }

    public void debit(PayAmount amount) {
        balance.subtract(amount);
    }

    public void credit(PayAmount amount) {
        balance.add(amount);
    }
}
