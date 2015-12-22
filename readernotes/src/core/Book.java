package readernotes.src.core;

import readernotes.src.exceptions.EmptyTitleException;

public class Book {
    private static final String EMPTY_FIELD = null;
    private static boolean _empty = true;
    private String _author;
    private String _title;
    private String _sinopse;

    public Book(String title, String author, String sinopse)
    throws
    EmptyTitleException {
        init(title, author, sinopse);
    }

    public Book(String title, String author)
    throws
    EmptyTitleException {
        this(title, author, EMPTY_FIELD);
    }

    public Book(String title)
    throws
    EmptyTitleException {
        this(title, EMPTY_FIELD);
    }

    public void init(String title, String author, String sinopse)
    throws
    EmptyTitleException {
      setTitle(title);
      setAuthor(author);
      setSinopse(sinopse);
      _empty = false;
    }

    private void setTitle(String title)
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

    public void setAuthor(String author) {
        _author = author;
    }

    public void setSinopse(String sinopse) {
        _sinopse = sinopse;
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
