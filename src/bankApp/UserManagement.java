package bankApp;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class UserManagement {
    public boolean signUp(String userFirstName, String userLastName, String userName, String userInputNationalID, String userGender, String userInputPassword) {
        try (FileWriter userData = new FileWriter("userData.txt", true)) {

            // Check Duplicate User
            File myfile = new File("C:\\Users\\user\\IdeaProjects\\Bank\\userData.txt");
            Scanner fileScanner = new Scanner(myfile);
            while (fileScanner.hasNextLine()) {
                String data = fileScanner.nextLine();
                int sub = data.indexOf(" ");
                if (userName.equals(data.substring(0, sub))) {
                    throw new IOException();

                }
            }
            // Create An Instance From Customer

            Customer newUser = new Customer(userFirstName, userLastName, userName, userInputNationalID, userGender, userInputPassword);

            // Write The Data To Text File
            userData.write(newUser.userName + " " + newUser.nationalID + " " + newUser.password + "-" + newUser.name + " " + newUser.lastName + " " + newUser.gender + "\n");
            System.out.println("Registration Has Been Successfully");


        } catch (IOException e) {
            System.out.println("This UserName Has Been Registered Before!");
            Menu menu=new Menu();
            menu.firstMenu();
        }
        return false;
    }

    public boolean login(String userInputNationalID, String userInputPassword) {
        try {
            File userDataFile = new File("C:\\Users\\user\\IdeaProjects\\Bank\\userData.txt");
            Scanner fileScanner = new Scanner(userDataFile);
            while (fileScanner.hasNextLine()) {
                String data = fileScanner.nextLine();
                int indexOfSubstring = data.indexOf("-");
                String checkUserPass = data.substring(0, indexOfSubstring);
                if (checkUserPass.equals(userInputNationalID + " " + userInputPassword)) {
                    String subTxt = data.substring(indexOfSubstring + 1, data.length());
                    String name = subTxt.substring(0, subTxt.indexOf(" "));
                    System.out.println(name + " Wellcome To Your Panel.");
                    System.out.println(data);
                }
                ;
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return false;
    }
}