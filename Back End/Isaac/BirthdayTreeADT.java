// --== CS400 File Header Information ==--
// Name: Isaac Schluesche
// Email: ischluesche@wisc.edu
// Team: AB
// Role: Back End Developer
// TA: Sophie Stephenson
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

/**
 * BirthdayTree Abstract Data Type used to define a set of methods 
 * laying out our birthday tree design and operations that may be performed 
 * upon it. 
 * @author Isaac
 *
 */
public interface BirthdayTreeADT{
	
	/**
	 * Method to add a Birthday object to the Red-Black Tree
	 * @param birthday Birthday object to add
	 * @return True if the object was added sucessfully, false otherwise. 
	 * @throws BirthdayAlreadyAddedException if the specified Birthday object is found 
	 * within the tree throughout the course of adding it 
	 */
	public boolean addBirthday(Birthday birthday) throws BirthdayAlreadyAddedException; 
	
	/**
	 * Method that clears all the birthdays from the BirthdayTree
	 */
	public void clear(); 
	
	/**
	 * Method utilized to search through the RedBlackTree to find a Birthday object matching
	 * the specified date
	 * @param fullDate The String form of the actul Birth Date
	 * @return Birthday, the Birthday object matching the fullDate
	 * @throws IllegalBirthdayFormatException if the date is not formatted correctly 
	 */
	public Birthday searchBirthday(String fullDate) throws IllegalBirthdayFormatException;
	
	/**
	 * Method to load in a csv file containing birthdays into the RBT
	 * @param fileName name of the CSV file containing Birthdays 
	 */
	public boolean loadBirthdaysFromReader(String fileName);
	
	/**
	 * Method to search through the birthday tree by a specified first and last 
	 * name pair. Returns the specified birthday if found. 
	 * @param firstName String object containing first name 
	 * @param lastName String object containing last name
	 * @return Birthday object matching the name pair if found. 
	 */
	public Birthday searchName(String firstName, String lastName); 
}
