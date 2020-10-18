
// --== CS400 File Header Information ==--
// Name: Isaac Schluesche
// Email: ischluesche@wisc.edu
// Team: AB
// Role: Back End Developer
// TA: Sophie Stephenson
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

/**
 * Class defining a BirthdayNotFoundException used to signify if a 
 * @author Isaac
 *
 */
public class BirthdayNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Public constructor for creating a new BirthdayNotFoundException
	 */
	public BirthdayNotFoundException(String message) {
		super(message);
	}
	
}
