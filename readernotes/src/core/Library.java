package readernotes.src.core;

import java.util.Map;
import java.util.HashMap;
import readernotes.src.exceptions.DoubleEntryException;
import readernotes.src.exceptions.EmptyAuthorException;
import readernotes.src.exceptions.EmptyTitleException;


public class Library {
    private Map<String, Book> _bookLibrary;
    private Map<String, Sintese> _sinteseLibrary;

    public Library() {
        init();
    }

    private void init() {
        _bookLibrary = new HashMap<String, Book>();
        _sinteseLibrary = new HashMap<String, Sintese>();
        //Load information from file.
    }

    public void addBook(String bookTitle, String bookAuthor)
    throws
    EmptyTitleException,
    EmptyAuthorException,
    DoubleEntryException {
        if (_bookLibrary.containsKey(bookTitle)) {
            throw new DoubleEntryException(bookTitle);
        } else {
            Book newBook = new Book(bookTitle, bookAuthor);
            _bookLibrary.put(bookTitle, newBook);
        }
    }

    public void addBook(String bookTitle, String bookAuthor, String sinopse)
    throws
    EmptyTitleException,
    EmptyAuthorException,
    DoubleEntryException {
        if (_bookLibrary.containsKey(bookTitle)) {
            throw new DoubleEntryException(bookTitle);
        } else {
            Book newBook = new Book(bookTitle, bookAuthor, sinopse);
            _bookLibrary.put(bookTitle, newBook);
        }
    }

    public void removeBook(String bookTitle) {
        _bookLibrary.remove(bookTitle);
    }

    public Book getBook(String bookTitle) {
        return _bookLibrary.get(bookTitle);
    }

    public void addSintese(String sinteseTitle, String bookTitle)
    throws
    EmptyTitleException,
    DoubleEntryException {
        if (_sinteseLibrary.containsKey(sinteseTitle)) {
            throw new DoubleEntryException(sinteseTitle);
        } else {
            Sintese newSintese = new Sintese(sinteseTitle, bookTitle);
            _sinteseLibrary.put(sinteseTitle, newSintese);
        }
    }

    public void addSintese(String sinteseTitle, String bookTitle, String content)
    throws
    EmptyTitleException,
    DoubleEntryException {
        if (_sinteseLibrary.containsKey(sinteseTitle)) {
            throw new DoubleEntryException(sinteseTitle);
        } else {
            Sintese newSintese = new Sintese(sinteseTitle, bookTitle, content);
            _sinteseLibrary.put(sinteseTitle, newSintese);
        }
    }

    public void removeSintese(String sinteseTitle) {
        _sinteseLibrary.remove(sinteseTitle);
    }

    public Sintese getSintese(String sinteseTitle) {
        return _sinteseLibrary.get(sinteseTitle);
    }
}
