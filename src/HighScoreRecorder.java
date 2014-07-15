import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;


public class HighScoreRecorder implements Serializable
{
	
	public HighScoreSpot[] ranker;
	public String currentPlayer;
	
	public static final int TOP_SCORE_LENGTH = 10;
	
	public HighScoreRecorder() throws IOException, ClassNotFoundException
	{
		currentPlayer = "Anonymous";
		ranker = new HighScoreSpot[TOP_SCORE_LENGTH];
		for(int i = 0; i < TOP_SCORE_LENGTH; i++)
		{
			ranker[i] = new HighScoreSpot();
		}
		load();
	}
	
	public void save() throws IOException
	{
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("/Users/dootfiddle/Desktop/Mario 2.0/saveFile.dat"));
		out.writeObject(ranker);
		out.close();
	}
	
	public void load() throws IOException, ClassNotFoundException
	{
		try
		{
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("/Users/dootfiddle/Desktop/Mario 2.0/saveFile.dat"));
			ranker = (HighScoreSpot[])in.readObject();
			in.close();
		}
		catch(FileNotFoundException e)
		{}
		catch(EOFException e)
		{}
	}
	
	public int getScore(int i)
	{
		return ranker[i].score;
	}
	public String getScorer(int i)
	{
		return ranker[i].player;
	}
	
	public void assignPlayer(String str)
	{
		currentPlayer = str;
	}
	
	public void addFinalScore(int score) throws IOException
	{
		HighScoreSpot newScore = new HighScoreSpot(currentPlayer, score);
		int newSpot = TOP_SCORE_LENGTH;
		
		for(int i = HighScoreRecorder.TOP_SCORE_LENGTH -1; i >=0; i--)
		{
			if(score > ranker[i].score)
			{
				newSpot--;
			}	
		}

		for(int i = HighScoreRecorder.TOP_SCORE_LENGTH -1; i > newSpot; i--)
		{
			ranker[i] = ranker[i-1];
		}
		ranker[newSpot] = newScore;
		
		save();
	}
	
	public void reset() throws IOException
	{
		for(int i = 0; i < TOP_SCORE_LENGTH; i++)
		{
			ranker[i] = new HighScoreSpot();
		}
		save();
	}
	
	public String toString()
	{
		for(int i = 0; i < HighScoreRecorder.TOP_SCORE_LENGTH; i++)
		{
			System.out.println(ranker[i].player + " " + ranker[i].score);
		}
		System.out.println();
		return "";
	}
	
}

class HighScoreSpot implements Serializable 
{
	
	public int score;
	public String player;
	
	public HighScoreSpot()
	{
		score = 0;
		player = "Empty";
	}
	
	public HighScoreSpot(String str, int i)
	{
		score = i;
		player = str;
	}
	
	
	
}


