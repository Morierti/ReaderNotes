package readernotes.src.exceptions;

public class InexistentBookException extends Exception {
    private String _title;
    private String _message = "The book " + _title + " does not exist.";

    public InexistentBookException(String title) {
        setTitle(title);
    }

    public void setTitle(String title) {
        _title = title;
    }

    public String getTitle() {
        return _title;
    }

    public String getMessage() {
        return _message;
    }
}
