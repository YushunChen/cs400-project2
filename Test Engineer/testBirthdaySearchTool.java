// --== CS400 File Header Information ==--
// Name: Peter Sykora
// Email: psykora@wisc.edu
// Team: AB
// Role: Test Engineer
// TA: Sophie Stephenson
// Lecturer: Gary Dahl
// Notes to Grader: NONE

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * This class contains the entire program that tests the files and respective methods within the BST
 * or Birthday Search Tool to ensure that they all function correctly and that the entire
 * application functions correctly.
 * 
 * @author Peter Sykora
 *
 */
public class testBirthdaySearchTool {

  /**
   * This test method ensures that the passed file can either be correctly loaded because the file
   * does exist or an exception is handled appropriately and the correct output is produced if the
   * file does not exist.
   * 
   * @return true if all tests pass, false if otherwise
   */
  public static boolean testLoadFile() {

    { // Test 1 - the file does not exist

      // create a new reader object that is going to attempt to read a file object
      BirthdayReader reader = new BirthdayReader();

      // attempts to read and furthermore get the birthdays from a file that does not exist
      if (reader.getBirthdaysFromCSV("birthdayList.csv")) {
        System.out.println("A file that does not exist could be loaded");
        return false; // return false if can load a file that does not exist
      }

      // check to see if birthdays can be added to the list of birthdays from a nonexistent file
      if (reader.getBirthdayList().size() != 0) {
        return false; // return false if there are birthdays within the file that does not exist
      }

    }

    { // Test 2 - the file does exist

      // create a new reader object that is going to attempt to read a file object
      BirthdayReader reader = new BirthdayReader();

      // attempts to read and furthermore get the birthdays from a file that does not exist
      if (!reader.getBirthdaysFromCSV("birthdays.csv")) {
        System.out.println("A file that does exist could be not loaded");
        return false; // return false if cannot load a file that does not exist
      }

    }

    return true; // return true if all tests pass
  }

  /**
   * This test method ensures that the data within an existent file can create Birthday Objects that
   * are of the correct format. This also ensures that those birthdays with an incorrect format
   * throw the correct exceptions appropriately.
   * 
   * @return true if all tests pass, false if otherwise
   */
  public static boolean testBirthdayObject() {

    { // Test 1 - create Birthday Objects with birthdates that are neither LONG or SHORT patterns

      // tries to create a Birthday Object with an empty string
      try {
        Birthday birthday = new Birthday("", "Gary", "Winthrop");
        return false; // if an empty string can be formatted to a birthdate pattern
      } catch (IllegalBirthdayFormatException e) {
        System.out.println(e.getMessage());
      }

      // tries to create Birthday Object with an mla formatted string
      try {
        Birthday birthday = new Birthday("22 June 1980", "Gary", "Winthrop");
        return false; // if an empty string can be formatted to a birthdate pattern
      } catch (IllegalBirthdayFormatException e) {
        System.out.println(e.getMessage());
      }

      // tries to create Birthday Object with an apa formatted string
      try {
        Birthday birthday = new Birthday("June 22, 1980", "Gary", "Winthrop");
        return false; // if an empty string can be formatted to a birthdate pattern
      } catch (IllegalBirthdayFormatException e) {
        System.out.println(e.getMessage());
      }

      // tries to create Birthday Object with a string or random numbers, symbols, breaks, & letters
      try {
        Birthday birthday = new Birthday("U#* u54 \n mwqfas", "Gary", "Winthrop");
        return false; // if an empty string can be formatted to a birthdate pattern
      } catch (IllegalBirthdayFormatException e) {
        System.out.println(e.getMessage());
      }

      // TODO:
      // tries to create Birthday Object with a LONG pattern whose month that doesn't exist
      try {
        Birthday birthday = new Birthday("1980/15/12/12/12", "Gary", "Winthrop");
        return false; // if an empty string can be formatted to a birthdate pattern
      } catch (IllegalBirthdayFormatException e) {
        System.out.println(e.getMessage());
      }

      // TODO:
      // tries to create Birthday Object with a LONG pattern whose day that doesn't exist
      try {
        Birthday birthday = new Birthday("1980/10/56/12/12", "Gary", "Winthrop");
        return false; // if an empty string can be formatted to a birthdate pattern
      } catch (IllegalBirthdayFormatException e) {
        System.out.println(e.getMessage());
      }

      // TODO:
      // tries to create Birthday Object with a LONG pattern whose hour that doesn't exist
      try {
        Birthday birthday = new Birthday("1980/10/11/63/12", "Gary", "Winthrop");
        return false; // if an empty string can be formatted to a birthdate pattern
      } catch (IllegalBirthdayFormatException e) {
        System.out.println(e.getMessage());
      }

      // TODO:
      // tries to create Birthday Object with a LONG pattern minute that doesn't exist
      try {
        Birthday birthday = new Birthday("1980/10/11/08/88", "Gary", "Winthrop");
        return false; // if an empty string can be formatted to a birthdate pattern
      } catch (IllegalBirthdayFormatException e) {
        System.out.println(e.getMessage());
      }

      // tries to create Birthday Object with a LONG pattern year that is too short
      try {
        Birthday birthday = new Birthday("198/10/11/08/33", "Gary", "Winthrop");
        return false; // if an empty string can be formatted to a birthdate pattern
      } catch (IllegalBirthdayFormatException e) {
        System.out.println(e.getMessage());
      }

      // tries to create Birthday Object with a LONG pattern whose year that is too long
      try {
        Birthday birthday = new Birthday("19233/10/11/08/33", "Gary", "Winthrop");
        return false; // if an empty string can be formatted to a birthdate pattern
      } catch (IllegalBirthdayFormatException e) {
        System.out.println(e.getMessage());
      }

      // tries to create Birthday Object with a LONG pattern whose month that is too short
      try {
        Birthday birthday = new Birthday("19233/1/11/08/33", "Gary", "Winthrop");
        return false; // if an empty string can be formatted to a birthdate pattern
      } catch (IllegalBirthdayFormatException e) {
        System.out.println(e.getMessage());
      }

      // tries to create Birthday Object with a LONG pattern whose month that is too long
      try {
        Birthday birthday = new Birthday("19233/103/11/08/33", "Gary", "Winthrop");
        return false; // if an empty string can be formatted to a birthdate pattern
      } catch (IllegalBirthdayFormatException e) {
        System.out.println(e.getMessage());
      }

      // tries to create Birthday Object with a LONG pattern whose day that is too short
      try {
        Birthday birthday = new Birthday("19233/10/1/08/33", "Gary", "Winthrop");
        return false; // if an empty string can be formatted to a birthdate pattern
      } catch (IllegalBirthdayFormatException e) {
        System.out.println(e.getMessage());
      }

      // tries to create Birthday Object with a LONG pattern whose day that is too long
      try {
        Birthday birthday = new Birthday("19233/10/113/08/33", "Gary", "Winthrop");
        return false; // if an empty string can be formatted to a birthdate pattern
      } catch (IllegalBirthdayFormatException e) {
        System.out.println(e.getMessage());
      }

      // tries to create Birthday Object with a LONG pattern whose hour that is too short
      try {
        Birthday birthday = new Birthday("19233/10/11/8/33", "Gary", "Winthrop");
        return false; // if an empty string can be formatted to a birthdate pattern
      } catch (IllegalBirthdayFormatException e) {
        System.out.println(e.getMessage());
      }

      // tries to create Birthday Object with a LONG pattern whose hour that is too long
      try {
        Birthday birthday = new Birthday("19233/10/113/089/33", "Gary", "Winthrop");
        return false; // if an empty string can be formatted to a birthdate pattern
      } catch (IllegalBirthdayFormatException e) {
        System.out.println(e.getMessage());
      }

      // tries to create Birthday Object with a LONG pattern whose minute that is too short
      try {
        Birthday birthday = new Birthday("19233/10/11/08/3", "Gary", "Winthrop");
        return false; // if an empty string can be formatted to a birthdate pattern
      } catch (IllegalBirthdayFormatException e) {
        System.out.println(e.getMessage());
      }

      // tries to create Birthday Object with a LONG pattern whose minute that is too long
      try {
        Birthday birthday = new Birthday("19233/10/113/08/333", "Gary", "Winthrop");
        return false; // if an empty string can be formatted to a birthdate pattern
      } catch (IllegalBirthdayFormatException e) {
        System.out.println(e.getMessage());
      }

      // tries to create Birthday Object with a SHORT pattern whose year that is too short
      try {
        Birthday birthday = new Birthday("198/06/22", "Gary", "Winthrop");
        return false; // if an empty string can be formatted to a birthdate pattern
      } catch (IllegalBirthdayFormatException e) {
        System.out.println(e.getMessage());
      }

      // tries to create Birthday Object with a SHORT pattern whose year that is too long
      try {
        Birthday birthday = new Birthday("19808/06/22", "Gary", "Winthrop");
        return false; // if an empty string can be formatted to a birthdate pattern
      } catch (IllegalBirthdayFormatException e) {
        System.out.println(e.getMessage());
      }

      // tries to create Birthday Object with a SHORT pattern whose month that is too short
      try {
        Birthday birthday = new Birthday("1980/6/22", "Gary", "Winthrop");
        return false; // if an empty string can be formatted to a birthdate pattern
      } catch (IllegalBirthdayFormatException e) {
        System.out.println(e.getMessage());
      }

      // tries to create Birthday Object with a SHORT pattern whose month that is too long
      try {
        Birthday birthday = new Birthday("1980/062/22", "Gary", "Winthrop");
        return false; // if an empty string can be formatted to a birthdate pattern
      } catch (IllegalBirthdayFormatException e) {
        System.out.println(e.getMessage());
      }

      // tries to create Birthday Object with a SHORT pattern whose day that is too short
      try {
        Birthday birthday = new Birthday("1980/06/2", "Gary", "Winthrop");
        return false; // if an empty string can be formatted to a birthdate pattern
      } catch (IllegalBirthdayFormatException e) {
        System.out.println(e.getMessage());
      }

      // tries to create Birthday Object with a SHORT pattern whose day that is too long
      try {
        Birthday birthday = new Birthday("1980/06/224", "Gary", "Winthrop");
        return false; // if an empty string can be formatted to a birthdate pattern
      } catch (IllegalBirthdayFormatException e) {
        System.out.println(e.getMessage());
      }

    }

    { // Test 2 - create Birthday Objects with birthdates that have valid SHORT patterns

      // create the first Birthday Object to be tested

      Birthday firstBirthday = null;
      // tries to create Birthday Object with a birthdate that is a valid SHORT pattern
      try {
        firstBirthday = new Birthday("1980/06/22", "Gary", "Winthrop");
      } catch (IllegalBirthdayFormatException e) {
        System.out.println(e.getMessage());
        return false; // the creation of valid Birthday Objects should not throw an exception
      }

      // ensures that the birthdate in the long format of this Birthday Object is correct
      if (!firstBirthday.getBirthdayLong().equals(19800622L)) {
        return false;
      }
      // ensures that the birthday Date Object of this Birthday Object is correct
      if (!firstBirthday.getBirthday().toString().equals("Sun Jun 22 00:00:00 CDT 1980")) {
        return false;
      }
      // ensures that the first name of this Birthday Object is correct
      if (!firstBirthday.getFirstName().equals("Gary")) {
        return false;
      }
      // ensures that the last name of this Birthday Object is correct
      if (!firstBirthday.getLastName().equals("Winthrop")) {
        return false;
      }
      // ensure that the toString() method returns the correct information of this Birthday Object
      if (!firstBirthday.toString().equals("Gary Winthrop: Sunday June 22, 1980 @ 00:00 AM")) {
        return false;
      }

      // create the second Birthday Object to be tested

      Birthday secondBirthday = null;
      // tries to create Birthday Object with a birthdate that is a valid SHORT pattern
      try {
        secondBirthday = new Birthday("1995/10/28", "Aaron", "Hall");
      } catch (IllegalBirthdayFormatException e) {
        System.out.println(e.getMessage());
        return false; // the creation of valid Birthday Objects should not throw an exception
      }

      // ensures that the birthdate in the long format of this Birthday Object is correct
      if (!secondBirthday.getBirthdayLong().equals(19951028L)) {
        return false;
      }
      // ensures that the birthday Date Object of this Birthday Object is correct
      if (!secondBirthday.getBirthday().toString().equals("Sat Oct 28 00:00:00 CDT 1995")) {
        return false;
      }
      // ensures that the first name of this Birthday Object is correct
      if (!secondBirthday.getFirstName().equals("Aaron")) {
        return false;
      }
      // ensures that the last name of this Birthday Object is correct
      if (!secondBirthday.getLastName().equals("Hall")) {
        return false;
      }
      // ensure that the toString() method returns the correct information of this Birthday Object
      if (!secondBirthday.toString().equals("Aaron Hall: Saturday October 28, 1995 @ 00:00 AM")) {
        return false;
      }

      // create the third Birthday Object to be tested

      Birthday thirdBirthday = null;
      // tries to create Birthday Object with a birthdate that is a valid SHORT pattern
      try {
        thirdBirthday = new Birthday("1995/10/28", "Cameron", "Famularo");
      } catch (IllegalBirthdayFormatException e) {
        System.out.println(e.getMessage());
        return false; // the creation of valid Birthday Objects should not throw an exception
      }

      // ensures that the birthdate in the long format of this Birthday Object is correct
      if (!thirdBirthday.getBirthdayLong().equals(19951028L)) {
        return false;
      }
      // ensures that the birthday Date Object of this Birthday Object is correct
      if (!thirdBirthday.getBirthday().toString().equals("Sat Oct 28 00:00:00 CDT 1995")) {
        return false;
      }
      // ensures that the first name of this Birthday Object is correct
      if (!thirdBirthday.getFirstName().equals("Cameron")) {
        return false;
      }
      // ensures that the last name of this Birthday Object is correct
      if (!thirdBirthday.getLastName().equals("Famularo")) {
        return false;
      }
      // ensure that the toString() method returns the correct information of this Birthday Object
      if (!thirdBirthday.toString()
        .equals("Cameron Famularo: Saturday October 28, 1995 @ 00:00 AM")) {
        return false;
      }

      // compare the Birthday Objects

      // ensure that the birthday this method is called on occurs after the arg and is yields 1
      if (secondBirthday.compareTo(firstBirthday) != 1) {
        return false;
      }
      // ensure that the birthday this method is called on occurs before the arg and is yields -1
      if (firstBirthday.compareTo(secondBirthday) != -1) {
        return false;
      }
      // ensure that the birthday this method is called on is the same as the arg and is yields 0
      if (secondBirthday.compareTo(thirdBirthday) != 0) {
        return false;
      }

    }

    { // Test 3 - create Birthday Objects with birthdates that have valid LONG patterns

      // create the first Birthday Object to be tested

      Birthday firstBirthday = null;
      // tries to create Birthday Object with a birthdate that is a valid SHORT pattern
      try {
        firstBirthday = new Birthday("1980/06/22/12/24", "Gary", "Winthrop");
      } catch (IllegalBirthdayFormatException e) {
        System.out.println(e.getMessage());
        return false; // the creation of valid Birthday Objects should not throw an exception
      }

      // ensures that the birthdate in the long format of this Birthday Object is correct
      if (!firstBirthday.getBirthdayLong().equals(198006221224L)) {
        return false;
      }
      // ensures that the birthday Date Object of this Birthday Object is correct
      if (!firstBirthday.getBirthday().toString().equals("Sun Jun 22 12:24:00 CDT 1980")) {
        return false;
      }
      // ensures that the first name of this Birthday Object is correct
      if (!firstBirthday.getFirstName().equals("Gary")) {
        return false;
      }
      // ensures that the last name of this Birthday Object is correct
      if (!firstBirthday.getLastName().equals("Winthrop")) {
        return false;
      }
      // ensure that the toString() method returns the correct information of this Birthday Object
      if (!firstBirthday.toString().equals("Gary Winthrop: Sunday June 22, 1980 @ 12:24 PM")) {
        return false;
      }

      // create the second Birthday Object to be tested

      // tires to create Birthday Object with a birthday that is a valid LONG pattern
      Birthday secondBirthday = null;
      try {
        secondBirthday = new Birthday("1995/10/28/10/11", "Aaron", "Hall");
      } catch (IllegalBirthdayFormatException e) {
        System.out.println(e.getMessage());
        return false; // the creation of valid Birthday Objects should not throw an exception
      }

      // ensures that the birthdate in the long format of this Birthday Object is correct
      if (!secondBirthday.getBirthdayLong().equals(199510281011L)) {
        return false;
      }
      // ensures that the birthday Date Object of this Birthday Object is correct
      if (!secondBirthday.getBirthday().toString().equals("Sat Oct 28 10:11:00 CDT 1995")) {
        return false;
      }
      // ensures that the first name of this Birthday Object is correct
      if (!secondBirthday.getFirstName().equals("Aaron")) {
        return false;
      }
      // ensures that the last name of this Birthday Object is correct
      if (!secondBirthday.getLastName().equals("Hall")) {
        return false;
      }
      // ensure that the toString() method returns the correct information of this Birthday Object
      if (!secondBirthday.toString().equals("Aaron Hall: Saturday October 28, 1995 @ 10:11 AM")) {
        return false;
      }

      // create the third Birthday Object to be tested

      Birthday thirdBirthday = null;
      // tries to create Birthday Object with a birthdate that is a valid SHORT pattern
      try {
        thirdBirthday = new Birthday("1995/10/28/10/11", "Cameron", "Famularo");
      } catch (IllegalBirthdayFormatException e) {
        System.out.println(e.getMessage());
        return false; // the creation of valid Birthday Objects should not throw an exception
      }

      // ensures that the birthdate in the long format of this Birthday Object is correct
      if (!thirdBirthday.getBirthdayLong().equals(199510281011L)) {
        return false;
      }
      // ensures that the birthday Date Object of this Birthday Object is correct
      if (!thirdBirthday.getBirthday().toString().equals("Sat Oct 28 10:11:00 CDT 1995")) {
        return false;
      }
      // ensures that the first name of this Birthday Object is correct
      if (!thirdBirthday.getFirstName().equals("Cameron")) {
        return false;
      }
      // ensures that the last name of this Birthday Object is correct
      if (!thirdBirthday.getLastName().equals("Famularo")) {
        return false;
      }
      // ensure that the toString() method returns the correct information of this Birthday Object
      if (!thirdBirthday.toString()
        .equals("Cameron Famularo: Saturday October 28, 1995 @ 10:11 AM")) {
        return false;
      }

      // compare the Birthday Objects

      // ensure that the birthday this method is called on occurs after the arg and is yields 1
      if (secondBirthday.compareTo(firstBirthday) != 1) {
        return false;
      }
      // ensure that the birthday this method is called on occurs before the arg and is yields -1
      if (firstBirthday.compareTo(secondBirthday) != -1) {
        return false;
      }
      // ensure that the birthday this method is called on is the same as the arg and is yields 0
      if (secondBirthday.compareTo(thirdBirthday) != 0) {
        return false;
      }

    }

    { // Test 3 - compare a mix of Birtday Objects that have SHORT and LONG patterns

      // create the first Birthday Object to be tested

      Birthday firstBirthday = null;
      Birthday secondBirthday = null;
      Birthday thirdBirthday = null;
      // tries to create Birthday Object with a birthdate that is a valid SHORT pattern
      try {
        firstBirthday = new Birthday("1980/06/22/12/24", "Gary", "Winthrop");
        secondBirthday = new Birthday("1995/10/28", "Aaron", "Hall");
        thirdBirthday = new Birthday("1995/10/28/10/11", "Cameron", "Famularo");
      } catch (IllegalBirthdayFormatException e) {
        System.out.println(e.getMessage());
        return false; // the creation of valid Birthday Objects should not throw an exception
      }

      // compare the Birthday Objects

      // ensure that the birthday this method is called on occurs after the arg and is yields 1
      if (secondBirthday.compareTo(firstBirthday) != 1) {
        return false;
      }
      // ensure that the birthday this method is called on occurs before the arg and is yields -1
      if (firstBirthday.compareTo(secondBirthday) != -1) {
        return false;
      }
      // ensure that the birthday this method is called on is the same as the arg and is yields 0
      if (secondBirthday.compareTo(thirdBirthday) != -1) {
        return false;
      }

    }

    return true; // return true if all tests pass
  }

  /**
   * This test method ensures that the creation of a BirthdayTree is correct along with some of the
   * methods associated with the tree. More specifically, this method ensures that the insert method
   * inserts nodes in the correct position, colors and balances the nodes correctly, and furthermore
   * ensures that the list of birthdays is correct.
   * 
   * @return true if all tests pass, false if otherwise
   */
  public static boolean testBirthdayTreeCreation() {

    { // Test 1 - ensures that a simple valid Red Black Tree can be created

      // creates a new BirthdayTree Object
      BirthdayTree tree = new BirthdayTree();

      // creates empty Birthday Objects
      Birthday birthday1 = null;
      Birthday birthday2 = null;
      Birthday birthday3 = null;
      Birthday birthday4 = null;
      Birthday birthday5 = null;
      Birthday birthday6 = null;
      Birthday birthday7 = null;
      Birthday birthday8 = null;
      Birthday birthday9 = null;
      Birthday birthday10 = null;

      // tries to create new Birthday Objects
      try {
        birthday1 = new Birthday("1990/01/01", "Patrick", "Harvey");
        birthday2 = new Birthday("1991/01/02", "Charleton", "Heston");
        birthday3 = new Birthday("1992/01/03", "Edward", "Bryant");
        birthday4 = new Birthday("1993/01/04", "Bean", "And Cream");
        birthday5 = new Birthday("1994/01/05", "Jerry", "Riva");
        birthday6 = new Birthday("1995/01/06", "Jeb", "Bush");
        birthday7 = new Birthday("1996/01/07", "Becky", "Bank");
        birthday8 = new Birthday("1997/01/08", "Mrs", "Bungus");
        birthday9 = new Birthday("1998/01/09", "Steve", "Jeffry");
        birthday10 = new Birthday("1999/01/10", "Baggie", "Beach");
      } catch (IllegalBirthdayFormatException e) {
        System.out.println(e.getMessage());
        return false; // all of these Birthday Objects should be valid input
      }

      // tries to add valid Birthday Objects to the BirthdayTree
      if (!tree.addBirthday(birthday4)) {
        return false;
      }
      if (!tree.addBirthday(birthday7)) {
        return false;
      }
      if (!tree.addBirthday(birthday3)) {
        return false;
      }
      if (!tree.addBirthday(birthday1)) {
        return false;
      }
      if (!tree.addBirthday(birthday10)) {
        return false;
      }
      if (!tree.addBirthday(birthday8)) {
        return false;
      }
      if (!tree.addBirthday(birthday6)) {
        return false;
      }
      if (!tree.addBirthday(birthday2)) {
        return false;
      }
      if (!tree.addBirthday(birthday5)) {
        return false;
      }
      if (!tree.addBirthday(birthday9)) {
        return false;
      }

      // ensures that the Birthday Objects are in the correct position and are the correct color
      if (!tree.getRoot().data.toString()
        .equals("Bean And Cream: Monday January 04, 1993 @ 00:00 AM") && !tree.getRoot().isBlack) {
        System.out.println("WRONG ROOT");
        return false;
      }
      if (!tree.getRoot().leftChild.data.toString()
        .equals("Charleton Heston: Wednesday January 02, 1991 @ 00:00 AM")
        && !tree.getRoot().leftChild.isBlack) {
        System.out.println("WRONG LEFTCHILD");
        return false;
      }
      if (!tree.getRoot().rightChild.data.toString()
        .equals("Mrs Bungus: Wednesday January 08, 1997 @ 00:00 AM")
        && tree.getRoot().rightChild.isBlack) {
        System.out.println("WRONG RIGHTCHILD");
        return false;
      }
      if (!tree.getRoot().leftChild.leftChild.data.toString()
        .equals("Patrick Harvey: Monday January 01, 1990 @ 00:00 AM")
        && tree.getRoot().leftChild.leftChild.isBlack) {
        System.out.println("WRONG LEFTCHILD LEFTCHILD");
        return false;
      }
      if (!tree.getRoot().leftChild.rightChild.data.toString()
        .equals("Edward Bryant: Friday January 03, 1992 @ 00:00 AM")
        && tree.getRoot().leftChild.rightChild.isBlack) {
        System.out.println("WRONG LEFTCHILD RIGHTCHILD");
        return false;
      }
      if (!tree.getRoot().rightChild.rightChild.data.toString()
        .equals("Baggie Beach: Sunday January 10, 1999 @ 00:00 AM")
        && !tree.getRoot().rightChild.rightChild.isBlack) {
        System.out.println("WRONG RIGHTCHILD RIGHTCHILD");
        return false;
      }
      if (!tree.getRoot().rightChild.leftChild.data.toString()
        .equals("Jeb Bush: Friday January 06, 1995 @ 00:00 AM")
        && !tree.getRoot().rightChild.leftChild.isBlack) {
        System.out.println("WRONG RIGHTCHILD LEFTCHILD");
        return false;
      }
      if (!tree.getRoot().rightChild.leftChild.leftChild.data.toString()
        .equals("Jerry Riva: Wednesday January 05, 1994 @ 00:00 AM")
        && tree.getRoot().rightChild.leftChild.leftChild.isBlack) {
        System.out.println("WRONG RIGHTCHILD LEFTCHILD LEFTCHILD");
        return false;
      }
      if (!tree.getRoot().rightChild.leftChild.rightChild.data.toString()
        .equals("Becky Bank: Sunday January 07, 1996 @ 00:00 AM")
        && tree.getRoot().rightChild.leftChild.rightChild.isBlack) {
        System.out.println("WRONG RIGHTCHILD LEFTCHILD RIGHTCHILD");
        return false;
      }
      if (!tree.getRoot().rightChild.rightChild.leftChild.data.toString()
        .equals("Steve Jeffry: Friday January 09, 1998 @ 00:00 AM")
        && tree.getRoot().rightChild.rightChild.leftChild.isBlack) {
        System.out.println("WRONG RIGHTCHILD RIGHTCHILD LEFTCHILD");
        return false;
      }

      // ensures that the size of the BirthdayTree is correct
      if (tree.getSize() != 10) {
        System.out.println("The size of the tree is wrong!");
        return false;
      }

      // ensures that the list of the BirthdayTree or the ArrayList of Birthday Objects is correct
      String listOutput = tree.getList().toString();
      String expectedListOutput =
        "[Bean And Cream: Monday January 04, 1993 @ 00:00 AM, Becky Bank: Sunday January 07, 1996 "
          + "@ 00:00 AM, Edward Bryant: Friday January 03, 1992 @ 00:00 AM, Patrick Harvey: Monday "
          + "January 01, 1990 @ 00:00 AM, Baggie Beach: Sunday January 10, 1999 @ 00:00 AM, Mrs "
          + "Bungus: Wednesday January 08, 1997 @ 00:00 AM, Jeb Bush: Friday January 06, 1995 "
          + "@ 00:00 AM, Charleton Heston: Wednesday January 02, 1991 @ 00:00 AM, Jerry Riva: "
          + "Wednesday January 05, 1994 @ 00:00 AM, Steve Jeffry: Friday January 09, 1998 @ 00:00 "
          + "AM]";
      if (!listOutput.equals(expectedListOutput)) {
        System.out.println("The ArrayList of Birthdays is wrong!");
        return false;
      }

    }

    { // Test 2 - ensures that an invalid Red Black Tree throws appropriate exceptions

      // creates a new BirthdayTree Object
      BirthdayTree tree = new BirthdayTree();

      // creates empty Birthday Objects
      Birthday birthday1 = null;
      Birthday birthday2 = null;
      Birthday birthday3 = null;
      Birthday birthday4 = null;

      // creates new Birthday Objects
      try {
        birthday1 = new Birthday("1990/01/01", "Patrick", "Harvey");
        birthday2 = new Birthday("1991/01/02", "Charleton", "Heston");
        birthday3 = new Birthday("1991/01/02", "Edward", "Bryant");
      } catch (IllegalBirthdayFormatException e) {
        System.out.println(e.getMessage());
        return false; // all of these Birthday Objects should be "valid" input when not in the tree
      }

      // ensures that two objects with the same birthdate cannot be added to the tree
      try {
        tree.addBirthday(birthday1);
        tree.addBirthday(birthday2);
        tree.addBirthday(birthday3); // this birthdate is the exact same as birthday2
        // TODO:
        return false; // if two objects with the same birthdate can be added to the tree
      } catch (BirthdayAlreadyAddedException e) {
        System.out.println(e.getMessage());
      }

      // ensures that you cannot add a null Birthday Object or null reference in general to the tree
      if (tree.addBirthday(birthday4)) {
        System.out.println("Could add a null Birthday Object to the tree which is wrong!");
        return false;
      }
      if (tree.addBirthday(null)) {
        System.out.println("Could add a null reference to the tree which is wrong!");
        return false;
      }

      // ensures that the Birthday Objects are in the correct position and are the correct color
      if (!tree.getRoot().data.toString()
        .equals("Patrick Harvey: Monday January 01, 1990 @ 00:00 AM") && !tree.getRoot().isBlack) {
        System.out.println("WRONG ROOT");
        return false;
      }
      if (!tree.getRoot().rightChild.data.toString()
        .equals("Charleton Heston: Wednesday January 02, 1991 @ 00:00 AM")
        && tree.getRoot().rightChild.isBlack) {
        System.out.println("WRONG RIGHTCHILD");
        return false;
      }

      // ensures that the size of the BirthdayTree is correct
      if (tree.getSize() != 2) {
        System.out.println("The size of the tree is wrong!");
        return false;
      }

      // ensures that the list of the BirthdayTree or the ArrayList of Birthday Objects is correct
      String listOutput = tree.getList().toString();
      String expectedListOutput = "[Patrick Harvey: Monday January 01, 1990 @ 00:00 AM, Charleton "
        + "Heston: Wednesday January 02, 1991 @ 00:00 AM]";
      if (!listOutput.equals(expectedListOutput)) {
        System.out.println("The ArrayList of Birthdays is wrong!");
        return false;
      }

    }

    return true; // return true if all tests pass
  }

  /**
   * This test method ensures that all of the Birthday Objects within the Red Black Tree or
   * BirthdayTree are removed correctly.
   * 
   * @return true if all tests pass, false if otherwise
   */
  public static boolean testClear() {

    { // Test 1 - clears a simple valid Red Black Tree

      // creates a new BirthdayTree Object
      BirthdayTree tree = new BirthdayTree();

      // creates empty Birthday Objects
      Birthday birthday1 = null;
      Birthday birthday2 = null;
      Birthday birthday3 = null;
      Birthday birthday4 = null;
      Birthday birthday5 = null;
      Birthday birthday6 = null;
      Birthday birthday7 = null;
      Birthday birthday8 = null;
      Birthday birthday9 = null;
      Birthday birthday10 = null;

      // tries to create new Birthday Objects
      try {
        birthday1 = new Birthday("1990/01/01", "Patrick", "Harvey");
        birthday2 = new Birthday("1991/01/02", "Charleton", "Heston");
        birthday3 = new Birthday("1992/01/03", "Edward", "Bryant");
        birthday4 = new Birthday("1993/01/04", "Bean", "And Cream");
        birthday5 = new Birthday("1994/01/05", "Jerry", "Riva");
        birthday6 = new Birthday("1995/01/06", "Jeb", "Bush");
        birthday7 = new Birthday("1996/01/07", "Becky", "Bank");
        birthday8 = new Birthday("1997/01/08", "Mrs", "Bungus");
        birthday9 = new Birthday("1998/01/09", "Steve", "Jeffry");
        birthday10 = new Birthday("1999/01/10", "Baggie", "Beach");
      } catch (IllegalBirthdayFormatException e) {
        System.out.println(e.getMessage());
        return false; // all of these Birthday Objects should be valid input
      }

      // adds valid Birthday Objects to the BirthdayTree
      tree.addBirthday(birthday4);
      tree.addBirthday(birthday7);
      tree.addBirthday(birthday3);
      tree.addBirthday(birthday1);
      tree.addBirthday(birthday10);
      tree.addBirthday(birthday8);
      tree.addBirthday(birthday6);
      tree.addBirthday(birthday2);
      tree.addBirthday(birthday5);
      tree.addBirthday(birthday9);

      // ensures that these Birthday Objects can be cleared from the BirthdayTree

      // clears the BirthdayTree
      tree.clear();

      // ensures that the size of an empty tree is zero
      if (tree.getSize() != 0) {
        System.out.println("The size of an empty BirthdayTree should be 0!");
        return false;
      }

      // ensures that the root node of the empty tree is null
      if (tree.getRoot() != null) {
        System.out.println("The root node of an empty tree should be null!");
        return false;
      }

      // ensures that the ArrayList of the BirthdayTree is empty
      String listOutput = tree.getList().toString();
      String expectedListOutput = "[]";
      if (!listOutput.equals(expectedListOutput)) {
        System.out.println("The ArrayList of Birthdays is wrong!");
        return false;
      }

    }

    { // Test 2 - clears a Red Black Tree after tying to add invalid Birthday Objects

      // creates a new BirthdayTree Object
      BirthdayTree tree = new BirthdayTree();

      // creates empty Birthday Objects
      Birthday birthday1 = null;
      Birthday birthday2 = null;
      Birthday birthday3 = null;
      Birthday birthday4 = null;

      // creates new Birthday Objects
      try {
        birthday1 = new Birthday("1990/01/01", "Patrick", "Harvey");
        birthday2 = new Birthday("1991/01/02", "Charleton", "Heston");
        birthday3 = new Birthday("1991/01/02", "Edward", "Bryant");
      } catch (IllegalBirthdayFormatException e) {
        System.out.println(e.getMessage());
        return false; // all of these Birthday Objects should be "valid" input when not in the tree
      }

      // ensures that two objects with the same birthdate cannot be added to the tree
      try {
        tree.addBirthday(birthday1);
        tree.addBirthday(birthday2);
        tree.addBirthday(birthday3); // this birthdate is the exact same as birthday2
        // TODO:
        return false; // if two objects with the same birthdate can be added to the tree
      } catch (BirthdayAlreadyAddedException e) {
        System.out.println(e.getMessage());
      }

      // ensures that you cannot add a null Birthday Object or null reference in general to the tree
      if (tree.addBirthday(birthday4)) {
        System.out.println("Could add a null Birthday Object to the tree which is wrong!");
        return false;
      }
      if (tree.addBirthday(null)) {
        System.out.println("Could add a null reference to the tree which is wrong!");
        return false;
      }

      // ensures that these Birthday Objects can be cleared from the BirthdayTree

      // clears the BirthdayTree
      tree.clear();

      // ensures that the size of an empty tree is zero
      if (tree.getSize() != 0) {
        System.out.println("The size of an empty BirthdayTree should be 0!");
        return false;
      }

      // ensures that the root node of the empty tree is null
      if (tree.getRoot() != null) {
        System.out.println("The root node of an empty tree should be null!");
        return false;
      }

      // ensures that the ArrayList of the BirthdayTree is empty
      String listOutput = tree.getList().toString();
      String expectedListOutput = "[]";
      if (!listOutput.equals(expectedListOutput)) {
        System.out.println("The ArrayList of Birthdays is wrong!");
        return false;
      }

    }

    { // Test 3 - clears an empty Red Black Tree

      // creates a new BirthdayTree Object
      BirthdayTree tree = new BirthdayTree();

      // clears the BirthdayTree
      tree.clear();

      // ensures that the size of an empty tree is zero
      if (tree.getSize() != 0) {
        System.out.println("The size of an empty BirthdayTree should be 0!");
        return false;
      }

      // ensures that the root node of the empty tree is null
      if (tree.getRoot() != null) {
        System.out.println("The root node of an empty tree should be null!");
        return false;
      }

      // ensures that the ArrayList of the BirthdayTree is empty
      String listOutput = tree.getList().toString();
      String expectedListOutput = "[]";
      if (!listOutput.equals(expectedListOutput)) {
        System.out.println("The ArrayList of Birthdays is wrong!");
        return false;
      }

    }

    return true; // return true if all tests pass
  }


  /**
   * This test method tests the methods within the front end that were not necessarily tested in the
   * backend to ensure that the front end methods function properly and that the front end in
   * general works with the backend code in general. This method is essentially an instance of
   * running the front end where each set of commands is entered one after the other just as a user
   * would do so when running the program in the console. Furthermore, this method ensures that the
   * output of both front-end's are correct and differentiates the input based on whose front end is
   * run.
   * 
   * @return true if all tests pass, false if otherwise
   */
  public static boolean testFrontEnd() {

    // create a String variable to store whose front end is running
    String backend = "";



    // Test loadCSV() to ensure that a valid file can be loaded


    // create a new front end Object
    BirthdaySearchFrontEnd frontEnd = new BirthdaySearchFrontEnd();

    // create a scanner to pass input to the front end
    Scanner input = new Scanner("birthdays.csv");

    // create a new PrintStream object to get the prior output to the console
    PrintStream oldConsoleStream = new PrintStream(System.out);

    // create a new console that will be able to collect the standard input to the console

    // creates a new buffer or console where the data that is going to be outputted is stored
    ByteArrayOutputStream newConsoleByteArray = new ByteArrayOutputStream();
    // creates PrintStream Object to get the data that is going to be outputted to the console
    PrintStream newConsoleStream = new PrintStream(newConsoleByteArray);
    // sets the new standard output to a new PrintStream whose output is going to be collected
    System.setOut(newConsoleStream);

    // passes simulated user input to loadCSV() to try and load a valid file
    frontEnd.loadCSV(input);

    // put the outputted data from the front end into the old print stream using System.out
    oldConsoleStream.append(newConsoleByteArray.toString());

    // stop using the new standard ouutput stream and go back to using System.out
    System.setOut(oldConsoleStream);

    // put the outputted text to the console into a string
    String actualOutput = newConsoleByteArray.toString();
    // remove the newline from the outputted text
    actualOutput = actualOutput.substring(0, actualOutput.length() - 2);
    // create String objects depending upon what the expected output is for each front end
    String expectedOutputOne = "CSV file successfully loaded!";
    String expectedOutputTwo = "CSV file loaded successfully.";

    // ensures that the output of the console is correct
    if (actualOutput.contains("Invalid")) {
      System.out.println("A valid csv file could not be loaded successfully!");
      return false;
    }



    // Test addBirthday() to ensure that a new valid birthday can be added to the tree


    // determine whose front end we are using based upon the output of the last test
    // assign the input based upon whose front end we are using
    if (actualOutput.contains("loaded!")) {
      input = new Scanner("John\nCena\n1977\n04\n23\ny\n10\n11\ny");
      backend = "Matt";
    } else {
      input = new Scanner("John\nCena\n1977\n04\n23\n10\n11\n");
      backend = "Riley";
    }

    // create a new PrintStream object to get the prior output to the console
    oldConsoleStream = new PrintStream(System.out);

    // create a new console that will be able to collect the standard input to the console

    // creates a new buffer or console where the data that is going to be outputted is stored
    newConsoleByteArray = new ByteArrayOutputStream();
    // creates PrintStream Object to get the data that is going to be outputted to the console
    newConsoleStream = new PrintStream(newConsoleByteArray);
    // sets the new standard output to a new PrintStream whose output is going to be collected
    System.setOut(newConsoleStream);

    // passes simulated user input to addBirthday() to try and add valid birthday
    frontEnd.addBirthday(input);

    // put the outputted data from the front end into the old print stream using System.out
    oldConsoleStream.append(newConsoleByteArray.toString());

    // stop using the new standard ouutput stream and go back to using System.out
    System.setOut(oldConsoleStream);

    // put the outputted text to the console into a string
    String actual = newConsoleByteArray.toString();
    // remove the newline from the outputted text
    actual = actual.substring(0, actual.length() - 2);

    // ensures that the output of the console is correct
    if (actual.contains("not added") || actual.contains("malformed")
      || actual.contains("already added")) {
      System.out.println("A valid birthday could not be added!");
      return false;
    }



    // Test search() when searching for a valid name with an associated birthdate in the tree


    // input is the same for both front ends so create only one new scanner
    input = new Scanner("n\nJohn\nCena\n");

    // create a new PrintStream object to get the prior output to the console
    oldConsoleStream = new PrintStream(System.out);

    // create a new console that will be able to collect the standard input to the console

    // creates a new buffer or console where the data that is going to be outputted is stored
    newConsoleByteArray = new ByteArrayOutputStream();
    // creates PrintStream Object to get the data that is going to be outputted to the console
    newConsoleStream = new PrintStream(newConsoleByteArray);
    // sets the new standard output to a new PrintStream whose output is going to be collected
    System.setOut(newConsoleStream);

    // passes simulated user input to addBirthday() to try and add valid birthday
    frontEnd.search(input);

    // put the outputted data from the front end into the old print stream using System.out
    oldConsoleStream.append(newConsoleByteArray.toString());

    // stop using the new standard ouutput stream and go back to using System.out
    System.setOut(oldConsoleStream);

    // put the outputted text to the console into a string
    actual = newConsoleByteArray.toString();
    // remove the newline from the outputted text
    actual = actual.substring(0, actual.length() - 2);

    // ensures that the output of the console is correct
    if (actual.contains("Sorry!")) {
      System.out.println("A valid name with a birthday could not be searched for!");
      return false;
    }



    // Test search() when searching for a valid birthdate in the tree


    input = new Scanner("d\n1977\n04\n23\n10\n11");

    // create a new PrintStream object to get the prior output to the console
    oldConsoleStream = new PrintStream(System.out);

    // create a new console that will be able to collect the standard input to the console

    // creates a new buffer or console where the data that is going to be outputted is stored
    newConsoleByteArray = new ByteArrayOutputStream();
    // creates PrintStream Object to get the data that is going to be outputted to the console
    newConsoleStream = new PrintStream(newConsoleByteArray);
    // sets the new standard output to a new PrintStream whose output is going to be collected
    System.setOut(newConsoleStream);

    // passes simulated user input to addBirthday() to try and add valid birthday
    frontEnd.search(input);

    // put the outputted data from the front end into the old print stream using System.out
    oldConsoleStream.append(newConsoleByteArray.toString());

    // stop using the new standard ouutput stream and go back to using System.out
    System.setOut(oldConsoleStream);

    // put the outputted text to the console into a string
    actual = newConsoleByteArray.toString();
    // remove the newline from the outputted text
    actual = actual.substring(0, actual.length() - 2);

    // ensures that the output of the console is correct
    if (actual.contains("Sorry!")) {
      System.out.println("A valid birthdate could not be searched for!");
      return false;
    }



    // Test printBirthdaysInRange() to ensure that valid birthdays within a range are found


    // assign the input based upon whose front end we are using
    if (backend.contains("Matt")) {
      input = new Scanner("1900\n01\n01\n2000\n01\n01");
    } else {
      input = new Scanner("1900\n01\n01\n01\n01\n2000\n01\n01\n01\n01");
    }

    // create a new PrintStream object to get the prior output to the console
    oldConsoleStream = new PrintStream(System.out);

    // create a new console that will be able to collect the standard input to the console

    // creates a new buffer or console where the data that is going to be outputted is stored
    newConsoleByteArray = new ByteArrayOutputStream();
    // creates PrintStream Object to get the data that is going to be outputted to the console
    newConsoleStream = new PrintStream(newConsoleByteArray);
    // sets the new standard output to a new PrintStream whose output is going to be collected
    System.setOut(newConsoleStream);

    // passes simulated user input to addBirthday() to try and add valid birthday
    frontEnd.printBirthdaysInRange(input);

    // put the outputted data from the front end into the old print stream using System.out
    oldConsoleStream.append(newConsoleByteArray.toString());

    // stop using the new standard ouutput stream and go back to using System.out
    System.setOut(oldConsoleStream);

    // put the outputted text to the console into a string
    actual = newConsoleByteArray.toString();
    // remove the newline from the outputted text
    actual = actual.substring(0, actual.length() - 2);

    // ensures that the output of the console is correct
    if (actual.contains("no birthdays")) {
      System.out.println("There were no birthdays in a valid range containing birthdays!");
      return false;
    }



    // Test birthdaysToday() to ensure that valid birthdays that are today are printed


    // assign the input based upon whose front end we are using
    if (backend.contains("Matt")) {
      input = new Scanner("Gangus\nKahn\n2020\n10\n20\ny\n12\n11\ny");
    } else {
      input = new Scanner("Mozilla\nFirefox\n2020\n10\n20\n10\n11\n");
    }
    frontEnd.addBirthday(input);

    // create a new PrintStream object to get the prior output to the console
    oldConsoleStream = new PrintStream(System.out);

    // create a new console that will be able to collect the standard input to the console

    // creates a new buffer or console where the data that is going to be outputted is stored
    newConsoleByteArray = new ByteArrayOutputStream();
    // creates PrintStream Object to get the data that is going to be outputted to the console
    newConsoleStream = new PrintStream(newConsoleByteArray);
    // sets the new standard output to a new PrintStream whose output is going to be collected
    System.setOut(newConsoleStream);

    // passes simulated user input to addBirthday() to try and add valid birthday
    frontEnd.birthdaysToday();

    // put the outputted data from the front end into the old print stream using System.out
    oldConsoleStream.append(newConsoleByteArray.toString());

    // stop using the new standard ouutput stream and go back to using System.out
    System.setOut(oldConsoleStream);

    // put the outputted text to the console into a string
    actual = newConsoleByteArray.toString();
    // remove the newline from the outputted text
    actual = actual.substring(0, actual.length() - 2);

    // ensures that the output of the console is correct
    if (actual.contains("Nobody")) {
      System.out.println("There should be at least one birthday today!");
      return false;
    }

    return true; // return true if all tests pass
  }


  /**
   * This main method contains the entire program that calls the tests methods within this class to
   * ensure that the BST or Birthday Search Tool functions correctly.
   * 
   * @param args unused
   */
  public static void main(String[] args) {
    System.out.println(testLoadFile());
    System.out.println(testBirthdayObject());
    System.out.println(testBirthdayTreeCreation());
    System.out.println(testClear());
    System.out.println(testFrontEnd());

  }

}
