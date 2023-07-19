
import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Array-based min-heap implementation of a priority queue storing PatientRecords. Guarantees the
 * min-heap invariant, so that the PatientRecord at the root should be the smallest PatientRecord,
 * which corresponds to the element having the highest priority to be dequeued first, and children
 * always are greater than their parent. We rely on the PatientRecord.compareTo() method to compare
 * PatientRecords. The root of a non-empty queue is always at index 0 of this array-heap.
 */
public class PriorityCareAdmissions {
  private PatientRecord[] queue; // array min-heap of PatientRecords representing this priority
                                 // queue
  private int size; // size of this priority queue

  /**
   * Creates a new empty PriorityCareAdmissions queue with the given capacity
   * 
   * @param capacity Capacity of this PriorityCareAdmissions queue
   * @throws IllegalArgumentException with a descriptive error message if the capacity is not a
   *                                  positive integer
   */
  public PriorityCareAdmissions(int capacity) throws IllegalArgumentException {

    // Throw exception if capacity is not positive
    if (capacity <= 0) {
      throw new IllegalArgumentException("Error! You need to input a capacity greater than 0");
    }

    this.queue = new PatientRecord[capacity]; // Initialize an array for this min-queue
    this.size = 0; // Set the array's size to 0
  }

  /**
   * Checks whether this PriorityCareAdmissions queue is empty
   * 
   * @return {@code true} if this PriorityCareAdmissions queue is empty
   */
  public boolean isEmpty() {

    // If there are no patientRecords in this queue, return true
    if (this.size == 0) {
      return true;
    }
    return false; // return false if there is more than 0 patientRecords in this queue
  }

  /**
   * Returns the size of this PriorityCareAdmissions queue
   * 
   * @return the total number of PatientRecords stored in this PriorityCareAdmissions queue
   */
  public int size() {
    return this.size; // Return this queue's size
  }

  /**
   * Returns the capacity of this PriorityCareAdmissions queue
   * 
   * @return the capacity of this PriorityCareAdmissions queue
   */
  public int capacity() {
    return this.queue.length; // return the length of the queue (capacity)
  }

  /**
   * Removes all the elements from this PriorityCareAdmissions queue
   */
  public void clear() {

    // Reset the queue to an empty array with size zero, keeping its capacity
    this.queue = new PatientRecord[queue.length];
    this.size = 0;
  }

  /**
   * Returns the PatientRecord at the root of this PriorityCareAdmissions queue, i.e. the
   * PatientRecord having the the highest priority.
   * 
   * @return the PatientRecord at the root of this PriorityCareAdmissions queue
   * @throws NoSuchElementException with the exact error message "Warning: Empty Admissions Queue!"
   *                                if this PriorityCareAdmissions queue is empty
   */
  public PatientRecord peek() throws NoSuchElementException {

    // If the queue is empty, throw an exception
    if (this.isEmpty()) {
      throw new NoSuchElementException("Warning: Empty Admissions Queue!");
    }
    return queue[0]; // Return the first value in the queue
  }

  /**
   * Adds the given PatientRecord to this PriorityCareAdmissions queue at the correct position based
   * on the min-heap ordering. This queue should maintain the min-heap invariant, so that the
   * PatientRecord at each index is less than or equal to than the PatientRecords in its child
   * nodes. PatientRecords should be compared using the PatientRecord.compareTo() method.
   * 
   * @param p PatientRecord to add to this PriorityCareAdmissions queue
   * @throws NullPointerException  if the given PatientRecord is null
   * @throws IllegalStateException with a the exact error message "Warning: Full Admissions Queue!"
   *                               if this PriorityCareAdmissions queue is full
   */
  public void addPatient(PatientRecord p) throws NullPointerException, IllegalStateException {

    // If the inputed patient is null, throw a NullPointerException
    if (p == null) {
      throw new NullPointerException();
    }

    // If this queue is full, throw a descriptive IllegalStateException
    if (this.queue.length == this.size()) {
      throw new IllegalStateException("Warning: Full Admissions Queue!");
    }

    queue[this.size()] = p; // Add the patient to the array of patients
    this.size += 1; // Increment size by
    percolateUp(this.size() - 1); // Keep percolating up the added patient until in correct position

  }

  /**
   * Recursive implementation of percolateUp() method. Restores the min-heap invariant of this
   * priority queue by percolating a leaf up the heap. If the element at the given index does not
   * violate the min-heap invariant (it is greater than its parent), then this method does not
   * modify the heap. Otherwise, if there is a heap violation, swap the element with its parent and
   * continue percolating the element up the heap.
   * 
   * @param i index of the element in the heap to percolate upwards
   * @throws IndexOutOfBoundsException if index is out of bounds (out of the range 0..size()-1
   *                                   inclusive)
   */
  protected void percolateUp(int i) throws IndexOutOfBoundsException {

    // If i is greater than size or negative, throw an IndexOutOfBoundsException
    if (i > this.size() || i < 0) {
      throw new IndexOutOfBoundsException();
    }

    // Set the parent and children index's for this current Patient at index i
    int parent = (i - 1) / 2;
    int leftChild = (2 * i) + 1;
    int rightChild = (2 * i) + 2;

    PatientRecord temp; // Set temporary patient variable to swap

    // If i is 0, it is does not need to percolate up, return
    if (i == 0) {
      return;
    }

    // If the parent patient has a smaller or equal triage than this current patient, return
    if (queue[parent].compareTo(queue[i]) <= 0) {
      return;
    }

    // If the parent has a greater triage than this current patient, swap patient and its parent
    else {
      temp = queue[parent];
      queue[parent] = queue[i];
      queue[i] = temp;
      percolateUp(parent);
    }
  }

  /**
   * Removes and returns the PatientRecord at the root of this PriorityCareAdmissions queue, i.e.
   * the PatientRecord having the highest priority (the minimum one).
   * 
   * @return the PatientRecord in this PriorityCareAdmissions queue at the root of this priority
   *         queue.
   * @throws NoSuchElementException with the exact error message "Warning: Empty Admissions Queue!"
   *                                if this PriorityCareAdmissions queue is empty
   */
  public PatientRecord removeBestRecord() throws NoSuchElementException {

    // If the min-queue is empty, throw a descriptive NoSuchElementException
    if (this.isEmpty()) {
      throw new NoSuchElementException("Warning: Empty Admissions Queue!");
    }

    PatientRecord patientReturn = this.queue[0]; // Set the min triage patient to be returned

    this.queue[0] = this.queue[size() - 1]; // Move the largest triage patient to the index 0 spot

    this.queue[this.size() - 1] = null; // Remove the last patient with the greatest triage

    this.size -= 1; // Decrease size by one

    this.percolateDown(0); // Percolate the patient at index 0 down

    return patientReturn; // Return the min-triage patient
  }


  /**
   * Recursive implementation of percolateDown() method. Restores the min-heap of the priority queue
   * by percolating its root down the tree. If the element at the given index does not violate the
   * min-heap ordering property (it is smaller than its smallest child), then this method does not
   * modify the heap. Otherwise, if there is a heap violation, then swap the element with the
   * correct child and continue percolating the element down the heap.
   * 
   * @param i index of the element in the heap to percolate downwards
   * @throws IndexOutOfBoundsException if index is out of bounds (out of the range 0..size()-1
   *                                   inclusive)
   */
  protected void percolateDown(int i) throws IndexOutOfBoundsException {

    // If is greater than size, throw an IndexOutOfBoundsException
    if (i > this.size()) {
      throw new IndexOutOfBoundsException();
    }

    PatientRecord temp; // Initialize the temp variable for swapping

    // Generate index's for left and right children of the node
    int leftChild = (2 * i) + 1;
    int rightChild = (2 * i) + 2;
    int smallerChild; // index of the smaller child (right or left)

    // If the right and left children's index are greater than size, return
    if (rightChild >= this.size() && leftChild >= this.size()) {
      return;
    }

    // If the right child is null but the left child is not
    else if (rightChild >= this.size && leftChild < this.size) {

      // If the current Patient has a higher triage than its child, swap the two
      if (this.queue[i].compareTo(this.queue[leftChild]) > 0) {
        smallerChild = leftChild;
        temp = this.queue[smallerChild];
        this.queue[smallerChild] = this.queue[i];
        this.queue[i] = temp;
        percolateDown(smallerChild); // Keep percolating the current patient until at correct place
      }
    }

    // If both children are not null
    else {

      // If the left child has a smaller triage than the right child
      if (this.queue[leftChild].compareTo(this.queue[rightChild]) < 0) {
        smallerChild = leftChild; // the leftChild is smaller
      }

      // If the right child is smaller or equal to the left child
      else {
        smallerChild = rightChild; // the right child is smaller
      }

      // If the current patient has smaller triage than the smaller child, return
      if (this.queue[i].compareTo(this.queue[smallerChild]) <= 0) {
        return;
      }

      // If the current patient has a larger triage than the smaller child, swap them both
      else {
        temp = this.queue[smallerChild];
        this.queue[smallerChild] = this.queue[i];
        this.queue[i] = temp;
        percolateDown(smallerChild); // Keep percolating the current patient until at correct place
      }
    }
  }


  /**
   * Returns a deep copy of this PriorityCareAdmissions queue containing all of its elements in the
   * same order. This method does not return the deepest copy, meaning that you do not need to
   * duplicate PatientRecords. Only the instance of the heap (including the array and its size) will
   * be duplicated.
   * 
   * @return a deep copy of this PriorityCareAdmissions queue. The returned new priority care
   *         admissions queue has the same length and size as this queue.
   */
  public PriorityCareAdmissions deepCopy() {
    PriorityCareAdmissions deepCopy = new PriorityCareAdmissions(this.capacity());
    deepCopy.queue = Arrays.copyOf(this.queue, this.queue.length);
    deepCopy.size = this.size;
    return deepCopy;
  }

  /**
   * Returns a deep copy of the array-heap of this PriorityCareAdmissions queue <BR/>
   * 
   * This method can be used for testing purposes.
   * 
   * @return a deep copy of the array-heap storing the ParientRecords in this queue
   */
  protected PatientRecord[] arrayHeapCopy() {
    return Arrays.copyOf(this.queue, this.queue.length);

  }

  /**
   * Returns a String representing this PriorityCareAdmissions queue, where each element
   * (PatientRecord) of the queue is listed on a separate line, in order from smallest to greatest.
   * 
   * @return a String representing this PriorityCareAdmissions queue, and an empty String "" if this
   *         queue is empty.
   */
  public String toString() {
    // Initialize a deep copy to be looped through to generate the toString
    PriorityCareAdmissions admissionsCopy = this.deepCopy();

    String returnString = ""; // Initialize the returned toString as blank

    // While the deep copy of the min-queue is not empty
    while (admissionsCopy.isEmpty() == false) {

      // add on the next lowest triage patient to the return string with a blank line
      returnString += admissionsCopy.removeBestRecord().toString() + "\n";
    }
    return returnString; // Return the entire toString of the queue
  }

}
