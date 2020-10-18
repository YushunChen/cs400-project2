// --== CS400 File Header Information ==--
// Name: Yushun Chen
// Email: ychen939@wisc.edu
// Team: AB
// Role: Front End Developer
// TA: Sophie Stephenson
// Lecturer: Florian Heimerl
// Notes to Grader: Project 2 Birthday Search Tool (Back End Section)

import java.util.ArrayList;

/**
 * Birthday Tree ADT containing public methods for a birthday tree that is established using a red
 * black tree.
 * 
 * @author Yushun Chen
 */
public interface BirthdayTreeADT {

  /**
   * Loads birthdays from a CSV file using the BirthdayReader and store them into the birthday tree
   * 
   * @param csvFile the file name of the CSV file
   * @return true if successfully loaded, and false otherwise
   */
  public boolean loadBirthdaysFromReader(String fileName);

  /**
   * Adds a birthday object to the birthday tree (red black tree).
   * 
   * @param newBD birthday object to be added
   * @return true if the birthday object is successfully added, and false otherwise
   * @throws BirthdayAlreadyAddedException thrown when the birthday is already added in the tree
   */
  public boolean addBirthday(Birthday newBD) throws BirthdayAlreadyAddedException;

  /**
   * Searches a birthday object in the birthday tree (red black tree) by date.
   * 
   * @param date the date of the birthday in proper format
   * @return the birthday object found
   * @throws BirthdayNotFoundException thrown when the birthday is not found
   */
  public Birthday searchBirthday(String date) throws BirthdayNotFoundException;

  /**
   * Searches a birthday object in the birthday tree (red black tree) by a person's name.
   * 
   * @param firstName first name of the person
   * @param lastName  last name of the person
   * @return the birthday object found
   * @throws BirthdayNotFoundException thrown when the birthday is not found
   */
  public Birthday searchName(String firstName, String lastName) throws BirthdayNotFoundException;

  /**
   * Lists all the birthday objects stored in the birthday tree.
   */
  public void list();
  
  /**
   * Obtains the size of the tree.
   */
  public int getSize();

  /**
   * Obtains the array list for listing
   * 
   * @return the array list of birthdays
   */
  public ArrayList<Birthday> getList();
  /**
   * Clears all the birthday objects stored in the birthday tree.
   */
  public void clear();

}
