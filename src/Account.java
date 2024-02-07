import java.util.Date;
public class Account {
    private String name;
    private String accountNumber;
    private Date creationDate;
    private double balance;

    private String accountType;

    public Account(String name, String accountNumber, double balance,String type) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.creationDate = new java.util.Date();;
        this.balance = balance;
        this.accountType=type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public boolean deposit(double amount) {
        balance += amount;
        return true;
    }

    public boolean withdraw(double amount) {
        double minimum_balance=0;
        if(accountType.equals(Constants.SAVING_ACCOUNT)){
            minimum_balance=Constants.SAVING_MINIMUM;
        }
        else if(accountType.equals(Constants.CURRENT_ACCOUNT)){
            minimum_balance=Constants.CURRENT_MINIMUM;
        }
        if(balance-amount<minimum_balance)return false;
        balance-=amount;
        return true;
    }

    @Override
    public String toString() {
        return "Account{" +
                "name='" + name + '\'' +
                ", accountNumber=" + accountNumber +
                ", creationDate='" + creationDate + '\'' +
                ", balance=" + balance +
                ", accountType='" + accountType + '\'' +
                '}';
    }
}
