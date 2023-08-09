package bankApp;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String userName, userLastName, userGender, userPassword, checkUserPass, activeUser;
        Integer indexOfSubstring, userNationalID;
        Scanner userInput = new Scanner(System.in);
        // Menu
        System.out.println("Please Login Or Signup");
        System.out.println("1.Login");
        System.out.println("2.Signup");
        Scanner menuScanner = new Scanner(System.in);
        System.out.print("Enter 1 For Login / 2 For SignUp: ");
        // Deploying Login And Signup
        switch (menuScanner.nextLine()) {
            // Login
            case "1": {
                System.out.println("---*-*-*-*-*-*-*-*-*-*-*---");
                System.out.print("Please Enter Your National ID: ");
                userNationalID = Integer.parseInt(userInput.nextLine());
                System.out.print("Please Enter Your password: ");
                userPassword = userInput.nextLine();
                try {
                    File myfile = new File("C:\\Users\\user\\IdeaProjects\\Bank\\userData.txt");
                    Scanner fileScanner = new Scanner(myfile);
                    while (fileScanner.hasNextLine()) {
                        String data = fileScanner.nextLine();
                        indexOfSubstring = data.indexOf("-");
                        checkUserPass = data.substring(0, indexOfSubstring);
                        if (checkUserPass.equals(userNationalID + " " + userPassword)) {
                            String subTxt = data.substring(indexOfSubstring + 1, data.length());
                            String name = subTxt.substring(0, subTxt.indexOf(" "));
                            System.out.println(name + " Wellcome To Your Panel.");
                            activeUser = data;
                            System.out.println(activeUser);
                        }
                        ;
                    }
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
            }
            break;
            case "2": {
                System.out.println("---*-*-*-*-*-*-*-*-*-*-*---");
                System.out.print("Please Enter Your Name: ");
                userName = userInput.nextLine();
                System.out.print("Please Enter Your LastName: ");
                userLastName = userInput.nextLine();
                System.out.print("Please Enter Your NationalID: ");
                userNationalID = Integer.parseInt(userInput.nextLine());
                System.out.print("Please Enter Your Gender(Male/FeMale): ");
                userGender = userInput.nextLine();
                System.out.print("Please Enter Your password: ");
                userPassword = userInput.nextLine();


                try (FileWriter userData = new FileWriter("userData.txt", true)) {
                    File myfile = new File("C:\\Users\\user\\IdeaProjects\\Bank\\userData.txt");
                    Scanner fileScanner = new Scanner(myfile);
                    while (fileScanner.hasNextLine()) {
                        String data = fileScanner.nextLine();
                        int sub = data.indexOf(" ");
                        if (userNationalID.toString().equals(data.substring(0, sub))) {
                            throw new IOException("This National ID Has Been Registered Before!");
                        }
                    }
                    Customer newUser = new Customer(userName, userLastName, userNationalID, userGender, userPassword);


                    userData.write(newUser.nationalID + " " + newUser.password + "-" + newUser.name + " " + newUser.lastName + " " + newUser.gender + "\n");
                } catch (IOException e) {
                    System.out.println(e.getMessage());

                }

            }
            break;
        }


        // Create User Data File
//        try(File userFile=new File("userData.txt")) {
//            if (userFile.createNewFile()){
//                System.out.println("File created: " + userFile.getName());
//            }else {
//                System.out.println("File already exists.");
//            }
//        }catch (IOException e){
//            System.out.println("An error occurred.");
//            e.printStackTrace();
//        }

        // Save User Data


    }

}
