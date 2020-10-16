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
 * thrown when a birthday is not found in the birthday tree.
 * 
 * @author Yushun Chen
 */
public class BirthdayNotFoundException extends RuntimeException {

  private static final long serialVersionUID = -9057667699384247613L;

  /**
   * Constructor for a BirthdayNotFoundException
   * 
   * @param errorMessage the error message when the exception is involved in the program
   */
  public BirthdayNotFoundException(String errorMessage) {
    super(errorMessage);
  }

}
