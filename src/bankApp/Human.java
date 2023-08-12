package bankApp;

import java.io.FileWriter;

public abstract class Human {

    String name, lastName, gender, password, bankName;
    Integer nationalID,accountNumber;

    public Human(String name, String lastName, Integer nationalID, String gender, String password, String userInputBankName,Integer accountNumber) {
        this.name = name;
        this.lastName = lastName;
        this.nationalID = nationalID;
        this.gender = gender;
        this.password = password;
        this.bankName = userInputBankName;
        this.accountNumber=accountNumber;
    }

    public void save(FileWriter file) {

    }


}
