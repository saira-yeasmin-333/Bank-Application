import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Bank {
    private static Bank bankInstance;
    private int totalAccount;
    private  List<Account> accounts;
    private HashMap<String, List<Account>> userAccount;

    private Bank(){
        totalAccount=0;
        accounts= new ArrayList<>();
        userAccount=new HashMap<>();
    }
    public static Bank getBankInstance(){
        if(bankInstance==null)bankInstance=new Bank();
        return bankInstance;
    }

    Account findAccount(String number) {
        for(Account a:accounts){
            if(a.getAccountNumber().equals(number)) return a;
        }
        return null;
    }

    boolean openingConditionFilled(double balance,int choice){
        double required=0;
        if(choice==Constants.SAVING_CHOICE)required=Constants.SAVING_OPENINGS;
        else if(choice==Constants.CURRENT_CHOICE)required=Constants.CURRENT_OPENINGS;
        else if(choice==Constants.SAlARY_CHOICE)required=Constants.SALARY_OPENINGS;
        return !(balance < required);
    }

    void createAccount(String name,double balance,int choice){
        totalAccount++;
        String accountId=Integer.toString(totalAccount);
        Account account=null;
        if(openingConditionFilled(balance,choice)&& choice==Constants.SAVING_CHOICE)account=new SavingAccount(name,accountId,balance,Constants.SAVING_ACCOUNT);
        else if(openingConditionFilled(balance,choice)&& choice==Constants.CURRENT_CHOICE)account=new CurrentAccount(name,accountId,balance,Constants.CURRENT_ACCOUNT);
        else if(openingConditionFilled(balance,choice)&& choice==Constants.SAlARY_CHOICE)account=new SalaryAccount(name,accountId,balance,Constants.SALARY_ACCOUNT);
        if(account!=null){
            accounts.add(account);
            System.out.println("Account has been created successfully");
            if(userAccount.containsKey(name))userAccount.get(name).add(account);
            else{
                List<Account> acc = new ArrayList<>();
                acc.add(account);
                userAccount.put(name,acc);
            }
        }
        else{
            System.out.println("Please fulfill the required money to open account");
        }
    }

    void displayAccounts(String userName) {
        List<Account> allAccounts = userAccount.get(userName);
        if (allAccounts.isEmpty()) {
            System.out.println("No accounts to display.");
            return;
        }
//        System.out.println("len: "+allAccounts.size());
        for(Account a:allAccounts) System.out.println(a.toString());
    }

    boolean canUpdated(double balance,int choice){
        double required=0;
        if(choice==Constants.SAVING_CHOICE)required=Constants.SAVING_MINIMUM;
        else if(choice==Constants.CURRENT_CHOICE)required=Constants.CURRENT_MINIMUM;
        else if(choice==Constants.SAlARY_CHOICE)required=Constants.SALARY_MINIMUM;
        return balance>=required;
    }

    void updateAccount(String number,int choice) {
        Account account = findAccount(number);
        if(account!=null) {
            Account updatedAccount = null;
            if (canUpdated(account.getBalance(), choice)) {
                if (choice == Constants.SAVING_CHOICE)
                    updatedAccount = new SavingAccount(account.getName(), number, account.getBalance(), Constants.SAVING_ACCOUNT);
                else if (choice == Constants.CURRENT_CHOICE)
                    updatedAccount = new CurrentAccount(account.getName(), number, account.getBalance(), Constants.CURRENT_ACCOUNT);
                else if (choice == Constants.SAlARY_CHOICE)
                    updatedAccount = new SalaryAccount(account.getName(), number, account.getBalance(), Constants.SALARY_ACCOUNT);
                accounts.remove(account);
                accounts.add(updatedAccount);
                List<Account> allAccounts = userAccount.get(updatedAccount.getName());
                for(Account a:allAccounts){
                    if(a.getAccountNumber().equals(number)){
                        allAccounts.remove(a);
                        break;
                    }
                }
                allAccounts.add(updatedAccount);
                userAccount.put(updatedAccount.getName(),allAccounts);
                System.out.println("Account " + number + " updated successfully.");
            }
            else System.out.println("Account can not be updated due to your low balance");
        }
        else System.out.println("Account not found.");
    }

    void deleteAccount(String number) {
        Account account = findAccount(number);
        if (account != null) {
            List<Account> toRemove = new ArrayList<>();
            List<Account> allAccounts = userAccount.get(account.getName());
            for(Account a : allAccounts) {
                if(a.getAccountNumber().equals(number)) {
                    toRemove.add(a); // Add to temporary list, not removing from allAccounts directly
                }
            }
            allAccounts.removeAll(toRemove);
            userAccount.put(account.getName(), allAccounts);
            accounts.remove(account);
            System.out.println("Account " + number + " deleted successfully.");

        } else {
            System.out.println("Account not found.");
        }
    }
    void withdraw(String number,double amount){
        Account account = findAccount(number);
        if(account!=null){
            if(account.withdraw(amount)){
                List<Account> allAccounts = userAccount.get(account.getName());
                for(Account a:allAccounts){
                    if(a.getAccountNumber().equals(number)){
                        allAccounts.remove(a);
                        break;
                    }
                }
                allAccounts.add(account);
                userAccount.put(account.getName(), allAccounts);
                System.out.println("Money withdrawal is successful");
            }
            else{
                System.out.println("Withdrawal would result in balance falling below minimum required.");
            }
        }
        else System.out.println("Account not found");
    }

    void deposit(String number,double amount){
        Account account = findAccount(number);
        if(account !=null){
            if(account.deposit(amount)){
                List<Account> allAccounts = userAccount.get(account.getName());
                for(Account a:allAccounts){
                    if(a.getAccountNumber().equals(number)){
                        allAccounts.remove(a);
                        break;
                    }
                }
                allAccounts.add(account);
                userAccount.put(account.getName(), allAccounts);
                System.out.println("Money has been deposited successfully");
            }
        }
        else{
            System.out.println("Account not found.");
        }
    }

    public void searchAccounts(String number){
        Account account = findAccount(number);
        if(account!=null)System.out.println(account.toString());
        else System.out.println("Account not found");
    }


}
