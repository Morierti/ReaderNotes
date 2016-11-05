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

package readernotes.src.core;

// Application Imports
import readernotes.src.exceptions.EmptyAuthorException;
import readernotes.src.exceptions.EmptyTitleException;

public class Book
extends Entity {
    private String _author;
    private String _sinopse;
    private String _isbn;
    
    public Book(String title, String author)
    throws
    EmptyTitleException,
    EmptyAuthorException {
        this(title, author, Book.DEFAULT_VALUE, Book.DEFAULT_VALUE, Book.DEFAULT_VALUE);
    }

    public Book(String title, String author, String isbn, String subject, String sinopse)
    throws
    EmptyTitleException,
    EmptyAuthorException {
        this.setTitle(title);
        this.setAuthor(author);
        this.setSinopse(sinopse);
        this.setISBN(isbn);
        this.setSubject(subject);
    }

    public void setAuthor(String author)
    throws
    EmptyAuthorException {
        if (!this.verifyIfEmpty(author)) {
            _author = author;
        } else {
            throw new EmptyAuthorException();
        }
    }

    public String getAuthor() {
        return _author;
    }

    public void setSinopse(String sinopse) {
        _sinopse = sinopse;
    }

    public String getSinopse() {
        return _sinopse;
    }

    public void setISBN(String isbn) {
        _isbn = isbn;
    }

    public String getISBN() {
        return _isbn;
    }
    
    @Override
    public boolean matchesEntity(String itemID) {
        return itemID.equals(this.getTitle())
               || itemID.equals(this.getAuthor())
               || itemID.equals(this.getISBN())
               || itemID.equals(this.getSubject());
    }

}
