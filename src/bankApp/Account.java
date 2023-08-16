package bankApp;

public abstract class Account {
    String bankName, accountType, accountNumber, userName;
    Integer accountBalance;
//    File readAccantData = new File("C:\\Users\\user\\IdeaProjects\\Bank\\accountData.txt");
//    Scanner fileScanner;
//
//    {
//        try {
//            fileScanner = new Scanner(readAccantData);
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//    }


//    public Account() {
//        int count=0;
//        List<Account> accounts=new ArrayList<>();
//        while (fileScanner.hasNextLine()) {
//            String data = fileScanner.nextLine();
//            List<String> accountDataList;
//            String accountData[] = data.split(" ");
//            accountDataList = Arrays.asList(accountData);
//            Account account=new Account(Integer.parseInt(accountDataList.get(0)),accountDataList.get(1),Integer.parseInt(accountDataList.get(2)),Integer.parseInt(accountDataList.get(3)));
//            accounts.add(account);
//
//
//        }
//    }

    public Account(String userName, String cardNumber, Integer accountBalance, String bankName, String accountType) {
        this.userName = userName;
        this.accountNumber = cardNumber;
        this.accountBalance = accountBalance;
        this.bankName = bankName;
        this.accountType = accountType;
    }

    @Override
    public String toString() {
        return userName + ' ' + accountNumber + ' ' + accountBalance + ' ' + bankName + ' ' + accountType+"n";
    }




    //    public void openAccount() {
//        try (FileWriter accountData = new FileWriter("accountData.txt", true)) {
//            accountData.write("userId + accountNumber + accountBalance + bankName");
//            accountData.write(userId + " " + accountNumber + " " + bankName + "-" + accountBalance + "\n");
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public void transfer(String destinationCard, String amount) {
//        File myfile = new File("C:\\Users\\user\\IdeaProjects\\Bank\\accountData.txt");
//
//        try {
//            fileScanner = new Scanner(myfile);
//            File tempFile = new File("tempFile.txt");
//            tempFile.createNewFile();
//        } catch (IOException e) {
//
//            e.printStackTrace();
//        }
//
//        try (FileWriter fw = new FileWriter("tempFile.txt", true);) {
//            while (fileScanner.hasNextLine()) {
//                String data = fileScanner.nextLine();
//                if (data.contains(destinationCard)) {
//                    int index = data.indexOf("-");
//                    String balance = data.substring(index + 1, data.length());
//                    balance = amount;
//                    fw.write(data.substring(0, index) + amount + "\n");
//                    continue;
//                }
//                fw.write(data + "\n");
//
//
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//    }


}
