package readernotes.src.exceptions;

import java.lang.Exception;

public class NullBookException extends Exception {
    private static final String MESSAGE = "The book doesn't exist.";

    public String getMessage() {
        return MESSAGE;
    }
}