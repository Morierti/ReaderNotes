package readernotes.src.core;

import java.util.List;
import java.util.ArrayList;

public class CoreClassBuilder {
    private IOManager _io;
	private static  CoreClassBuilder _instance;

	public static CoreClassBuilder getInstance() {
		if (_instance.isEmpty()) {
			new CoreClassBuilder();
		}
		return _instance;
	}

	private CoreClassBuilder() {
		init();
	}

	public void init() {
		_instance = this;
		_io = IOManager.getInstance();
	}

	public static boolean isEmpty() {
		return _instance == null;
	}

    public Sintese buildSintese(String title) {
        return null;
    }

    public Book buildBook(String title) {
        return null;
    }

    public List<Book> buildAllBooks() {
        return null;
    }

    public List<Sintese> buildAllSinteses() {
        return null;
    }
}
