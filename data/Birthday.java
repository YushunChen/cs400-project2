import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * 
 * 
 * @author Patrick Harvey
 */
public class Birthday implements Comparable<Birthday> {

    // format for birthday strings
    private static final String PATTERN = "yyyy/MM/dd/HH/mm";
    // date format for birthday date parser on instantiation of birthday object
    private static final SimpleDateFormat BIRTHDAY_FORMAT = new SimpleDateFormat(PATTERN); 

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
     * Format for birthday: "yyyy/MM/dd/HH/mm"
     * 
     * @param birthdayDate - a string of the birthday in the above format
     * @param firstName - the first name of the person's birthday
     * @param lastName - the last name of the person's birthday
     */
    public Birthday(String stringDate, String firstName, String lastName) {

        this.firstName = firstName;
        this.lastName = lastName;

        // attempt to parse string date into appropriate format
        try {
            this.birthdayDate = BIRTHDAY_FORMAT.parse(stringDate);    
        } catch (ParseException e1) {
            System.out.println("Error: cannot parse string date.");
        }

        // convert string date into long format for getting/removing
        birthdayLong = dateToLong(stringDate);

    }



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
     * The format of this to string is as follows:
     * 
     * "firstname lastname: birthday"
     * 
     * @return the string representation of this birthday
     */
    public String toString() {

        SimpleDateFormat simple = new SimpleDateFormat("EEEE MMMM dd, yyyy @ HH:mm a");
        String birthString = simple.format(birthdayDate);

        return firstName + " " + lastName + ": " + birthString;
    }

    /**
     * Compares two birthdays to one another by date and time. 
     * The object is called the comparator and the other is called the comparandum.
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


    public static void main(String[] args) {
        
        Birthday birthday = new Birthday("1990/07/27/3/45", "Patrick", "Harvey");
        Birthday birthday2 = new Birthday("6/11/1652/12/45", "Charleton", "Heston");


        // to string examples
        System.out.println(birthday);
        System.out.println(birthday2);

        // comparisons
        System.out.println(birthday.compareTo(birthday2));


        BirthdayReader.getBirthdaysFromCSV("birthdays.csv");

    }
}