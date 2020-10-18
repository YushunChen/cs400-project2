// --== CS400 File Header Information ==--
// Name: Matt Gochee
// Email: gochee@wisc.edu
// Team: AB
// Role: Front End Developer
// TA: Sophie Stephenson
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class establishes the front end UI for the project Birthday Search Tool.
 * 
 * @author Matt Gochee
 */
public class BirthdaySearchFrontEnd {

    private static BirthdayTree birthdayTree = new BirthdayTree();

    /**
     * Prints out the welcome messages to the user
     * 
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
    }

    /**
     * Prints out the list of user commands
     * 
     */
    public static void printUserCommands() {
        // helpful list for the users when they are navigating in the UI
        System.out.println("1) Load a CSV file of birhtdays:                               load");
        System.out.println("2) Add a new birthday into the BST:                            add");
        System.out.println("3) Search and return a specific birthday or person in the BST: search");
        System.out.println("4) List all the birthdays' in the BST:                         list");
        System.out.println("5) Print out birthdays' in a date range in the BST:            print");
        System.out.println("6) Print all of today's birthdays in the BST:                  today");
        System.out.println("7) Delete all birthdays that are stored in BST:                clear");
        System.out.println("8) Get help from the command list again:                       help");
        System.out.println("9) Quit BST:                                                   quit");
    }


    /**
     * * helper method for addBirthday and search asks the user for the year, month,
     * and date and puts it in the proper format
     * 
     * @param userInput scanner
     * @param fullDate  date that is to be returned in the proper format
     * @return
     */
    private static String retrieveYearMonthDate(Scanner userInput, String fullDate) {

        System.out.println("Enter the year of birth: (xxxx):");
        // makes sure the input is an integer
        integerCheck(userInput);
        String year = userInput.nextLine().trim();
        // makes sure that the length of the year is 4 digits
        if (year.length() > 4) {
            // invalid reenter a better value
            while (year.length() > 4) {
                System.out.println("Please enter a valid year that is 4 or less integers long:");
                // makes sure the input is an integer
                integerCheck(userInput);
                year = userInput.nextLine().trim();
            }
        } else if (year.length() < 4) {
            for (int i = 0; i < 4 - year.length(); i++)
                year = "0" + year;
        }

        fullDate = fullDate + year + "/";

        System.out.println("Enter the month of birth: (01-12):");
        // makes sure the input is an integer
        integerCheck(userInput);
        String month = userInput.nextLine().trim();
        // makes sure that the length of the month is two digits
        if (month.length() > 2) {
            while (month.length() > 2) {
                System.out.println("Please enter a valid month that is 2 digits long and between 01-12:");
                // makes sure the input is an integer
                integerCheck(userInput);
                month = userInput.nextLine().trim();
            }
        } else if (month.length() < 2) {
            for (int i = 0; i < 2 - month.length(); i++)
                month = "0" + month;
        }

        fullDate = fullDate + month + "/";

        System.out.println("Enter the date of birth: (01-31):");
        // makes sure the input is an integer
        integerCheck(userInput);
        String date = userInput.nextLine().trim();
        // makes sure that the length of the date is two digits
        if (date.length() > 2) {
            while (date.length() > 2) {
                System.out.println("Please enter a valid date that is 2 digits long and between 01-31:");
                // makes sure the input is an integer
                integerCheck(userInput);
                date = userInput.nextLine().trim();
            }
        } else if (date.length() < 2) {
            for (int i = 0; i < 2 - date.length(); i++)
                date = "0" + date;
        }

        fullDate = fullDate + date;

        return fullDate;
    }

    /**
     * * helper method for addBirthday and search asks the user for the hour and
     * minute and puts it in the proper format
     * 
     * @param userInput scanner
     * @param fullDate  date that is to be returned in the proper format
     * @return
     */
    private static String retrieveHourMinute(Scanner userInput, String fullDate) {

        System.out.println("Enter the hour of birth: (00-23):");
        // makes sure the input is an integer
        integerCheck(userInput);
        String hour = userInput.nextLine().trim();
        // makes sure that the length of the hour is two digits
        if (hour.length() > 2) {
            while (hour.length() > 2) {
                System.out.println("Please enter a valid hour that is 2 digits long and between 00-23:");
                // makes sure the input is an integer
                integerCheck(userInput);
                hour = userInput.nextLine().trim();
            }
        } else if (hour.length() < 2) {
            for (int i = 0; i < 2 - hour.length(); i++)
                hour = "0" + hour;
        }

        fullDate = fullDate + "/" + hour + "/";

        System.out.println("Enter the minute of birth: (00-59):");
        // makes sure the input is an integer
        integerCheck(userInput);
        String minute = userInput.nextLine().trim();
        // makes sure that the length of the minute is two digits
        if (minute.length() > 2) {
            while (minute.length() > 2) {
                System.out.println("Please enter a valid minute that is 2 digits long and between 00-59:");
                // makes sure the input is an integer
                integerCheck(userInput);
                minute = userInput.nextLine().trim();
            }
        } else if (minute.length() < 2) {
            for (int i = 0; i < 2 - minute.length(); i++)
                minute = "0" + minute;
        }

        fullDate = fullDate + minute;

        return fullDate;
    }

    /**
     * helper method for retreiving the dates and other values to make sure that the
     * input will be an integer
     * 
     * @param userInput scanner
     */
    private static void integerCheck(Scanner userInput) {
        // makes sure the input is an integer
        while (!userInput.hasNextInt()) {
            System.out.println("Please enter an integer:");
            userInput.nextLine();
        }
    }

    /**
     * Helper method that abstracts away a while loop for a yes or no prompt
     * 
     * @param userInput scanner
     * @return true if response was yes, false if response was no
     */
    private static boolean yesToPrompt(Scanner userInput) {

        String response = userInput.nextLine().trim().toLowerCase();
        // prompt for a yes or no
        while (!(response.equals("y") || response.equals("n"))) {
            System.out.println("Please enter a valid action: yes(y) or no(n)");
            response = userInput.nextLine().trim().toLowerCase();
        }
        return response.equals("y");
    }

    /**
     * This method loads a CSV file of birthdays into the BST
     * 
     * @param userInput
     */
    public static void loadCSV(Scanner userInput) {

        System.out.print("Enter your file name:");
        String userFileName = "";
        boolean loaded = false;

        // will continue to prompt the user until a file successfully loads or they
        // decide to quit
        while (!(userFileName.equals("q") || loaded)) {

            userFileName = userInput.nextLine().trim().toLowerCase();

            if (userFileName.equals("q")) {
                System.out.println("Returning to main menu...");
                break;
            }

            else if (!userFileName.contains(".csv")) { // makes sure it is a CSV file
                System.out.println("Invalid file name! Please try again!");
                System.out.println("type quit(q) to return to the main menu");
                // return;
            } else {
                if (birthdayTree.loadBirthdaysFromReader(userFileName)) {
                    System.out.println("CSV file successfully loaded!");
                    loaded = true;
                } else {
                    System.out.println("Error! Please try again.");
                    System.out.println("Type quit(q) to return to the main menu");
                }
            }
        }
    }

    /**
     * This method adds a birthday to the BST
     * 
     * @param userInput scanner
     */
    public static void addBirthday(Scanner userInput) {

        String fullDate = "";
        boolean yes;

        // will run until a birthday is added or the user quits
        while (true) {
            fullDate = "";
            // prompt the user to enter all the needed information of the Birthday
            System.out.println("Enter the first name of the person you wish to add:");
            String firstName = userInput.nextLine().trim();

            System.out.println("Enter the last name of the person you wish to add:");
            String lastName = userInput.nextLine().trim();

            // puts the year, month, and date in the proper format into fullDate
            fullDate = retrieveYearMonthDate(userInput, fullDate);

            System.out.println("Do you want to add the hour and minute as well? yes(y) or no(n)");
            // gets a yes or no answer to the above question
            yes = yesToPrompt(userInput);

            // add hour and minutes if the response was yes
            if (yes) {
                fullDate = retrieveHourMinute(userInput, fullDate);
            }

            System.out.println("The birthday you entered for " + firstName + " " + lastName + " was: " + fullDate);

            System.out.println("Is this correct? yes(y) or no(n)");
            // gets a yes or no answer to the above question
            yes = yesToPrompt(userInput);

            // put birthday into Birthday tree
            if (yes) {
                // create the Birthday from the user info
                try {
                    Birthday birthday = new Birthday(fullDate, firstName, lastName);
                    if (birthdayTree.addBirthday(birthday)) {
                        System.out.println(firstName + "'s birthday was successfully added into the BST!");
                    } else {
                        System.out.println(firstName + "'s birthday was not added into the BST!");
                    }
                } catch (BirthdayAlreadyAddedException e) {
                    System.out.println("This person's birthday is already in the BST!");
                } catch (IllegalBirthdayFormatException e) {
                    System.out.println("This person's birthday is not in the proper format!");
                }
                // now that the birthday is added we can exit the while loop
                break;
            }

            else if (!yes) {
                System.out.println("Do you wish to add a different birthday? yes(y) or no(n)");
                // gets a yes or no answer to the above question
                yes = yesToPrompt(userInput);

                // allows users to exit without having to add a birthday
                if (!yes) {
                    System.out.println("Returning to main menu...");
                    break;
                }
                // if a yes then stay in the while loop
            }
        }
    }

    /**
     * This methods obtains the information of a birthday of the user's choice
     * 
     * @param userInput
     */
    public static void search(Scanner userInput) {

        System.out.println("How do you wish to search for a Birthday in the BST? By name(n) or date(d)");

        String response = userInput.nextLine().trim().toLowerCase();

        // confirmation step for the users
        while (!(response.equals("d") || response.equals("n"))) {
            System.out.println("Please enter a valid action: Search by name(n) or date(d)");
            response = userInput.nextLine();
        }

        try {
            Birthday birthday = null;

            // search by date
            if (response.equals("d")) {
                String fullDate = "";
                // puts the year, month, and date in the proper format into fullDate
                fullDate = retrieveYearMonthDate(userInput, fullDate);

                // puts the hour and minute in the proper format into fullDate
                fullDate = retrieveHourMinute(userInput, fullDate);

                birthday = birthdayTree.searchBirthday(fullDate);
            }
            // search by name
            else if (response.equals("n")) {
                System.out.println("Enter the first name of the person you wish to search for:");
                String firstName = userInput.nextLine().trim();

                System.out.println("Enter the last name of the person you wish to search for:");
                String lastName = userInput.nextLine().trim();

                birthday = birthdayTree.searchName(firstName, lastName);
            }

            System.out.println("Here is the information about the searched birthday:");
            System.out.println("First Name: " + birthday.getFirstName());
            System.out.println("Last Name: " + birthday.getLastName());
            System.out.println("Birthday: " + birthday.getBirthday());

        } catch (BirthdayNotFoundException e) {
            System.out.println("Sorry! We could not find that person in the BST.");
            return;
        }

    }

    /**
     * Lists out all of the birthdays in the BST
     * 
     * @param userInput scanner
     */
    public static void list(Scanner userInput) {
        System.out.println("Are you sure you want to list the " + birthdayTree.getSize() + " birthday(s) in your BST?");
        System.out.println("Proceed? yes(y) or no(n)");

        // gets a yes or no answer to the above question
        if (yesToPrompt(userInput)) {
            birthdayTree.list();
        }
        // if no then return
        return;
    }

    /**
     * Prints out all birthdays in a certain time range
     * 
     * @param userInput scanner
     */
    public static void printBirthdaysInRange(Scanner userInput) {

        String startDate = "";
        String endDate = "";
        Birthday start = null;
        Birthday end = null;
        ArrayList<Birthday> birthdaysInRange = new ArrayList<>();

        // grabs the beginnning and end of the range
        try {
            // creates a birthday object for the start in order to use the birthday compare
            System.out.println("Please enter the lower bound of the range first:");
            startDate = retrieveYearMonthDate(userInput, startDate);
            start = new Birthday(startDate, "start", "date");

            // creates a birthday object for the end in order to use the birthday compare
            System.out.println("Please enter the upper bound of the range now:");
            endDate = retrieveYearMonthDate(userInput, endDate);
            end = new Birthday(endDate, "end", "date");
        } catch (IllegalBirthdayFormatException e) {
            // if exception is thrown, exit the method
            return;
        }

        // if the birthday is inbetween the start and end dates, then it will be printed
        for (Birthday person : birthdayTree.getList()) {
            if (start.compareTo(person) != 1 && end.compareTo(person) != -1) {
                birthdaysInRange.add(person);
            }
        }

        // prints out the birthdays in the range
        if (birthdaysInRange.size() == 0) {
            System.out.println("There are no birthdays in this range:(");
        } else {
            System.out.println("There is/are " + birthdaysInRange.size() + " birthday(s) in this range!");
            for (Birthday person : birthdaysInRange) {
                System.out.println(person.toString());
            }
        }
    }

    /**
     * This methods prints the birthdays from today in the BST
     * 
     */
    @Deprecated
    public static void birthdaysToday() {
        // parses todays date
        ZoneId zonedId = ZoneId.of("America/Montreal");
        LocalDate today = LocalDate.now(zonedId);
        String[] yearMonthDate = today.toString().split("-");
        int month = Integer.parseInt(yearMonthDate[1]);
        int date = Integer.parseInt(yearMonthDate[2]);

        ArrayList<Birthday> todaysBirthdays = new ArrayList<>();

        for (Birthday person : birthdayTree.getList()) {
            // must subtract due to formatting differences between date and LocalDate
            if (person.getBirthday().getDate() == date && person.getBirthday().getMonth() == (month - 1)) {
                todaysBirthdays.add(person);
            }
        }
        // print today's birthdays
        if (todaysBirthdays.size() == 0) {
            System.out.println("Nobody in your BST has today as their birthday :(");
        } else {
            System.out.println("There is/are " + todaysBirthdays.size() + " birthday(s) today!");
            for (Birthday person : todaysBirthdays) {
                System.out.println(person.toString());
            }
            System.out.println("Happy Birthday to all!");
        }
    }

    /**
     * This methods deletes all the birthdays and clears the BST
     * 
     * @param userInput
     */
    public static void clear(Scanner userInput) {
        System.out.println("Confirm: you are about to delete the " + birthdayTree.getSize() + " birthdays in the BST");
        System.out.println("Proceed? yes(y) or no(n)");

        // gets a yes or no answer to the above question
        boolean yes = yesToPrompt(userInput);

        if (yes) {
            birthdayTree.clear();
            System.out.println("The BST has been cleared!");
        }
        // if no, then leave the method
    }

    /**
     * Main user interface
     * 
     * @param args
     */
    public static void main(String[] args) {
        welcome(); // welcome message for the users
        System.out.println("Here is a list of helpful commands you can use in the BST:");
        printUserCommands();

        Scanner userInput = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            System.out.println();
            System.out.println("Please enter a command: ");
            String userCom = userInput.nextLine().trim().toLowerCase();
            switch (userCom) {
                case "load": // load a CSV file
                    loadCSV(userInput);
                    break;
                case "add": // add a birthday to the BST
                    addBirthday(userInput);
                    break;
                case "search": // gets information of a birthday of the user's choice
                    search(userInput);
                    break;
                case "list": // list all the birthdays currently in the BST
                    list(userInput);
                    break;
                case "print": // prints all of the birthdays in the specified range
                    printBirthdaysInRange(userInput);
                    break;
                case "today": // print todays birthdays
                    birthdaysToday();
                    break;
                case "clear": // clear the BST
                    clear(userInput);
                    break;
                case "help": // get the user commands list
                    printUserCommands();
                    break;
                case "quit": // quit the program
                    isRunning = false;
                    System.out.println("Goodbye!");
                    break;
                default: // any other commands
                    System.out.println("Command not valid!");
            }
        }
    }

}
