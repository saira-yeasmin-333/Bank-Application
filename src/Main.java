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
            switch (choice) {
                case 1:
                    scanner = new Scanner(System.in);
                    System.out.println("Enter account name:");
                    String name = scanner.next();
                    System.out.println("Enter initial balance:");
                    double balance = scanner.nextDouble();
                    System.out.println("Select account type (1 for Savings, 2 for Current):");
                    int type = scanner.nextInt();
                    bank.createAccount(name,balance,type);
                    break;
                case 2:
                    System.out.println("Enter your name: ");
                    name= scanner.next();
                    bank.displayAccounts(name);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}