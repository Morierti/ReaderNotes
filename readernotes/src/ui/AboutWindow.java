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
		JLabel version = this.createLabel("Version 0.1");
		JLabel name = this.createLabel("Reader Notes");
		JLabel description = this.createLabel("Description");

		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		panel.add(Box.createVerticalGlue());
		panel.add(version);
		panel.add(Box.createVerticalGlue());
		panel.add(name);
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
		this.setSize(200,200);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
}
