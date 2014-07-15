import java.awt.Image;
import java.awt.Component;
import java.io.*;

public class Collectable extends Physical   
{
	public boolean Coin;
	public boolean Mushroom; 
	public boolean OneUp; 
	public boolean Flower; 
		
		/**
	 *creates a Collectable Object
	 *@param x the x position of the Physical
	 *@param y he y position of the Physical
	 *@param pic the picture of the Physical
	 *@param component the World component
	 *@param physicalID the number ID of the Physical
	 */
	public Collectable(int x,int y,Image pic ,Component component , int physicalID)
	{
		super(x, y,  component, pic, physicalID);
			
		if(physicalID == 23 ) 	
		{
			Coin = true;
			super.setPhase1(true);
		}			
		else if(physicalID == 29 )
		{
			Flower = true;
			super.setPhase1(true);
		}
		else if (physicalID == 25 )
		{
			Mushroom = true;
			super.setPhase1(true);
		}
		else if (physicalID == 27 )
		{
			OneUp = true;
			super.setPhase1(true);
		}
	}
	////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////
	/**the activity of the physical
	 *@return whether or not the physical is active
	 */
	public boolean active() //ignores them. yeahhhhhh
	{
		return super.getPhase();
	}
	
	/**
	 *how the physcial reacts when mario hits it on his right
	 *@param mario the character hitting him
	 *@return what the physical turns into
	 */
	public String physicalRightReact(Mario mario) throws IOException   //copy to all once it is correct
	{
		
		if(super.getPhase())
		{
			super.setPhase1(false);
			if(Coin)
			{
				mario.addCoin();
				mario.addPoints(100);
				return ".";
			}
			if(Flower)
			{
				int size = mario.getMarioSize();
				mario.setMarioSize(size + 1);
				mario.addPoints(500);
				return ".";
			}
			if(Mushroom)
			{
				int size = mario.getMarioSize();
				mario.setMarioSize(size + 1);
				mario.addPoints(250);
				return ".";
			}	
			if(OneUp)
			{
				
				mario.lifeChange(1);
				mario.addPoints(250);
				return ".";
			}	
		}
		return "_";
	}
	
	/**
	 *how the physcial reacts when mario hits it on his left
	 *@param mario the character hitting him
	 *@return what the physical turns into
	 */
	public String physicalLeftReact(Mario mario) throws IOException
	{
		if(super.getPhase())
		{
			super.setPhase1(false);
			if(Coin)
			{
				mario.addCoin();
				mario.addPoints(100);
				return ".";
			}
			if(Flower)
			{
				int size = mario.getMarioSize();
				mario.setMarioSize(size + 1);
				mario.addPoints(500);
				return ".";
			}
			if(Mushroom)
			{
				int size = mario.getMarioSize();
				mario.setMarioSize(size + 1);
				mario.addPoints(250);
				return ".";
			}
			if(OneUp)
			{
				
				mario.lifeChange(1);
				mario.addPoints(250);
				return ".";
			}		
		
		}
		return "_";
	}
	
		/**
	 *how the physcial reacts when mario hits it on his head
	 *@param mario the character hitting him
	 *@return what the physical turns into
	 */
	public String physicalTopReact(Mario mario) throws IOException//mario head hits the bottom
	{
		if(super.getPhase())
		{
			super.setPhase1(false);
			if(Coin)
			{
				mario.addCoin();
				mario.addPoints(100);
				return ".";
			}
			if(Flower)
			{
				int size = mario.getMarioSize();
				mario.setMarioSize(size + 1);
				mario.addPoints(500);
				return ".";
			}
			if(Mushroom)
			{
				int size = mario.getMarioSize();
				mario.setMarioSize(size + 1);
				mario.addPoints(250);
				return ".";
			}
			if(OneUp)
			{
				
				mario.lifeChange(1);
				mario.addPoints(250);
				return ".";
			}	
		
		}
		return "_";
	}
	
		/**
	 *how the physcial reacts when mario hits it on his feet
	 *@param mario the character hitting him
	 *@return what the physical turns into
	 */
	public String physicalBottomReact(Mario mario) throws IOException
	{
		if(super.getPhase())
		{
			super.setPhase1(false);
			if(Coin)
			{
				mario.addCoin();
				mario.addPoints(100);
				return ".";
			}
			if(Flower)
			{
				int size = mario.getMarioSize();
				mario.setMarioSize(size + 1);
				mario.addPoints(500);
				return ".";
			}
			if(Mushroom)
			{
				int size = mario.getMarioSize();
				mario.setMarioSize(size + 1);
				mario.addPoints(250);
				return ".";
			}
			if(OneUp)
			{
				
				mario.lifeChange(1);
				mario.addPoints(250);
				return ".";
			}		
		
		}
		return "_";
	}
	
}