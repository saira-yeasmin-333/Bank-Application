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

    public void createAccount(String name,double balance,int choice){
        totalAccount++;
        String accountId=Integer.toString(totalAccount);
        Account account=null;
        if(choice ==Constants.SAVING_CHOICE){
            if(balance<Constants.SAVING_OPENINGS)System.out.println("Required money to open saving account is "+Constants.SAVING_OPENINGS);
            else account=new SavingAccount(name,accountId,balance,Constants.SAVING_ACCOUNT);
        }
        else if(choice==Constants.CURRENT_CHOICE){
            if(balance<Constants.CURRENT_OPENINGS)System.out.println("Required money to open current account is "+Constants.CURRENT_OPENINGS);
            else account=new SavingAccount(name,accountId,balance,Constants.CURRENT_ACCOUNT);
        }
        else if(choice==Constants.SAlARY_CHOICE){
            if(balance<Constants.SALARY_OPENINGS)System.out.println("Required money to open current account is "+Constants.SALARY_OPENINGS);
            else account=new SavingAccount(name,accountId,balance,Constants.SALARY_ACCOUNT);
        }

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
            System.out.println("Account creation is unsuccessful");
        }
    }

    public void displayAccounts(String userName) {
        List<Account> allAccounts = userAccount.get(userName);
        if (allAccounts.isEmpty()) {
            System.out.println("No accounts to display.");
            return;
        }
//        System.out.println("len: "+allAccounts.size());
        for(Account a:allAccounts) System.out.println(a.toString());

    }



}
