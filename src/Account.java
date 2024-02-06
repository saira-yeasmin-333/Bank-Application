abstract class Account {
    String name;
    int number;
    String creationDate;
    double balance;
    public Account(String name, String creationDate, double initialBalance) {
        this.name = name;
        this.creationDate = creationDate;
        this.balance = initialBalance;
    }

    public abstract boolean deposit(double amount);

    public abstract boolean withdraw(double amount);
}
