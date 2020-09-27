// --== CS400 File Header Information ==--
// Name: Sushanth Rao
// Email: srao32@wisc.edu
// Team: CB
// TA: Yeping
// Lecturer: Florian Heimerl
// Notes to Grader: <optional extra notes>

public class LibraryTest {
    public static void main(String[] args) {
        System.out.println("bookTest: " + bookTest());
        System.out.println("getBookTest: " + getBookTest());
        System.out.println("checkoutBookTest: " + checkoutBookTest());
    }

    /**
     * FOR DATA WRANGLERS: Tests the Book class constructor and methods
     * @return false if the test fails, true otherwise
     */
    public static boolean bookTest() {
        Book myBook = new Book( "The Great Gatsby", 439594, "Fitzgerald",
                true,"Classic", "The main character dies");;

        // 1. Get values the Book was created with
        // Author name
        if(!myBook.getAuthorName().equals("Fitzgerald")) {
            System.out.println("The author name that was returned did not match the name the Book was " +
                    "created with");
            return false;
        }
        // book title
        if(!myBook.getBookTitle().equals("The Great Gatsby")) {
            System.out.println("The book title that was returned did not match the title the Book was " +
                    "created with");
            return false;
        }
        // ISBN number
        if(myBook.getIsbn() != 439594) {
            System.out.println("The isbn number that was returned did not match the number the Book was " +
                    "created with");
            return false;
        }
        // genre
        if(!myBook.getGenre().equals("Classic")) {
            System.out.println("The genre that was returned did not match the genre the Book was " +
                    "created with");
            return false;
        }
        // checked in/out
        // TODO: Make sure logic still holds
        if(myBook.getCheckedIn()) {
            System.out.println("The checkedOut status that was returned was true before the Book has" +
                    "been checked out");
            return false;
        }
        // description
        if(!myBook.getDescription().equals("The main character dies")) {
            System.out.println("The description that was returned did not match the description the Book was " +
                    "created with");
            return false;
        }

        // 2. Set values and make sure the Book is updated accordingly
        myBook.setAuthorName("Dan Brown");
        // Author name
        if(!myBook.getAuthorName().equals("Dan Brown")) {
            System.out.println("The author name that was returned did not match the name the Book was " +
                    "updated with");
            return false;
        }
        // book title
        myBook.setBookTitle("DaVinci Code");
        if(!myBook.getBookTitle().equals("DaVinci Code")) {
            System.out.println("The book title that was returned did not match the title the Book was " +
                    "updated with");
            return false;
        }
        // ISBN number
        myBook.setIsbn(93845);
        if(myBook.getIsbn() != 93845) {
            System.out.println("The isbn number that was returned did not match the number the Book was " +
                    "updated with");
            return false;
        }
        // genre
        myBook.setGenre("Fiction");
        if(!myBook.getGenre().equals("Fiction")) {
            System.out.println("The genre that was returned did not match the genre the Book was " +
                    "updated with");
            return false;
        }
        // checked in/out
        // TODO: Make sure logic still holds
        myBook.setCheckedOut(true);
        if(!myBook.getCheckedIn()) {
            System.out.println("The checkedOut status that was returned was false after the Book's status" +
                    "was changed to true");
            return false;
        }
        // description
        myBook.setDescription("Historical adventure of the Church and its adversaries");
        if(!myBook.getDescription().equals("Historical adventure of the Church and its adversaries")) {
            System.out.println("The description that was returned did not match the description the Book was " +
                    "updated with");
            return false;
        }

        return true;
    }

    /**
     * FOR BACK END DEVELOPERS: Tests getBook method
     * @return false if the test fails, true otherwise
     */
    public static boolean getBookTest() {
        // 1. Try to get an existing Book
        Library myLibrary = new Library();
        Book book1 = new Book( "book1", 1, "author1", true,
                "genre1", "description1");
        Book book2 = new Book( "book2", 2, "author2", true,
                "genre2", "description2");
        if(!book1.equals(myLibrary.getBook("book1"))) {
            System.out.println("Trying to get Book1 returned a different value than expected");
            return false;
        }
        if(!book2.equals(myLibrary.getBook("book2"))) {
            System.out.println("Trying to get Book2 returned a different value than expected");
            return false;
        }
        // 2. Try to get a nonexistent Book
        if(myLibrary.getBook("book4") != null) {
            System.out.println("Trying to get a nonexistent Book returned a non-null value");
            return false;
        }
        return true;
    }

    /**
     * FOR BACK END DEVELOPERS: Tests the checkoutBook functionality
     * @return false if the test fails, true otherwise
     */
    public static boolean checkoutBookTest() {
        Library myLibrary = new Library();
        Book book1 = myLibrary.getBook("book1");
        // 1. Make sure checking out a Book twice is not possible
        myLibrary.checkOutBook("book1");
        if(!book1.getCheckedIn()) {
            // TODO: Make sure logic still is correct
            System.out.println("Book variable checkedOut is still false after the Book is checked out");
            return false;
        }
        // 2. Make sure checking out a Book twice is not possible
        try {
            myLibrary.checkOutBook("book1");
            System.out.println("Checking out Book for a second time does not throw an Exception");
            return false;
        }
        catch (IllegalStateException e1) {
        }
        catch (Exception e2) {
            System.out.println("Checking out Book for the second time threw an unexpected exception");
            return false;
        }
        // 3. Make sure checking out a nonexistent Book is not possible
        try {
            myLibrary.checkOutBook("book4");
            System.out.println("Checking out a nonexistent Book resulted in no exception");
            return false;
        }
        catch (IllegalArgumentException e1) {}
        catch (Exception e2) {
            System.out.println("Checking out a nonexistent book resulted in an unexpected exception");
            return false;
        }
        return true;
    }
}
