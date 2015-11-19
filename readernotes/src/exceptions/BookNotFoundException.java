package readernotes.src.exceptions;

import java.lang.Exception;

public class BookNotFoundException extends Exception {
    private String _bookTitle;

    public BookNotFoundException(String bookTitle) {
        setBookTitle(bookTitle);
    }

    public void setBookTitle(String bookTitle) {
        _bookTitle = bookTitle;
    }

    public String getMessage() {
        return "The Book " + _bookTitle + "does not exist in the database.";
    }
}