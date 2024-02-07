import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Bank bank = Bank.getBankInstance();
        System.out.println("Welcome to the Bank Application!");
        while (true) {
            System.out.println("Please choose an option:");
            System.out.println("1: Create Account\n2: Display All Accounts\n3: Update Account\n4: Delete Account\n5: Deposit\n6: Withdraw\n7: Search Account\n8: Exit");
            int choice = scanner.nextInt();
            if(choice<1 || choice>8) {
                System.out.println("Please select option between 1 and 8");
                continue;
            };
            switch (choice) {
                case 1:
                    System.out.println("There are 3 types of account available!");
                    System.out.println(
                            "1. Savings(Required money to open: "+Constants.SAVING_OPENINGS+" , Minimum Balance : "+Constants.SAVING_MINIMUM+")\n"+
                            "2. Current(Required money to open: "+Constants.CURRENT_OPENINGS+" , Minimum Balance : "+Constants.CURRENT_MINIMUM+")\n"+
                            "3. Salary(Required money to open: "+Constants.SAVING_OPENINGS+" , Minimum Balance : "+Constants.SALARY_MINIMUM+")\n");
                    scanner = new Scanner(System.in);
                    System.out.println("Please enter account name:");
                    String name = scanner.next();
                    System.out.println("Please enter initial balance:");
                    double balance = scanner.nextDouble();
                    System.out.println("Please select account type any of the 3 types mentioned above:");
                    int type = scanner.nextInt();
                    bank.createAccount(name,balance,type);
                    break;
                case 2:
                    System.out.println("Please enter your name: ");
                    name= scanner.next();
                    bank.displayAccounts(name);
                    break;
                case 3:
                    System.out.println("Please enter Account Number:");
                    String updateNumber = scanner.next();
                    System.out.println(
                    "1. Savings(Required money to open: "+Constants.SAVING_OPENINGS+" , Minimum Balance : "+Constants.SAVING_MINIMUM+")\n"+
                    "2. Current(Required money to open: "+Constants.CURRENT_OPENINGS+" , Minimum Balance : "+Constants.CURRENT_MINIMUM+")\n"+
                    "3. Salary(Required money to open: "+Constants.SAVING_OPENINGS+" , Minimum Balance : "+Constants.SALARY_MINIMUM+")\n");
                    System.out.println("Please enter your updated account type");
                    int updatedType= scanner.nextInt();
                    bank.updateAccount(updateNumber,updatedType);
                    break;
                case 4:
                    System.out.println("Please enter Account Number to Delete:");
                    String deleteNumber = scanner.next();
                    bank.deleteAccount(deleteNumber);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}