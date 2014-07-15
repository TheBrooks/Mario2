import javax.swing.JComponent;
import java.awt.*;
import java.io.*;
import java.awt.Graphics;
import javax.imageio.ImageIO;
import java.io.*;
import java.awt.image.BufferedImage;
import javax.swing.JComponent;

public class World extends JComponent
{
	Background back;
	WorldReader WR;
	Mario mario;
	MapDraft highScore;
	
	/**
	 *makes a new world object that makes everything needed to run mario
	 *
	 */
	public World()  throws IOException, ClassNotFoundException
	{
						
		back = new Background(this, "mario-1-0.GIF");
		WR = new WorldReader(this, 1,0);
		mario = new Mario(40,40,this,3,0,0);
		
		
		highScore = new MapDraft();
		highScore.load();
		
		
		InputStream yes = this.getClass().getClassLoader().getResourceAsStream("DeadLeft.png");
		InputStream is = new BufferedInputStream((yes));
					
		BufferedImage DeadLeftLittle =  ImageIO.read(is);
				
	  //BufferedImage DeadLeftLittle = ImageIO.read(new File("DeadLeft.png"));
	  	yes = this.getClass().getClassLoader().getResourceAsStream("DeadRight.png");
		is = new BufferedInputStream((yes));
					
		BufferedImage DeadRightLittle =  ImageIO.read(is);
		
	  //BufferedImage DeadRightLittle = ImageIO.read(new File("DeadRight.png"));
	  	yes = this.getClass().getClassLoader().getResourceAsStream("JumpLeft.png");
		is = new BufferedInputStream((yes));
					
		BufferedImage JumpLeftLittle =  ImageIO.read(is);
		
	  //BufferedImage JumpLeftLittle = ImageIO.read(new File("JumpLeft.png"));
	  	yes = this.getClass().getClassLoader().getResourceAsStream("JumpRight.png");
		is = new BufferedInputStream((yes));
					
		BufferedImage JumpRightLittle =  ImageIO.read(is);
		
	  //BufferedImage JumpRightLittle = ImageIO.read(new File("JumpRight.png"));
	  	yes = this.getClass().getClassLoader().getResourceAsStream("StandingLeft.png");
		is = new BufferedInputStream((yes));
					
		BufferedImage StandingLeftLittle =  ImageIO.read(is);
		
	  //BufferedImage StandingLeftLittle = ImageIO.read(new File("StandingLeft.png"));
	  	yes = this.getClass().getClassLoader().getResourceAsStream("StandingRight.png");
		is = new BufferedInputStream((yes));
					
		BufferedImage StandingRightLittle =  ImageIO.read(is);
		
	  //BufferedImage StandingRightLittle = ImageIO.read(new File("StandingRight.png"));
	  	yes = this.getClass().getClassLoader().getResourceAsStream("StepLeft1.png");
		is = new BufferedInputStream((yes));
					
		BufferedImage StepLeft1Little =  ImageIO.read(is);
		
	  //BufferedImage StepLeft1Little = ImageIO.read(new File("StepLeft1.png"));
	  	yes = this.getClass().getClassLoader().getResourceAsStream("StepLeft2.png");
		is = new BufferedInputStream((yes));
					
		BufferedImage StepLeft2Little =  ImageIO.read(is);
		
	  //BufferedImage StepLeft2Little = ImageIO.read(new File("StepLeft2.png"));
	  	yes = this.getClass().getClassLoader().getResourceAsStream("StepLeft3.png");
		is = new BufferedInputStream((yes));
					
		BufferedImage StepLeft3Little =  ImageIO.read(is);
		
	  //BufferedImage StepLeft3Little = ImageIO.read(new File("StepLeft3.png"));
	  	yes = this.getClass().getClassLoader().getResourceAsStream("StepLeft2.png");
		is = new BufferedInputStream((yes));
					
		BufferedImage StepLeft4Little =  ImageIO.read(is);
		
	  //BufferedImage StepLeft4Little = ImageIO.read(new File("StepLeft2.png"));
	  	yes = this.getClass().getClassLoader().getResourceAsStream("StepRight1.png");
		is = new BufferedInputStream((yes));
					
		BufferedImage StepRight1Little =  ImageIO.read(is);
		
	  //BufferedImage StepRight1Little = ImageIO.read(new File("StepRight1.png"));
	  	yes = this.getClass().getClassLoader().getResourceAsStream("StepRight2.png");
		is = new BufferedInputStream((yes));
					
		BufferedImage StepRight2Little =  ImageIO.read(is);
		
	  //BufferedImage StepRight2Little = ImageIO.read(new File("StepRight2.png"));
	  	yes = this.getClass().getClassLoader().getResourceAsStream("StepRight3.png");
		is = new BufferedInputStream((yes));
					
		BufferedImage StepRight3Little =  ImageIO.read(is);
		
	  //BufferedImage StepRight3Little = ImageIO.read(new File("StepRight3.png"));
	  	yes = this.getClass().getClassLoader().getResourceAsStream("StepRight2.png");
		is = new BufferedInputStream((yes));
					
		BufferedImage StepRight4Little =  ImageIO.read(is);
		
	  //BufferedImage StepRight4Little = ImageIO.read(new File("StepRight2.png"));
	  	yes = this.getClass().getClassLoader().getResourceAsStream("BigDuckLeft.png");
		is = new BufferedInputStream((yes));
					
		BufferedImage DuckLeftBig =  ImageIO.read(is);
		
	  //BufferedImage DuckLeftBig = ImageIO.read(new File("BigDuckLeft.png"));
	  	yes = this.getClass().getClassLoader().getResourceAsStream("BigDuckRight.png");
		is = new BufferedInputStream((yes));
					
		BufferedImage DuckRightBig =  ImageIO.read(is);
		
	  //BufferedImage DuckRightBig = ImageIO.read(new File("BigDuckRight.png"));
	  	yes = this.getClass().getClassLoader().getResourceAsStream("BigJumpLeft.png");
		is = new BufferedInputStream((yes));
					
		BufferedImage JumpLeftBig =  ImageIO.read(is);
		
	  //BufferedImage JumpLeftBig = ImageIO.read(new File("BigJumpLeft.png"));
	  	yes = this.getClass().getClassLoader().getResourceAsStream("BigJumpRight.png");
		is = new BufferedInputStream((yes));
					
		BufferedImage JumpRightBig =  ImageIO.read(is);
		
	  //BufferedImage JumpRightBig = ImageIO.read(new File("BigJumpRight.png"));
	  	yes = this.getClass().getClassLoader().getResourceAsStream("BigStandingLeft.png");
		is = new BufferedInputStream((yes));
					
		BufferedImage StandingLeftBig =  ImageIO.read(is);
	  //BufferedImage StandingLeftBig = ImageIO.read(new File("BigStandingLeft.png"));
	  	yes = this.getClass().getClassLoader().getResourceAsStream("BigStandingRight.png");
		is = new BufferedInputStream((yes));
					
		BufferedImage StandingRightBig =  ImageIO.read(is);
	  //BufferedImage StandingRightBig = ImageIO.read(new File("BigStandingRight.png"));
	  	yes = this.getClass().getClassLoader().getResourceAsStream("BigStepLeft1.png");
		is = new BufferedInputStream((yes));
					
		BufferedImage StepLeft1Big =  ImageIO.read(is);
	  //BufferedImage StepLeft1Big = ImageIO.read(new File("BigStepLeft1.png"));
	 	 yes = this.getClass().getClassLoader().getResourceAsStream("BigStepLeft2.png");
		is = new BufferedInputStream((yes));
					
		BufferedImage StepLeft2Big =  ImageIO.read(is);
	  //BufferedImage StepLeft2Big = ImageIO.read(new File("BigStepLeft2.png"));
	   	yes = this.getClass().getClassLoader().getResourceAsStream("BigStepLeft3.png");
		is = new BufferedInputStream((yes));
					
		BufferedImage StepLeft3Big =  ImageIO.read(is);
	  //BufferedImage StepLeft3Big = ImageIO.read(new File("BigStepLeft3.png"));
	  	yes = this.getClass().getClassLoader().getResourceAsStream("BigStepLeft2.png");
		is = new BufferedInputStream((yes));
					
		BufferedImage StepLeft4Big =  ImageIO.read(is);
	  //BufferedImage StepLeft4Big = ImageIO.read(new File("BigStepLeft2.png"));
	  	yes = this.getClass().getClassLoader().getResourceAsStream("BigStepRight1.png");
		is = new BufferedInputStream((yes));
					
		BufferedImage StepRight1Big =  ImageIO.read(is);
		
	  //BufferedImage StepRight1Big = ImageIO.read(new File("BigStepRight1.png"));
	 	 yes = this.getClass().getClassLoader().getResourceAsStream("BigStepRight2.png");
		is = new BufferedInputStream((yes));
					
		BufferedImage StepRight2Big =  ImageIO.read(is);
	  //BufferedImage StepRight2Big = ImageIO.read(new File("BigStepRight2.png"));
	  	yes = this.getClass().getClassLoader().getResourceAsStream("BigStepRight3.png");
		is = new BufferedInputStream((yes));
					
		BufferedImage StepRight3Big =  ImageIO.read(is);
	  //BufferedImage StepRight3Big = ImageIO.read(new File("BigStepRight3.png"));
	  	yes = this.getClass().getClassLoader().getResourceAsStream("BigStepRight2.png");
		is = new BufferedInputStream((yes));
					
		BufferedImage StepRight4Big =  ImageIO.read(is);
	  //BufferedImage StepRight4Big = ImageIO.read(new File("BigStepRight2.png"));
	  	yes = this.getClass().getClassLoader().getResourceAsStream("FlowerDuckLeft.png");
		is = new BufferedInputStream((yes));
					
		BufferedImage DuckLeftFlower =  ImageIO.read(is);
	  //BufferedImage DuckLeftFlower = ImageIO.read(new File("FlowerDuckLeft.png"));
	  	yes = this.getClass().getClassLoader().getResourceAsStream("FlowerDuckRight.png");
		is = new BufferedInputStream((yes));
					
		BufferedImage DuckRightFlower =  ImageIO.read(is);
	  //BufferedImage DuckRightFlower = ImageIO.read(new File("FlowerDuckRight.png"));
	   	yes = this.getClass().getClassLoader().getResourceAsStream("FlowerJumpLeft.png");
		is = new BufferedInputStream((yes));
					
		BufferedImage JumpLeftFlower =  ImageIO.read(is);
	  //BufferedImage JumpLeftFlower = ImageIO.read(new File("FlowerJumpLeft.png"));
	   	yes = this.getClass().getClassLoader().getResourceAsStream("FlowerJumpRight.png");
		is = new BufferedInputStream((yes));
					
		BufferedImage JumpRightFlower =  ImageIO.read(is);
	  //BufferedImage JumpRightFlower = ImageIO.read(new File("FlowerJumpRight.png"));
	   	yes = this.getClass().getClassLoader().getResourceAsStream("FlowerStandLeft.png");
		is = new BufferedInputStream((yes));
					
		BufferedImage StandingLeftFlower =  ImageIO.read(is);
	  //BufferedImage StandingLeftFlower = ImageIO.read(new File("FlowerStandLeft.png"));
	   	yes = this.getClass().getClassLoader().getResourceAsStream("FlowerStandRight.png");
		is = new BufferedInputStream((yes));
					
		BufferedImage StandingRightFlower =  ImageIO.read(is);
	  //BufferedImage StandingRightFlower = ImageIO.read(new File("FlowerStandRight.png"));
	 	 yes = this.getClass().getClassLoader().getResourceAsStream("FlowerStepLeft1.png");
		is = new BufferedInputStream((yes));
					
		BufferedImage StepLeft1Flower =  ImageIO.read(is);
	  //BufferedImage StepLeft1Flower = ImageIO.read(new File("FlowerStepLeft1.png"));
	   	yes = this.getClass().getClassLoader().getResourceAsStream("FlowerStepLeft2.png");
		is = new BufferedInputStream((yes));
					
		BufferedImage StepLeft2Flower =  ImageIO.read(is);
	  //BufferedImage StepLeft2Flower = ImageIO.read(new File("FlowerStepLeft2.png"));
	  	yes = this.getClass().getClassLoader().getResourceAsStream("FlowerStepLeft3.png");
		is = new BufferedInputStream((yes));
					
		BufferedImage StepLeft3Flower =  ImageIO.read(is);
	  //BufferedImage StepLeft3Flower = ImageIO.read(new File("FlowerStepLeft3.png"));
	  	yes = this.getClass().getClassLoader().getResourceAsStream("FlowerStepLeft2.png");
		is = new BufferedInputStream((yes));
					
		BufferedImage StepLeft4Flower =  ImageIO.read(is);
	  //BufferedImage StepLeft4Flower = ImageIO.read(new File("FlowerStepLeft2.png"));
	  	yes = this.getClass().getClassLoader().getResourceAsStream("FlowerStepRight1.png");
		is = new BufferedInputStream((yes));
					
		BufferedImage StepRight1Flower =  ImageIO.read(is);
	  //BufferedImage StepRight1Flower = ImageIO.read(new File("FlowerStepRight1.png"));
	  	yes = this.getClass().getClassLoader().getResourceAsStream("FlowerStepRight2.png");
		is = new BufferedInputStream((yes));
					
		BufferedImage StepRight2Flower =  ImageIO.read(is);
	  //BufferedImage StepRight2Flower = ImageIO.read(new File("FlowerStepRight2.png"));
	  	yes = this.getClass().getClassLoader().getResourceAsStream("FlowerStepRight3.png");
		is = new BufferedInputStream((yes));
					
		BufferedImage StepRight3Flower =  ImageIO.read(is);
	  //BufferedImage StepRight3Flower = ImageIO.read(new File("FlowerStepright3.png"));
	  	yes = this.getClass().getClassLoader().getResourceAsStream("FlowerStepRight2.png");
		is = new BufferedInputStream((yes));
					
		BufferedImage StepRight4Flower =  ImageIO.read(is);
	  //BufferedImage StepRight4Flower = ImageIO.read(new File("FlowerStepRight2.png"));
	  	yes = this.getClass().getClassLoader().getResourceAsStream("FlowerFireRight.png");
		is = new BufferedInputStream((yes));
					
		BufferedImage FireRightFlower =  ImageIO.read(is);
	  //BufferedImage FireRightFlower = ImageIO.read(new File("FlowerFireRight.png"));
	  	yes = this.getClass().getClassLoader().getResourceAsStream("FlowerFireLeft.png");
		is = new BufferedInputStream((yes));
					
		BufferedImage FireLeftFlower =  ImageIO.read(is);
	  //BufferedImage FireLeftFlower = ImageIO.read(new File("FlowerFireLeft.png"));

	 mario.imageSetter( DeadLeftLittle,	  DeadRightLittle,   JumpLeftLittle, 
	  JumpRightLittle,	  StandingLeftLittle,	  StandingRightLittle,	  StepLeft1Little,
	  StepLeft2Little,	  StepLeft3Little,	  StepLeft4Little,	  StepRight1Little,
	  StepRight2Little,	  StepRight3Little,	  StepRight4Little,	  DuckLeftBig,
	  DuckRightBig,	  JumpLeftBig,	  JumpRightBig,	  StandingLeftBig,
	  StandingRightBig,	  StepLeft1Big,	  StepLeft2Big,	  StepLeft3Big,
	  StepLeft4Big,	  StepRight1Big,	  StepRight2Big,	  StepRight3Big,
	  StepRight4Big,	  DuckLeftFlower,	  DuckRightFlower,	  JumpLeftFlower,	  JumpRightFlower,
	  StandingLeftFlower,	  StandingRightFlower,	  StepLeft1Flower,	  StepLeft2Flower,
	  StepLeft3Flower,	  StepLeft4Flower,	  StepRight1Flower,	  StepRight2Flower,
	  StepRight3Flower,	  StepRight4Flower,	  FireRightFlower,	  FireLeftFlower);
		
		
//		BufferedImage i= ImageIO.read(new File("StandingRight.png"));
//		mario.imageSetter(i,i,i,i,i,i,i,i,i,i,i,i,i,i,i,i,i,i,i,i,i,i,i,i,i,i,i,i,i,i,i,i,i,i,i,i,i,i,i,i,i,i,i,i);
	}
	
	public Background getBack()
	{
		return back;
	}
	
	/**
	 *@return the WorldReader Object
	 */
	public WorldReader getWorldReader()
	{
		return WR;
	}
	
	/**
	 *scrolls the level at selected speed
	 *@param i the speed to scroll
	 */
	public void WRscroll(int i)
	{
		WR.scrollEverything(i);
	}
	
	/**
	 *Paints the level on the World Component
	 *@param g the graphics component
	 */
	protected void paintComponent(Graphics g)
	{
		back.draw(g);
		mario.drawMario(g);
		
		WR.paintLevel(g);
	}
	
	/**
	 *Starts the game
	 */
	public void startAnimation()
	{
		class AnimationRunnable implements Runnable
		{
			private Background bak;
			public AnimationRunnable(Background bak)
			{
				this.bak = bak;
			}
			public void run() 
			{
				try
				{
					bak.startMoving();
				}
				catch(InterruptedException exception){}
				catch(ClassNotFoundException e){}
				catch(IOException e){}
			}
		}
		Runnable r1 = new AnimationRunnable(back);
		Thread t1 = new Thread(r1);
		t1.start();
	}
}