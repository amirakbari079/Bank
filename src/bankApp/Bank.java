package bankApp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Bank {
    String name, bankStartNumber;
    Integer moneyFee;
    ArrayList<Account> allAccount = new ArrayList<>();
    ArrayList<Account> bankAccounts = new ArrayList<>();
    File path;
    Scanner fileScanner;


    protected Bank(String name, Integer moneyFee, String bankStartNumber) {
        this.name = name;
        this.moneyFee = moneyFee;
        this.bankStartNumber = bankStartNumber;
        path = new File("" + name + "\\accountData.txt");
        try {
            fileScanner = new Scanner(path);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        initAccounts();
        try {
            loadAllAccount();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public void openNewAccount(String userName, Integer accountBalance, String accountType) {
        Random randomNumber = new Random();
        String cardNumber = bankStartNumber + Integer.toString(randomNumber.nextInt(99999999));
        Account newAccount = null;
        switch (accountType) {
            case "1": {
                accountType = "freeProfitAccount";
                newAccount = new FreeProfitAccount(userName, cardNumber, accountBalance, name, accountType);
                allAccount.add(newAccount);
                break;
            }
            case "2": {
                accountType = "investmentAccount";
                newAccount = new InvestmentAccount(userName, cardNumber, accountBalance, name, accountType);
                allAccount.add(newAccount);
                break;
            }
        }
        String accountString = newAccount.toString() + "\n";


        // add accountString to bottom
        try (FileWriter fileAccount = new FileWriter(path, true)) {
            fileAccount.write(accountString);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    public void initAccounts() {
        File directory = new File("allAccount.txt");
        String data;
        // Load current accounts of the bank
        while (fileScanner.hasNextLine()) {
            data = fileScanner.nextLine();
            // Load all account in one file
            try (FileWriter addAll = new FileWriter(directory, true)) {
                addAll.write(data + "\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            bankAccounts.add(AccountManger.stringToObject(data));

        }

    }

    public void loadAllAccount() throws FileNotFoundException {
        // load accounts of the bank
        File path = new File("allAccount.txt");
        fileScanner = new Scanner(path);
        while (fileScanner.hasNextLine()) {
            String data = fileScanner.nextLine();
            Account tempAccount = AccountManger.stringToObject(data);
            allAccount.add(tempAccount);
        }
    }

//    public Account stringToObject(String data) {
//        String[] account = data.split(" ");
//        Account newAccount;
//        switch (account[4]) {
//            case "freeProfitAccount":
//                return newAccount = new FreeProfitAccount(account[0], account[1], Integer.parseInt(account[2]), account[3], account[4]);
//            case "investmentAccount":
//                return newAccount = new InvestmentAccount(account[0], account[1], Integer.parseInt(account[2]), account[3], account[4]);
//        }
//        return null;
//
//    }

    Account currentAccount;

    public void moneyTransfer(HashMap<String, Bank> banks, Bank destinationBank, String currentUser, String destinationCard, Integer amount) {
        if (isAccountExist(banks, destinationCard)) {
            if (moneyTransferOriginBank(amount, currentUser))
                destinationBank.moneyTransferDestinationBank(destinationCard, amount);
        } else System.out.println("The desired card number does not exist");
    }

    int index;

    private Boolean isAccountExist(HashMap<String, Bank> banks, String destinationCard) {
        int startNumber = Integer.parseInt(destinationCard.substring(0, 4));

        for (Map.Entry<String, Bank> entry : banks.entrySet()) {
            if (entry.getValue().bankStartNumber.equals(destinationCard.substring(0, 4))) {
                for (Account account : entry.getValue().bankAccounts)
                    if (account.accountNumber.equals(destinationCard)) {
                        return true;
                    }
            }

        }

        return false;
    }
    

    public void moneyTransferDestinationBank(String destinationCard, Integer amount) {
        clearFile();
        for (Account destAccount : bankAccounts) {
            if (destAccount.accountNumber.equals(destinationCard)) {
                index = bankAccounts.indexOf(destAccount);

                bankAccounts.get(index).accountBalance += amount;
                try (FileWriter fileAccount = new FileWriter(path, true)) {
                    for (Account account2 : bankAccounts) {
                        fileAccount.write(account2.toString() + "\n");
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public Boolean moneyTransferOriginBank(Integer amount, String currentUser) {
        int checkAmount = 0, index;
        for (Account account : bankAccounts) {

            if (account.userName.equals(currentUser)) {
                currentAccount = account;
                moneyFee = (amount * moneyFee) / 100;
                checkAmount = currentAccount.accountBalance - amount;
                if (checkAmount < 0) {
                    System.out.println("Not enough inventory!!!");
                    return false;
                }
                index = bankAccounts.indexOf(currentAccount);
                bankAccounts.get(index).accountBalance -= (amount + moneyFee);
                clearFile();
                try (FileWriter fileAccount = new FileWriter(path, true)) {
                    for (Account account1 : bankAccounts) {
                        fileAccount.write(account1.toString() + "\n");
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                break;
            }
        }
        return true;
    }


    private void clearFile() {
        File directory = new File(String.valueOf(path));
        FileWriter clear = null;
        try {
            clear = new FileWriter(directory, false);
            clear.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}

