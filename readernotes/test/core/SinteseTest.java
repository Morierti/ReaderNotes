package readernotes.test.core;

import readernotes.src.core.Sintese;
import readernotes.src.core.Book;
import readernotes.src.exceptions.EmptyTitleException;
import readernotes.src.exceptions.NullBookException;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class SinteseTest extends CoreTest {
    private static final String BOOK_TITLE = "Book Title";
    private static final String TITLE = "Sintese";
    private static final String OTHER_TITLE = "New Sintese";
    private static final String CONTENT = "Some content";
    private static final String OTHER_CONTENT = "Other content";
    private static Book _testBook;

    @Override
    public void populateForTest() {
        try {
            _testBook = new Book(BOOK_TITLE);
        } catch (EmptyTitleException exception) {
            System.out.println(exception.getMessage());
        }
    }
    
    @Override
    public void destroyTestConditions() {
        
    }         

    @Test
    public void successNewSintese()
    throws
    EmptyTitleException,
    NullBookException {
        Sintese newSintese = new Sintese(TITLE, _testBook);
        assertNotNull(newSintese);
        assertEquals(TITLE, newSintese.getTitle());
        assertNotNull(newSintese.getBook());
    }

    @Test(expected=EmptyTitleException.class)
    public void nullTitleSintese()
    throws
    EmptyTitleException,
    NullBookException {
        Sintese newSintese = new Sintese(null, _testBook);
    }

    @Test(expected=NullBookException.class)
    public void nullBookSintese()
    throws
    EmptyTitleException,
    NullBookException {
        Sintese newSintese = new Sintese(TITLE, null);
    }

    @Test
    public void newTitleSuccess()
    throws
    EmptyTitleException,
    NullBookException {
        Sintese newSintese = new Sintese(TITLE, _testBook);
        newSintese.setTitle(OTHER_TITLE);
        assertEquals(OTHER_TITLE, newSintese.getTitle());
    }

    @Test
    public void newBookSintese()
    throws
    EmptyTitleException,
    NullBookException {
        Sintese newSintese = new Sintese(TITLE, _testBook);
        Book newBook = new Book(OTHER_TITLE);
        newSintese.setBook(newBook);
        assertEquals(newBook.getTitle(), newSintese.getBook().getTitle());
    }

    @Test
    public void setNewContent()
    throws
    EmptyTitleException,
    NullBookException {
        Sintese newSintese = new Sintese(TITLE, _testBook);
        newSintese.setContent(CONTENT);
        assertEquals(CONTENT, newSintese.getContent());
    }

    @Test
    public void addNewContent()
    throws
    EmptyTitleException,
    NullBookException {
        Sintese newSintese = new Sintese(TITLE, _testBook);
        newSintese.setContent(CONTENT);
        newSintese.addContent(CONTENT);
        assertEquals(CONTENT + CONTENT, newSintese.getContent());
    }

    @Test
    public void replaceContent()
    throws 
    EmptyTitleException,
    NullBookException {
        Sintese newSintese = new Sintese(TITLE, _testBook);
        newSintese.setContent(CONTENT);
        assertEquals(CONTENT, newSintese.getContent());
        newSintese.setContent(OTHER_CONTENT);
        assertEquals(OTHER_CONTENT, newSintese.getContent());
    }
}