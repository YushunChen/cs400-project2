// --== CS400 File Header Information ==--
// Name: Isaac Schluesche
// Email: ischluesche@wisc.edu
// Team: AB
// Role: Back End Developer
// TA: Sophie Stephenson
// Lecturer: Gary Dahl 
// Notes to Grader: 


import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * BirthdayTree class defining a set of operations that can be made on a Red-Black tree
 * structure containing birthdays. Some functionality includes searching for birthdays by
 * name, date, and listing all the birthdays contained in the tree. 
 * @author Isaac
 *
 */
public class BirthdayTree extends RedBlackTree<Birthday> implements BirthdayTreeADT  { 
	
	
	private RedBlackTree<Birthday> tree;
	private int size;
	//ArrayList of birthdays used for iterative searching
	private ArrayList<Birthday> birthdayList; 
	
	/**
	 * No-argument constructor for a new BirthdayTree object. Instantiates the proper
	 * instance fields. 
	 */
	public BirthdayTree() {
		this.tree = new RedBlackTree<Birthday>();
		this.size = 0; 
		this.birthdayList = new ArrayList<Birthday>(); 
	}
	
	@Override
	/**
	 * Overriding addBirthday method. Takes a birthday object and attempts to add it to the tree.
	 * Prints out any error messages if necessary. 
	 * @param birthday Birthday object to add to the tree 
	 * @throws BirthdayAlreadyAddedException if the Birthday object specified is
	 * already contained within the tree. 
	 * @return true if the birthday is sucessfully added, false otherwise. 
	 */
	public boolean addBirthday(Birthday birthday) throws BirthdayAlreadyAddedException {		
		try {
			tree.insert(birthday);
			//Adds birthday to LinkedList so that it can be accesssed by iterative methods 
			birthdayList.add(birthday); 
		}
		catch ( IllegalArgumentException iae) {
			throw new BirthdayAlreadyAddedException("Birthday Already Contained in Tree"); 
		}
		catch ( NullPointerException npe) {
			return false;
		}
		size ++; 
		return true;
	}

	@Override
	/**
	 * Method to clear the tree of all birthdays. Resets all proper instance fields and 
	 * starts anew with a new RedBlack tree. 
	 */
	public void clear() {
		//Resets all instance fields 
		this.tree = new RedBlackTree<Birthday>(); 
		this.size = 0; 
		this.birthdayList = new ArrayList<Birthday>(); 
	} 
	
	/** 
	 * Method to search the tree for a specified birth date. If found, returns the
	 * Birthday object represented by the date. 
	 * @param fullDate date String representing the date of the Birthday object
	 * @throws IllegalBirthdayFormatException if the fullDate string is not in the proper format
	 * @returns foundBirthday the Birthday Object if found within the tree 
	 */
	public Birthday searchBirthday(String fullDate) throws BirthdayNotFoundException{
		//Instantiating new "dummy" Birthday Object. This works because Birthday's overriden
		//compareTo only looks at the Birthday Date. 
		try {
			Birthday toFind = new Birthday(fullDate, "", "");
			Birthday foundBirthday = searchHelper(toFind, this.tree.root);  
			return foundBirthday; 
		}
		catch(IllegalBirthdayFormatException ibfe) {
			System.out.println("Birthday not in proper format!");
			return null; 
		}
	}
	
	/**
	 * Recursive helper method designed to traverse the RedBlack tree to search for the 
	 * specified Birthday Object. 
	 * @param birthday the Birthday object to search for in the tree
	 * @param currNode the current Node of the RedBlack tree being searched
	 * @throws BirthdayNotFoundException if the birthday is not found in the tree 
	 * @return toReturn the Birthday object matching the one we are searching for if found.
	 */
	private Birthday searchHelper(Birthday birthday, Node<Birthday> currNode) throws BirthdayNotFoundException {
	
		if (currNode == null) {
			throw new BirthdayNotFoundException("Birthday Not Found In Tree"); 
		}
		//If the birthday contained within the current Node matches, returns this birthday 
		Birthday currBirthday = currNode.data;
		Birthday foundBirthday = null; 
		int diffVal = currBirthday.compareTo(birthday); 
		//If Birthday contained within current Node is equal to birthday we're searching for, return it
		if (diffVal == 0) {
			return currBirthday; 
		}
		//If compareTo returns 1, this means that the Birthday within the node is AFTER the birthday we're 
		//searching for, so recursively search the left subtree. 
		if (diffVal > 1) {
			foundBirthday = searchHelper(birthday, currNode.leftChild);
		}
		else {
			foundBirthday = searchHelper(birthday, currNode.rightChild);
		}
		return foundBirthday; 
	}
			
	/**
	 * Reads and adds birthdays from a user-specified csv file to the BirthdayTree 
	 * @param fileName String object representing the name of a csv file containing birthdays
	 */
	public boolean loadBirthdaysFromReader(String fileName) { 
		//New BirthdayReader object used to read information from csv 
		BirthdayReader reader = new BirthdayReader();
		if (reader.getBirthdaysFromCSV(fileName)) {
			ArrayList<Birthday> birthdayList = reader.getBirthdayList();
			//Iterates through ArrayList of birthdays and calls BirthdayTree add method
			//To add teh Birthdays to the tree
			for (Birthday birthday : birthdayList) { 
				try {
					this.addBirthday(birthday);
				}
				catch (Exception e) {
					System.out.println(e);
					continue; 
				}
			}
			return true; 
		}
		else {
			return false; 
		} 
	}
	
	/**
	 * Method to search through the Birthday tree by name. Iterates through the LinkedList containing 
	 * all of the Birthdays in the tree, comparing each one to the specified names. If matched, returns
	 * that Birthday 
	 * @param firstName First name of the specified Birthday
	 * @param lastName Last name of the specified Birthday
	 * @return the matching Birthday object if found, null otherwise. 
	 * @Override
	 */
	public Birthday searchName(String firstName, String lastName) {
		for (Birthday birthday: birthdayList) {
			if ((birthday.getFirstName().equals(firstName)) && birthday.getLastName().equals(lastName)) {
				return birthday; 
			}
		}
		return null; 
	}
	
	/**
	 * Method to iterate through the LinkedList containing all the Birthdays in the tree and print them out.
	 */
	public void list() {
		for (Birthday birthday : birthdayList) { 
			System.out.println(birthday); 
		}
	}
	
	/**
	 * Accessor method for the ArrayList containing all the Birthdays currently in the tree 
	 * @return birthdayList, the instance field containing all the birthdays currently in the tree
	 */
	public ArrayList<Birthday> getList() {
		return this.birthdayList; 
	}
	
	/**
	 * Accessor method for the size field of the BirthdayTree. Used by some diagnostic methods 
	 * on the frontend
	 * @return size, the current number of elements in the BirthdayTree.  
	 */
	public int getSize() {
		return this.size; 
	}
	/**
	 * Accessor method for the node contained at the root of the BirthdayTree 
	 * @return Node<Birthday> this.tree.root, the roote node in the redBlackTree
	 */
	public Node<Birthday> getRoot() {
		return this.tree.root; 
	}
}
