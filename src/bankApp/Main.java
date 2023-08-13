package bankApp;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {


        Scanner userInput = new Scanner(System.in);
        Menu menu=new Menu();
        menu.firstMenu();




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
                System.out.print("Enter The Transfer Amount: ");
                String amount = menuScanner.next();
                Account account = new Account();
                account.transfer(destinationCard, amount);


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
