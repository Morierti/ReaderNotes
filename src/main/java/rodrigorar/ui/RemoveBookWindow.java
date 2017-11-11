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

package rodrigorar.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Box;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import rodrigorar.core.Library;

public class RemoveBookWindow extends JFrame {
    private JScrollPane _titleArea;

    public RemoveBookWindow() {
        this.initUI();
        this.setVisible(true);
    }

    private void setTitleArea(JScrollPane titleArea) {
        _titleArea = titleArea;
    }

    private JScrollPane getTitleArea() {
        return _titleArea;
    }

    private JLabel createNewLabel(String value) {
        JLabel label = new JLabel(value);

        return label;
    }

    private JScrollPane createTitleArea() {
        JTextArea titleArea = new JTextArea();
        JScrollPane scrollPanel = new JScrollPane(titleArea);

        titleArea.setLineWrap(true);
        titleArea.setWrapStyleWord(true);

        scrollPanel.setPreferredSize(new Dimension(300,20));
        scrollPanel.setMaximumSize(new Dimension(2000,20));

        return scrollPanel;
    }

    private JButton createRemoveButton() {
        JButton removeButton = new JButton("Remove");

        removeButton.addActionListener(new ActionListener() {
            //Some calls belong to RemoveBookWindow class.
            @Override
            public void actionPerformed(ActionEvent evetn) {
                Library library = Library.getInstance();
                JViewport titleViewport = getTitleArea().getViewport();
                JTextArea title = (JTextArea) titleViewport.getView();
                library.removeBook(title.getText().trim());

                //Update list on main window.
                MainWindow.getInstance().updateBookList();

                dispose();
            }
        });
        return removeButton;
    }

    private JButton createSeeBookButton() {
        JButton seeBookButton = new JButton("See Book");

        seeBookButton.addActionListener(new ActionListener() {
            //Some calls belong to RemoveBookWindow class.
            @Override
            public void actionPerformed(ActionEvent event) {
                JViewport titleViewport = getTitleArea().getViewport();
                JTextArea title = (JTextArea) titleViewport.getView();

                new BookForm(title.getText().trim());
            }
        });
        return seeBookButton;
    }

    private JPanel createTitlePanel() {
        JPanel panel = new JPanel();

        this.setTitleArea(this.createTitleArea());

        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.add(this.createNewLabel("Title"));
        panel.add(Box.createRigidArea(new Dimension(10,0)));
        panel.add(this.getTitleArea());

        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel();

        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.add(Box.createHorizontalGlue());
        panel.add(this.createSeeBookButton());
        panel.add(Box.createRigidArea(new Dimension(10,0)));
        panel.add(this.createRemoveButton());

        return panel;
    }

    private JPanel createLayout() {
        JPanel panel = new JPanel();

        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(this.createTitlePanel());
        panel.add(Box.createVerticalGlue());
        panel.add(this.createButtonPanel());

        return panel;
    }

    private void initUI() {
        this.add(this.createLayout());
        this.setTitle("Remove Book");
        this.setSize(400,80);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
