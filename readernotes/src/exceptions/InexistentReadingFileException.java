package readernotes.src.exceptions;

public class InexistentReadingFileException extends Exception {
    private String _title;
    private String _message = "The Reading File " + _title + " does not exist.";

    public InexistentReadingFileException(String title) {
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
