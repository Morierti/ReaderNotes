package readernotes.src.exceptions;

public class EmptyTitleException extends Exception {
    private static final String _errorMessage = "The Title cannot be empty.";
    
    public String getMessage() {
        return _errorMessage;
    }

}
