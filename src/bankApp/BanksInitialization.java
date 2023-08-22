package bankApp;

import java.util.ArrayList;
import java.util.HashMap;

public class BanksInitialization {
    public HashMap<String, Bank> run() {
        HashMap<String, Bank> banks = new HashMap<>();
        banks.put("meli", new Bank("meli", 3,"1020"));
        banks.put("melat", new Bank("melat", 5,"6037"));
        banks.put("resalat", new Bank("resalat", 2,"5041"));
        return banks;
    }
}
