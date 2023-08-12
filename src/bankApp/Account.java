package bankApp;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Account {
    String bankName;
    Integer accountBalance, userId, accountNumber;

    public Account(Integer accountNumber, String bankName, Integer accountBalance, Integer userId) {
        this.accountNumber = accountNumber;
        this.bankName = bankName;
        this.accountBalance = accountBalance;
        this.userId = userId;
    }

    public void openAccount() {
        try (FileWriter accountData = new FileWriter("accountData.txt");) {
//            accountData.write("userId + accountNumber + accountBalance + bankName");
            accountData.write(userId + " " + accountNumber + " " + accountBalance + " " + bankName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void transfer(){
        File tempFile=new File("tempFile.txt");

    }


}
