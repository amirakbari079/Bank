package bankApp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Menu {
    Scanner scanner = new Scanner(System.in);
    String inputUserName;
    UserManagement userManagement = new UserManagement();
    BanksInitialization bankInit = new BanksInitialization();
    HashMap<String, Bank> banks;
    public void firstMenu() {


        // Menu
        System.out.println("---*-*-*-*-*-*-*-*-*-*-*---");
        System.out.println("Please Login To Your Account Or Signup");
        System.out.println("1.Login");
        System.out.println("2.Signup");
        Scanner menuScanner = new Scanner(System.in);
        System.out.print("Enter Your Choice: ");

        // Deploying Login And Signup
        switch (menuScanner.nextLine()) {
            // Login
            case "1": {
                loginMenu();
            }
            break;
            // Signup
            case "2": {
                signUpMenu();
            }
            break;
            default:

                System.out.println("Wrong Input");
                firstMenu();
        }
    }

    public Customer currentUser;

    public void loginMenu() {

        System.out.println("---*-*-*-*-*-*-*-*-*-*-*---");
        System.out.print("Please Enter Your UserName: ");
        inputUserName = scanner.nextLine();
        System.out.print("Please Enter Your password: ");
        String inputPassword = scanner.nextLine();
        currentUser = userManagement.login(inputUserName, inputPassword);
        if (currentUser != null) {
            accountMenu();
        } else {
            System.out.println("Wrong userName or Password. Try again");
            loginMenu();
        }
    }

    public void signUpMenu() {
        System.out.println("---*-*-*-*-*-*-*-*-*-*-*---");
        System.out.print("Please Enter Your Name: ");
        String userFirstName = scanner.nextLine();
        System.out.print("Please Enter Your LastName: ");
        String userLastName = scanner.nextLine();
        System.out.print("Please Enter Your UserName: ");
        String userName = scanner.nextLine();
        System.out.print("Please Enter Your NationalID: ");
        String userInputNationalID = scanner.nextLine();
        System.out.print("Please Enter Your Gender(Male/FeMale): ");
        String userGender = scanner.nextLine();
        System.out.print("Please Enter Your password: ");
        String userInputPassword = scanner.nextLine();

        // Register A User
        userManagement.signUp(userFirstName, userLastName, userName, userInputNationalID, userGender, userInputPassword);
    }

    public void accountMenu() {
        banks = bankInit.run();
        System.out.println("---*-*-*-*-*-*-*-*-*-*-*---");
        System.out.println("1-Open a new account");
        System.out.println("2-Money transfer");
        System.out.println("3-Balance");
        System.out.print("Enter Your Choice: ");

        switch (scanner.next()) {
            case "1":
                openNewAccount();
                break;
            case "2":
                moneyTransfer();
                break;
            case "3":
                accountBalance();
                break;
        }

    }

    public void openNewAccount() {
        System.out.println("---*-*-*-*-*-*-*-*-*-*-*---");
        ArrayList<String> bankHolder = new ArrayList<>();
        System.out.println("Choice your bank name");
        int counter = 0;
        for (Bank bank : banks.values()) {
            counter++;
            System.out.println(counter + "-" + bank.name);
            bankHolder.add(bank.name);
        }
        System.out.print("Enter Your Choice: ");
        Bank selectedBank = null;
        int index = Integer.parseInt(scanner.next());
        selectedBank = banks.get(bankHolder.get(index - 1));

        System.out.println("---*-*-*-*-*-*-*-*-*-*-*---");
        System.out.print("Enter your initial deposit :");
        Integer accountBalance = Integer.parseInt(scanner.next());
        System.out.println("---*-*-*-*-*-*-*-*-*-*-*---");
        System.out.println("Enter your account type :");
        System.out.println("1-Free profit account");
        System.out.println("2-Investment account");
        System.out.print("Enter the name of your bank: ");
        String accountType = scanner.next();
        selectedBank.openNewAccount(inputUserName, accountBalance, accountType);
    }

    Bank destinationBank = null;
    String bankName;

    public void moneyTransfer() {
        System.out.println("---*-*-*-*-*-*-*-*-*-*-*---");
        Bank selectedBank = null;
        String destinationCard;
        int amount;
        accountListHandler();

        selectedBank = banks.get(bankName);
        System.out.print("Enter destination card: ");
        destinationCard = scanner.next();
        destinationBank = selectedBank.findDestinationAccount(destinationCard, banks);
        System.out.print("Enter amount: ");
        amount = Integer.parseInt(scanner.next());
        selectedBank.moneyTransfer(banks, destinationBank, currentUser.userName, destinationCard, amount);
    }

//    public void findDestinationBank(String destinationCard) {
//        for (Map.Entry<String, Bank> entry : banks.entrySet()) {
//            if (entry.getValue().bankStartNumber.equals(destinationCard.substring(0, 4)))
//                destinationBank = entry.getValue();
//        }
//
//    }

    ArrayList<String> accountsOfUser = new ArrayList<>();
    Account selectedAccount;

    public void accountListHandler() {
        currentUser.getAllAccounts(banks, currentUser);
//        findAccountsOfUser(userName);
        System.out.println("Select your bank account :");
        for (int i = 0; i < currentUser.accountsOfUser.size(); i++) {
            System.out.println(i + 1 + "-" + currentUser.accountsOfUser.get(i));
        }

        System.out.print("Enter Your Choice: ");
        int index = Integer.parseInt(scanner.next()) - 1;
        selectedAccount = currentUser.accountsOfUser.get(index);
        bankName = selectedAccount.bankName;

//        int counter = 0;
//        for (String account : accountsOfUser) {
//            counter++;
//        }
//        int index;
//        String data = accountsOfUser.get(Integer.parseInt(scanner.next()) - 1);
//        index = data.indexOf(" ");
//        cardNumber = data.substring(0, index - 1);
    }


//    public void findAccountsOfUser(String userName) {
//        Scanner fileReader = null;
//        try {
//            fileReader = new Scanner(directory);
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//
//        while (fileReader.hasNextLine()) {
//            String data = fileReader.nextLine();
//            if (data.contains(userName)) {
//                String[] splitData = data.split(" ");
//                accountsOfUser.add(splitData[1] + " " + splitData[3]);
//            }
//        }
//    }

    public void accountBalance() {
        accountListHandler();


//        Scanner fileReader = null;
//        try {
//            fileReader = new Scanner(directory);
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//        while (fileReader.hasNextLine()) {
//            String data = fileReader.nextLine();
//            if (data.contains(selectedAccount.accountNumber)) {
//                String[] splitData = data.split(" ");
//                System.out.println("Your account balance is: " + splitData[2]);
//            }
//        }
    }

}

