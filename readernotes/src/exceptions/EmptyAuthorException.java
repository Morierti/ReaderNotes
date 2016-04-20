package readernotes.src.exceptions;

public class EmptyAuthorException extends Exception {
    private static final String _errorMessage = "The Author can't be empty.";
    
    public String getMessage() {
        return _errorMessage;
    }

}
