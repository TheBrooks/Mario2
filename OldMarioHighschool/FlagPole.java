import java.awt.Image;
import java.awt.Component;
import java.io.*;
import java.util.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.*;


public class FlagPole extends Physical   //indestructables
{
	
	/**
	 *creates a Flagpole Object
	 *@param x the x position of the Physical
	 *@param y he y position of the Physical
	 *@param pic the picture of the Physical
	 *@param component the World component
	 *@param physicalID the number ID of the Physical
	 */
	public FlagPole(int x,int y, Image pic, Component component , int physicalID)
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
	public String physicalRightReact(Mario mario)  throws IOException
	{
		if(super.getPhase())
		{
			mario.addPoints(600 - mario.yTop);
			super.compo.WR.end = true;
			super.setPhase1(false);
			int currentWorld = compo.WR.getWorld();
			int currentLevel = compo.WR.getLevel();
			
			int newlevel = currentLevel+1;
					
			
			int size = super.compo.mario.getMarioSize();
			
			super.compo.mario.resetMario();
			super.compo.mario.setMarioSize(size);
			
			
				if(newlevel == 1)
			{
					InputStream yes = this.getClass().getClassLoader().getResourceAsStream("mario-1-1.GIF");
					InputStream is = new BufferedInputStream((yes));
					
    				super.compo.back.background =  ImageIO.read(is);
				
				 //original
			}
			if(newlevel == 2)
			{
				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("mario-1-1a.gif");
				InputStream is = new BufferedInputStream((yes));
					
				 super.compo.back.background =  ImageIO.read(is);
    				
			
			}
			if(newlevel == 3)
			{
				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("mario-1-2.gif");
				InputStream is = new BufferedInputStream((yes));
					
				 super.compo.back.background =  ImageIO.read(is);
				
				//background = ImageIO.read(new File("mario-1-2.gif"));
			}	
			if(newlevel == 4)
			{
				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("mario-1-2a.gif");
				InputStream is = new BufferedInputStream((yes));
					
				 super.compo.back.background =  ImageIO.read(is);
				
				//background = ImageIO.read(new File("mario-1-2a.gif"));
			}
			if(newlevel == 5)
			{
				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("mario-1-3.gif");
				InputStream is = new BufferedInputStream((yes));
					
				 super.compo.back.background =  ImageIO.read(is);
				
			//	background = ImageIO.read(new File("mario-1-3.gif"));
			}
			if(newlevel == 6)
			{
				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("mario-1-4.GIF");
				InputStream is = new BufferedInputStream((yes));
					
				 super.compo.back.background =  ImageIO.read(is);
				
			//	background = ImageIO.read(new File("mario-1-4.GIF"));  //1-4
			}
			if(newlevel == 7)
			{
				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("mario-2-1.GIF");
				InputStream is = new BufferedInputStream((yes));
					
				super.compo.back.background =  ImageIO.read(is);
				
			//	background = ImageIO.read(new File("mario-2-1.GIF")); // 2-1
			}
			if(newlevel == 8)
			{
				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("mario-2-2.gif");
				InputStream is = new BufferedInputStream((yes));
					
				super.compo.back.background =  ImageIO.read(is);
				
			//	background = ImageIO.read(new File("mario-2-2.gif")); // 2-2
			}
			if(newlevel == 9)
			{
				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("mario-2-4.GIF");
				InputStream is = new BufferedInputStream((yes));
					
				super.compo.back.background =  ImageIO.read(is);
				
			//	background = ImageIO.read(new File("mario-2-4.GIF")); // 2-4
			}
			if(newlevel == 10)
			{
				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("mario-3-1.GIF");
				InputStream is = new BufferedInputStream((yes));
					
				super.compo.back.background =  ImageIO.read(is);
				
			//	background = ImageIO.read(new File("mario-3-1.GIF")); //3-1
			}
			if(newlevel == 11)
			{
				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("mario-3-2.GIF");
				InputStream is = new BufferedInputStream((yes));
					
				super.compo.back.background =  ImageIO.read(is);
				
			//	background = ImageIO.read(new File("mario-3-2.GIF"));  //3-2
			}
			if(newlevel == 12)
			{
				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("mario-3-3.GIF");
				InputStream is = new BufferedInputStream((yes));
					
				super.compo.back.background =  ImageIO.read(is);
				
				//background = ImageIO.read(new File("mario-3-3.gif"));  // 3-3
			}
			if(newlevel == 13)
			{
				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("mario-3-4.GIF");
				InputStream is = new BufferedInputStream((yes));
					
				super.compo.back.background =  ImageIO.read(is);
				
			//	background = ImageIO.read(new File("mario-3-4.GIF")); // 3-4
			}
			if(newlevel == 14)
			{
				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("mario-4-1.GIF");
				InputStream is = new BufferedInputStream((yes));
					
				super.compo.back.background =  ImageIO.read(is);
				
				//background = ImageIO.read(new File("mario-4-1.GIF")); //4-1
			}
			if(newlevel == 15)
			{
				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("mario-1-1a.gif");
				InputStream is = new BufferedInputStream((yes));
					
				super.compo.back.background =  ImageIO.read(is);
				
				//background = ImageIO.read(new File("mario-1-1a.gif"));  // transition
			}
			if(newlevel == 16)
			{
				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("mario-4-2.GIF");
				InputStream is = new BufferedInputStream((yes));
					
				super.compo.back.background =  ImageIO.read(is);
				
				//background = ImageIO.read(new File("mario-4-2.GIF")); // 4-2
			}
			if(newlevel == 17)
			{
				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("mario-1-2a.gif");
				InputStream is = new BufferedInputStream((yes));
					
				super.compo.back.background =  ImageIO.read(is);
				
				//background = ImageIO.read(new File("mario-1-2a.gif")); // transition
			}
			if(newlevel == 18)
			{
				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("mario-4-3.bmp");
				InputStream is = new BufferedInputStream((yes));
					
				super.compo.back.background =  ImageIO.read(is);
				
			//	background = ImageIO.read(new File("mario-4-3.bmp"));  // 4-3
			}
			if(newlevel == 19)
			{
				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("mario-4-4.GIF");
				InputStream is = new BufferedInputStream((yes));
					
				super.compo.back.background =  ImageIO.read(is);
				
				//background = ImageIO.read(new File("mario-4-4.GIF")); // 4-4
			}
			if(newlevel == 20)
			{
				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("mario-5-1.GIF");
				InputStream is = new BufferedInputStream((yes));
					
				super.compo.back.background =  ImageIO.read(is);
				
			//background = ImageIO.read(new File("mario-5-1.GIF"));  // 5-1
			}
			if(newlevel == 21)
			{
				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("mario-5-2.GIF");
				InputStream is = new BufferedInputStream((yes));
					
				super.compo.back.background =  ImageIO.read(is);
				
				//background = ImageIO.read(new File("mario-5-2.GIF"));  // 5-2
			}
			if(newlevel == 22)
			{
				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("mario-goomba.GIF");
				InputStream is = new BufferedInputStream((yes));
					
				super.compo.back.background =  ImageIO.read(is);
				
				//super.compo.back.background = ImageIO.read(new File("mario-goomba.GIF"));  //level 0
			}
			if(newlevel == 23)
			{
				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("mario-suicide.GIF");
				InputStream is = new BufferedInputStream((yes));
					
				super.compo.back.background =  ImageIO.read(is);
				
				//super.compo.back.background = ImageIO.read(new File("mario-suicide.GIF"));  //StartOver
			}
			
			super.compo.WR = new WorldReader(super.compo, currentWorld, newlevel);	
			
			//System.exit(0);
			return "_";
		}
		return "_";
	}
	
		
	/**
	 *how the physcial reacts when mario hits it on his left
	 *@param mario the character hitting him
	 *@return what the physical turns into
	 */
	public String physicalLeftReact(Mario mario)
	{
		if(super.getPhase())
		{
			System.exit(0);
			return "_";
		}
		return "_";
	}
	
	/**
	 *how the physcial reacts when mario hits it on his head
	 *@param mario the character hitting him
	 *@return what the physical turns into
	 */
	public String physicalTopReact(Mario mario)//mario head hits the bottom
	{
		if(super.getPhase())
		{
			System.exit(0);
			return "_";
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
		if(super.getPhase())
		{
			System.exit(0);
			return "_";
		}
		return "_";
	}
	
	/**
	 *the String representation of the object
	 *@return the representation made
	 */
	public String toString()
	{
		return "Flagpole" + getRow() + " "+ getCol();
	}
}