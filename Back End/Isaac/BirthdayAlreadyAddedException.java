// --== CS400 File Header Information ==--
// Name: Isaac Schluesche
// Email: ischluesche@wisc.edu
// Team: AB
// Role: Back End Developer
// TA: Sophie Stephenson
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

/**
 * Class defining a BirthdayAlreadyAddedException used to indicate that 
 * a specified birthday is already in the tree. 
 * @author Isaac
 *
 */
public class BirthdayAlreadyAddedException extends RuntimeException {
	
	/**
	 * Public constructor for the Exception. Simply calls the superconstructor
	 * with the specified message
	 * @param message Descriptive message of the exception. 
	 */
	public BirthdayAlreadyAddedException(String message) {
		super(message); 
	}

}
