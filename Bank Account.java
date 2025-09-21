import java.util.Scanner;

class Account {
    static long baseAccountNumber;
    static {
        baseAccountNumber = 1000000;
        System.out.println("Banking system ready");
    }

    long accountNumber;
    String accountHolderName;
    double balance;
    String email;
    String phoneNumber;

    {
        accountNumber = ++baseAccountNumber;
        System.out.println("New account number: " + accountNumber);
    }

    Account(String name, double amount, String mail, String phone) {
        accountHolderName = name;
        balance = amount;
        email = mail;
        phoneNumber = phone;
        System.out.println("Account created for " + name);
    }

    void depositMoney(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited " + amount + ", balance: " + balance);
        } else System.out.println("Amount must be positive");
    }

    void withdrawMoney(double amount) {
        if (amount > 0) {
            if (amount <= balance) {
                balance -= amount;
                System.out.println("Withdrawn " + amount + ", balance: " + balance);
            } else System.out.println("Not enough balance");
        } else System.out.println("Amount must be positive");
    }

    void updateContactDetails(String mail, String phone) {
        email = mail;
        phoneNumber = phone;
        System.out.println("Contact details updated");
    }

    void displayAccountDetails() {
        System.out.println("---- Account Information ----");
        System.out.println("Account No: " + accountNumber);
        System.out.println("Holder Name: " + accountHolderName);
        System.out.println("Balance: " + balance);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phoneNumber);
        System.out.println("-----------------------------");
    }
}

class BankingApplication {
    Account[] accounts = new Account[50];
    int accountCount = 0;
    Scanner sc = new Scanner(System.in);

    void createNewAccount() {
        if (accountCount >= accounts.length) {
            System.out.println("Account limit reached");
            return;
        }
        System.out.print("Enter Account Holder Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Initial Deposit Amount: ");
        double balance = sc.nextDouble();
        sc.nextLine();
        System.out.print("Enter Email Address: ");
        String email = sc.nextLine();
        System.out.print("Enter Phone Number: ");
        String phone = sc.nextLine();

        accounts[accountCount++] = new Account(name, balance, email, phone);
        System.out.println("Account created successfully!");
    }

    Account findAccountByNumber(long number) {
        for (int i = 0; i < accountCount; i++) {
            if (accounts[i].accountNumber == number) return accounts[i];
        }
        return null;
    }

    void performDepositMoney() {
        System.out.print("Enter Account Number: ");
        long number = sc.nextLong();
        System.out.print("Enter Amount to Deposit: ");
        double amount = sc.nextDouble();
        sc.nextLine();
        Account acc = findAccountByNumber(number);
        if (acc != null) acc.depositMoney(amount);
        else System.out.println("Account not found");
    }

    void performWithdrawMoney() {
        System.out.print("Enter Account Number: ");
        long number = sc.nextLong();
        System.out.print("Enter Amount to Withdraw: ");
        double amount = sc.nextDouble();
        sc.nextLine();
        Account acc = findAccountByNumber(number);
        if (acc != null) acc.withdrawMoney(amount);
        else System.out.println("Account not found");
    }

    void showAccountDetailsByNumber() {
        System.out.print("Enter Account Number: ");
        long number = sc.nextLong();
        sc.nextLine();
        Account acc = findAccountByNumber(number);
        if (acc != null) acc.displayAccountDetails();
        else System.out.println("Account not found");
    }

    void updateAccountContactDetails() {
        System.out.print("Enter Account Number: ");
        long number = sc.nextLong();
        sc.nextLine();
        Account acc = findAccountByNumber(number);
        if (acc != null) {
            System.out.print("Enter New Email: ");
            String email = sc.nextLine();
            System.out.print("Enter New Phone Number: ");
            String phone = sc.nextLine();
            acc.updateContactDetails(email, phone);
        } else System.out.println("Account not found");
    }

    void showMainMenu() {
        int choice;
        do {
            System.out.println("\n===== Banking Application Menu =====");
            System.out.println("1. Create a New Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. View Account Details");
            System.out.println("5. Update Contact Details");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> createNewAccount();
                case 2 -> performDepositMoney();
                case 3 -> performWithdrawMoney();
                case 4 -> showAccountDetailsByNumber();
                case 5 -> updateAccountContactDetails();
                case 6 -> System.out.println("Thank you for using the Banking Application!");
                default -> System.out.println("Invalid choice, please try again");
            }
        } while (choice != 6);
    }

    public static void main(String[] args) {
        new BankingApplication().showMainMenu();
    }
}