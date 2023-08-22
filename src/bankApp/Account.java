package bankApp;

public abstract class Account {
    String bankName, accountType, accountNumber, userName;
    Integer accountBalance;

    public Account(String userName, String cardNumber, Integer accountBalance, String bankName, String accountType) {
        this.userName = userName;
        this.accountNumber = cardNumber;
        this.accountBalance = accountBalance;
        this.bankName = bankName;
        this.accountType = accountType;
    }

    @Override
    public String toString() {
        return userName + ' ' + accountNumber + ' ' + accountBalance + ' ' + bankName + ' ' + accountType;
    }

}
