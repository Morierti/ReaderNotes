package readernotes.src.exceptions;

public class DoubleEntryException extends Exception {
    private String _title;
    private String _message = _title + " already exists!";

    public DoubleEntryException (String title) {
        setTitle(title);
    }

    public void setTitle(String title) {
        _title = title;
    }

    public String getMessage() {
        return _message;
    }
}
