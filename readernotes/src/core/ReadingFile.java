package readernotes.src.core;

import readernotes.src.exceptions.EmptyTitleException;

public class ReadingFile {
    private static final String defaultValue = "DEFAULT";
    private String _title;
    private String _bookTitle;
    private String _content;

    public ReadingFile(String title, String bookTitle)
    throws
    EmptyTitleException {
        this(title, bookTitle, defaultValue);
    }

    public ReadingFile(String title, String bookTitle, String content)
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
        if (verifyIfEmpty(title)) {
            throw new EmptyTitleException();
        } else {
            _title = title;
        }
    }

    public String getTitle() {
        return _title;
    }

    public void setBookTitle(String title)
    throws
    EmptyTitleException {
        if (verifyIfEmpty(title)) {
            throw new EmptyTitleException();
        } else {
            _bookTitle = title;
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
        return value == null || value.equals("");
    }
}
