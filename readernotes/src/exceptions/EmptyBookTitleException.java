package readernotes.src.exceptions;

import java.lang.Exception;

public class EmptyBookTitleException extends Exception {
    private static final String MESSAGE = "The book has to have a title.";

    @Override
    public String getMessage() {
        return MESSAGE;
    }
}