package bankApp;

import java.io.FileWriter;

public abstract class Human {

    String name, lastName, gender, password,userName,nationalID;
    Integer accountNumber;

    public Human(String name, String lastName,String userName,String nationalID, String gender, String password) {
        this.name = name;
        this.lastName = lastName;
        this.nationalID = nationalID;
        this.userName=userName;
        this.gender = gender;
        this.password = password;
    }



}
