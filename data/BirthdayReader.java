// --== CS400 File Header Information ==--
// Name: Patrick Harvey
// Email: plharvey@wisc.edu
// Team: Team AB
// TA: Sophie Stephenson
// Lecturer: Gary Dahl
// Notes to Grader: Used within Project 2 for Birthday Search Tool
// Contribution: Data Wrangler section


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class attempts to read from a csv file the birthdays 
 * and names listed therein. The class contains an array list with the 
 * produced birthdays for later usage.
 * 
 * @author Patrick Harvey
 */
public class BirthdayReader {
 
    // contains the birthdays read from files
    private ArrayList<Birthday> birthdayList;

    /**
     * This constructor initializes this birthday reader
     * that contains the list of birthdays read from a csv file.
     */
    public BirthdayReader() {
        birthdayList = new ArrayList<>();
    }

    /**
     * This method returns the birthday array list from this class
     * containing the added birthdays.
     * 
     * @return the birthday list from this reader 
     */
    public ArrayList<Birthday> getBirthdayList() {
        return birthdayList;
    }

    /**
     * This method reads from a string filename of a csv file and converts
     * its contents into an array list of birthdays.
     * 
     * @param filename the string name of the csv file
     * @return returns true if the file was successfully read or false if otherwise
     */
    public boolean getBirthdaysFromCSV(String filename) {

        File file = null;
        Scanner filescanner = null;

        // open file reader or catch exception
        try {
            // initiate file
            file = new File(filename);
            //load file to scanner for reading read with comma delimiter
            filescanner = new Scanner(file).useDelimiter(",");  
            
        } catch (FileNotFoundException e1) {
            System.out.println("Error reading file or file not found!");
            return false;
        } 

        // birthday line buffer
        String birthdayLine[];
        // skip initial line
        filescanner.nextLine();

        // iterate through file contents with scanner
        while (filescanner.hasNext()) {
            
            // get line of birthday into buffer
            birthdayLine = filescanner.nextLine().split(",");

            // create and add new birthday to birthday list
            try {
                birthdayList.add(new Birthday(birthdayLine[0], birthdayLine[1], birthdayLine[2]));
            } catch (IllegalBirthdayFormatException e) {
                System.out.println(e.getMessage());
            }
        }

        // close scanner
        filescanner.close();
        return true;
    }
}
