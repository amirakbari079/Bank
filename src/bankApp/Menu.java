package bankApp;

import java.util.Scanner;

public class Menu {
    Scanner userInput = new Scanner(System.in);
    UserManagement userManagement = new UserManagement();

    public void firstMenu() {


        // Menu
        System.out.println("---*-*-*-*-*-*-*-*-*-*-*---");
        System.out.println("Please Login To Your Account Or Open A New Account");
        System.out.println("1.Login");
        System.out.println("2.Opne a new account");
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
        System.out.print("Please Enter Your National ID: ");
        String userInputNationalID = userInput.nextLine();
        System.out.print("Please Enter Your password: ");
        String userInputPassword = userInput.nextLine();


        userManagement.login(userInputNationalID, userInputPassword);
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
}

