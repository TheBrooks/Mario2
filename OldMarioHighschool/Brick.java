import java.awt.Image;
import java.awt.Component;

public class Brick extends Physical 
{
	public boolean UpperWorld;
	public boolean LowerWorld; 
		
	/**
	 *creates a Brick Object
	 *@param x the x position of the Physical
	 *@param y he y position of the Physical
	 *@param pic the picture of the Physical
	 *@param component the World component
	 *@param physicalID the number ID of the Physical
	 */
	public Brick(int x,int y, Image pic, Component component, int physicalID)
	{
		super(x, y, component, pic, physicalID);
		
		if(physicalID == 19 ) 		
		{
			UpperWorld = true;
			super.setPhase1(true);
		}
		
		if(physicalID == 20)
		{
			LowerWorld = true;
			super.setPhase1(true);
		}
		
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
	public String physicalRightReact(Mario mario) 
	{
		return "_";
	}
	
	/**
	 *how the physcial reacts when mario hits it on his left
	 *@param mario the character hitting him
	 *@return what the physical turns into
	 */
	public String physicalLeftReact(Mario mario)
	{
		return "_";
	}
	
	/**
	 *how the physcial reacts when mario hits it on his head
	 *@param mario the character hitting him
	 *@return what the physical turns into
	 */
	public String physicalTopReact(Mario mario) 
	{
		if(super.getPhase())
		{
			
			if(mario.isBig())
			{
				super.setPhase1(false);
				((World)super.compo).back.collisionU = true;
				
				return ".";
							
			}
			else
			{
				mario.addPoints(100);
				return "_";
			}
		}
	
		return "_";
	}
	
	/**
	 *how the physcial reacts when mario hits it on his feet
	 *@param mario the character hitting him
	 *@return what the physical turns into
	 */
	public String physicalBottomReact(Mario mario)
	{
		return "_";
	}
	
	
	
	
}
