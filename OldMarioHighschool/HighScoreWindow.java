//	Daniel Huang

import java.util.*;
import javax.swing.*;
import java.awt.*;

public class HighScoreWindow extends JFrame
{
	ArrayList<Integer> topScores;
	ArrayList<String> topScorers;
	private JTextPane scores;
	private JTextPane scorers;
	private GridLayout layout;
	
	/*constructs the high score GUI
	 *@param es the list of high scores
	 *@param ers the list of players that corresponds to the scores
	 */
	public HighScoreWindow(ArrayList<Integer> es, ArrayList<String> ers)
	{
		topScores = es;
		topScorers = ers;
		scores = new JTextPane();
		scorers = new JTextPane();
		
		setSize(300,500);
		setTitle("high scores");
		
		setLayout(new GridLayout(1,2));
		add(scores);
		add(scorers);
		
		for(Integer i:topScores)
			scores.setText(scores.getText() + "\n" + i);
		for(String n:topScorers)
			scorers.setText(scorers.getText() + "\n" + n);
		
	}
}