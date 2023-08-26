package bankApp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class UserManagement {
    File userDataFile = new File("C:\\Users\\user\\IdeaProjects\\Bank\\userData.txt");
    Scanner fileScanner;


    public void signUp(String userFirstName, String userLastName, String userName, String userInputNationalID, String userGender, String userInputPassword) {
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
            userData.write(newUser.name + " " + newUser.lastName + " " + newUser.userName + " " + newUser.nationalID + " " + newUser.gender + " " + newUser.password + "\n");
            System.out.println("Registration Has Been Successfully");


        } catch (IOException e) {
            System.out.println("This UserName Has Been Registered Before!");
            Menu menu = new Menu();
            menu.firstMenu();
        }
    }

    public Customer login(String inputUserName, String inputPassword) {
        HashMap<String, Customer> allUser = getAllUser();
        if (allUser.containsKey(inputUserName)) {
            Customer user = allUser.get(inputUserName);
            if (user.password.equals(inputPassword)) {
                System.out.println(user.name + " Welcome to your app");
                return user;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    private HashMap<String, Customer> getAllUser() {

        HashMap<String, Customer> output = new HashMap<>();

        try {
            fileScanner = new Scanner(userDataFile);
            while (fileScanner.hasNextLine()) {
                String data = fileScanner.nextLine();
                String[] accountArray = data.split(" ");

                output.put(accountArray[2], new Customer(accountArray[0], accountArray[1], accountArray[2], accountArray[3], accountArray[4], accountArray[5]));


                //accountDataList = Arrays.asList(accountArray);


            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return output;
    }


}