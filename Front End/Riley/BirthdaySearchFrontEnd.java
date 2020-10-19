// --== CS400 File Header Information ==--
// Name: Riley Griffin
// Email: rlgriffin2@wisc.edu
// Team: AB
// Role: Front End
// TA: Sophie Stephenson
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

import java.util.Scanner;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;

/**
 * This class represents the front end UI for the Birthday Search Tool
 */
public class BirthdaySearchFrontEnd {

    private static BirthdayTree birthdayTree = new BirthdayTree();

    /**
     * Prints out a welcome message at start of usage.
     */
    public static void welcome() {
        System.out.println("=========================================================================");
        System.out.println("============================ WELCOME TO BST!=============================");
        System.out.println("========================= BIRTHDAY SEARCH TOOL ==========================");
        System.out.println("=========================================================================");
        System.out.println();
        System.out.println("BST is an app that allows you to store birthdays of all the important");
        System.out.println("people in your life without worrying about missing their birthday.");
        System.out.println("Birthdays can be added manually or loaded through a csv file. Our default");
        System.out.println("file contains random birthdays and is called birthdays.csv");
        System.out.println();
        System.out.println("Here is a list of commands available to you: ");
        printUserCommands();
    }
    
    /**
     * Load a csv file of birthdays into the tree.
     * @param input the user input to get data for operation from
     */
    public static void loadCSV(Scanner input){
        System.out.println("Enter csv file name or type q to return to menu:");
        String fileName = "";
        boolean success = false;
        // while file hasn't been successfully loaded or the user hasn't quit
        while(!(success || fileName.equals("q"))){
            fileName = input.nextLine().trim().toLowerCase();
            // check for quit command
            if(fileName.equals("q")){
                System.out.println("Returning to menu.");
                break;
            }
            // check for bad file extensions
            else if(!fileName.contains(".csv")){
                System.out.println("Invalid file name. Must be a csv. Try again.");
            }
            else{
                // success if works
                if(birthdayTree.loadBirthdaysFromReader(fileName)){
                    success = true;
                    System.out.println("CSV file loaded successfully.");
                }
                // if file contents invalid, etc., then retry.
                else{
                    System.out.println("Something went wrong. Try again or type quit(q) to return to menu.");
                }
            }
        }
    }
    /**
     * Adds a birthday object to the birthday tree
     * Birthday object should have the following form for concise search:
     * Long Format for birthday: "yyyy/MM/dd/HH/mm"
     * @param input the user input to get data for operation from
     */
    public static void addBirthday(Scanner input){
        boolean success = false;
        String date = "";
        while(!success){
            // get the first and last name of the person
            System.out.println("Enter first name of the desired person to add:");
            String firstName = input.nextLine().trim();
            System.out.println("Enter last name of the desired person to add:");
            String lastName = input.nextLine().trim();
            // get the date of the birthday
            date = getBirthDate(input);
            Birthday birthday;
            // try putting it all together in an object
            try{birthday = new Birthday(date, firstName, lastName);}
            catch(IllegalBirthdayFormatException e){
                System.out.println("The date of that birthday was malformed");
                return;
            }
            // try adding it to the tree, or catch the duplicate error and exit
            try{
                birthdayTree.addBirthday(birthday);
                success = true;
            }
            catch (BirthdayAlreadyAddedException e){
                System.out.println("That birthday was already added. Exiting back to menu.");
                break;
            }
        }
        return;
    }

    /**
     * Helper method to get the date to be converted into birthday object.
     * Checks to make sure all values are in correct range and in proper format.
     * @param input the user input to get data for operation from
     * @return
     */
    private static String getBirthDate(Scanner input){
        boolean success = false;
        String date = "";
        // taken from the Birthday.java file
        Pattern pattern = Pattern.compile("[0-9]{4}/[0-9]{2}/[0-9]{2}/[0-9]{2}/[0-9]{2}");
        while(!success){
            // get each date component
            System.out.println("Enter the year in format 'YYYY':");
            String year = input.nextLine().trim();
            System.out.println("Enter the year in format 'MM':");
            String month = input.nextLine().trim();
            System.out.println("Enter the year in format 'DD':");
            String day = input.nextLine().trim();
            System.out.println("Enter the year in format 'HH':");
            String hour = input.nextLine().trim();
            System.out.println("Enter the year in format 'mm':");
            String min = input.nextLine().trim();
            // var for making sure values are in proper ranges
            boolean checkedValues = false;
            // var for making sure the date is properly formatted
            boolean matched = false;
            try {
                // year can be anything, so just check the other ones to make sure they make sense
                int monthInt = Integer.parseInt(month);
                int dayInt = Integer.parseInt(day);
                int hourInt = Integer.parseInt(hour);
                int minInt = Integer.parseInt(min);
                // ranges: month-[1, 12], day-[1,31], hour-[0,23](24 hr time), min-[0,59]
                if (1<=monthInt && monthInt<=12 && 1<=dayInt && dayInt<=31 &&
                        0<=hourInt && hourInt<=23 && 0<=minInt && minInt<=59){
                    checkedValues = true;
                    date = year + "/" + month + "/" + day + "/" + hour + "/" + min;
                    Matcher format = pattern.matcher(date);
                    matched = format.matches();
                }
            }
            catch(NumberFormatException e) {
                System.out.println("One of the fields entered was invalid. Try again.");
            }
            success = matched && checkedValues;
            if(!success){
                System.out.println("One of the fields entered was invalid. Try again.");
            }
        }
        return date;
    }

    /**
     * Searches the tree for a birthday object based on a given date
     * @param input the user input to get data for operation from
     */
    public static void search(Scanner input){
        Birthday birthday = null;
        System.out.println("Would you like to search by name(n) or by date(d)?");
        String temp = input.nextLine().trim().toLowerCase();
        while(!(temp.equals("d") || temp.equals("n"))){
            System.out.println("That was not a valid option. Try n for name or d for date.");
            temp = input.nextLine().trim().toLowerCase();
        }
        if (temp.equals("d")){
            // search by date
            String date = getBirthDate(input);
            try{
                birthday = birthdayTree.searchBirthday(date);
            }
            // we will handle the not found at the end of the method for both options to keep code DRY
            catch (BirthdayNotFoundException e){}
        }
        else{
            // search by name
            System.out.println("Enter first name of the desired person to search for:");
            String firstName = input.nextLine().trim();
            System.out.println("Enter last name of the desired person to search for:");
            String lastName = input.nextLine().trim();
            try{
                birthday = birthdayTree.searchName(firstName, lastName);
            }
            // we will handle the not found at the end of the method for both options to keep code DRY
            catch (BirthdayNotFoundException e){}
        }
        if(birthday!=null){
            System.out.println("Here is the information about the desired birthday: ");
            System.out.println("First name: " + birthday.getFirstName());
            System.out.println("Last name: " + birthday.getLastName());
            System.out.println("Birthday: " + birthday.getBirthday());
        }
        else{
            System.out.println("That birthday/person was not found in the tree. Returning to menu.");
        }

    }

    /**
     * Clears the current birthday tree by removing all birthday objects from it
     * @param input the user input to get data for operation from
     */
    public static void clear(Scanner input){
        // Confirm that they want to delete their tree
        System.out.println("Are you sure you want to clear the birthday tree (y or n)?");
        boolean clear = yesNoInput(input);
        if(clear){
            birthdayTree.clear();
            System.out.println("The birthday tree has been succesfully cleared.");
        }
        else
            System.out.println("Okay. Returning to menu.");
    }

    /**
     * Prints the birthday objects in the date range given by user.
     * @param input the user input to get data for operation from
     */
    public static void printBirthdaysInRange(Scanner input){
        // get the start and end birthday objects for the range
        String startDate = "";
        String endDate = "";
        Birthday start, end;
        ArrayList<Birthday> inRange = new ArrayList<>();
        startDate = getBirthDate(input);
        endDate = getBirthDate(input);
        // try creating bday objects for range, if error then a bad date was entered and just start over at menu
        try{start = new Birthday(startDate, "Kakashi", "Hatake");}
        catch(IllegalBirthdayFormatException e){
            System.out.println("The start date was malformed. Returning to menu.");
            return;
        }
        try{end = new Birthday(endDate, "Sasuke", "Uchiha");}
        catch(IllegalBirthdayFormatException e){
            System.out.println("The end date was malformed. Returning to menu.");
            return;
        }
        // for all the birthdays, check if they are in the range
        // if start.compareTo(b) is not 1, b is greater than or equal to that date
        // and if end.compareTo(b) is not -1, b is less than or equal to that date
        // So, putting these together shows it's in range
        for(Birthday b: birthdayTree.getList()){
            if(start.compareTo(b) != 1 && end.compareTo(b) != -1)
                inRange.add(b);
        }
        if(inRange.size() == 0)
            System.out.println("There were no birthdays in that range.");
        else{
            for(Birthday b: inRange){
                System.out.println(b.toString());
            }
        }

    }

    /**
     * List all of the birthdays currently stored in the tree.
     */
    public static void list(){
        if(birthdayTree.getSize() == 0)
            System.out.println("There are currently no items in the birthday tree.");
        else
            birthdayTree.list();
    }

    /**
     * Prints all of the birthdays in the tree taking place today.
     */
    public static void birthdaysToday(){
        ZoneId zoneId = ZoneId.of("America/Montreal");
        // get the current day and month
        LocalDate localDate = LocalDate.now(zoneId);
        int month = localDate.getMonthValue();
        int day = localDate.getDayOfMonth();
        System.out.println("Getting birthdays for: " + localDate);
        ArrayList<Birthday> todayBdays = new ArrayList<>();
        for(Birthday b : birthdayTree.getList()){
            if(b.getBirthday().getMonth()+1 == month && 
                b.getBirthday().getDate() == day){
                    todayBdays.add(b);
                }
        }
        if(todayBdays.size() == 0)
            System.out.println("No people in the birthday tree have today as their birthday.");
        else{
            for(Birthday b: todayBdays){
                System.out.println(b.toString());
            }
        }
    }

    /**
     * List out all of the commands that are valid for user
     */
    public static void printUserCommands() {
        // print out the list of valid commands for user
        System.out.println("1) Load a CSV file of birthdays:                                         load");
        System.out.println("2) Add a new birthday into the birthday tree:                            add");
        System.out.println("3) Search and return a specific birthday or person in the birthday tree: search");
        System.out.println("4) List all the birthdays' in the birthday tree:                         list");
        System.out.println("5) Print out birthdays' in a date range in the birthday tree:            print");
        System.out.println("6) Print all of today's birthdays in the birthday tree:                  today");
        System.out.println("7) Delete all birthdays that are stored in birthday tree:                clear");
        System.out.println("8) Get help from the command list again:                                 help");
        System.out.println("9) Quit birthday tree:                                                  quit");
    }

    /**
     * Helper method that makes it easy to ask user yes/no questions
     * @param input the user input to get data operation from
     * @return true if user answers yes, false if user answers no
     */
    private static boolean yesNoInput(Scanner input){
        String in = input.nextLine().trim().toLowerCase();
        while (!(in.equals("y") || in.equals("n"))){
            System.out.println("Please enter either yes(y) or no(n)");
            in = input.nextLine().trim().toLowerCase();
        }
        return in.equals("y");
    }

    /**
     * Runs the front end UI
     * @param args
     */
    public static void main(String[] args){
        boolean inUse = true;
        welcome();
        Scanner input = new Scanner(System.in);
        while (inUse) {
            System.out.println("\nPlease enter a command: ");
            String command = input.nextLine();
            switch (command) {
                case "load": // load a CSV file
                    loadCSV(input);
                    break;
                case "add": // add a birthday to the birthday tree
                    addBirthday(input);
                    break;
                case "search": // print the information of a desired birthday object
                    search(input);
                    break;
                case "list": // print all birthdays in the birthday tree
                    list();
                    break;
                case "print": // print all birthdays in the given range
                    printBirthdaysInRange(input);
                    break;
                case "today": // print birthdays with todays date
                    birthdaysToday();
                    break;
                case "clear": // clear the birthday tree
                    clear(input);
                    break;
                case "help": // retrieve the list of commands
                    printUserCommands();
                    break;
                case "quit": // exit the program
                    inUse = false;
                    System.out.println("Ending program. Getting cold...so cold.");
                    break;
                default: // any other commands
                    System.out.println("That command is not valid. Try again.");
          }
        }
        

    }
}
