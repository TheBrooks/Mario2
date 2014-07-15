import java.awt.Image;
import java.awt.Component;
import java.io.*;
import java.util.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.*;


public class Metal extends Physical   //indestructables
{
	public boolean QuestionCoin;
	public boolean MushroomCoin; 
	public boolean BronzeDead; 
	public boolean BronzeDeadI; 
	public boolean OneUp; 
	public boolean Star; 
	public boolean DeadSteel; 
		
	/**
	 *creates a Metal Object
	 *@param x the x position of the Physical
	 *@param y he y position of the Physical
	 *@param pic the picture of the Physical
	 *@param component the World component
	 *@param physicalID the number ID of the Physical
	 */	
	public Metal(int x,int y,Image pic ,Component component , int physicalID)
	{
		super(x, y,  component, pic, physicalID);
				


		if(physicalID == 7 ) 	
		{
			QuestionCoin = true;
			super.setPhase1(true);
		}			
		else if(physicalID == 9 )
		{
			MushroomCoin = true;
			super.setPhase1(true);
		}
		else if(physicalID == 11 )
		{
			BronzeDeadI = true;
			super.setPhase1(true);
		}
		else if(physicalID == 12 )
		{
			BronzeDead = true;
			super.setPhase1(true);
		}
		else if(physicalID == 13 )
		{
			OneUp = true;
			super.setPhase1(true);
		}
		/*else if(physicalID == 15 )
		{
			Star = true;
			super.setPhase1(true);
		}*/
		else if(physicalID == 17 )
		{
			DeadSteel = true;
			super.setPhase1(true);
		}
		else if(physicalID == 18 )
		{
			DeadSteel = true;
			super.setPhase1(true);
		}
			
	}
	////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////
	
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
	public String physicalTopReact(Mario mario) throws IOException
	{
		if(super.getPhase())
		{
			
			if(QuestionCoin)
			{
				mario.addCoin();
				mario.addPoints(100);
				super.setPhase1(false);
				return "I";
			}
			
			if(MushroomCoin)
			{
				super.setPhase1(false);
				if(mario.isBig())	
				{
					
						
					mario.addPoints(150);
					  	InputStream yes = this.getClass().getClassLoader().getResourceAsStream("flower.png");
						InputStream is = new BufferedInputStream((yes));
					
						BufferedImage pic =  ImageIO.read(is);
						
					//BufferedImage pic = ImageIO.read(new File("flower.png"));
					Collectable	phys = new Collectable((((super.compo.mario.xLeftWorld+16)/32)*32), ((((super.compo.mario.yTopWorld)/32)-1)*32) , pic, compo,     29     );
    					
    					phys.xLeftBound = this.xLeftBound;
    					phys.xRightBound = this.xRightBound;
    					phys.yTopBound = this.yTopBound-32;
    					phys.yBottomBound = this.yBottomBound-32;
    					
    					super.compo.WR.everywhere[(((super.compo.mario.yTopWorld)/32)-1)][((super.compo.mario.xLeftWorld+16)/32)] = phys;
    					super.compo.WR.everything.add(super.compo.WR.everything.size(), phys);
						phys.setIndex(super.compo.WR.everything.size());
						return "I";
				}
				else 
				{
					
					mario.addPoints(200);
					InputStream yes = this.getClass().getClassLoader().getResourceAsStream("Mushroom.png");
					InputStream is = new BufferedInputStream((yes));
					BufferedImage pict =  ImageIO.read(is);
					
					//BufferedImage pict = ImageIO.read(new File("Mushroom.png"));

					Collectable	phys = new Collectable((((super.compo.mario.xLeftWorld+16)/32)*32), ((((super.compo.mario.yTopWorld)/32)-1)*32) , pict, compo,     25    );
    				
    				phys .xLeftBound = this.xLeftBound;
    				phys .xRightBound = this.xRightBound;
    				phys .yTopBound = this.yTopBound-32;
    				phys .yBottomBound = this.yBottomBound-32;
    				
       				super.compo.WR.everywhere[(((super.compo.mario.yTopWorld)/32)-1)][((super.compo.mario.xLeftWorld+16)/32)] =  phys ;
    				super.compo.WR.everything.add(super.compo.WR.everything.size(), phys );
    				phys.setIndex(super.compo.WR.everything.size());
					
					return "I";
				}
			
			}
			
			if(BronzeDead)
			{
				return "_";
			}
			
			if(BronzeDeadI)
			{
				return "I";
			}
			
			if(OneUp)
			{
				super.setPhase1(false);
				mario.addPoints(500);
				
				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("OneUp.png");
				InputStream is = new BufferedInputStream((yes));
				BufferedImage pic =  ImageIO.read(is);
				
				//BufferedImage pic = ImageIO.read(new File("OneUp.png"));
				Collectable oneUp = new Collectable((((super.compo.mario.xLeftWorld+16)/32)*32), ((((super.compo.mario.yTopWorld)/32)-1)*32) , pic, compo,  27);
				
				oneUp .xLeftBound = this.xLeftBound;
    			oneUp .xRightBound = this.xRightBound;
    			oneUp .yTopBound = this.yTopBound-32;
    			oneUp .yBottomBound = this.yBottomBound-32;
    			oneUp .setIndex(super.compo.WR.everything.size());
    			
    			super.compo.WR.everywhere[(((super.compo.mario.yTopWorld)/32)-1)][((super.compo.mario.xLeftWorld+16)/32)] = oneUp ;
    			super.compo.WR.everything.add(super.compo.WR.everything.size(), oneUp );

				return "I";
			}
			
			/*if(Star)
			{
				super.setPhase1(false);
				mario.addPoints(150);
				//BufferedImage pic = ImageIO.read(new File("Star.png"));
				//MovingCollectable star = new MovingCollectable(((((super.compo.mario.xLeftWorld+16)/32)*32), ((((super.compo.mario.yTopWorld)/32)-1)*32) , pic, compo,  ??));
				//star .xLeftBound = this.xLeftBound;
    				//star .xRightBound = this.xRightBound;
    				//star .yTopBound = this.yTopBound-32;
    				//star .yBottomBound = this.yBottomBound-32;
    				//star .setIndex(super.compo.WR.everything.size());
    				//super.compo.WR.everywhere[(((super.compo.mario.yTopWorld)/32)-1)][((super.compo.mario.xLeftWorld+16)/32)] = star ;
    				//super.compo.WR.everything.add(super.compo.WR.everything.size(), star );


				return "I";
			}*/
			
			if(DeadSteel)
			{
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
