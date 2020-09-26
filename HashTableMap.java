// --== CS400 File Header Information ==--
// Name: Connor William Dyjach
// Email:dyjach@wisc.edu
// Team: CB
// TA: Yeping
// Lecturer: Gary Dahl
// Notes to Grader:


import java.util.NoSuchElementException;
import java.util.LinkedList;

/*
 * This class represents the hash table used to store the books
 * 
 * @author
 */
public class HashTableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType> {

  private int capacity; //how many spaces in the hash table
  private int size; //how many elements in the hash table
  
  private LinkedList <Book>[] array;
  
  /*
   * creates a new HashTableMap
   * 
   * @param capacity int value specifying the capacity of the hash table
   * 
   */
  public HashTableMap(int capacity) {
    
  this.capacity = capacity; 
  this.size = 0;
  
  this.array = new LinkedList[capacity];
    
  }
  
  /*
   * creates a new HashTableMap
   * 
   */
  public HashTableMap() {
    this.capacity = 10; // with default capacity = 10
    this.size = 0;
    this.array = new LinkedList[capacity];

  }

  
  /*
   * doubles the capacity, resizes the array, and copies the contents of the old array into the new 
   * array.
   * 
   * @param array2 LinkedList<KeyValue>[] array to copy and resize
   */
  private void grow(LinkedList<Book>[] array2) {
    
    //condition to double the capacity, resize the array, and copy the contents
    if ((this.size + 1) > (this.capacity * 0.8)) {
      
      int oldCapacity = this.capacity; //stores the old capacity
      
      this.capacity = this.capacity * 2; //doubles the capacity
      
      LinkedList [] oldArray = array2; //prior to change array
      
      LinkedList [] newArray = new LinkedList[capacity]; //new array to be filled
      
      //steps through the old array of linked lists
      for (int i = 0; i < oldCapacity;i++) {
        
        //checks if there is a linked list at index i, if not it skips this iteration
        if (oldArray[i]==null) {
          continue; 
        }
        
        //if there is a linked list it is stepped through 
        for (int j = 0; j < oldArray[i].size();j++) {

          // if the oldArray[i] has a KeyValue object
          if (oldArray[i].get(j) != null) {


            KeyType oldKey = (KeyType) ((Book) oldArray[i].get(j)).getTitle(); // copies the key
                                                                                 // of the KeyValue
                                                                                 // object

            
            Book bookNode = ((Book) oldArray[i].get(j)); // copies the KeyValue object

            int index = Math.abs(oldKey.hashCode()) % this.capacity; // rehashes the key to find its
                                                                     // new index to be placed at

            // if theres no linked list at newArray[index] create one then add the node from
            // oldArray[]
            if (newArray[index] == null) {
              newArray[index] = new LinkedList<Book>();
              newArray[index].add(bookNode);


            }


            newArray[index].add(bookNode); //otherwise add it to the list at that index

            this.array = newArray; //sets array

          }
        }
      }
    }
  }


  /*
   * adds a KeyValue object to the hash table
   * 
   * @param key KeyType the key (linked to a value)
   * 
   * @param value ValueType the (value linked to a key)
   * 
   * @return true if a new index is filled with a linked list and false otherwise
   */
  @Override
  public boolean put(KeyType key, int isbn, String author, boolean checkedIn, String genre,
      String description) {


    
    int index = Math.abs(key.hashCode()) % this.capacity; //hashes the key to a usable index
    
    //adds a KeyValue object to a linked list creating a linked list at the specific index if 
    //needed
    
    
    if (this.array[index] == null) {
      this.array[index] = new LinkedList<Book>(); // creates a linked list at the index
      this.array[index].add(new Book((String) key, isbn, author, checkedIn, genre, description)); // adds
                                                                                                  // the
                                                                                                  // KeyValue
                                                                                                  // object
                                                                                                  // to
                                                                                                  // the
                                                                                                  // list
      this.size++;

      grow(this.array);
      return true;
    }

    this.array[index].add(new Book((String) key,isbn, author, checkedIn, genre, description));
    this.size++;

    grow(this.array);
    return false;
    

  }

  /*
   * gets a value from the specified key
   * 
   * @param key KeyType the key (linked to a value)
   * @throws NoSuchElementException if the hash table doesnt contain the key
   * @return ValueType the value linked to the passed key
   * 
   */
  @Override
  public ValueType get(KeyType key) throws NoSuchElementException {
    
    if (!containsKey(key)) {
      throw new NoSuchElementException();
    }
    
    String bookDetails = "";
    
    int index = Math.abs(key.hashCode()) % this.capacity;
    

    // steps through the linked list at a given index
    for (int i = 0; i < array[index].size(); i++) {

      // if the key at the index of the linked list mathces the passed key
      if (array[index].get(i).getTitle().equals(key)) {


        return (ValueType) (bookDetails + "ISBN: " + array[index].get(i).getIsbn()
            + System.lineSeparator() + "Author: " + array[index].get(i).getAuthor()
            + System.lineSeparator() + "CheckedIn: " + array[index].get(i).getChekedIn()
            + System.lineSeparator() + "Genre: " + array[index].get(i).getGenre()
            + System.lineSeparator() + "Description: " + array[index].get(i).getDescription());

      }

    }
    return null;
    
    }

  
  /*
   * returns the size of the array
   * 
   * @return int value that represents the amount of KeyValue objects stored in this array
   */
  @Override
  public int size() {
    return this.size;
  }

  /*
   * checks the hash table for a given key
   * 
   * @param key KeyType that is used to compare to the hash table to see if it is present
   * 
   * @return true if the hash table contains the key and else otherwise
   */
  @Override
  public boolean containsKey(KeyType key) {

    int index = Math.abs(key.hashCode()) % this.capacity;

    // if the index is within the array of linked lists, the array of linked lists exists, and there
    // is a Linked list object at the specified index
    if (index < this.capacity && index >= 0 && array[index] != null && array != null) {

      
      for (int i = 0; i < array[index].size(); i++) {


          if (array[index].get(i).getTitle().equals(key)) {
            
            return true;
            
        }
        
      }
      
    }
    
    return false;
  }

  /*
   * removes from the hash table
   * 
   * @param key KeyType object to remove from the hash table
   * @return the KeyValue object that was removed
   */
  @Override
  public ValueType remove(KeyType key) {
    
    int index = Math.abs(key.hashCode()) % this.capacity;
    int removeIndex = -1; //-1 not a possible index so an error would be thrown if its not updated
    ValueType returnVar = null; // variable to store the KeyValue object


    if (containsKey(key)) {
    

      for (int i = 0; i < array[index].size(); i++) {

        if (array[index].get(i).getTitle().equals(key)) {

          returnVar = (ValueType) ("" + "ISBN: " + array[index].get(i).getIsbn()
              + System.lineSeparator() + "Author: " + array[index].get(i).getAuthor()
              + System.lineSeparator() + "CheckedIn: " + array[index].get(i).getChekedIn()
              + System.lineSeparator() + "Genre: " + array[index].get(i).getGenre()
              + System.lineSeparator() + "Description: " + array[index].get(i).getDescription()); // sets
                                                                                                  // returnVar
                                                                                                  // to
                                                                                                  // the
                                                                                                  // KeyValue
          // object at the current index

          removeIndex = i;// stores the index in the linked list to remove from
          break;
        }


      }

      this.array[index].remove(removeIndex); //removes the specified index from the linked list

      this.size--;
      
     
      
    }
    
    return returnVar;
    
  }
/*
 * clears the array of linked lists and sets its size to zero
 */
  @Override
  public void clear() {
 
    this.array = new LinkedList[capacity];
    
    this.size = 0;
    
  }
  
  public int getCapcity() {
    return this.capacity;
  }
  
}
