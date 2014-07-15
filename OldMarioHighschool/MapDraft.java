//	Daniel Huang

import java.util.*;
import java.io.*;

public class MapDraft
{
	Map<String, ArrayList<Integer>> files;
	String currentPlayer;
	ArrayList<Integer> topScores;
	ArrayList<String> topScorers;
	
	/*constructs the map to keep track of players' scores
	 */
	public MapDraft() throws IOException, ClassNotFoundException
	{
		files = new HashMap<String, ArrayList<Integer>>();
		currentPlayer = null;
		topScores = new ArrayList<Integer>();
		topScorers = new ArrayList<String>();
		load();
		new NameWindow(this).setVisible(true);
	}
	
	/*saves the player's name
	 *@param name the player's name
	 */
	public void setPlayer(String name)
	{
		currentPlayer = name;
		ArrayList<Integer> old = files.put(currentPlayer,new ArrayList<Integer>());
		if(old != null)
		{
			for(Integer i:old)
				addScore(i);
		}
	}
	
	/*adds the latest score to the current player's scores
	 *@param i the latest score
	 */
	public void addScore(int i)
	{
		if(files.get(currentPlayer).size() == 0 || files.get(currentPlayer).get(files.get(currentPlayer).size() - 1) > i)
			files.get(currentPlayer).add(i);
		else
		{
			int key = 0;
			while(files.get(currentPlayer).get(key) > i)
				key ++;
			files.get(currentPlayer).add(key,i);
		}
		
	}
	
	/*synchronizes the arrays to the map
	 */
	public void mapToArrays()
	{
		topScores = new ArrayList<Integer>();
		topScorers = new ArrayList<String>();
		for(String player:files.keySet())
		{
			for(Integer i:files.get(player))
			{
				if(topScores.size() == 0 || topScores.get(topScores.size() - 1) > i)
				{
					topScores.add(i);
					topScorers.add(player);
				}
				else
				{
					int key = 0;
					while(topScores.get(key) > i)
						key ++;
					topScores.add(key,i);
					topScorers.add(key,player);
				}
			}
		}
	}
	
	/*synchronizes the map to the arrays
	 */
	public /*Map<String, ArrayList<Integer>>*/ void arraysToMap()
	{
		files = new HashMap<String, ArrayList<Integer>>();
		for(int x = 0;x < topScores.size();x++)
		{
			setPlayer(topScorers.get(x));
			addScore(topScores.get(x));
		}
		//return files;
	}
	
	/*creates the high score window
	 */
	public void highScores()
	{
		mapToArrays();		
		new HighScoreWindow(topScores,topScorers).setVisible(true);
	}
	
	/*saves the high scores
	 */
	public void save() throws IOException
	{
		mapToArrays();
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("saveA.dat"));
		out.writeObject(topScores);
		out = new ObjectOutputStream(new FileOutputStream("saveB.dat"));
		out.writeObject(topScorers);
	}
	
	/*loads the high scores
	 */
	public void load() throws /**/IOException, ClassNotFoundException
	{
		try
		{
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("saveA.dat"));
			topScores = (ArrayList<Integer>) in.readObject();
			in = new ObjectInputStream(new FileInputStream("saveB.dat"));
			topScorers = (ArrayList<String>) in.readObject();
			arraysToMap();
		}
		catch(FileNotFoundException e)
		{
			
		}
	}
	
//	public void reset()
//	{
//		files = new HashMap<String, ArrayList<Integer>>();
//		topScores = new ArrayList<Integer>();
//		topScorers = new ArrayList<String>();
//		save();
//	}
}