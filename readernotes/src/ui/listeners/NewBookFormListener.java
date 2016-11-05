/**
Copyright (C) 2016  Rodrigo Ramos Rosa

    This program is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License along
    with this program; if not, write to the Free Software Foundation, Inc.,
    51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
**/

package readernotes.src.ui.listeners;

// Lib imports
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

// Application imports
import readernotes.src.ui.NewBookForm;
import readernotes.src.ui.MainWindow;
import readernotes.src.core.Library;
import readernotes.src.core.Book;
import readernotes.src.exceptions.DoubleEntryException;
import readernotes.src.exceptions.EmptyTitleException;
import readernotes.src.exceptions.EmptyAuthorException;

public class NewBookFormListener
extends FormListener {
    private NewBookForm _newBookForm;
    private String _title;
    private String _author;
    private String _isbn;
    private String _subject;
    private String _sinopse;

    public NewBookFormListener(NewBookForm newBookForm) {
        this.setNewBookForm(newBookForm);      
    }
    
    private void setNewBookForm(NewBookForm newBookForm) {
        _newBookForm = newBookForm;
    }
    
    public NewBookForm getNewBookForm() {
        return _newBookForm;
    }
    
    private void setTitle(String title) {
        _title = title;
    }
    
    public String getTitle() {
        return _title;
    }
    
    private void setAuthor(String author) {
        _author = author;
    }
    
    public String getAuthor() {
        return _author;
    }
    
    private void setISBN(String isbn) {
        _isbn = isbn;
    }
    
    public String getISBN() {
        return _isbn;
    }
    
    private void setSubject(String subject) {
        _subject = subject;
    }
    
    public String getSubject() {
        return _subject;
    }
    
    private void setSinopse(String sinopse) {
        _sinopse = sinopse;
    }
    
    public String getSinopse() {
        return _sinopse;
    }
    
    @Override
    public void setup() {
        this.setTitle(this.parseScrollPane(getNewBookForm().getTitleArea()));
        this.setAuthor(this.parseScrollPane(getNewBookForm().getAuthorArea()));
        this.setISBN(this.parseScrollPane(getNewBookForm().getISBNArea()));
        this.setSubject(this.parseScrollPane(getNewBookForm().getSubjectArea()));
        this.setSinopse(this.parseScrollPane(getNewBookForm().getSinopseArea())); 
    }

    @Override
    public void execute(ActionEvent event) {
        try {
            Library library = Library.getInstance();
            
            library.addBook(new Book(this.getTitle(),
                                     this.getAuthor(),
                                     this.getISBN(),
                                     this.getSubject(),
                                     this.getSinopse()));
            // Update List on Main Window
		    MainWindow.getInstance().updateBookList();
        } catch (DoubleEntryException
                 | EmptyTitleException
                 | EmptyAuthorException exception) {
          System.err.println(exception.getMessage());
        }
        this.getNewBookForm().dispose();
    }
}
