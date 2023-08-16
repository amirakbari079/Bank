package bankApp;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FreeProfitAccount extends Account {
    public FreeProfitAccount(String userName, String cardNumber, Integer accountBalance, String bankName, String accountType) {
        super(userName, cardNumber, accountBalance, bankName, accountType);

        // Create Directory
        File FreeProfitAccountFile = new File("C:\\Users\\user\\IdeaProjects\\Bank\\FreeProfitAccountFile.txt");
        try {
            FreeProfitAccountFile.createNewFile();

        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileWriter insertAccount = new FileWriter(FreeProfitAccountFile);) {
            insertAccount.write(userName + " " + cardNumber + " " + bankName + " " + accountBalance + " " + accountType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
