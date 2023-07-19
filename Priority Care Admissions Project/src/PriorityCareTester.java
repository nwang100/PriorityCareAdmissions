
import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * This is a Utility class which contains tester methods to ensure the correctness of the
 * implementation of the main operations defined in cs300 spring 2023 p10 Priority Care.
 *
 */
public class PriorityCareTester {

  /**
   * Tests whether compareTo() method implemented in PatientRecord returns a positive integer when a
   * higher triage level is compared to a lower triage level, regardless of patient order of
   * arrival. Similarly, this method tests whether compareTo() method implemented in PatientRecord
   * returns a negative integer when a lower triage level is compared to a higher triage level,
   * regardless of patient order of arival.
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   * @see PatientRecord#compareTo(PatientRecord)
   */
  public static boolean testPatientRecordCompareToDifferentTriage() {

    // Initialize PatientRecords to be compared to
    PatientRecord testLargest = new PatientRecord('F', 65, TriageLevel.GREEN);
    PatientRecord testSmallest = new PatientRecord('M', 18, TriageLevel.RED);

    // Ensure that a lower to a higher triage returns -1
    if (!(testSmallest.compareTo(testLargest) < 0)) {
      return false;
    }

    // Ensure that comparing a higher to a lower triage returns 1
    if (!(testLargest.compareTo(testSmallest) > 0)) {
      return false;
    }

    return true; // Return true if and only if all test cases pass
  }

  /**
   * Tests whether patients in the same triage level are compared based on their order of arrival.
   * Patients of the same triage level with a lower arrival number compared to patients with a
   * higher arrival number should return a negative integer. The reverse situation should return a
   * positive integer.
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   * @see PatientRecord#compareTo(PatientRecord)
   */
  public static boolean testPatientRecordCompareToSameTriageDifferentArrival() {

    // Create a PatientRecord and a copy of it with a different arrival number
    PatientRecord test = new PatientRecord('M', 18, TriageLevel.RED);
    PatientRecord testCopy = new PatientRecord('M', 18, TriageLevel.RED);

    // Ensure that comparing two patients that are the same besides order of arrival returns -1
    if (!(test.compareTo(testCopy) < 0)) {
      return false;
    }

    return true; // Return true if and only if all test cases pass
  }

  /**
   * Tests whether patients in the same triage level and with the same order of arrival are equal
   * (compareTo should return 0). Even though this case will not be possible in your priority queue,
   * it is required for testing the full functionality of the compareTo() method. Hint: you will
   * need to use the resetCounter() to create equivalent PatientRecords.
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   * @see PatientRecord#compareTo(PatientRecord)
   */
  public static boolean testPatientRecordCompareToSameTriageSameArrival() {
    // Create a patient and a deep copy of this patient with the same arrival order and triage
    PatientRecord dummyPatient = new PatientRecord('M', 19, TriageLevel.RED);
    dummyPatient.resetCounter();
    PatientRecord testSmallest = new PatientRecord('M', 18, TriageLevel.RED);
    testSmallest.resetCounter();
    PatientRecord testSmallestCopy = new PatientRecord('M', 18, TriageLevel.RED);

    // Compare a deep copy of the smallest test patientRecord to a deep copy of itself
    if (testSmallest.compareTo(testSmallestCopy) != 0) {
      return false;
    }

    return true; // Return if and only if all test cases pass
  }

  /**
   * Tests the functionality of the constructor for PriorityCareAdmissions Should implement at least
   * the following tests:
   *
   * - Calling the PriorityCareAdmissions with an invalid capacity should throw an
   * IllegalArgumentException - Calling the PriorityCareAdmissions with a valid capacity should not
   * throw any errors, and should result in a new PriorityCareAdmissions which is empty, has size 0,
   * a capacity equal to the capacity that was passed as a parameter.
   *
   * @return true if the constructor of PriorityCareAdmissions functions properly, false otherwise
   * @see PriorityCareAdmissions#PriorityCareAdmissions(int)
   */
  public static boolean testConstructor() {

    // Test the constructor with a faulty capacity argument
    try {
      PriorityCareAdmissions test = new PriorityCareAdmissions(-1);
      return false; // return false if the constructor does not return an error
    } catch (IllegalArgumentException e) {
    } ;

    // Test the constructor with a valid capacity argument
    PriorityCareAdmissions test = new PriorityCareAdmissions(5);

    // Verify that the constructor has correctly set up the test variable

    if (test.size() != 0 || test.isEmpty() != true) {
      return false;
    }

    return true; // Return true if and only if all test cases pass
  }

  /**
   * Tests the functionality of peek() method by calling peek on an empty queue and verifying it
   * throws a NoSuchElementException.
   * 
   * @return true if PriorityCareAdmissions.peek() exhibits expected behavior, false otherwise.
   */
  public static boolean testPeekEmpty() {

    // Initialize PriorityCareAdmissions to be tested
    PriorityCareAdmissions test = new PriorityCareAdmissions(5);

    // Verify that peeking at an empty queue throws a NoSuchElementException
    try {
      test.peek();
      return false;
    } catch (NoSuchElementException e) {
    } ;

    return true; // Return true if and only if all test cases work
  }

  /**
   * Tests the functionality of peek() method by calling peek on a non-empty queue and verifying it
   * 1) returns the PatientRecord having the highest priority (the minimum) and 2) does not remove
   * the PatientRecord from the queue.
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean testPeekNonEmpty() {

    // Create new objects of PatientREcord and PriorityCareAdmissions to test peek function
    PriorityCareAdmissions testAdmissions = new PriorityCareAdmissions(5);
    PatientRecord test = new PatientRecord('M', 18, TriageLevel.RED);
    PatientRecord test1 = new PatientRecord('F', 28, TriageLevel.YELLOW);

    // Add the patients to the PriorityCareAdmissions
    testAdmissions.addPatient(test);
    testAdmissions.addPatient(test1);

    // Verify that peek() returns the patient with the highest Priority by comparing toStrings
    if (!(testAdmissions.peek().toString().equals(test.toString()) && testAdmissions.size() == 2)) {
      return false;
    }

    return true; // Return true if and only if all the test cases pass
  }

  /**
   * Tests the functionality of addPatient() method by calling addPatient() on an empty queue and
   * ensuring the method 1) adds the PatientRecord and 2) increments the size.
   * 
   * @return true if PriorityCareAdmissions.addPatient() exhibits expected behavior, false
   *         otherwise.
   */
  public static boolean testAddPatientEmpty() {

    // Initialize the min-queue and patient to be added to the queue
    PriorityCareAdmissions testAdmissions = new PriorityCareAdmissions(5);
    PatientRecord test = new PatientRecord('M', 18, TriageLevel.RED);

    testAdmissions.addPatient(test); // Add the patient to the empty queue

    // Make sure the queue is not empty
    if (testAdmissions.isEmpty()) {
      return false;
    }

    // Ensure that the contents of the string are that just of the patient added and size is 1
    if (!testAdmissions.peek().toString().equals(test.toString()) && testAdmissions.size() != 1) {
      return false;
    }

    return true; // Return true if and only if all test cases pass
  }

  /**
   * Tests the functionality of addPatient() method by calling addPatient() on a non-empty queue and
   * ensuring the method 1) adds the PatientRecord at the proper position and 2) increments the
   * size. Try add at least 5 PatientRecords.
   * 
   * @return true if PriorityCareAdmissions.addPatient() exhibits expected behavior, false otherwise
   */
  public static boolean testAddPatientNonEmpty() {

    // Initialize min-queue to be tested on
    PriorityCareAdmissions testAdmissions = new PriorityCareAdmissions(6);

    // Initialize the patients to be added to the min-queue
    PatientRecord test1 = new PatientRecord('M', 18, TriageLevel.RED);
    PatientRecord test2 = new PatientRecord('F', 19, TriageLevel.RED);
    PatientRecord test3 = new PatientRecord('M', 20, TriageLevel.YELLOW);
    PatientRecord test4 = new PatientRecord('F', 21, TriageLevel.YELLOW);
    PatientRecord test5 = new PatientRecord('M', 48, TriageLevel.GREEN);

    // Add all the patients to the min-queue
    testAdmissions.addPatient(test1);
    testAdmissions.addPatient(test2);
    testAdmissions.addPatient(test3);
    testAdmissions.addPatient(test4);
    testAdmissions.addPatient(test5);

    // Expected toString of the queue with patients added
    String expected = test1.toString() + "\n" + test2.toString() + "\n" + test3.toString() + "\n"
        + test4.toString() + "\n" + test5.toString() + "\n";

    // Make sure the queue is not empty
    if (testAdmissions.isEmpty()) {
      return false;
    }

    // Ensure the contents of the array are the same as the patients added
    if (!testAdmissions.toString().equals(expected)) {
      return false;
    }

    // Ensure that the size of the min-queue is 5
    if (testAdmissions.size() != 5) {
      return false;
    }

    return true; // return true if and only if all of the test cases pass
  }

  /**
   * Tests the functionality of addPatient() method by calling addPatient() on a full queue and
   * ensuring the method throws an IllegalStateException.
   * 
   * @return true if PriorityCareAdmissions.addPatient() exhibits expected behavior, false
   *         otherwise.
   */
  public static boolean testAddPatientFull() {

    // Initialize a min-queue to be tested on with size 3
    PriorityCareAdmissions testAdmissions = new PriorityCareAdmissions(3);

    // Initialize the patients to be added
    PatientRecord test1 = new PatientRecord('M', 18, TriageLevel.RED);
    PatientRecord test2 = new PatientRecord('F', 30, TriageLevel.RED);
    PatientRecord test3 = new PatientRecord('F', 40, TriageLevel.RED);
    PatientRecord test4 = new PatientRecord('M', 50, TriageLevel.RED);

    // Add the first three patients to the queue
    testAdmissions.addPatient(test1);
    testAdmissions.addPatient(test2);
    testAdmissions.addPatient(test3);

    // Ensure that adding the 4th patient to the queue throws an IllegalStateException and no other
    try {
      testAdmissions.addPatient(test4);
      return false;
    } catch (IllegalStateException e) {
    } catch (Exception e) {
      return false;
    }

    return true; // return true if and only if all test cases pass
  }

  /**
   * Tests the functionality of addPatient() method by calling addPatient() with a null
   * PatientRecord and ensuring the method throws a NullPointerException.
   * 
   * @return true if PriorityCareAdmissions.addPatient() exhibits expected behavior, false
   *         otherwise.
   */
  public static boolean testAddPatientNull() {

    // Initialize queue to be tested on
    PriorityCareAdmissions testAdmissions = new PriorityCareAdmissions(5);

    // Try adding with a null index, ensuring that it throws a NullPointerException
    try {
      testAdmissions.addPatient(null);
      return false;
    } catch (NullPointerException e) {
    } ;

    return true; // Return true if and only if all test cases pass
  }

  /**
   * Tests the functionality of removeBestRecord() method by calling removeBestRecord() on an empty
   * queue.
   * 
   * @return true if PriorityCareAdmissions.removeBestRecord() throws a NoSuchElementException,
   *         false otherwise
   */
  public static boolean testRemoveBestRecordEmpty() {

    // Initialize a queue to be tested on
    PriorityCareAdmissions testAdmissions = new PriorityCareAdmissions(6);

    // ensure that removing a patient from the full queue throws a NoSuchElementException
    try {
      testAdmissions.removeBestRecord();
      return false;
    } catch (NoSuchElementException e) {
    } ;

    return true; // Return true if and only if all test cases pass
  }


  /**
   * Tests the functionality of removeBestRecord() method by calling removeBestRecord() on a queue
   * of size one.
   * 
   * @return true if PriorityCareAdmissions.removeBestRecord() returns the correct PatientRecord and
   *         size is 0
   */
  public static boolean testRemoveBestRecordSizeOne() {

    // Initialize queue to be tested on
    PriorityCareAdmissions testAdmissions = new PriorityCareAdmissions(3);

    // Initialize patientRecord to be added
    PatientRecord test1 = new PatientRecord('M', 18, TriageLevel.RED);

    testAdmissions.addPatient(test1); // Add the patient record to the queue

    testAdmissions.removeBestRecord(); // Remove the patient record from the queue

    // Ensure that the queue is empty and the size is 0
    if (!(testAdmissions.isEmpty() && testAdmissions.size() == 0)) {
      return false;
    }

    return true; // Return true if and only if all test cases pass
  }

  /**
   * Tests the functionality of removeBestRecord() methods.
   * 
   * The removeBestRecord() method must remove, and return the patient record with the highest
   * priority in the queue. The size must be decremented by one, each time the removeBestRecord()
   * method is successfully called.
   * 
   * Remove the best record from a queue whose size is at least 6. Consider cases where
   * percolate-down recurses on left and right.
   * 
   * @return true if PriorityCareAdmissions.removeBestRecord() returns the correct PatientRecord
   *         each time it is called and size is appropriately decremented, false otherwise
   */
  public static boolean testRemoveBestRecordNonEmpty() {

    // Initialize the queue to be tested onx
    PriorityCareAdmissions testAdmissions = new PriorityCareAdmissions(7);

    // Initialize the patient records to be added to the queue
    PatientRecord test1 = new PatientRecord('M', 18, TriageLevel.RED);
    PatientRecord test2 = new PatientRecord('F', 19, TriageLevel.YELLOW);
    PatientRecord test3 = new PatientRecord('M', 20, TriageLevel.YELLOW);
    PatientRecord test4 = new PatientRecord('F', 60, TriageLevel.GREEN);
    PatientRecord test5 = new PatientRecord('M', 48, TriageLevel.RED);
    PatientRecord test6 = new PatientRecord('M', 60, TriageLevel.RED);
    PatientRecord test7 = new PatientRecord('M', 70, TriageLevel.RED);

    // Add the patient records to the queue
    testAdmissions.addPatient(test1);
    testAdmissions.addPatient(test2);
    testAdmissions.addPatient(test3);
    testAdmissions.addPatient(test4);
    testAdmissions.addPatient(test5);
    testAdmissions.addPatient(test6);
    testAdmissions.addPatient(test7);

    // Ensure that removing the best record removes test1, and that size is now 6
    if (!testAdmissions.removeBestRecord().equals(test1) || testAdmissions.size() != 6) {
      return false;
    }

    // Expected string output from the queue's toString
    String expected = test5.toString() + "\n" + test6.toString() + "\n" + test7.toString() + "\n"
        + test2.toString() + "\n" + test3.toString() + "\n" + test4.toString() + "\n";

    // Ensure the contents of the queue are as expected
    if (!expected.equals(testAdmissions.toString())) {
      return false;
    }
    return true; // Return true if and only if all test cases pass
  }

  /**
   * Tests the functionality of the clear() method. Should implement at least the following
   * scenarios: - clear can be called on an empty queue with no errors - clear can be called on a
   * non-empty queue with no errors - After calling clear(), the queue should contain zero
   * PatientRecords. - After calling clear(), the size should be 0
   *
   * @return true if PriorityCareAdmissions.clear() functions properly
   */
  public static boolean testClear() {

    // Initialize a queue to be tested on
    PriorityCareAdmissions testAdmissions = new PriorityCareAdmissions(6);

    testAdmissions.clear(); // Clear the queue

    // Ensure that the queue is still empty and that size is 0
    if (testAdmissions.size() != 0 || !testAdmissions.isEmpty()) {
      return false;
    }

    // Add 5 patient records to the queue
    PatientRecord test1 = new PatientRecord('M', 18, TriageLevel.RED);
    PatientRecord test2 = new PatientRecord('F', 19, TriageLevel.RED);
    PatientRecord test3 = new PatientRecord('M', 20, TriageLevel.YELLOW);
    PatientRecord test4 = new PatientRecord('F', 21, TriageLevel.YELLOW);
    PatientRecord test5 = new PatientRecord('M', 48, TriageLevel.GREEN);

    testAdmissions.clear(); // Clear the queue

    // Ensure the queue is now empty with size being 0
    if (testAdmissions.size() != 0 || !testAdmissions.isEmpty()) {
      return false;
    }

    return true; // Return true if and only if all test cases pass
  }


  /**
   * Tests toString() method of PriorityCareAdmissions class.
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean testToString() {

    // Initialize queue to be tested on
    PriorityCareAdmissions testAdmissions = new PriorityCareAdmissions(6);

    // Initialize the patient records to be added to the queue
    PatientRecord test1 = new PatientRecord('M', 18, TriageLevel.RED);
    PatientRecord test2 = new PatientRecord('F', 19, TriageLevel.RED);
    PatientRecord test3 = new PatientRecord('M', 20, TriageLevel.YELLOW);
    PatientRecord test4 = new PatientRecord('F', 21, TriageLevel.YELLOW);
    PatientRecord test5 = new PatientRecord('M', 48, TriageLevel.GREEN);

    // Add the patient records to the queue
    testAdmissions.addPatient(test1);
    testAdmissions.addPatient(test2);
    testAdmissions.addPatient(test3);
    testAdmissions.addPatient(test4);
    testAdmissions.addPatient(test5);

    // Create a string variable holding the correct output of toString
    String expected = test1.toString() + "\n" + test2.toString() + "\n" + test3.toString() + "\n"
        + test4.toString() + "\n" + test5.toString() + "\n";

    // If the actual toString does not equal the expected output, return false
    if (!testAdmissions.toString().equals(expected)) {
      return false;
    }

    testAdmissions.clear(); // Clear all of the patients from the queue

    // Ensure that the toString now is empty because there are no patients in the queue
    if (!testAdmissions.toString().equals("")) {
      return false;
    }
    return true; // returns true if and only if all test cases pass
  }

  /**
   * Runs all the tester methods defined in this class.
   * 
   * @return true if no bugs are detected.
   */
  public static boolean runAllTests() {

    return testPatientRecordCompareToDifferentTriage()
        && testPatientRecordCompareToSameTriageDifferentArrival()
        && testPatientRecordCompareToSameTriageSameArrival() && testPeekEmpty()
        && testPeekNonEmpty() && testAddPatientEmpty() && testAddPatientNonEmpty()
        && testAddPatientFull() && testAddPatientNull() && testRemoveBestRecordNonEmpty()
        && testRemoveBestRecordEmpty() && testRemoveBestRecordSizeOne() && testClear()
        && testToString();
  }

  /**
   * Main method to run this tester class.
   * 
   * @param args list of input arguments if any
   */
  public static void main(String[] args) {
    System.out.println("runAllTests: " + (runAllTests() ? "Pass" : "Failed!"));
    System.out.println("testPatientRecordCompareToDifferentTriage: "
        + (testPatientRecordCompareToDifferentTriage() ? "Pass" : "Failed!"));
    System.out.println("testPatientRecordCompareToSameTriageDifferentArrival: "
        + (testPatientRecordCompareToSameTriageDifferentArrival() ? "Pass" : "Failed!"));
    System.out.println("testPatientRecordCompareToSameTriageSameArrival: "
        + (testPatientRecordCompareToSameTriageSameArrival() ? "Pass" : "Failed!"));
    System.out.println("testConstructor: " + (testConstructor() ? "Pass" : "Failed!"));
    System.out.println("testPeekEmpty: " + (testPeekEmpty() ? "Pass" : "Failed!"));
    System.out.println("testPeekNonEmpty: " + (testPeekNonEmpty() ? "Pass" : "Failed!"));
    System.out.println("testAddPatientEmpty: " + (testAddPatientEmpty() ? "Pass" : "Failed!"));
    System.out
        .println("testAddPatientNonEmpty: " + (testAddPatientNonEmpty() ? "Pass" : "Failed!"));
    System.out.println("testAddPatientFull: " + (testAddPatientFull() ? "Pass" : "Failed!"));
    System.out.println("testAddPatientNull: " + (testAddPatientNull() ? "Pass" : "Failed!"));
    System.out.println(
        "testRemoveBestRecordNonEmpty: " + (testRemoveBestRecordNonEmpty() ? "Pass" : "Failed!"));
    System.out.println(
        "testRemoveBestRecordEmpty: " + (testRemoveBestRecordEmpty() ? "Pass" : "Failed!"));
    System.out.println(
        "testRemoveBestRecordSizeOne: " + (testRemoveBestRecordSizeOne() ? "Pass" : "Failed!"));
    System.out.println("testClear: " + (testClear() ? "Pass" : "Failed!"));
    System.out.println("testToString: " + (testToString() ? "Pass" : "Failed!"));
  }

}
