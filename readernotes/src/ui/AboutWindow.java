package readernotes.src.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
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
		return label;
	}

	private void createLayout() {
		Container pane = this.getContentPane();
		JLabel version = this.createLabel("Version 0.1");
		JLabel name = this.createLabel("Reader Notes");
		JLabel description = this.createLabel("Description");

		version.setBounds(55,10,100,30);
		name.setBounds(45,60,120,30);
		description.setBounds(55,90,120,60);

		pane.add(version);
		pane.add(name);
		pane.add(description);
	}

	public void initUI() {
		JPanel panel = new JPanel();
		this.createLayout();
		this.add(panel);
		this.setTitle("About");
		this.setSize(200,200);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
}
