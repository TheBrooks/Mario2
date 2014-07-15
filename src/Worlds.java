import java.io.*;
import java.util.*;
import javax.swing.*;

								
public class Worlds extends JComponent    //Collision detection can be done in here
{										 
	private WorldItem[][] blocks;
	private String level;
	private File f;
				//contains an array of enemies objects which has their location
	
	public static final int ABOVE = 1;
	public static final int RIGHT = 2;
	public static final int BELOW = 3;
	public static final int LEFT = 4;
	
	public Worlds(String str)
	{
		level = str;
	}
	
	public String getLevelName()
	{
		return level;
	}
	
	public void setDim(int h, int w)
	{
		blocks = new WorldItem[h][w];
	}
	
	public void add(int r, int c, WorldItem val)
	{
		blocks[r][c] = val;
	}
	
	public WorldItem get(int row, int col)//might be something other for boolean
	{
		return blocks[row][col];
	}
	
	
	
	public WorldItem collisionDetectionBlocks(Mario mario, int direction)
	{
		int marioLeft = mario.getLeftWorld();
 		int marioRight = marioLeft +  32; 		
 		int marioTop = mario.getTopWorld();
 		int marioBottom = mario.getBottomWorld();
 		
 		int marioCenterX = (marioLeft + marioRight)/2;
 		int marioCenterY = (marioTop + marioBottom)/2;
 		
 		boolean isBig = mario.isBig();
 		
		if(direction == Worlds.ABOVE)
		{
			WorldItem topRight =CollisionAboveBlock(marioCenterX , marioTop, false); //top Right
			WorldItem topLeft = CollisionAboveBlock(marioCenterX , marioTop, true); //top Left
			
			
			
			if(topRight != null)
				return topRight;
			return topLeft;
		}
		else if(direction == Worlds.RIGHT)
		{
			WorldItem rightBottom = CollisionRightBlock(marioRight , marioCenterY, true, isBig);
			WorldItem rightTop = CollisionRightBlock(marioRight , marioCenterY, false, isBig); 
			WorldItem rightCenter;
			if(isBig)
				rightTop = CollisionRightBlock(marioRight , (marioCenterY -30) , true, isBig);
			
			
			if(rightBottom != null)
				return rightBottom;
			if(rightTop != null)
				return rightTop;
			return rightTop;
		}
		else if(direction == Worlds.BELOW)
		{
			WorldItem bottomRight = CollisionBelowBlock(marioCenterX , marioBottom, false); 
			WorldItem bottomLeft = CollisionBelowBlock(marioCenterX , marioBottom, true); 
			
			
			
			if(bottomRight != null)
				return bottomRight;
			return bottomLeft;
		}
		else if(direction == Worlds.LEFT)
		{
			WorldItem leftBottom = CollisionLeftBlock(marioLeft , marioCenterY, true, isBig);
			WorldItem leftTop =CollisionLeftBlock(marioLeft , marioCenterY, false, isBig); 
			WorldItem leftCenter;
			if(isBig)
				leftCenter =CollisionLeftBlock(marioLeft , (marioCenterY-30), true, isBig); 
			
			if(leftBottom != null)
				return leftBottom;
			if(leftTop != null)
				return leftTop;
			return leftCenter;
		}
		
	}

	private WorldItem CollisionLeftBlock(int marioLeft, int marioCenterY, boolean b, boolean big)
	{
		int col = (marioLeft - 2)/32;
		int row;
		if(b)	 //true = leftBottom
		{
			if(big)
				row = ((marioCenterY + 30) / 32);
			else
				row = ((marioCenterY + 14) / 32);
		}
		else  // left top
		{
			if(big)
				row = ((marioCenterY - 30) / 32);
			else
				row = ((marioCenterY - 14) / 32);
		}
		try
		{
			return blocks[row][col];
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			return null;
		}
	}

	private WorldItem CollisionBelowBlock(int marioCenterX, int marioBottom, boolean b)  //false = bottomRight
	{
		int row = (marioBottom + 17)/32;
		int col = (marioCenterX + 14)/32;
		
		if(b)
			col = (marioCenterX - 14)/32;
		
		try
		{
			return blocks[row][col];
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			return null;
		}
	}

	private WorldItem CollisionRightBlock(int marioRight, int marioCenterY, boolean b, boolean big)
	{
		int col = (marioRight + 2)/32;
		int row;
		if(b)	 //true = leftBottom
		{
			if(big)
				row = ((marioCenterY + 30) / 32);
			else
				row = ((marioCenterY + 14) / 32);
		}
		else  // left top
		{
			if(big)
				row = ((marioCenterY - 30) / 32);
			else
				row = ((marioCenterY - 14) / 32);
		}
		try
		{
			return blocks[row][col];
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			return null;
		}
	}

	private WorldItem CollisionAboveBlock(int marioCenterX, int marioTop, boolean b)
	{	
		int row = (marioTop + 17)/32;
		int col = (marioCenterX + 14)/32;
		
		if(b)
			col = (marioCenterX - 14)/32;
		
		try
		{
			return blocks[row][col];
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			return null;
		}
		
	}
}
