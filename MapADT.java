// --== CS400 File Header Information ==--
// Name: Connor William Dyjach
// Email:dyjach@wisc.edu
// Team: CB
// TA: Yeping
// Lecturer: Gary Dahl
// Notes to Grader:

import java.util.NoSuchElementException;

public interface MapADT<KeyType,ValueType> {

  public boolean put(KeyType key, int isbn, String author, boolean checkedIn, String genre,
      String description);
  public ValueType get(KeyType key) throws NoSuchElementException;
  public int size();
  public boolean containsKey(KeyType key);    
  public ValueType remove(KeyType key);    
  public void clear();
  
}


