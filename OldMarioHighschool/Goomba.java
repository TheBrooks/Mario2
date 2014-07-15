import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class Goomba extends Physical
{
	
	/**
	 *creates a Goomba Object
	 *@param x the x position of the Physical
	 *@param y he y position of the Physical
	 *@param pic the picture of the Physical
	 *@param component the World component
	 *@param physicalID the number ID of the Physical
	 */
	public Goomba(int x,int y,Image pic,  Component component , int physicalID)
	{
		super(x, y,  component, pic, physicalID);
		
		super.setPhase1(true);
	}
	
	/**the activity of the physical
	 *@return whether or not the physical is active
	 */
	public boolean active()
	{
		return super.getPhase();
	}
	
	/**
	 *how the physcial reacts when mario hits it on his right
	 *@param mario the character hitting him
	 *@return what the physical turns into
	 */
	public String physicalRightReact(Mario mario)  throws IOException, ClassNotFoundException
	{
		
		int size = mario.getMarioSize();
		if(size == 1 || size == 2)
		{
			mario.setMarioSize(0);	
		}
		else
		{
			mario.kill();
		}
			
		super.setPhase1(false);	
		return ".";
	}
	
	/**
	 *how the physcial reacts when mario hits it on his left
	 *@param mario the character hitting him
	 *@return what the physical turns into
	 */
	public String physicalLeftReact(Mario mario)  throws IOException, ClassNotFoundException
	{
		
		int size = mario.getMarioSize();
		if(size == 1 || size == 2)
		{
			mario.setMarioSize(0);
		}
		else
		{
			mario.kill();
		}
		
		super.setPhase1(false);		
		return ".";
	}
	
	/**
	 *how the physcial reacts when mario hits it on his head
	 *@param mario the character hitting him
	 *@return what the physical turns into
	 */
	public String physicalTopReact(Mario mario)  throws IOException, ClassNotFoundException
	{
		if(phase1)
		{
			int size = mario.getMarioSize();
			if(size == 1 || size == 2)
			{
				mario.setMarioSize(0);
			}
			else
			{
				mario.kill();
			}
				
		}
	
		super.setPhase1(false);		
		return ".";
	}
	
	/**
	 *how the physcial reacts when mario hits it on his feet
	 *@param mario the character hitting him
	 *@return what the physical turns into
	 */
	public String physicalBottomReact(Mario mario)
	{
	
		mario.addPoints(150);
		super.setPhase1(false);	
		
		return ".";
	}
	
}

/**
	 *the String representation of the object
	 *@return the representation made
	 */