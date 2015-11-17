package readernotes.src.core;

import readernotes.src.exceptions.EmptyTitleException;
import readernotes.src.exceptions.NullBookException;

public class Sintese {
    private static boolean _empty = true;
    private Book _book;
    private String _title;
    private StringBuilder _content;

    public Sintese(String title, Book book)
    throws
    EmptyTitleException,
    NullBookException {
        init(title, book);
    }

    private void init(String title, Book book)
    throws
    EmptyTitleException,
    NullBookException {
        setTitle(title);    
        setBook(book);
        _content = new StringBuilder();
        _empty = false;
    }
    
    public void setTitle(String title)
    throws
    EmptyTitleException {
        if (title != null) {
            if (!title.equals("")) {
                _title = title;
            } else {
                throw new EmptyTitleException();
            }
        } else {
            throw new EmptyTitleException();
        }
    }

    public void setBook(Book book)
    throws
    NullBookException {
        if (book != null) {
            _book = book;
        } else {
            throw new NullBookException();
        }
    }

    public void setContent(String newContent) {
        _content.replace(0, _content.length(), newContent);
    }

    public void addContent(String newContent) {
        _content.append(newContent);
    }

    public String getTitle() {
        return _title;
    }

    public Book getBook() {
        return _book;
    }

    public String getContent() {
        return _content.toString();
    }

    public boolean isEmpty() {
        return _empty;
    }

}
