package readernotes.test.core;

import org.junit.After;
import org.junit.Before;
import readernotes.src.core.Sintese;
import readernotes.src.core.Book;
import readernotes.src.core.Shell;
import readernotes.src.exceptions.NullBookException;
import readernotes.src.exceptions.EmptyTitleException;
import readernotes.src.exceptions.EmptyAuthorException;
import readernotes.src.exceptions.EmptySinopseException;

public abstract class CoreTest {
		
	@Before
	public void setUp() {
		//Setup for test
		populateForTest();
	}

	@After
	public void tearDown() {
		//Tear down test conditions
		destroyTestConditions();
	}	

	public abstract void populateForTest();

	public abstract void destroyTestConditions();

	public Book createNewBook(String title, String author) {
		try {
			Book newBook = new Book(title);
			newBook.setAuthor(author);
			return newBook;
		} catch (EmptyTitleException
				 | EmptyAuthorException exception) {
			System.out.println(exception.getMessage());
		}
		return null;
	}

	public Sintese createNewSintese(String title, Book book) {
		try {
			return new Sintese(title, book);
		} catch (EmptyTitleException
				 | NullBookException exception) {
			System.out.println(exception.getMessage());
		}
		return null;
	}
	
}