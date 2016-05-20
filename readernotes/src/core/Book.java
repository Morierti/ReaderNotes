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

import readernotes.src.exceptions.EmptyTitleException;
import readernotes.src.exceptions.EmptyAuthorException;

public class Book {
    private static final String defaultValue = "DEFAULT";
    private String _title;
    private String _author;
    private String _sinopse;

    public Book(String title, String author)
    throws
    EmptyTitleException,
    EmptyAuthorException {
        this(title, author, defaultValue);
    }

    public Book(String title, String author, String sinopse)
    throws
    EmptyTitleException,
    EmptyAuthorException {
        init(title, author, sinopse);
    }

    public void init(String title, String author, String sinopse)
    throws
    EmptyTitleException,
    EmptyAuthorException {
        setTitle(title);
        setAuthor(author);
        setSinopse(sinopse);
    }

    public void setTitle(String title)
    throws
    EmptyTitleException {
        if (!verifyIfEmpty(title)) {
            _title = title;
        } else {
            throw new EmptyTitleException();
        }
    }

    public String getTitle() {
        return _title;
    }

    public void setAuthor(String author)
    throws
    EmptyAuthorException {
        if (!verifyIfEmpty(author)) {
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

    public boolean verifyIfEmpty(String value) {
        return value == null || value == "";
    }
}
