// --== CS400 File Header Information ==--
// Name: Yushun Chen
// Email: ychen939@wisc.edu
// Team: AB
// Role: Front End Developer
// TA: Sophie Stephenson
// Lecturer: Florian Heimerl
// Notes to Grader: Project 2 Birthday Search Tool (Back End Section)

/**
 * This class creates an unchecked exception that is used in the birthday search tool program. It is
 * thrown when a birthday is already added in the birthday tree.
 * 
 * @author Yushun Chen
 *
 */
public class BirthdayAlreadyAddedException extends RuntimeException {

  private static final long serialVersionUID = 5628282993582759769L;

  /**
   * Constructor for a BirthdayAlreadyAddedException
   * 
   * @param errorMessage the error message when the exception is involved in the program
   */
  public BirthdayAlreadyAddedException(String errorMessage) {
    super(errorMessage);
  }

}
