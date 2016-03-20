package readernotes.src.core;

public class Library {
    private static Library _instance;
    
    public static boolean isEmpty() {
        return _instance == null;
    }

    public static Library getInstance() {
        if (_instance.isEmpty()) {
            new Library();
        }
        return _instance;
    }

    private Library() {
        init();
    }

    private void init() {
        _instance = this;
    }
    
}
