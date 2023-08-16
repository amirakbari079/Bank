package bankApp;

import java.util.ArrayList;
import java.util.HashMap;

public class BanksInitialization {

    public HashMap<String, Bank> run() {
        HashMap<String, Bank> banks = new HashMap<>();
        banks.put("meli", new Bank("meli", 23,"1020"));
        banks.put("melat", new Bank("melat", 25,"6037"));
        banks.put("resalat", new Bank("resalat", 15,"5041"));
        return banks;
    }
}
