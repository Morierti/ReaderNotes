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

package readernotes.test.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import readernotes.src.core.Book;
import readernotes.src.exceptions.EmptyAuthorException;
import readernotes.src.exceptions.EmptyTitleException;

public class BookTest {
    //TOKENS
    private static final String AUTHOR_SUCCESS = "José Saramago";
    private static final String TITLE_SUCCESS = "A Mensagem";
    private static final String SINOPSE_SUCCESS = "Success Sinopse";
    private static final String EMPTY_AUTHOR = "";
    private static final String NULL_AUTHOR = null;
    private static final String EMPTY_TITLE = "";
    private static final String NULL_TITLE = null;
    private static final String EMPTY_SINOPSE = "";
    private static final String NULL_SINOPSE = null;
    private static final String DEFAULT = "DEFAULT";

    @Test
    public void bookCreationSucess()
    throws
    EmptyAuthorException,
    EmptyTitleException {
        Book newBook = new Book(TITLE_SUCCESS, AUTHOR_SUCCESS);

        assertEquals(TITLE_SUCCESS, newBook.getTitle());
        assertEquals(AUTHOR_SUCCESS, newBook.getAuthor());
    }

    @Test(expected = EmptyTitleException.class)
    public void emptyBookTitle()
    throws
    EmptyAuthorException,
    EmptyTitleException {
        Book newBook = new Book(EMPTY_TITLE, AUTHOR_SUCCESS);

        assertNull(newBook.getTitle());
    }

    @Test(expected = EmptyTitleException.class)
    public void nullBookTitle()
    throws
    EmptyAuthorException,
    EmptyTitleException {
        Book newBook = new Book(NULL_TITLE, AUTHOR_SUCCESS);

        assertNull(newBook.getTitle());
    }

    @Test(expected = EmptyAuthorException.class)
    public void emptyAuthor()
    throws
    EmptyAuthorException,
    EmptyTitleException {
        Book newBook = new Book(TITLE_SUCCESS, EMPTY_AUTHOR);

        assertNull(newBook.getAuthor());
    }

    @Test(expected = EmptyAuthorException.class)
    public void nullAuthor()
    throws
    EmptyAuthorException,
    EmptyTitleException {
        Book newBook = new Book(TITLE_SUCCESS, NULL_AUTHOR);

        assertNull(newBook.getAuthor());
    }

    @Test
    public void successSinopseAdd()
    throws
    EmptyAuthorException,
    EmptyTitleException {
        Book newBook = new Book(TITLE_SUCCESS, AUTHOR_SUCCESS);
        newBook.setSinopse(SINOPSE_SUCCESS);

        assertEquals(SINOPSE_SUCCESS, newBook.getSinopse());
    }

    @Test
    public void emptySinopse()
    throws
    EmptyAuthorException,
    EmptyTitleException {
        Book newBook = new Book(TITLE_SUCCESS, AUTHOR_SUCCESS);
        newBook.setSinopse(EMPTY_SINOPSE);

        assertEquals(DEFAULT, newBook.getSinopse());
    }

    //Null Sinopse
    @Test
    public void nullSinopse()
    throws
    EmptyTitleException,
    EmptyAuthorException {
        Book newBook = new Book(TITLE_SUCCESS, AUTHOR_SUCCESS);
        newBook.setSinopse(NULL_SINOPSE);

        assertEquals(DEFAULT, newBook.getSinopse());
    }
}
