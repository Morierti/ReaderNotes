package readernotes.src.core;

import readernotes.src.exceptions.EmptyAuthorException;
import readernotes.src.exceptions.EmptySinopseException;
import readernotes.src.exceptions.EmptyBookTitleException;

public class Book {
    private static boolean _empty = true;
    private String _author;
    private String _title;
    private String _sinopse;

    public Book(String title, String author, String sinopse)
    throws
    EmptyBookTitleException,
    EmptyAuthorException,
    EmptySinopseException {
        this(title, author);
        setSinopse(sinopse);
    }

    public Book(String title, String author)
    throws
    EmptyBookTitleException,
    EmptyAuthorException {
        this(title);
        setAuthor(author);
    }

    public Book(String title)
    throws
    EmptyBookTitleException {
        setTitle(title);
        _empty = false;
    }

    private void setTitle(String title)
    throws
    EmptyBookTitleException {
        if (title != null) {
            if (!title.equals("")) {
                
                _title = title;
            } else {
                throw new EmptyBookTitleException();
            }
        } else {
            throw new EmptyBookTitleException();
        }
    }

    public void setAuthor(String author)
    throws
    EmptyAuthorException {
        if (author != null) {
            if (!author.equals("")) {
                _author = author;
            } else {
                throw new EmptyAuthorException();
            }
        } else {
            throw new EmptyAuthorException();
        }
    }

    public void setSinopse(String sinopse)
    throws
    EmptySinopseException {
        if(sinopse != null) {
            _sinopse = sinopse;
        } else {
            throw new EmptySinopseException();
        }
    }

    public String getAuthor() {
        return _author;
    }

    public String getTitle() {
        return _title;
    }

    public String getSinopse() {
        return _sinopse;
    }

    public static boolean isEmpty() {
        return _empty;
    }
}
