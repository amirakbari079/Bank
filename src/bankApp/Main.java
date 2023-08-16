package bankApp;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        clearFile();
        Menu menu = new Menu();
        menu.firstMenu();


    }

    private static void clearFile(){
        File directory = new File("C:\\Users\\user\\IdeaProjects\\Bank\\allAccount.txt");
        FileWriter clear = null;
        try {
            clear = new FileWriter(directory, false);
            clear.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
