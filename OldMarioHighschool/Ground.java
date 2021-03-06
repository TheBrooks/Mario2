import java.awt.Image;
import java.awt.Component;

public class Ground extends Physical   //indestructables
{
	/**
	 *creates a Goomba Object
	 *@param x the x position of the Physical
	 *@param y he y position of the Physical
	 *@param pic the picture of the Physical
	 *@param component the World component
	 *@param physicalID the number ID of the Physical
	 */
	public Ground(int x,int y, Image pic, Component component , int physicalID)
	{
		super(x, y, component, pic,  physicalID);
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
	public String physicalRightReact()
	{
		return "_";
	}
	
	/**
	 *how the physcial reacts when mario hits it on his left
	 *@param mario the character hitting him
	 *@return what the physical turns into
	 */
	public String physicalLeftReact()
	{
		return "_";
	}
	
		/**
	 *how the physcial reacts when mario hits it on his head
	 *@param mario the character hitting him
	 *@return what the physical turns into
	 */
	public String physicalTopReact()//mario head hits the bottom
	{
		return "_";
	}
	
	/**
	 *how the physcial reacts when mario hits it on his feet
	 *@param mario the character hitting him
	 *@return what the physical turns into
	 */
	public String physicalBottomReact()
	{
		return "_";
	}
	
	/**
	 *the String representation of the object
	 *@return the representation made
	 */
	public String toString()
	{
		return "Ground" + getRow() + " "+ getCol();
	}
	

}