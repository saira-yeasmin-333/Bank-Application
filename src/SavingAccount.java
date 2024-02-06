class SavingAccount extends Account {
    static final double MINIMUM_BALANCE = 100.0;

    public SavingAccount(String name, String creationDate, double initialBalance) {
        super(name, creationDate, initialBalance);
        if (initialBalance < MINIMUM_BALANCE) {
            throw new IllegalArgumentException("Initial balance must be at least " + MINIMUM_BALANCE);
        }
    }

    @Override
    public boolean deposit(double amount) {
        balance += amount;
        return true;
    }

    @Override
    public boolean withdraw(double amount) {
        if (balance - amount < MINIMUM_BALANCE) {
            System.out.println("Withdrawal would bring account below minimum balance.");
            return false;
        }
        balance -= amount;
        return true;
    }
}