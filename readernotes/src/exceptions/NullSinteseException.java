package readernotes.src.exceptions;

import java.lang.Exception;

public class NullSinteseException extends Exception {
    private static final String MESSAGE = "The Sintese does not exist";

    public String getMessage() {
        return MESSAGE;
    }
}