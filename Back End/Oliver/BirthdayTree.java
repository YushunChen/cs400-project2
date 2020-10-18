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
 * This class represents the birthday tree that takes the form of a red black tree to store the
 * birthdays
 * 
 * @author Yushun Chen
 */
public class BirthdayTree extends RedBlackTree<Birthday> implements BirthdayTreeADT {

  // A red black tree of birthday objects
  private RedBlackTree<Birthday> bdTree;
  private Node<Birthday> root;
  private int size;
  private ArrayList<Birthday> bdList;

  /**
   * Constructor of BirthdayTree without parameter
   */
  public BirthdayTree() {
    bdTree = new RedBlackTree<Birthday>();
    root = bdTree.root;
    bdList = new ArrayList<Birthday>();
  }

  /**
   * Loads birthdays from a CSV file using the BirthdayReader and store them into the birthday tree
   * 
   * @param csvFile the file name of the CSV file
   * @return true if successfully loaded, and false otherwise
   */
  @Override
  public boolean loadBirthdaysFromReader(String fileName) {
    BirthdayReader reader = new BirthdayReader();
    reader.getBirthdaysFromCSV(fileName);
    ArrayList<Birthday> list = reader.getBirthdayList();
    for (Birthday i : list) {
      try {
        bdTree.insert(i);
        size++;
      } catch (NullPointerException e1) {
        System.out.print("The provided data argument (Birthday) is null!");
      } catch (BirthdayAlreadyAddedException e2) {
        System.out.print("This birthday object has already been added!");
      }
    }
    root = bdTree.root;
    return true;
  }

  /**
   * Adds a birthday object to the birthday tree (red black tree).
   * 
   * @param newBD birthday object to be added
   * @return true if the birthday object is successfully added, and false otherwise
   * @throws BirthdayAlreadyAddedException thrown when the birthday is already added in the tree
   */
  @Override
  public boolean addBirthday(Birthday newBD) throws BirthdayAlreadyAddedException {
    try {
      bdTree.insert(newBD);
      root = bdTree.root;
      size++;
    } catch (NullPointerException e1) {
      System.out.print("The provided data argument (Birthday) is null!");
    }
    return true;
  }

  /**
   * Searches a birthday object in the birthday tree (red black tree) by date.
   * 
   * @param date the date of the birthday in proper format
   * @return the birthday object found
   * @throws BirthdayNotFoundException thrown when the birthday is not found
   */
  @Override
  public Birthday searchBirthday(String date) throws BirthdayNotFoundException {
    Birthday findBd = null;
    root = bdTree.root;
    try {
      findBd = new Birthday(date, "", "");
    } catch (IllegalBirthdayFormatException e) {
      System.out.println("The birthday format is illegal!");
    }
    return this.searchBirthdayHelper(findBd, root);
  }

  private Birthday searchBirthdayHelper(Birthday findBd, Node<Birthday> current)
      throws BirthdayNotFoundException {
    if (current == null) { // no birthday matches dates stored in the BST
      throw new BirthdayNotFoundException(
          "There is no birthday as stated that is stored in this birthday tree!");
    }
    int compareNum = findBd.compareTo(current.data); // compare result
    Birthday foundBirthday = null;
    if (compareNum == 0) { // immediately found!
      foundBirthday = current.data;
    } else if (compareNum < 0) {
      // traverse to the left of the RBT if date is after current
      foundBirthday = searchBirthdayHelper(findBd, current.leftChild);
    } else {
      // traverse to the right of the RBT if date is before current
      foundBirthday = searchBirthdayHelper(findBd, current.rightChild);
    }
    return foundBirthday;
  }

  /**
   * Searches a birthday object by a person's name (not searching through tree directly). Different
   * from search by birthday (Date)
   * 
   * @param firstName first name of the person
   * @param lastName  last name of the person
   * @return the birthday object found
   * @throws BirthdayNotFoundException thrown when the birthday is not found
   */
  @Override
  public Birthday searchName(String firstName, String lastName) throws BirthdayNotFoundException {
    loadList(root);
    Birthday findBd = null;
    for (Birthday i : bdList) {
      if ((i.getFirstName().equals(firstName)) && i.getLastName().equals(lastName)) {
        findBd = i;
        break;
      }
    }
    return findBd;
  }

  /**
   * Loads the array list with birthdays for listing purpose
   * 
   * @param current node to start with
   */
  public void loadList(Node<Birthday> current) {
    if (current == null) { // base case for the recursive method
      return;
    }
    if (current.leftChild != null) {
      // recursively call the method when current has a left child
      bdList.add(current.leftChild.data);
      loadList(current.leftChild);
    }
    // after getting the left child and the parent, obtain the right child
    if (current.rightChild != null) {
      bdList.add(current.rightChild.data);
      loadList(current.rightChild);
    }
  }

  /**
   * Lists all the birthday objects stored in the birthday tree.
   */
  @Override
  public void list() {
    for (Birthday i : bdList) {
      System.out.println(i);
    }
  }

  /**
   * Getter method for the array list of birthdays
   */
  @Override
  public ArrayList<Birthday> getList() {
    return bdList;
  }

  /**
   * Getter method for the number of birthday objects in the tree
   */
  @Override
  public int getSize() {
    return size;
  }

  /**
   * Clears all the birthday objects stored in the birthday tree.
   */
  @Override
  public void clear() {
    root = null;
    size = 0;
    bdList = new ArrayList<Birthday>();
  }

  public static void main(String[] args) {
    BirthdayTree tree = new BirthdayTree();

    Birthday birthday1 = null;
    Birthday birthday2 = null;
    Birthday birthday3 = null;
    Birthday birthday4 = null;
    try {
      birthday1 = new Birthday("1990/07/27/03/45", "Patrick", "Harvey");
      birthday2 = new Birthday("1652/05/13/16/00", "Charleton", "Heston");
      birthday3 = new Birthday("1988/02/29", "Edward", "Bryant");
      birthday4 = new Birthday("1988/10/01", "Somebody", "Else");
    } catch (IllegalBirthdayFormatException e) {
      System.out.println(e.getMessage());
    }
    tree.addBirthday(birthday1);
    tree.addBirthday(birthday2);
    tree.addBirthday(birthday3);
    tree.addBirthday(birthday4);

    // Check nodes after adding birthdays
    System.out.println("================Test addBirthday====================");
    System.out.println(tree.root.data);
    System.out.println(tree.root.leftChild.data);
    System.out.println(tree.root.rightChild.data);

    // Check searchBirthday method
    System.out.println("================Test searchBirthday====================");
    System.out.println(tree.searchBirthday("1988/02/29"));
    System.out.println(tree.searchBirthday("1990/07/27/03/45"));
    System.out.println(tree.searchBirthday("1988/10/01"));


    // Check searchName method
    System.out.println("================Test searchName====================");
    System.out.println(tree.searchName("Charleton", "Heston"));
    
    System.out.println("================Test loadBirthdaysFromReader====================");
    BirthdayTree tree2 = new BirthdayTree();
    tree2.loadBirthdaysFromReader("birthdays.csv");
    System.out.println(tree2.root.data);
    System.out.println(tree2.root.leftChild.data);
    System.out.println(tree2.root.rightChild.data);
    System.out.println(tree2.size);
    System.out.println(tree2.getSize());

    // Listing all birthdays
    tree2.loadList(tree2.root);
    tree2.list();

    System.out.println("====================Test Clear========================");
    tree2.clear();
    if (tree2.root == null) {
      System.out.println(
          "The tree has been cleared and the garbage collector will clean the rest of the tree");
    }

    System.out.println("====================Test list========================");
    tree2.list();


  }

}
