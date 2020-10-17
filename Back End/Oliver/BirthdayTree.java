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

  /**
   * Constructor of BirthdayTree without parameter
   */
  public BirthdayTree() {
    bdTree = new RedBlackTree<Birthday>();
    root = bdTree.root;
  }

  /**
   *
   */
  @Override
  public boolean loadBirthdaysFromReader(BirthdayReader reader) {
    if (reader == null) {
      System.out.println("The birthday reader is null!");
      return false;
    }
    ArrayList<Birthday> list = reader.getBirthdayList();
    if (list == null) {
      System.out.println("The birthday list is null!");
      return false;
    }
    for (int i = 0; i < list.size(); i++) {
      bdTree.insert(list.get(i));
    }
    return true;
  }

  /**
   *
   */
  @Override
  public boolean addBirthday(Birthday newBD) throws BirthdayAlreadyAddedException {
    try {
      bdTree.insert(newBD);
      root = bdTree.root;
    } catch (NullPointerException e1) {
      System.out.print("The provided data argument (Birthday) is null!");
    } catch (BirthdayAlreadyAddedException e2) {
      System.out.print("This birthday object has already been added!");
    }
    return true;
  }

  /**
   *
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
    //System.out.println(root.data); NOPRO
    return this.searchBirthdayHelper(findBd, root);
  }

  private Birthday searchBirthdayHelper(Birthday findBd, Node<Birthday> current)
      throws BirthdayNotFoundException {
    if (current == null) { // no patient record whose date of birth matches dates stored in the BST
      throw new BirthdayNotFoundException(
          "There is no birthday as stated that is stored in this birthday tree!");
    }
//    if (findBd == null) {
//      throw new BirthdayNotFoundException(
//          "There is no birthday as stated that is stored in this birthday tree!");
//    }
    int compareNum = findBd.compareTo(current.data); // compare result
    Birthday foundBirthday = null;
    if (compareNum == 0) { // immediately found!
      foundBirthday = current.data;
    } else if (compareNum < 0) {
      // traverse to the left of the BST if findRecord is older than current
      foundBirthday = searchBirthdayHelper(findBd, current.leftChild);
    } else {
      // traverse to the right of the BST if findRecord is younger than current
      foundBirthday = searchBirthdayHelper(findBd, current.rightChild);
    }
    return foundBirthday;
  }

  @Override
  public Birthday searchName(String firstName, String lastName) throws BirthdayNotFoundException {
    Birthday findBd = null;
    try {
      findBd = new Birthday("", firstName, lastName);
    } catch (IllegalBirthdayFormatException e) {
      System.out.println("The birthday format is illegal!");
    }
    return this.searchNameHelper(findBd, bdTree.root);
  }

  private Birthday searchNameHelper(Birthday findBd, Node<Birthday> current)
      throws BirthdayNotFoundException {
    if (current == null) { // no patient record whose date of birth matches dates stored in the BST
      throw new BirthdayNotFoundException(
          "There is no birthday as stated that is stored in this birthday tree!");
    }
    int compareNum = findBd.compareTo(current.data); // compare result
    Birthday foundBirthday = null;
    if (compareNum == 0) { // immediately found!
      foundBirthday = current.data;
    } else if (compareNum < 0) {
      // traverse to the left of the BST if findRecord is older than current
      foundBirthday = searchNameHelper(findBd, current.leftChild);
    } else {
      // traverse to the right of the BST if findRecord is younger than current
      foundBirthday = searchNameHelper(findBd, current.rightChild);
    }
    return foundBirthday;
  }

  @Override
  public void list() {
    bdTree.toString();
  }

  @Override
  public void clear() {
    bdTree = new RedBlackTree<Birthday>();
  }

  public static void main(String[] args) {
    BirthdayTree tree = new BirthdayTree();

    // BirthdayReader bdayReader = new BirthdayReader();
    // bdayReader.getBirthdaysFromCSV("birthdays.csv");
    //
    // tree.loadBirthdaysFromReader(bdayReader);
    // tree.toString();
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
    System.out.println(tree.root.data);
    System.out.println(tree.root.leftChild.data);
    System.out.println(tree.root.rightChild.data);
    
    // Check searchBirthday method
    System.out.println(tree.searchBirthday("1988/02/29"));
    System.out.println(tree.searchBirthday("1990/07/27/03/45"));
    System.out.println(tree.searchBirthday("1988/10/01"));

  }

}
