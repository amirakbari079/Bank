package bankApp;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {


    public static void main(String[] args) {
        String file = "allAccount.txt";
        clearFile(file);
        Menu menu = new Menu();
        menu.firstMenu();
    }

    private static void clearFile(String file) {
        File directory = new File(file);
        FileWriter clear;
        try {
            clear = new FileWriter(directory, false);
            clear.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
