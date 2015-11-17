package readernotes.src.core;

public class Sintese {
    private static boolean _empty = true;
    private Book _book;
    private String _title;
    private StringBuilder _content;

    public Sintese(String title, Book book) {
        setTitle(title);    
        setBook(book);
        _empty = false;
    }
    
    public void setTitle(String title) {
        _title = title;
    }

    public void setBook(Book book) {
        _book = book;
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
