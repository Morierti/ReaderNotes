package readernotes.test.core;

import readernotes.src.core.Book;
import readernotes.src.core.Sintese;
import readernotes.src.core.Shell;
import readernotes.src.exceptions.EmptyBookTitleException;
import readernotes.src.exceptions.EmptyAuthorException;
import readernotes.src.exceptions.EmptySinopseException;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class BookTest extends CoreTest {
    private static final String TITLE   = "A Mitologia Portuguesa";
    private static final String AUTHOR  = "Sérgio Franclim";
    private static final String CHANGED_AUTHOR = "Manuel Abecasis";
    private static final String CHANGED_SINOPSE = "Changed sinopse";
    private static final String SINOPSE = "Uma viagem pelos segredos...";
    private Book _createdBook;                                                            

    @Test
    public void createBookWithTitleTest()
    throws
    EmptyBookTitleException {
        Book newBook = new Book(TITLE);

        assertEquals(TITLE, newBook.getTitle());
    }

    @Test
    public void createBookWithAllAttributes()
    throws
    EmptyBookTitleException,
    EmptySinopseException,
    EmptyAuthorException {
        Book newBook = new Book(TITLE, AUTHOR, SINOPSE);

        assertEquals(TITLE, newBook.getTitle());
        assertEquals(AUTHOR, newBook.getAuthor());
        assertEquals(SINOPSE, newBook.getSinopse());
    }

    @Test(expected = EmptyAuthorException.class)
    public void createBookNullAuthor()
    throws
    EmptyBookTitleException,
    EmptyAuthorException {
        Book newBook = new Book(TITLE, null);
    }

    @Test(expected = EmptyAuthorException.class)
    public void createBookEmptyAuthor()
    throws
    EmptyBookTitleException,
    EmptyAuthorException {
        Book newBook = new Book(TITLE, "");
    }

    @Test(expected = EmptySinopseException.class)
    public void createBookNullSinopse()
    throws
    EmptyBookTitleException,
    EmptyAuthorException,
    EmptySinopseException {
        Book newBook = new Book(TITLE, AUTHOR, null);
    }

    @Test(expected = EmptyBookTitleException.class)
    public void createBookWithNullTitle()
    throws
    EmptyBookTitleException {
        Book newBook = new Book(null);
    }

    @Test(expected = EmptyBookTitleException.class)
    public void createBookWithEmptyTitle()
    throws
    EmptyBookTitleException {
        Book newBook = new Book("");
    }

    @Test
    public void setAuthorWithAuthorDefined()
    throws
    EmptyBookTitleException,
    EmptySinopseException,
    EmptyAuthorException {
        Book newBook = new Book(TITLE, AUTHOR);
        newBook.setAuthor(CHANGED_AUTHOR);

        assertEquals(CHANGED_AUTHOR, newBook.getAuthor());
    }

    @Test
    public void setAuthorWithNoAuthor()
    throws
    EmptyBookTitleException,
    EmptySinopseException,
    EmptyAuthorException {
        Book newBook = new Book(TITLE);
        newBook.setAuthor(CHANGED_AUTHOR);

        assertEquals(CHANGED_AUTHOR, newBook.getAuthor());
    }

    @Test(expected = EmptyAuthorException.class)
    public void setAuthorWithNull()
    throws
    EmptyBookTitleException,
    EmptyAuthorException {
        Book newBook = new Book(TITLE);
        newBook.setAuthor(null);
    }

    @Test(expected = EmptyAuthorException.class)
    public void setAuthorWithEmpty()
    throws
    EmptyBookTitleException,
    EmptyAuthorException {
        Book newBook = new Book(TITLE);
        newBook.setAuthor("");
    }

    @Test
    public void setSinopse()
    throws
    EmptyBookTitleException,
    EmptySinopseException {
        Book newBook = new Book(TITLE);
        newBook.setSinopse(CHANGED_SINOPSE);

        assertEquals(CHANGED_SINOPSE, newBook.getSinopse());
    }

}