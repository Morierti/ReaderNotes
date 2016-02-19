package readernotes.src.core;

import readernotes.src.exceptions.EmptyTitleException;

public class Sintese {
    private String _title;
    private String _bookTitle;
    private String _content;

    public Sintese(String title, String bookTitle)
    throws
    EmptyTitleException {
        init(title, bookTitle, null);
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
        if (title != null && !title.equals("")) {
            _title = title;
        } else {
            throw new EmptyTitleException();
        }
    }

    public void setBookTitle(String bookTitle)
    throws
    EmptyTitleException {
        if (bookTitle != null && !bookTitle.equals("")) {
            _bookTitle = bookTitle;
        } else {
            throw new EmptyTitleException();
        }
    }

    public void setContent(String content) {
        _content = content;
    }
}
