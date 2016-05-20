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

package readernotes;

import readernotes.src.ui.MainWindow;
import readernotes.src.core.Book;
import readernotes.src.core.ReadingFile;
import readernotes.src.core.Library;
import readernotes.src.database.BookXML;
import readernotes.src.database.ReadingFileXML;
import readernotes.src.database.IOManager;
import readernotes.src.exceptions.EmptyTitleException;
import readernotes.src.exceptions.EmptyAuthorException;
import readernotes.src.exceptions.DoubleEntryException;
import readernotes.src.exceptions.InexistentBookException;
import readernotes.src.exceptions.InexistentReadingFileException;
import java.awt.EventQueue;

public class Shell {
	private static Library _library;
	private IOManager _ioManager;
	private Shell _instance;

	public Shell() {
		init();
	}

	private void init() {
		_ioManager.getInstance();
		_library = Library.getInstance();
		_instance = this;
	}

	public static void main(String[] args)
	throws
	Exception {
		Shell shell = new Shell();

	    EventQueue.invokeLater(new Runnable() {

	        @Override
	        public void run() {
	            MainWindow mainWindow = MainWindow.getInstance();
	            mainWindow.setVisible(true);
	        }
	    });
	}
}
