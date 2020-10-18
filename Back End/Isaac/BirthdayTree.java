import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class BirthdayTree extends RedBlackTree<Birthday> implements BirthdayTreeADT  { 
	
	
	private RedBlackTree<Birthday> tree;
	private int size;
	private ArrayList<Birthday> birthdayList; 
	public BirthdayTree() {
		this.tree = new RedBlackTree<Birthday>();
		this.size = 0; 
		this.birthdayList = new ArrayList<Birthday>(); 
	}
	
	@Override
	public boolean addBirthday(Birthday birthday) throws BirthdayAlreadyAddedException {
		
		try {
			tree.insert(birthday);
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
	public void clear() {
		this.tree = new RedBlackTree<Birthday>(); 
	} 
	
	public Birthday searchBirthday(String fullDate) throws IllegalBirthdayFormatException {
		Birthday toFind = new Birthday(fullDate, "test", "test");
		Birthday foundBirthday = searchHelper(toFind, this.root);  
		return foundBirthday; 
	}
	
	private Birthday searchHelper(Birthday birthday, Node<Birthday> currNode) {
		if (currNode.data.compareTo(birthday) == 0 ) {
			return currNode.data; 
		}
		if (currNode.data.compareTo(birthday)<0 ) {
			if (currNode.leftChild == null) { 
				throw new BirthdayNotFoundException("Birthday Not Found in Tree"); 
			}
			else {
				Birthday toReturn = searchHelper(birthday, currNode.leftChild);
				return toReturn; 
			}
		}
		else if (currNode.data.compareTo(birthday) > 0) {
			if (currNode.rightChild == null) {
				throw new BirthdayNotFoundException("Birthday Not Found in Tree"); 
			}
			else {
				Birthday toReturn = searchHelper(birthday, currNode.rightChild); 
				return toReturn; 
			}
		}
		
		//Check what the final return statement should be 
		return birthday; 
	}
	
	
	public void loadCSV(String fileName) { 
		
		BirthdayReader reader = new BirthdayReader();
		if (reader.getBirthdaysFromCSV(fileName)) {
			ArrayList<Birthday> birthdayList = reader.getBirthdayList(); 
			for (Birthday birthday : birthdayList) { 
				try {
					this.insert(birthday);
				}
				catch (Exception e) {
					continue; 
				}
			}
		}
		else {
			return; 
		}
		return; 
	}
	
	public void list() {
		for (Birthday birthday : birthdayList) {
			System.out.println(birthday); 
		}
	}
	
} 
