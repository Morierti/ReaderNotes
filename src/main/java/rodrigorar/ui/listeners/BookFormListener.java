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

import java.awt.event.ActionEvent;

import rodrigorar.core.Book;
import rodrigorar.ui.BookForm;
import rodrigorar.integration.dto.BookDTO;
import rodrigorar.exceptions.EmptyTitleException;
import rodrigorar.exceptions.EmptyAuthorException;

public class BookFormListener
extends FormListener {
    private BookForm _bookForm;
    private BookDTO _bookDTO;

    public BookFormListener(BookForm bookForm) {
        this.setBookForm(bookForm);
    }

    private void setBookForm(BookForm bookForm) {
        _bookForm = bookForm;
    }

    public BookForm getBookForm() {
        return _bookForm;
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
                this.parseScrollPane(getBookForm().getTitleArea()),
                this.parseScrollPane(getBookForm().getAuthorArea()),
                this.parseScrollPane(getBookForm().getISBNArea()),
                this.parseScrollPane(getBookForm().getSubjectArea()),
                this.parseScrollPane(getBookForm().getSinopseArea())
            )
        );
    }

    @Override
    public void execute(ActionEvent event) {
        try {
		    Book book = getBookForm().getBook();
		    BookDTO bookDTO = getBookDTO();
			if (bookDTO.getTitle() != null) {
				book.setTitle(bookDTO.getTitle());
			}
			if (bookDTO.getAuthor() != null) {
				book.setAuthor(bookDTO.getAuthor());
			}
			if (bookDTO.getSinopse() != null) {
				book.setSinopse(bookDTO.getSinopse());
			}
			if (bookDTO.getISBN() != null) {
				book.setISBN(bookDTO.getISBN());
			}
			if (bookDTO.getSubject() != null)   {
				book.setSubject(bookDTO.getSubject());
			}

		} catch (EmptyTitleException
				| EmptyAuthorException exception) {
			System.err.print(exception.getMessage());
		}
		getBookForm().dispose();
    }
}
