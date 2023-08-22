package bankApp;

public class AccountManger {

    public static Account stringToObject(String data){
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
}
