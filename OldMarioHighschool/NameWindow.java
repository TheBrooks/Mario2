//	Daniel Huang

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class NameWindow extends JFrame implements ActionListener
{
	JTextField name;
	JLabel nameLabel;
	JButton save;

	private MapDraft MD;

	/*constructs a GUI that allows the player to enter a name
	 *@param game the high scores system that creates this
	 */
	public NameWindow(MapDraft game)
	{
		MD = game;

		setSize(400,100);
		setLocation(500,500);
		setTitle("enter your name before you die");
		name = new JTextField(20);
		nameLabel = new JLabel("enter your name");
		save = new JButton("save");
		name.addActionListener(this);
		save.addActionListener(this);

		setLayout(new GridLayout(2,1));
		add(name);
		add(save);
	}
	
	/*makes the button save the player's name and closes the window
	 *@param e the save button
	 */
	public void actionPerformed(ActionEvent e)
	{
		String actionCommand = e.getActionCommand();
		if (e.getSource() instanceof JButton)
		{
			MD.setPlayer(name.getText());
			dispose();// make window go away
		}
	}
}