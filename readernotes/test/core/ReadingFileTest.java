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
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import readernotes.src.core.ReadingFile;
import readernotes.src.exceptions.EmptyTitleException;

public class ReadingFileTest {

    //TOKENS
    private static final String RF_TITLE = "Chapter I - Adventure";
    private static final String RF_NEW_TITLE = "Chapter II - Adventure";
    private static final String RF_BOOK_TITLE = "Adventurer";
    private static final String RF_NEW_BOOK_TITLE = "Guitarman";
    private static final String RF_SUBJECT = "Store of the Amidas Adventure";
    private static final String RF_CONTENT = "This is the story of...";
    private static final String DEFAULT_VALUE = "DEFAULT";
    private ReadingFile defaultFile;

    @Before
    public void prepareForTest() {
        try {
            defaultFile = new ReadingFile(RF_TITLE, RF_BOOK_TITLE);
        } catch (EmptyTitleException exception) {
            System.out.println(exception.getMessage());
        }
    }

    @After
    public void tearDown() {
        defaultFile = null;
    }

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

        assertNotNull(defaultFile);
        assertEquals(this.RF_TITLE, defaultFile.getTitle());
        assertEquals(this.RF_BOOK_TITLE, defaultFile.getBookTitle());
        assertEquals(this.DEFAULT_VALUE, defaultFile.getSubject());
        assertEquals(this.DEFAULT_VALUE, defaultFile.getContent());

    }

    @Test
    public void TitleUpdate()
    throws
    EmptyTitleException {
        ReadingFile fileTest = new ReadingFile(this.RF_TITLE, this.RF_BOOK_TITLE);

        assertNotNull(fileTest);
        assertEquals(this.RF_TITLE, fileTest.getTitle());
        assertEquals(this.RF_BOOK_TITLE, fileTest.getBookTitle());

        fileTest.setTitle(this.RF_NEW_TITLE);

        assertEquals(this.RF_NEW_TITLE, fileTest.getTitle());
    }

    @Test (expected = EmptyTitleException.class)
    public void nullTitleUpdate()
    throws
    EmptyTitleException {
        ReadingFile fileTest = new ReadingFile(null, this.RF_BOOK_TITLE);

        assertNotNull(fileTest);
    }

    @Test (expected = EmptyTitleException.class)
    public void emptyTitleUpdate()
    throws
    EmptyTitleException {
        ReadingFile fileTest = new ReadingFile("", this.RF_BOOK_TITLE);

        assertNotNull(fileTest);
    }

    @Test
    public void BookTitleUpdate()
    throws
    EmptyTitleException {
        ReadingFile fileTest = new ReadingFile(this.RF_TITLE, this.RF_BOOK_TITLE);

        assertNotNull(this.RF_TITLE, fileTest.getBookTitle());

        fileTest.setBookTitle(this.RF_NEW_BOOK_TITLE);

        assertEquals(this.RF_NEW_BOOK_TITLE, fileTest.getBookTitle());
    }

    @Test (expected = EmptyTitleException.class)
    public void nullBookTitleUpdate()
    throws
    EmptyTitleException {
        ReadingFile testFile = new ReadingFile(RF_TITLE, null);

        assertNotNull(testFile);
    }

    @Test (expected = EmptyTitleException.class)
    public void emptyBookTitleUpdate()
    throws
    EmptyTitleException {
        ReadingFile testFile = new ReadingFile(RF_TITLE, "");

        assertNotNull(testFile);
    }

    @Test
    public void subjectUpdate()
    throws
    EmptyTitleException {
        ReadingFile testFile = new ReadingFile(RF_TITLE, RF_BOOK_TITLE);

        testFile.setSubject(RF_SUBJECT);

        assertEquals(testFile.getSubject(), RF_SUBJECT);
    }

    @Test
    public void nullSubjectUpdate()
    throws
    EmptyTitleException {
        ReadingFile testFile = new ReadingFile(RF_TITLE, RF_BOOK_TITLE);

        testFile.setSubject(null);
        assertNull(testFile.getSubject());
    }

    @Test
    public void emptySubjectUpdate()
    throws
    EmptyTitleException {
        ReadingFile testFile = new ReadingFile(RF_TITLE, RF_BOOK_TITLE);

        testFile.setSubject("");
        assertEquals("", testFile.getSubject());
    }

    @Test
    public void contentUpdate()
    throws
    EmptyTitleException {
        ReadingFile testFile = new ReadingFile(RF_TITLE, RF_BOOK_TITLE);

        testFile.setContent(RF_CONTENT);
        assertEquals(RF_CONTENT, testFile.getContent());
    }

    @Test
    public void nullContentUpdate()
    throws
    EmptyTitleException {
        ReadingFile testFile = new ReadingFile(RF_TITLE, RF_BOOK_TITLE);

        testFile.setContent(null);
        assertNull(testFile.getContent());
    }

    @Test
    public void emptyContentUpdate()
    throws
    EmptyTitleException {
        ReadingFile testFile = new ReadingFile(RF_TITLE, RF_BOOK_TITLE);

        testFile.setContent("");
        assertEquals("", testFile.getContent());
    }

    @Test
    public void nullVerifyIfEmptyTest()
    throws
    EmptyTitleException {
        ReadingFile testFile = new ReadingFile(RF_TITLE, RF_BOOK_TITLE);

        assertTrue(testFile.verifyIfEmpty(null));
        assertTrue(testFile.verifyIfEmpty(""));
    }

    @Test
    public void notNullVerifyIfEmptyTest()
    throws
    EmptyTitleException {
        ReadingFile testFile = new ReadingFile(RF_TITLE, RF_BOOK_TITLE);

        assertFalse(testFile.verifyIfEmpty(RF_TITLE));
    }

}
