package bankApp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Customer extends Human {
    public Customer(String name, String lastName,String userName, String nationalID, String gender, String password ) {
        super(name, lastName,userName, nationalID, gender, password);
    }
    ArrayList<Account> accountsOfUser=new ArrayList<>();
    public ArrayList<Account> getAllAccounts(HashMap<String, Bank> banks,Customer currentUser){
        for (Map.Entry<String, Bank> entry : banks.entrySet()) {
            accountsOfUser.add(entry.getValue().findAccountsOfCurrentUser(currentUser.userName));
        }
        return null;
    }

}
