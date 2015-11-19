package readernotes.src.exceptions;

import java.lang.Exception;

public class SinteseNotFoundException extends Exception {
    private String _sinteseTitle;

    public SinteseNotFoundException(String sinteseTitle) {
        setSinteseTitle(sinteseTitle);
    }

    public void setSinteseTitle(String sinteseTitle) {
        _sinteseTitle = sinteseTitle;
    }

    public String getMessage() {
        return "The Sintese " + _sinteseTitle + " does not exist in the database.";
    }
}