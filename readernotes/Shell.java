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
import java.awt.EventQueue;
import java.io.File;

public class Shell {

	public static void main(String[] args)
	throws
	Exception {

	    EventQueue.invokeLater(new Runnable() {

	        @Override
	        public void run() {
				Shell.createWorkDirectory();
	            MainWindow.getInstance();
	        }
	    });
	}

	public static String getUserDirectory() {
		String userDir = "/home/";
		String user = System.getProperty("user.name");

		userDir = userDir.concat(user);
		userDir = userDir.concat("/.readernotes");

		return userDir;
	}

	public static void createWorkDirectory() {
		File workDir = new File(Shell.getUserDirectory());

		if(!workDir.exists()) {
			System.out.println("Creating work directory.");
			try {
				workDir.mkdir();
			} catch (SecurityException exception) {
				exception.printStackTrace();
			}
		} else {
			System.out.println(Shell.getUserDirectory() + " already exists.");
		}
	}
}
