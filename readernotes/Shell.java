package readernotes;

import readernotes.src.core.Book;
import readernotes.src.core.Sintese;
import readernotes.src.core.Library;
import readernotes.src.database.BookXML;
import readernotes.src.database.SinteseXML;
import readernotes.src.database.IOManager;
import readernotes.src.exceptions.EmptyTitleException;
import readernotes.src.exceptions.EmptyAuthorException;

public class Shell {
	
	public static void main(String[] args) 
	throws
	EmptyTitleException,
	EmptyAuthorException {
		Book newBook = new Book("A Mensagem", "Fernando Pessoa");
		newBook.setSinopse("Um livro sobre livros.");
        Book newBook2 = new Book("Moby Dick", "Don't know");
        newBook2.setSinopse("A book about whales");
        Book newBook3 = new Book("Biblia", "Deus");
        newBook3.setSinopse("A fairytale");
        Sintese newSintese = new Sintese("Review", "Moby Dick");
        newSintese.setContent("A review on the famous novel.");
        
        
        IOManager iomanager = new IOManager();
        Library library = Library.getInstance();
        library.addBook(newBook);
        library.addBook(newBook3);
        library.addBook(newBook2);
        library.addSintese(newSintese);
        
        iomanager.buildBookDatabase();
        iomanager.buildSinteseDatabase();
        
        iomanager.buildBookDatabaseDocument();
        iomanager.buildSinteseDatabaseDocument();

		//MAIS TESTES PARA VER SE EXISTEM FALHAS.
	}

}
