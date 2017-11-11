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

package rodrigorar.core;

// Application Imports
import rodrigorar.exceptions.EmptyTitleException;

public class ReadingFile
extends Entity {
    private String _bookTitle;
    private String _content;
    public static final String DEFAULT_VALUE = "DEFAULT";

    public ReadingFile(String title, String bookTitle)
    throws
    EmptyTitleException {
        this(title, bookTitle, ReadingFile.DEFAULT_VALUE, ReadingFile.DEFAULT_VALUE);
    }

    public ReadingFile(String title, String bookTitle, String subject, String content)
    throws
    EmptyTitleException {
        this.setTitle(title);
        this.setBookTitle(title);
        this.setSubject(subject);
        this.setContent(content);
    }

    public void setBookTitle(String title)
    throws
    EmptyTitleException {
        if (!this.verifyIfEmpty(title)) {
            this._bookTitle = title;
        } else {
            throw new EmptyTitleException();
        }
    }

    public String getBookTitle() {
        return _bookTitle;
    }

    public void setContent(String content) {
        _content = content;
    }

    public String getContent() {
        return _content;
    }

    @Override
    public boolean matchesEntity(String itemID) {
        return itemID.equals(this.getTitle())
               || itemID.equals(this.getBookTitle())
               || itemID.equals(this.getSubject());
    }
}
