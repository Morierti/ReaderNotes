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

package rodrigorar.ui.listeners;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import rodrigorar.ui.NewBookForm;
import rodrigorar.ui.MainWindow;
import rodrigorar.integration.dto.BookDTO;
import rodrigorar.core.Library;
import rodrigorar.core.Book;
import rodrigorar.exceptions.DoubleEntryException;
import rodrigorar.exceptions.EmptyTitleException;
import rodrigorar.exceptions.EmptyAuthorException;

public class NewBookFormListener
extends FormListener {
    private NewBookForm _newBookForm;
    private BookDTO _bookDTO;

    public NewBookFormListener(NewBookForm newBookForm) {
        this.setNewBookForm(newBookForm);

    }

    private void setNewBookForm(NewBookForm newBookForm) {
        _newBookForm = newBookForm;
    }

    public NewBookForm getNewBookForm() {
        return _newBookForm;
    }

    private void setBookDTO(BookDTO bookDTO) {
        _bookDTO = bookDTO;
    }

    public BookDTO getBookDTO() {
        return _bookDTO;
    }

    @Override
    public void setup() {
         this.setBookDTO(
            new BookDTO(
                    this.parseScrollPane(getNewBookForm().getTitleArea()),
                    this.parseScrollPane(getNewBookForm().getAuthorArea()),
                    this.parseScrollPane(getNewBookForm().getISBNArea()),
                    this.parseScrollPane(getNewBookForm().getSubjectArea()),
                    this.parseScrollPane(getNewBookForm().getSinopseArea())
                )
              );
    }

    @Override
    public void execute(ActionEvent event) {
        try {
            Library library = Library.getInstance();
            BookDTO bookDTO = this.getBookDTO();

            library.addBook(new Book(bookDTO.getTitle(),
                                     bookDTO.getAuthor(),
                                     bookDTO.getISBN(),
                                     bookDTO.getSubject(),
                                     bookDTO.getSinopse()));
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
