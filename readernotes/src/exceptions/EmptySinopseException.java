package readernotes.src.exceptions;

import java.lang.Exception;

public class EmptySinopseException extends Exception {
    private static final String MESSAGE = "Empty Sinopse is not allowed.";

    @Override
    public String getMessage() {
        return MESSAGE;
    }
}
