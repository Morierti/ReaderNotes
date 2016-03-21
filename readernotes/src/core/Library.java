package readernotes.src.core;

import java.util.Map;
import java.util.HashMap;

public class Library {
    private static Library _instance;
    private Map<String, Book> _bookDB;
    private Map<String, Sintese> _sinteseDB;
    
    public static boolean isEmpty() {
        return _instance == null;
    }

    public static Library getInstance() {
        if (_instance.isEmpty()) {
            new Library();
        }
        return _instance;
    }

    private Library() {
        init();
    }

    private void init() {
        _instance = this;
        _bookDB = new HashMap<String, Book>();
        _sinteseDB = new HashMap<String, Sintese>();
    }
    
    public void addBook(Book newBook) {
        if (_bookDB.containsKey(newBook.getTitle())) {
            //throw new DoubleEntryException(newBook.getTitle());
        } else {
            _bookDB.put(newBook.getTitle(), newBook);
        }
    }
    
    public void removeBook(String bookTitle) {
        _bookDB.remove(bookTitle);
    }
    
    public Book getBook(String bookTitle) {
        if (_bookDB.containsKey(bookTitle)) {
            return _bookDB.get(bookTitle);
        } else {
            //throw new InexistentBookException(bookTitle);
        }
        return null;
    }
    
    public Map<String, Book> getBookDB() {
        return _bookDB;
    }
    
    public void addSintese(Sintese newSintese) {
        if (_sinteseDB.containsKey(newSintese.getTitle())) {
            //throw new DoubleEntryException(newSintese.getTitle());
        } else {
            _sinteseDB.put(newSintese.getTitle(), newSintese);
        }
    }
    
    public void removeSintese(String title) {
        _sinteseDB.remove(title);
    }
    
    public Sintese getSintese(String sinteseTitle) {
        if (!_sinteseDB.containsKey(sinteseTitle)) {
            //throw new InexistentSinteseException(sinteseTitle);
        } else {
            return _sinteseDB.get(sinteseTitle);
        }
        return null;
    }
    
    public Map<String, Sintese> getSinteseDB() {
        return _sinteseDB;
    }
    
}
