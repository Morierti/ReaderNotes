package readernotes.test.core;

import org.junit.Test;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import readernotes.src.core.Shell;
import readernotes.src.core.Sintese;
import readernotes.src.core.Book;
import readernotes.src.exceptions.NullBookException;
import readernotes.src.exceptions.NullSinteseException;
import readernotes.src.exceptions.SinteseNotFoundException;
import readernotes.src.exceptions.BookNotFoundException;

public class ShellTest extends CoreTest {
    private Book _book1;
    private Book _book2;
    private Sintese _sinteseOfBook1;
    private Sintese _sinteseOfBook2;

    @Override
    public void populateForTest() {
        _book1 = this.createNewBook("Lusiadas", "Luis de Camoes");
        _book2 = this.createNewBook("Memorial do Convento", "José Saramago");
        _sinteseOfBook1 = this.createNewSintese("Lusiadas", _book1);
        _sinteseOfBook2 = this.createNewSintese("Memorial do Convento", _book2);
    }

    @Override
    public void destroyTestConditions() { /* Stub */ }

    @Test
    public void successShellCreation() {
        Shell newShell = Shell.getInstance();
        assertNotNull(newShell);
    }

    @Test
    public void successInitialization() {
        Shell newShell = Shell.getInstance();
        assertNotNull(newShell.getBookList());
        assertNotNull(newShell.getSinteseList());
    }

    @Test
    public void successAddBook()
    throws
    NullBookException,
    BookNotFoundException {
        Shell newShell = Shell.getInstance();
        assertNotNull(newShell);
        newShell.addBook(_book1);
        Book returnedBook = newShell.getBook(_book1.getTitle());
        assertNotNull(returnedBook);
        assertEquals(_book1.getTitle(), returnedBook.getTitle());
    }

    @Test
    public void successAddTwoBooks()
    throws
    NullBookException,
    BookNotFoundException {
        Shell newShell = Shell.getInstance();
        assertNotNull(newShell);
        newShell.addBook(_book1);
        newShell.addBook(_book2);
        Book returnedBook1 = newShell.getBook(_book1.getTitle());
        Book returnedBook2 = newShell.getBook(_book2.getTitle());
        assertNotNull(returnedBook1);
        assertNotNull(returnedBook2);
        assertEquals(returnedBook1.getTitle(), _book1.getTitle());
        assertEquals(returnedBook2.getTitle(), _book2.getTitle());
    }

    @Test
    public void successAddSintese()
    throws
    NullSinteseException,
    SinteseNotFoundException {
        Shell newShell = Shell.getInstance();
        assertNotNull(newShell);
        newShell.addSintese(_sinteseOfBook1);
        Sintese returnedSintese = newShell.getSintese(_sinteseOfBook1.getTitle());
        assertNotNull(returnedSintese);
        assertEquals(_sinteseOfBook1.getTitle(), returnedSintese.getTitle());
    }

    @Test
    public void successAddTwoSintese()
    throws
    NullSinteseException,
    SinteseNotFoundException {
        Shell newShell = Shell.getInstance();
        assertNotNull(newShell);
        newShell.addSintese(_sinteseOfBook1);
        newShell.addSintese(_sinteseOfBook2);
        Sintese returnedSintese1 = newShell.getSintese(_sinteseOfBook1.getTitle());
        Sintese returnedSintese2 = newShell.getSintese(_sinteseOfBook2.getTitle());
        assertNotNull(returnedSintese1);
        assertNotNull(returnedSintese2);
        assertEquals(returnedSintese1.getTitle(), _sinteseOfBook1.getTitle());
        assertEquals(returnedSintese2.getTitle(), _sinteseOfBook2.getTitle());
    }

    @Test(expected=NullSinteseException.class)
    public void nullSinteseAdd()
    throws
    NullSinteseException {
        Shell newShell = Shell.getInstance();
        assertNotNull(newShell);
        newShell.addSintese(null);
    }

    @Test(expected=NullBookException.class)
    public void nullBookAdd()
    throws
    NullBookException {
        Shell newShell = Shell.getInstance();
        assertNotNull(newShell);
        newShell.addBook(null);
    }

    @Test(expected=BookNotFoundException.class)
    public void findNonExistingBook()
    throws
    NullBookException,
    BookNotFoundException {
        Shell newShell = Shell.getInstance();
        assertNotNull(newShell);
        newShell.addBook(_book1);
        newShell.addBook(_book2);
        Book foundBook = newShell.findBook("Non Existent Book");
    }

    @Test(expected=BookNotFoundException.class)
    public void findBookOnEmptyList()
    throws
    BookNotFoundException {
        Shell newShell = Shell.getInstance();
        assertNotNull(newShell);
        Book foundBook = newShell.findBook("Test Book");
    }

    @Test(expected=SinteseNotFoundException.class)
    public void findNOnExistingSintese()
    throws
    NullSinteseException,
    SinteseNotFoundException {
        Shell newShell = Shell.getInstance();
        assertNotNull(newShell);
        newShell.addSintese(_sinteseOfBook1);
        newShell.addSintese(_sinteseOfBook2);
        Sintese foundSintese = newShell.findSintese("Non Existent Sintese");
    }

    @Test(expected=SinteseNotFoundException.class)
    public void findSinteseOnEmptyList()
    throws
    SinteseNotFoundException {
        Shell newShell = Shell.getInstance();
        assertNotNull(newShell);
        Sintese foundSintese = newShell.findSintese("Test Sintese");
    }
}