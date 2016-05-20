package readernotes.src.exceptions;

public class EmptyAuthorException extends Exception {
    private static final String _errorMessage = "Please insert an author.";

    public String getMessage() {
        return _errorMessage;
    }

}
