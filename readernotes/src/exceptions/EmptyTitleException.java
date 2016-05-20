package readernotes.src.exceptions;

public class EmptyTitleException extends Exception {
    private static final String _errorMessage = "Please insert a title.";

    public String getMessage() {
        return _errorMessage;
    }

}
