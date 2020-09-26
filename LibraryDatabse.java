// --== CS400 File Header Information ==--
// Name: Connor William Dyjach
// Email:dyjach@wisc.edu
// Team: CB
// TA: Yeping
// Lecturer: Gary Dahl
// Notes to Grader:

/*
 * this class represents a database of books
 * 
 * @author Connor Dyjach
 */
public class LibraryDatabse<KeyType, ValueType> {

 private  HashTableMap LibraryDatabase;
  
  public void create() {
    
    LibraryDatabase = new HashTableMap();
    
    LibraryDatabase.put("title", 0, "author", true, "genre", "description");
    
  }
  
  
  
  
  
  
  
}
