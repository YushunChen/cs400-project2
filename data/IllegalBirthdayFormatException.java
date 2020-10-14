// --== CS400 File Header Information ==--
// Name: Patrick Harvey
// Email: plharvey@wisc.edu
// Team: Team AB
// TA: Sophie Stephenson
// Lecturer: Gary Dahl
// Notes to Grader: Used within Project 2 for Birthday Search Tool
// Contribution: Data Wrangler section


/**
 * This is a checked exception that is thrown if a 
 * birthday is created with the wrong format.
 * 
 * @author Patrick Harvey
 */
public class IllegalBirthdayFormatException extends Exception {

    private static final long serialVersionUID = 2519980159241526877L;

    public IllegalBirthdayFormatException(String message) {
        super(message);
    }

}
