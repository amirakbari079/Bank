package bankApp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Menu {
    Scanner userInput = new Scanner(System.in);
    String inputUserName;
    UserManagement userManagement = new UserManagement();
    BanksInitialization banInit = new BanksInitialization();
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

    public void loginMenu() {

        System.out.println("---*-*-*-*-*-*-*-*-*-*-*---");
        System.out.print("Please Enter Your UserName: ");
        inputUserName = userInput.nextLine();
        System.out.print("Please Enter Your password: ");
        String inputPassword = userInput.nextLine();
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
        String userFirstName = userInput.nextLine();
        System.out.print("Please Enter Your LastName: ");
        String userLastName = userInput.nextLine();
        System.out.print("Please Enter Your UserName: ");
        String userName = userInput.nextLine();
        System.out.print("Please Enter Your NationalID: ");
        String userInputNationalID = userInput.nextLine();
        System.out.print("Please Enter Your Gender(Male/FeMale): ");
        String userGender = userInput.nextLine();
        System.out.print("Please Enter Your password: ");
        String userInputPassword = userInput.nextLine();

        // Register A User
        userManagement.signUp(userFirstName, userLastName, userName, userInputNationalID, userGender, userInputPassword);
    }

    public void accountMenu(String userName) {

        banks = banInit.run();
        System.out.println("---*-*-*-*-*-*-*-*-*-*-*---");
        System.out.println("1-Open a new account");
        System.out.println("2-Money transfer");
        System.out.println("3-Receive a loan");
        System.out.println("4-Balance");
        System.out.print("Enter Your Choice: ");

        switch (userInput.next()) {
            case "1":
                openNewAccount();
                break;
            case "2":
                monyTransfer(userName);
                break;
            case "3":
                System.out.println("3");
                break;
        }

    }

    public void openNewAccount() {
        System.out.println("---*-*-*-*-*-*-*-*-*-*-*---");
        System.out.println("Enter your bank Name");
        int counter = 0;
        for (Bank bank : banks.values()) {
            counter++;
            System.out.println(counter + "-" + bank.name);
        }
        System.out.print("Enter Your Choice: ");
        Bank selectedBank = null;
        switch (userInput.next()) {
            case "1":
                selectedBank = banks.get("meli");
                break;
            case "2":
                selectedBank = banks.get("melat");
                break;
            case "3":
                selectedBank = banks.get("resalat");
        }

        System.out.println("---*-*-*-*-*-*-*-*-*-*-*---");
        System.out.print("Enter your initial deposit :");
        Integer accountBalance = Integer.parseInt(userInput.next());
        System.out.println("---*-*-*-*-*-*-*-*-*-*-*---");
        System.out.println("Enter your account type :");
        System.out.println("1-Free profit account");
        System.out.println("2-Investment account");
        System.out.print("Enter the name of your bank: ");
        String accountType = userInput.next();
        selectedBank.openNewAccount(inputUserName, accountBalance, accountType);
    }

    public void monyTransfer(String userName) {
        System.out.println("---*-*-*-*-*-*-*-*-*-*-*---");
        showAccountsList(userName);
        System.out.print("Enter Your Choice: ");
        userInput.next();

//        Bank selectedBank = null;
    }

    void showAccountsList(String userName) {
        File directory = new File("C:\\Users\\user\\IdeaProjects\\Bank\\allAccount.txt");
        Scanner fileReader = null;
        try {
            fileReader = new Scanner(directory);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ArrayList<String> accountsOfUser = new ArrayList<>();

        while (fileReader.hasNextLine()) {
            String data = fileReader.nextLine();
            if (data.contains(userName)) {
                String[] splitData = data.split(" ");
                accountsOfUser.add(splitData[1] + " " + splitData[3]);
            }
        }
        int counter = 0;
        System.out.println("Select your banck account :");
        for (String account : accountsOfUser) {
            counter++;
            System.out.println(counter + "-" + account);
        }
    }
}

