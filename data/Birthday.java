// --== CS400 File Header Information ==--
// Name: Patrick Harvey
// Email: plharvey@wisc.edu
// Team: AB
// Role: Data Wrangler
// TA: Sophie Stephenson
// Lecturer: Gary Dahl
// Notes to Grader: Used within Project 2 for Birthday Search Tool

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.regex.Matcher;

/**
 * This class represents a Birthday object to be contained within the Red
 * Black Tree used for this project. The birthday may be compared to one another
 * based on their relative dates, and they also may be accessed via their long
 * format.
 * 
 * Birthdays may be created with the following two format strings: 
 *  LONG_PATTERN:  "yyyy/MM/dd/HH/mm"
 *  SHORT_PATTERN: "yyyy/MM/dd"
 * 
 * @author Patrick Harvey
 */
public class Birthday implements Comparable<Birthday> {

    // string format for birthday strings
    private static final String STRING_LONG_PATTERN = "yyyy/MM/dd/HH/mm";
    private static final String STRING_SHORT_PATTERN = "yyyy/MM/dd";

    // the regex format patterns used for birthdays
    private static final Pattern LONG_PATTERN = Pattern.compile("[0-9]{4}/[0-9]{2}/[0-9]{2}/[0-9]{2}/[0-9]{2}");
    private static final Pattern SHORT_PATTERN = Pattern.compile("[0-9]{4}/[0-9]{2}/[0-9]{2}");

    // patterns used by this birthday
    private SimpleDateFormat BIRTHDAY_FORMAT;

    // name fields and date field for birthday
    private String firstName;
    private String lastName;
    private Date birthdayDate;
    private long birthdayLong;

    /**
     * Constructor for birthday that initializes the first and last names,
     * and the method also parses the string given for the birthday into the 
     * following format: 
     * 
     * Long Format for birthday: "yyyy/MM/dd/HH/mm"
     * Short Format for birthday: "yyyy/MM/dd"
     * 
     * @param birthdayDate - a string of the birthday in the above format
     * @param firstName - the first name of the person's birthday
     * @param lastName - the last name of the person's birthday
     * @throws IllegalBirthdayFormatException if the birthday date is not in either of the two
     * correct formats LONG or SHORT
     */
    public Birthday(String stringDate, String firstName, String lastName) throws IllegalBirthdayFormatException {

        this.firstName = firstName;
        this.lastName = lastName;

        // check for null pointer in stringdate
        if (stringDate == null) {
            throw new IllegalBirthdayFormatException("There was in error in reading the format: "
            + " You cannot use null strings.");
        }

        try {
            // determine whether the format is long of short
            String PATTERN = determineFormat(stringDate);

            // use the determined pattern for the birthday format
            BIRTHDAY_FORMAT = new SimpleDateFormat(PATTERN); 

            // sets the birthday date with the parsed Date format
            this.birthdayDate = BIRTHDAY_FORMAT.parse(stringDate);    

        // catch incorrect formats for birthday and throw exception
        } catch (PatternSyntaxException|ParseException e) {
            throw new IllegalBirthdayFormatException("There was an error in reading the format" 
            + " of this birthday date: " + stringDate + " " + firstName + " " + lastName);
        }

        // convert string date into long format for getting/removing
        birthdayLong = dateToLong(stringDate);
    }

    /**
     * Determines the format of the string based on the appropriate
     * regular expressions LONG_PATTERN or SHORT_PATTERN.
     * 
     * @param date the string date of the birthday
     * @return the string format of the given date for creating a date format
     * @throws PatternSyntaxException if the given String date matches neither of the patterns
     */
    private String determineFormat(String date) throws PatternSyntaxException {

        // create matchers for date given
        Matcher long_format = LONG_PATTERN.matcher(date);
        Matcher short_format = SHORT_PATTERN.matcher(date);

        // determine if patterns match
        boolean longMatched = long_format.matches();
        boolean shortMatched = short_format.matches();

        // return either pattern that matched
        if (longMatched) {
            return STRING_LONG_PATTERN;
        } else if (shortMatched) {
            return STRING_SHORT_PATTERN;
        }
        // else, throw pattern exception
        throw new PatternSyntaxException("The date was not in the correct format", date, 0);
    }

    /**
     * Converts the date of this birthday to its long format for use within
     * the constructor method.
     * 
     * @param birthday the string of this birthday
     * @return the long format of the birthday
     */
    private Long dateToLong(String birthday) {
        String birthdayString = birthday.replaceAll("/", "");
        return Long.parseLong(birthdayString);
    }

    /**
     * This method gets the birthday date object from this Birthday.
     * 
     * @return the date of this birthday
     */
    public Date getBirthday() {
        return birthdayDate;
    }

    /**
     * A getter method for returning the long version
     * of this birthday's date.
     * @return the long format of this birthday's date
     */
    public Long getBirthdayLong() {
        return birthdayLong;
    }
    
    /**
     * The first name of this person's birthday
     * @return the string first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * The last name of this person's birthday
     * @return the string last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * The format of this Birthday string is as follows (excluding braces):
     * 
     * "Firstname Lastname: [Weekday] [Month] [Day], [Year] @ [Hour]:[Minute] [AM/PM]"
     * 
     * @return the string representation of this birthday in the above format for printing.
     */
    public String toString() {
        // create format for printing
        SimpleDateFormat simple = new SimpleDateFormat("EEEE MMMM dd, yyyy @ HH:mm a");
        String birthString = simple.format(birthdayDate);

        return firstName + " " + lastName + ": " + birthString;
    }

    /**
     * Compares two birthdays to one another by date and time. 
     * The object is called this and the other argument object is called other.
     *    - returns -1 if this occurs BEFORE other
     *    - returns 1 if this occurs AFTER other
     *    - returns 0 if this occurs at the same time as other
     * 
     * @return the -1 if this is before other, 1 if other occurs before this, 0 if dates are equal.
     */
    @Override
    public int compareTo(Birthday other) {

        // this birthday is before the arg birthday
        if (birthdayDate.before(other.birthdayDate)) {
            return -1;
        } 
        
        // this birthday is after the arg birthday
        else if (birthdayDate.after(other.birthdayDate)) {
            return 1;
        } 
        
        // the two birthdays are equal
        else {
            return 0;
        }
    }


}