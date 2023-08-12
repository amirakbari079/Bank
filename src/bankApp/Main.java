package bankApp;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        File myfile = new File("C:\\Users\\user\\IdeaProjects\\Bank\\userData.txt");
        Scanner fileScanner = new Scanner(myfile);
        File tempFile = new File("tempFile.txt");
        tempFile.createNewFile();

        try (FileWriter fw = new FileWriter("tempFile.txt", true);) {
            while (fileScanner.hasNextLine()) {
                String data = fileScanner.nextLine();
                if (data.contains("2567")) {

                }
                fw.write(data + "\n");


            }
        }

        Scanner userInput = new Scanner(System.in);


        // Menu
        System.out.println("Please Login To Your Account Or Open A New Account");
        System.out.println("1.Login");
        System.out.println("2.Opne a new account");
        Scanner menuScanner = new Scanner(System.in);
        System.out.print("Enter Your Choice: ");

        // Deploying Login And Signup
        switch (menuScanner.nextLine()) {
            // Login
            case "1": {
                System.out.println("---*-*-*-*-*-*-*-*-*-*-*---");
                System.out.print("Please Enter Your National ID: ");
                Integer userInputNationalID = Integer.parseInt(userInput.nextLine());
                System.out.print("Please Enter Your password: ");
                String userInputPassword = userInput.nextLine();

                Main main = new Main();
                main.Login(userInputNationalID, userInputPassword);

                main.accountMenu();
            }
            break;
            // Signup
            case "2": {
                System.out.println("---*-*-*-*-*-*-*-*-*-*-*---");
                System.out.print("Please Enter Your Name: ");
                String userName = userInput.nextLine();
                System.out.print("Please Enter Your LastName: ");
                String userLastName = userInput.nextLine();
                System.out.print("Please Enter Your NationalID: ");
                Integer userInputNationalID = Integer.parseInt(userInput.nextLine());
                System.out.print("Please Enter Your Gender(Male/FeMale): ");
                String userGender = userInput.nextLine();
                System.out.print("Please Enter Your password: ");
                String userInputPassword = userInput.nextLine();
                System.out.print("Please Enter Your Bank Name(Melat/Meli/Resalat): ");
                String userInputBankName = userInput.nextLine();

                // Register A User
                Bank bank = new Bank("meli");
                bank.register(userInputNationalID, userName, userLastName, userGender, userInputPassword, userInputBankName);

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

    public void Login(Integer userInputNationalID, String userInputPassword) {
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
    }

    public void accountMenu() {
        Scanner menuScanner = new Scanner(System.in);
        System.out.println("---*-*-*-*-*-*-*-*-*-*-*---");
        System.out.println("1-Money transfer");
        System.out.println("2-Receive a loan");
        System.out.println("3-Balance");
        System.out.print("Enter Your Choice: ");

        switch (menuScanner.next()) {
            case "1":
                System.out.print("Enter Destination Card: ");
                String destinationCard = menuScanner.next();
                break;
            case "2":
                System.out.println("2");
                break;
            case "3":
                System.out.println("3");
                break;
        }

    }


}
