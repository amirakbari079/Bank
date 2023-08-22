package bankApp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Menu {
    Scanner scanner = new Scanner(System.in);
    String inputUserName;
    UserManagement userManagement = new UserManagement();
    BanksInitialization banInit = new BanksInitialization();
    HashMap<String, Bank> banks;
    File directory = new File("C:\\Users\\user\\IdeaProjects\\Bank\\allAccount.txt");

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

    public void loginMenu() {

        System.out.println("---*-*-*-*-*-*-*-*-*-*-*---");
        System.out.print("Please Enter Your UserName: ");
        inputUserName = scanner.nextLine();
        System.out.print("Please Enter Your password: ");
        String inputPassword = scanner.nextLine();
        if (userManagement.login(inputUserName, inputPassword))
            accountMenu(inputUserName);
        else {
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

    public void accountMenu(String userName) {
        banks = banInit.run();
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
                moneyTransfer(userName);
                break;
            case "3":
                accountBalance(userName);
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
        int index=Integer.parseInt(scanner.next());
        selectedBank=banks.get(bankHolder.get(index-1));

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

    public void moneyTransfer(String userName) {
        System.out.println("---*-*-*-*-*-*-*-*-*-*-*---");
        Bank selectedBank = null;
        String destinationCard;
        Integer amount;
        accountListHandler(userName);
        selectedBank = banks.get(bankName);
        System.out.print("Enter destination card: ");
        destinationCard = scanner.next();
        findDestinationAccount(destinationCard);
        System.out.print("Enter amount: ");
        amount = Integer.parseInt(scanner.next());
        selectedBank.moneyTransfer(banks,destinationBank,userName,destinationCard,amount);
    }

    public void findDestinationAccount(String destinationCard) {
        for (Map.Entry<String, Bank> entry : banks.entrySet()) {
            if (entry.getValue().bankStartNumber.equals(destinationCard.substring(0, 4)))
                destinationBank = entry.getValue();
        }

    }

    ArrayList<String> accountsOfUser = new ArrayList<>();
    String cardNumber;

    /**
     * @param userName this method shows accounts of the user and then get the account that user want to do something with it
     */
    public void accountListHandler(String userName) {
        findAccount(userName);
        int counter = 0;
        System.out.println("Select your bank account :");
        for (String account : accountsOfUser) {
            counter++;
            System.out.println(counter + "-" + account);
        }
        System.out.print("Enter Your Choice: ");
        int index;
        String data = accountsOfUser.get(Integer.parseInt(scanner.next()) - 1);
        index = data.indexOf(" ");
        bankName = data.substring(index + 1);
        cardNumber = data.substring(0, index - 1);
    }


    public void findAccount(String userName) {
        Scanner fileReader = null;
        try {
            fileReader = new Scanner(directory);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        while (fileReader.hasNextLine()) {
            String data = fileReader.nextLine();
            if (data.contains(userName)) {
                String[] splitData = data.split(" ");
                accountsOfUser.add(splitData[1] + " " + splitData[3]);
            }
        }
    }

    public void accountBalance(String userName) {
        accountListHandler(userName);

        Scanner fileReader = null;
        try {
            fileReader = new Scanner(directory);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (fileReader.hasNextLine()) {
            String data = fileReader.nextLine();
            if (data.contains(cardNumber)) {
                String[] splitData = data.split(" ");
                System.out.println("Your account balance is: " + splitData[2]);
            }
        }
    }

}

