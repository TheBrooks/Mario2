import java.awt.*;
import java.awt.image.BufferedImage;


public class Moveable 
{
	
	 
    	public int physId;
    	public int indexNum;
    	
    	public int speed ;
	public boolean left;
    	
    	public int xLeftBound;
    	public int xRightBound;
    	public int yTopBound;
    	public int yBottomBound;
	public int xCenter;

	public int xLeftWorld;
    	public int xRightWorld;
    	    	
    	public boolean inScreen; 
    	public boolean active;
    	   	
       public boolean isFalling;
       public int lastRow;
       public int lastCol;
   			
   //True if in 4 of Screen's right or left bound
   	public boolean inMind;
   		
   	//Image of the physical
   	public World compo;
   	   		
   	public int step;  
   	public Image step1;
   	public Image step2;
   	
	public boolean dead;
		
	public int row;
	public int col;
	public int pauseStep;


	public boolean phase1;


	public boolean collisionL;
	public boolean collisionR;
	public boolean collisionD;

   		
   		
  /////////////////HAve to make a method where it contunially updates it's everywhere[][] position. 		
    		
    public Moveable(int x, int y, Component component, Image pic1, int ID) 
    {
    	lastRow = ((y + 16)/32);
    	lastCol = ((x + 16)/32);
    		
    	compo = (World)component;
	
    	xLeftWorld = x;
    	xRightWorld = x +32;
    	yTopBound = y;
    	yBottomBound = y + 32;

		xCenter = x + 16;    	

		xLeftBound = x- compo.back.xPos;

    	inScreen = false;
    	inMind = false;
    	
    
    	step = 0;
    	isFalling = false;
    	active = false;
    	dead = false;
    	
    	step1 = pic1;
    	    	
	
    	collisionD = true;
		pauseStep = 0;
    }
    
     public void setDead(boolean value)
   	 {
   	 	dead = value;
   	 }
    
   	 public int getRow()
   	 {
   	 	return (xLeftWorld + 16)/32;
   	 }
   	 public int getCol()
   	 {
   	 	return (yTopBound + 16)/32;
   	 }  
   	 	
   	 public void setStep2(BufferedImage pic) 
   	 {
   	 	step2 = pic;
   	 }
   	 	
   	 public void setIndex(int i)
   	 {
   	 	indexNum = i;
   	 }
   	 public int getIndex()
   	 {
    		return indexNum;
    	}
	public boolean getPhase()
	{
		return phase1;
	}
	public void setPhase1(boolean y)
	{
		phase1= y;
	}
    	
    
    
    /////////////////////////////////////////////////////
    /////////////////////////////////////////////////////
    public String moveableRightReact(Mario mario)
    {
    	return "_";
    }
     public String moveableTopReact(Mario mario)
    {
    	return "_";
    }
     public String moveableLeftReact(Mario mario)
    {
    	return "_";
    }
     public String moveableBottomReact(Mario mario)
    {
    	return "_";
    }
    /////////////////////////////////////////////////////	
    /////////////////////////////////////////////////////	
    public void ScrollingAwareness(int velocity)
    {
    	
    	xLeftBound = xLeftBound+velocity;
    	xRightBound = xLeftBound+velocity;
    	
    	
    	//setting boolean if in or out of screen
    	if(xRightBound > Constants.inScreenRight || xLeftBound < Constants.inScreenLeft)
    	{
    		inScreen = true;
    	}
    	else
    	{
    		inScreen = false;
    	}
    	
    	//setting boolean if in or out of the games mind
    	if(xRightBound > Constants.inMindRight || xLeftBound < Constants.inMindLeft)
    	{
    		if(!inMind)
    			inMind = true;
    		if(!active)
    			activate();
     	}
    	else
    	{
    		if(inMind)
    			inMind = false;
    		if(active)
    			deactivate();
    	}
    	
    	
    }
    
    public boolean fallenToDeath()
	{
		if(yTopBound > 480)	
		{
			deactivate();
			setDead(true);
			phase1 = false;
			return true;
			
		}
		return false;

	}
    
    /////////////////////////////////GETTERS
    public int getXLeftBound()
    {
    	return xLeftBound;
    }
	public int getXCenter()
    {
    	return xCenter;
    }

    public int getXRightBound()
    {
    	return xRightBound;
    }
    public int getYTopBound()
    {
    	return yTopBound;
    }
    public int getYBottomBound()
    {
    	return yBottomBound;
    }
    public boolean getInScreen()
    {
    	return inScreen;
    }
    public boolean getInMind()
    {
    	return inMind;
    }

	public int getLeftWorld()
	{
	return xLeftWorld;
	}
	

	public boolean getStatus()
{
	return active;
}
 	
 	////////////////////////////////////////////////////////////////
 	public void move()
	{
		if(active)
		{
			
			compo.WR.everywhere[lastRow][lastCol] = null;
			compo.WR.everywhere[getRow()][getCol()] = this;
			lastCol = getCol();
			lastRow = getRow();
			
		
			xLeftBound = xLeftWorld - compo.back.xPos;
			if(!left)
			{
				xLeftWorld += speed;
				xRightWorld += speed;
				pauseStep++;
				
				if(pauseStep % 15 == 0)
				{
					if(step == 1)  // sets the picture every step
					{
						step = 0;
					}
					else
						step++;
				}
				pauseStep++;
						
				left = false;
				
			}
			else if(left)
			{
				xLeftWorld -= speed;
				xRightWorld -= speed;
				
				if(pauseStep % 15 == 0)
				{

					if(step == 1)
					{
						step = 0;
					}
					else
						step++;
					}
				pauseStep++;
				
					
				
				
				left = true;
				
				
				
			}
			
			
			if(isFalling && !collisionD)  
			{
				yTopBound += speed;
				yBottomBound += speed;
			}
		}
		
		
		
	}
	
	public void activate()
	{
		active = true;
		xLeftBound = xLeftWorld - compo.back.xPos;
		
	}
	
	public void deactivate()
	{ 
		active = false;
	}
	
	
	public void scrollRight(int ScreenVelocity)
	{
		if(ScreenVelocity >= 0)
		{
			xLeftBound += ScreenVelocity;
			xRightBound += ScreenVelocity;
		}
	
	}

	public void destroy()
	{
	compo.WR.everything.remove(indexNum);
	}
	
	
   	//////////////////////////////////painting the image
   	public void physicalPaint(Graphics g)	
   	{
   		if(inScreen)
   		{
   			if(!dead)
   			{
   				if(step == 0)
	   			{
	   				g.drawImage(step1, xLeftBound, yTopBound, compo);
	   			}
	   			else if(step == 1)
	   			{
	   				g.drawImage(step2, xLeftBound, yTopBound, compo);
	   			}
			}
     		}
   	}
}

   		