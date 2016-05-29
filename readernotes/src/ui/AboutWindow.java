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

package readernotes.src.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import javax.swing.JComponent;
import javax.swing.Box;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Color;
import java.awt.Font;
import readernotes.Shell;

public class AboutWindow extends JFrame {
	private String _version;
	private String _name;
	private String _description;

	public AboutWindow() {
		this.initUI();
		this.setVisible(true);
	}

	private void setVersion(String version) {
		_version = version;
	}

	private String getVersion() {
		return _version;
	}

	private void setAboutName(String name) {
		_name = name;
	}

	private String getAboutName() {
		return _name;
	}

	private void setDescription(String description) {
		_description = description;
	}

	private String getDescription() {
		return _description;
	}

	private JLabel createLabel(String value) {
		JLabel label = new JLabel(value);
		label.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		label.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		label.setAlignmentY(JComponent.CENTER_ALIGNMENT);
		return label;
	}

	private JPanel createLayout() {
		JPanel panel = new JPanel();
		JLabel version = this.createLabel(Shell.VERSION);
		JLabel name = this.createLabel(Shell.NAME);
		JLabel website = this.createLabel("Website:");
		JLabel websiteUrl = this.createLabel(Shell.WEBSITE);
		JLabel description = this.createLabel(Shell.DESCRIPTION);

		name.setFont(new Font("Arial", Font.BOLD, 16));
		websiteUrl.setForeground(Color.blue);

		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		panel.add(Box.createVerticalGlue());
		panel.add(name);
		panel.add(Box.createVerticalGlue());
		panel.add(version);
		panel.add(website);
		panel.add(websiteUrl);
		panel.add(Box.createVerticalGlue());
		panel.add(description);
		panel.add(Box.createVerticalGlue());

		return panel;
	}

	public void initUI() {
		Container contentPane = this.getContentPane();
		contentPane.setLayout(new BorderLayout());
		this.add(this.createLayout(), BorderLayout.CENTER);
		this.setTitle("About");
		this.setSize(300,200);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
}
