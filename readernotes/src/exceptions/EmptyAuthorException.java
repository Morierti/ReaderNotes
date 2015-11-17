package readernotes.src.exceptions;

import java.lang.Exception;

public class EmptyAuthorException extends Exception {
    private static final String MESSAGE = "Empty Author is not allowed.";

    @Override
    public String getMessage() {
        return MESSAGE;
    }
}