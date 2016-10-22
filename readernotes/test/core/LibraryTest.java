package readernotes.test.core;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import readernotes.src.core.Library;
import readernotes.src.core.ReadingFile;
import readernotes.src.core.Book;
import readernotes.src.exceptions.DoubleEntryException;

public class LibraryTest {
    //TOKENS
    private static final String BOOK_TITLE = "Adventurer"
    private static final String READINGFILE_TITLE = "Cp. 1 The Beginning";
    private static final String BOOK_AUTHOR = "John";
    private static final String BOOK_ISBN = "123-456-789";
    private static final String BOOK_SUBJECT = "Adventure";
    private Library _testLibrary;
    private ReadingFile _testFile;
    private Book _testBook;

    @Before
    public void setup() {
        _testLibrary = Library.getInstance();
        _testBook = new Book(BOOK_TITLE, BOOK_AUTHOR);
        _testBook.setISBN(BOOK_ISBN);
        _testBook.setSubject(BOOK_SUBJECT);

        _testFile = new ReadingFile(READINGFILE_TITLE, BOOK_TITLE);

        _testLibrary.addBook(testBook);
        _testLibrary.addReadingFile(testReadingFile);
    }

    @After
    public void tearDown() {
        _library = null;
        _testBook = null;
        _testFile = null;
    }

    @Test
    public void setupInstance() {
        Library testLibrary = Library.getInstance();

        assertNotNull(testLibrary);
    }

    @Test
    public void getBookTestByTitle() {
        Book testBook = _library.getBook(BOOK_TITLE);

        assertEquals(testBook.getTitle(), BOOK_TITLE);
    }

    @Test
    public void getReadingFileTest() {
        ReadingFile testFile = _library.getReadingFile(READINGFILE_TITLE);

        assertEquals(testFile.getTitle(), READINGFILE_TITLE);
    }

    @Test (expected = DoubleEntryException.class)
    public void addExistingBook() {
        Book newBook = new Book(BOOK_TITLE, BOOK_AUTHOR);
        _library.addBook(newBook);
    }

    @Test (expected = DoubleEntryException.class)
    public void addExistingReadingFile() {
        ReadingFile newFile = new ReadingFile(READINGFILE_TITLE, BOOK_TITLE);
        _library.addReadingFile(newFile);
    }

    @Test
    public void matchesBookTitleID() {
        assertTrue(_library.matchesBookTitleID(BOOK_TITLE, _testBook))
    }

    @Test
    public void matchesBookAuthorID() {
        assertTrue(_library.matchesBookID(BOOK_AUTHOR, _testBook));
    }

    @Test
    public void matchesBooISBN() {
        assertTrue(_library.matchesBookID(BOOK_ISBN, _testBook));
    }

    @Test
    public void matchesBookSubject() {
        assertTrue(_library.matchesBookSubject(BOOK_SUBJECT, _testBook));
    }

}
