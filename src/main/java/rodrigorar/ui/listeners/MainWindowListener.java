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

import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import rodrigorar.ui.SaveMessageWindow;

public class MainWindowListener implements WindowListener {

    public void windowActivated(WindowEvent event) {
        System.out.println("windowActivated method called");
    }

    public void windowClosed(WindowEvent event) {
        System.out.println("windowClosed method called");
    }

    public void windowClosing(WindowEvent event) {
        new SaveMessageWindow();
    }

    public void windowDeactivated(WindowEvent event) {
        System.out.println("windowDeactivated method called");
    }

    public void windowDeiconified(WindowEvent e) {
        System.out.println("windowDeiconified method called");
    }

    public void windowIconified(WindowEvent e) {
        System.out.println("windowIconified method called");
    }

    public void windowOpened(WindowEvent e) {
        System.out.println("windowOpened method called");
    }
}
