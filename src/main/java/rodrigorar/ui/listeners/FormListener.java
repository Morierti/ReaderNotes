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

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.JTextArea;

public abstract class FormListener
implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent event) {
        setup();
        execute(event);
    }

    public abstract void setup();

    public abstract void execute(ActionEvent event);

    public String parseScrollPane(JScrollPane scrollPane) {
        JViewport viewport = scrollPane.getViewport();
        JTextArea textArea = (JTextArea)viewport.getView();

        return textArea.getText().trim();
    }
}
