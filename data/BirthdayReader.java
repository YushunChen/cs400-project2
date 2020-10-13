import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class BirthdayReader {
 
    static ArrayList<Birthday> birthdayList;

    public static void getBirthdaysFromCSV(String filename) {

        File file = null;
        Scanner filescanner = null;

        // open file reader or catch exception
        try {
            file = new File(filename);
            filescanner = new Scanner(file);  
            
        } catch (FileNotFoundException e1) {
            System.out.println("Error reading file or file not found!");
            return;
        } 

        String next = filescanner.nextLine();
        System.out.println(next);




        filescanner.close();

    }
}
