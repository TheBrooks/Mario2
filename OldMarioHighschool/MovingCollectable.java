import java.awt.Image;
import java.awt.Component;

public class MovingCollectable extends Moveable   //PROBABLE CHANGE THIS BECAUSE THEY ARE MOVEABLE
{

	public boolean Mushroom; 
	public boolean OneUp; 
		
	
	public MovingCollectable(int x,int y,Image pic ,Component component , int physicalID)
	{
		super(x, y,  component, pic, physicalID);
		
			
		if(physicalID == 25 )
		{
			Mushroom = true;
			super.setPhase1(true);
		}
		else if(physicalID == 27 )
		{
			OneUp = true;
			super.setPhase1(true);
		}
		
	}
	
	public boolean active()
	{
		return super.getPhase();
	}
	
	
	public String moveableRightReact(Mario mario)   //copy to all once it is correct
	{
		
		
		if(super.getPhase())
		{
			super.setPhase1(false);
			if(Mushroom)
			{
				int size = mario.getMarioSize();
				if(size == 0)
					mario.setMarioSize(1);
				
				mario.addPoints(200);
				
				return ".";
			
			}
			if(OneUp)
			{
				mario.lifeChange(1);
				mario.addPoints(1000);
				return ".";
			}
		}
		return "_";
	}
	
	public String moveableLeftReact(Mario mario)
	{
		if(super.getPhase())
		{
			super.setPhase1(false);
			if(Mushroom)
			{
				int size = mario.getMarioSize();
				if(size == 0)
					mario.setMarioSize(1);
				
				mario.addPoints(200);
				
				return ".";
			
			}
			if(OneUp)
			{
				mario.lifeChange(1);
				mario.addPoints(1000);
				return ".";
			}
		}
		return "_";

	}
	
	public String moveableTopReact(Mario mario)//mario head hits the bottom
	{
		if(super.getPhase())
		{
			super.setPhase1(false);
			if(Mushroom)
			{
				int size = mario.getMarioSize();
				if(size == 0)
					mario.setMarioSize(1);
				
				mario.addPoints(200);
				
				return ".";
			
			}
			if(OneUp)
			{
				mario.lifeChange(1);
				mario.addPoints(1000);
				return ".";
			}
		}
		return "_";

	}
	
	public String moveableBottomReact(Mario mario)
	{
		if(super.getPhase())
		{
			super.setPhase1(false);
			if(Mushroom)
			{
				int size = mario.getMarioSize();
				if(size == 0)
					mario.setMarioSize(1);
				
				mario.addPoints(200);
				
				return ".";
			
			}
			if(OneUp)
			{
				mario.lifeChange(1);
				mario.addPoints(1000);
				return ".";
			}
		}
		return "_";

	}
	
}
