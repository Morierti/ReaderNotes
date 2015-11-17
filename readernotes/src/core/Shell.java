package readernotes.src.core;

import java.util.List;
import java.util.ArrayList;

public class Shell {
    private List<Book> _bookList;
    private List<Sintese> _sinteseList;
    
    public Shell() {
        init();
    }

    public void init() {
        _bookList = new ArrayList<Book>();
        _sinteseList = new ArrayList<Sintese>();
    }

    public Sintese getSintese(String sinteseName) {
        return null;
    }

    public Book getBook(String bookTitle) {
        return null;
    }

    public List<Book> getBookList() {
        return _bookList;
    }

    public List<Sintese> getSinteseList() {
        return _sinteseList;
    }

    public void addBook(Book newBook) {
        _bookList.add(newBook);
    }

    public void addSintese(Sintese newSintese) {
        _sinteseList.add(newSintese);
    }

    public void changeSintese(String sinteseId, String newContent) {
        Sintese sintese = findSintese(sinteseId);
        if (!sintese.isEmpty()) {
            sintese.setContent(newContent);
        }
    }

    public void addSinteseContent(String sinteseId, String newContent) {
        Sintese sintese = findSintese(sinteseId);
        if (!sintese.isEmpty()) {
            sintese.addContent(newContent);
        }
    }

    public void removeBook(String bookId) {
        Book book = findBook(bookId);
        if (!book.isEmpty()) {
            _bookList.remove(book);
        }
    }

    public void removeSintese(String sinteseId) {
        Sintese sintese = findSintese(sinteseId);
        if (!sintese.isEmpty()) {
            _sinteseList.remove(sintese);
        }
    }

    public Book findBook(String bookId) {
        for (Book iterBook : _bookList) {   
            if (iterBook.getTitle().equals(bookId) ||
                iterBook.getAuthor().equals(bookId)) {
                return iterBook;
            }
        }
        return null;
    }

    public Sintese findSintese(String sinteseId) {
        for (Sintese iterSintese : _sinteseList) {
            if (iterSintese.getTitle().equals(sinteseId) ||
                iterSintese.getContent().equals(sinteseId)) {
                return iterSintese;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        
    }
}
