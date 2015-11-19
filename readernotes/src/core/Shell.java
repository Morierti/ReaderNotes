package readernotes.src.core;

import java.util.List;
import java.util.ArrayList;
import readernotes.src.exceptions.NullBookException;
import readernotes.src.exceptions.NullSinteseException;
import readernotes.src.exceptions.BookNotFoundException;
import readernotes.src.exceptions.SinteseNotFoundException;

public class Shell {
    private List<Book> _bookList;
    private List<Sintese> _sinteseList;
    private static Shell _instance;

    public static Shell getInstance() {
        if (!exists()) {
            new Shell();
        }
        return _instance;
    }
    
    private Shell() {
        init();
    }

    public void init() {
        _instance = this;
        _bookList = new ArrayList<Book>();
        _sinteseList = new ArrayList<Sintese>();
    }

    public static boolean exists() {
        return _instance != null;
    }

    public Sintese getSintese(String sinteseName)
    throws
    SinteseNotFoundException {
        return findSintese(sinteseName);
    }

    public Book getBook(String bookTitle)
    throws
    BookNotFoundException {
        return findBook(bookTitle);
    }

    public List<Book> getBookList() {
        return _bookList;
    }

    public List<Sintese> getSinteseList() {
        return _sinteseList;
    }

    public void addBook(Book newBook)
    throws
    NullBookException {
        if (!isNull(newBook) && newBook instanceof Book) {
            _bookList.add(newBook);
        } else {
            throw new NullBookException();
        }
    }

    public void addSintese(Sintese newSintese)
    throws
    NullSinteseException {
        if (!isNull(newSintese) && newSintese instanceof Sintese) {
            _sinteseList.add(newSintese);
        } else {
            throw new NullSinteseException();
        }
    }

    public void changeSintese(String sinteseId, String newContent)
    throws
    SinteseNotFoundException {
        Sintese sintese = findSintese(sinteseId);
        if (!sintese.isEmpty()) {
            sintese.setContent(newContent);
        }
    }

    public void addSinteseContent(String sinteseId, String newContent)
    throws
    SinteseNotFoundException {
        Sintese sintese = findSintese(sinteseId);
        if (!sintese.isEmpty()) {
            sintese.addContent(newContent);
        }
    }

    public void removeBook(String bookId)
    throws
    BookNotFoundException {
        Book book = findBook(bookId);
        if (!book.isEmpty()) {
            _bookList.remove(book);
        }
    }

    public void removeSintese(String sinteseId)
    throws
    SinteseNotFoundException {
        Sintese sintese = findSintese(sinteseId);
        if (!sintese.isEmpty()) {
            _sinteseList.remove(sintese);
        }
    }

    public Book findBook(String bookId)
    throws
    BookNotFoundException {
        for (Book iterBook : _bookList) {   
            if (iterBook.getTitle().equals(bookId)) {
                return iterBook;
            }
        }
        throw new BookNotFoundException(bookId);
    }

    public Sintese findSintese(String sinteseId)
    throws
    SinteseNotFoundException {
        for (Sintese iterSintese : _sinteseList) {
            if (iterSintese.getTitle().equals(sinteseId)) {
                return iterSintese;
            }
        }
        throw new SinteseNotFoundException(sinteseId);
    }

    public boolean isNull(Object testObject) {
        return testObject == null;
    }

    public static void main(String[] args) {
        
    }
}
