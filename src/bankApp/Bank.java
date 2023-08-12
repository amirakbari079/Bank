package bankApp;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Bank {
    String name;

    protected Bank(String name) {
        name = this.name;

    }

    public void register(Integer userInputNationalID, String userName, String userLastName, String userGender, String userInputPassword, String userInputBankName) {
        try (FileWriter userData = new FileWriter("userData.txt", true)) {
            File myfile = new File("C:\\Users\\user\\IdeaProjects\\Bank\\userData.txt");
            Scanner fileScanner = new Scanner(myfile);
            while (fileScanner.hasNextLine()) {
                String data = fileScanner.nextLine();
                int sub = data.indexOf(" ");
                if (userInputNationalID.toString().equals(data.substring(0, sub))) {
                    throw new IOException("This National ID Has Been Registered Before!");
                }
            }

            // Create An Instance From Customer
            Integer accountNumber = (int) (Math.random() * 1000000);
            Customer newUser = new Customer(userName, userLastName, userInputNationalID, userGender, userInputPassword, userInputBankName, accountNumber);

            Account newAccount = new Account(accountNumber, userInputBankName, 0, userInputNationalID);
            newAccount.openAccount();
            // Write The Data To Text File
            userData.write(newUser.nationalID + " " + newUser.password + "-" + newUser.name + " " + newUser.lastName + " " + newUser.gender + "/" + newUser.bankName + "*" + newUser.accountNumber + "\n");
            System.out.println("Registration Has Been Successfully");
        } catch (IOException e) {
            System.out.println(e.getMessage());

        }
    }

}
