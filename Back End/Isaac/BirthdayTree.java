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
	public Birthday searchBirthday(String fullDate) throws IllegalBirthdayFormatException {
		//Instantiating new "dummy" Birthday Object. This works because Birthday's overriden
		//compareTo only looks that the Birthday Date. 
		Birthday toFind = new Birthday(fullDate, "test", "test");
		Birthday foundBirthday = searchHelper(toFind, this.root);  
		return foundBirthday; 
	}
	
	/**
	 * Recursive helper method designed to traverse the RedBlack tree to search for the 
	 * specified Birthday Object. 
	 * @param birthday the Birthday object to search for in the tree
	 * @param currNode the current Node of the RedBlack tree being searched
	 * @throws BirthdayNotFoundException if the birthday is not found in the tree 
	 * @return toReturn the Birthday object matching the one we are searching for if found.
	 */
	private Birthday searchHelper(Birthday birthday, Node<Birthday> currNode) {
		//If the birthday contained within the current Node matches, returns this birthday 
		if (currNode.data.compareTo(birthday) == 0 ) {
			return currNode.data; 
		}
		//Checks to see if the birthday is to the left of the current Node in the tree
		if (currNode.data.compareTo(birthday)>0 ) {
			//If left child of current node is null, birthday is not contained in the tree
			if (currNode.leftChild == null) { 
				throw new BirthdayNotFoundException("Birthday Not Found in Tree"); 
			}
			//Otherwise recursive call on the left subtree of the current Node  
			else {
				Birthday toReturn = searchHelper(birthday, currNode.leftChild);
				return toReturn; 
			}
		}
		//Checks to see if the birthday should be to the right of the current Node in the tree
		else if (currNode.data.compareTo(birthday)<0) {
			//If right child of current Node is null, then the Birthday doesn't exist in the tree.
			if (currNode.rightChild == null) {
				throw new BirthdayNotFoundException("Birthday Not Found in Tree"); 
			}
			//Otherwise recursive call to search throught the right subtree of the current node.  
			else {
				Birthday toReturn = searchHelper(birthday, currNode.rightChild); 
				return toReturn; 
			}
		}
		
		//Check what the final return statement should be 
		return birthday; 
	}
	
	/**
	 * Reads and adds birthdays from a user-specified csv file to the BirthdayTree 
	 * @param fileName String object representing the name of a csv file containing birthdays
	 */
	public boolean loadCSV(String fileName) { 
		
		BirthdayReader reader = new BirthdayReader();
		if (reader.getBirthdaysFromCSV(fileName)) {
			ArrayList<Birthday> birthdayList = reader.getBirthdayList(); 
			for (Birthday birthday : birthdayList) { 
				try {
					this.addBirthday(birthday);
				}
				catch (Exception e) {
					System.out.println(e);
					continue; 
				}
			}
		}
		else {
			return true; 
		}
		return true; 
	}
	
	/**
	 * Method to search through the Birthday tree by name. Iterates through the LinkedList containing 
	 * all of the Birthdays in the tree, comparing each one to the specified names. If matched, returns
	 * that Birthday 
	 * @param firstName First name of the specified Birthday
	 * @param lastName Last name of the specified Birthday
	 * @return the matching Birthday object if found, null otherwise. 
	 */
	public Birthday searchByName(String firstName, String lastName) {
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

	@Override
	public boolean loadBirthdaysFromReader(String fileName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Birthday searchName(String firstName, String lastName) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
