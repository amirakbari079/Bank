package bankApp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Bank {
    String name, bankStartNumber;
    Integer moneyFee;
    Account bankAccounts;
    ArrayList<Account> accounts = new ArrayList<>();
    File path;
    Scanner fileSccaner;


    protected Bank(String name, Integer moneyFee, String bankStartNumber) {
        this.name = name;
        this.moneyFee = moneyFee;
        this.bankStartNumber = bankStartNumber;
        path = new File("C:\\Users\\user\\IdeaProjects\\Bank\\" + name + "\\accountData.txt");
        try {
            fileSccaner = new Scanner(path);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        loadAllAcount();
    }


    public void openNewAccount(String userName, Integer accountBalance, String accountType) {
        Random randomNumber = new Random();
        String cardNumber = bankStartNumber + Integer.toString(randomNumber.nextInt(99999999));
        Account newAccount = null;
        switch (accountType) {
            case "1": {
                accountType = "freeProfitAccount";
                newAccount = new FreeProfitAccount(userName, cardNumber, accountBalance, name, accountType);
                break;
            }
            case "2": {
                accountType = "investmentAccount";
                newAccount = new InvestmentAccount(userName, cardNumber, accountBalance, name, accountType);
                break;
            }
        }
        String accountString = newAccount.toString();


        // add accountString to bottom
        try (FileWriter fileAccount = new FileWriter(path, true)) {
            fileAccount.write(accountString);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    public void loadAllAcount() {
        File directory = new File("C:\\Users\\user\\IdeaProjects\\Bank\\allAccount.txt");
        while (fileSccaner.hasNextLine()) {
            String data = fileSccaner.nextLine();
            // Load all account in one file
            try (FileWriter addAll = new FileWriter(directory, true)) {
                addAll.write(data + "\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            // load accounts of the bank
            Account allAccaount = stringToObject(data);
            accounts.add(allAccaount);

        }


    }

    public Account stringToObject(String data) {
        String[] account = data.split(" ");
        Account newAccount;
        switch (account[4]) {
            case "freeProfitAccount":
                return newAccount = new FreeProfitAccount(account[0], account[1], Integer.parseInt(account[2]), account[3], account[4]);
            case "investmentAccount":
                return newAccount = new InvestmentAccount(account[0], account[1], Integer.parseInt(account[2]), account[3], account[4]);
        }
        return null;

    }

    Account currentAccount;
    public void MoneyTransfer(String curentUser, String destinationCard, Integer amount) {
        Boolean flag = false;
        int checkAmount=0;
        for (Account account : accounts) {
            if (account.userName.equals(curentUser)) {
                flag = true;
                currentAccount=account;
                checkAmount=currentAccount.accountBalance-amount;
                if (checkAmount>0){
                    currentAccount.accountBalance-=amount;
                }else{
                    System.out.println("Not enough inventory!!!");
                }
                break;
            }
        }

    }


}

