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

public abstract class Entity {
    public static final String DEFAULT_VALUE = "DEFAULT";
    private String _title;
    private String _subject;

    public void setTitle(String newTitle)
    throws
    EmptyTitleException {
        if (!this.verifyIfEmpty(newTitle)) {
            this._title = newTitle;
        } else {
            throw new EmptyTitleException();
        }
    }

    public String getTitle() {
        return this._title;
    }

    public void setSubject(String newSubject) {
        this._subject = newSubject;
    }

    public String getSubject() {
        return this._subject;
    }

    public boolean matchesEntity(String itemID) {
        return itemID == this.getTitle();
    }

    public boolean verifyIfEmpty(String value) {
        return value == null || value == "";
    }


}
