package JavaSwingGUI;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileWriter {

    public void readFile(String fileName) {
        try {
            Scanner file = new Scanner(new File(fileName));
            file.close();
        }
        catch (IOException e) {
            System.out.println("Wrong file name!");
        }
    }

    public void writeFile(String gson, String fileName) {
        try {
            PrintWriter file = new PrintWriter(new java.io.FileWriter(fileName));
            file.println(gson);
            file.close();
        }
        catch (IOException e) {
            System.out.println("Wrong file name!");
        }
    }

}
