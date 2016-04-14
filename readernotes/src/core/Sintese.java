package readernotes.src.core;

import readernotes.src.exceptions.EmptyTitleException;

public class Sintese {
    private static final String defaultValue = "DEFAULT";
    private String _title;
    private String _bookTitle;
    private String _content;

    public Sintese(String title, String bookTitle)
    throws
    EmptyTitleException {
        init(title, bookTitle, defaultValue);
    }

    public Sintese(String title, String bookTitle, String content)
    throws
    EmptyTitleException {
        init(title, bookTitle, content);
    }

    private void init(String title, String bookTitle, String content)
    throws
    EmptyTitleException {
        setTitle(title);
        setBookTitle(bookTitle);
        setContent(content);
    }

    public void setTitle(String title)
    throws
    EmptyTitleException {
        if (!verifyIfEmpty(title)) {
            _title = title;
        } else {
            throw new EmptyTitleException();
        }
    }
    
    public String getTitle() {
        return _title;
    }

    public void setBookTitle(String bookTitle)
    throws
    EmptyTitleException {
        if (!verifyIfEmpty(bookTitle)) {
            _bookTitle = bookTitle;
        } else {
            throw new EmptyTitleException();
        }
    }
    
    public String getBookTitle() {
        return _bookTitle;
    }

    public void setContent(String content) {
        _content = content;
    }
    
    public String getContent() {
        return _content;
    }
    
    public boolean verifyIfEmpty(String value) {
        return value == null || value == "";
    }
}
