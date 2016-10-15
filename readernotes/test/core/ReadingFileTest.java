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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import readernotes.src.core.ReadingFile;
import readernotes.src.exceptions.EmptyTitleException;

public class ReadingFileTest {
    //TOKENS

    private static final String RF_TITLE = "Chapter I - Adventure";
    private static final String RF_BOOK_TITLE = "Adventurer";
    private static final String RF_SUBJECT = "Store of the Amidas Adventure";
    private static final String RF_CONTENT = "This is the story of...";
    private static final String DEFAULT_VALUE = "DEFAULT";

    @Test
    public void ReadingFileCreationSuccess()
    throws
    EmptyTitleException {
        ReadingFile testFile = new ReadingFile(this.RF_TITLE,
                                                this.RF_BOOK_TITLE,
                                                this.RF_SUBJECT,
                                                this.RF_CONTENT);

        assertNotNull(testFile);
        assertEquals(this.RF_TITLE, testFile.getTitle());
        assertEquals(this.RF_BOOK_TITLE, testFile.getBookTitle());
        assertEquals(this.RF_SUBJECT, testFile.getSubject());
        assertEquals(this.RF_CONTENT, testFile.getContent());
    }

    @Test
    public void ReadingFileCreationSuccess2()
    throws
    EmptyTitleException {
        ReadingFile testFile = new ReadingFile(this.RF_TITLE, this.RF_BOOK_TITLE);

        assertNotNull(testFile);
        assertEquals(this.RF_TITLE, testFile.getTitle());
        assertEquals(this.RF_BOOK_TITLE, testFile.getBookTitle());
        assertEquals(this.DEFAULT_VALUE, testFile.getSubject());
        assertEquals(this.DEFAULT_VALUE, testFile.getContent());

    }

    @Test
    public void TitleUpdate()
    throws {

    }

    @Test (expected = EmptyTitleException.class)
    public void nullTitleUpdate()
    throws
    EmptyTitleException {

    }

    @Test (expected = EmptyTitleException.class)
    public void emptyTitleUpdate()
    throws
    EmptyTitleException {

    }

    @Test
    public void BookTitleUpdate()
    throws
    EmptyTitleException {

    }

    @Test (expected = EmptyTitleException.class)
    public void nullBookTitleUpdate()
    throws
    EmptyTitleException {

    }

    @Test (expected = EmptyTitleException.class)
    public void emptyTitleUpdate()
    throws
    EmptyTitleException {

    }

    @Test
    public void subjectUpdate()
    throws
    EmptyTitleException {

    }

    @Test
    public void contentUpdate()
    throws
    EmptyTitleException {

    }

    @Test


}
