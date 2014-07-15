import java.awt.Graphics;
import javax.imageio.ImageIO;
import java.io.*;
import java.awt.image.BufferedImage;
import javax.swing.JComponent;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.*;

public class Background implements KeyListener
{
	int xPos;

	public BufferedImage background;
	World cop;
	String godMod;
	boolean move;
	boolean dir, run, collisionR, collisionL, collisionU, collisionD, godMode;
	int goToLevel;
	
	/*
	 *Creates a new background image that comtains the background pic
	 *@param comp the world component
	 *@param w the level to be made
	 */
	public Background(JComponent comp, String w) throws IOException
	{
		
		xPos = 0;
		
		InputStream yes = this.getClass().getClassLoader().getResourceAsStream(w);
		InputStream is = new BufferedInputStream((yes));
    	BufferedImage pic =  ImageIO.read(is);	
    	background = pic;	
		//background = ImageIO.read(new File(w));
		//background = ImageIO.read(new File("dot.bmp"));
		cop = (World)comp;
		move = false;		
		cop.addKeyListener(this);
		cop.setFocusable(true);
		dir = true;
		run = false;
		collisionR = false;
		collisionL = false;
		collisionU = false;
		collisionD = false;
		godMode= false;
		godMod = "";
		goToLevel = -1;
	}
	
	/**
	 *draws the background picture behind everything
	 *@param g the graphiics component
	 */
	public void draw(Graphics g)
	{
		g.drawImage(background,xPos,0,cop);
	}
	
	/**
	 *controlls the mario game movement and collision detetion
	 */
	public void startMoving() throws InterruptedException, IOException, ClassNotFoundException
	{
		
		while(true)
		{
			while(move)
			{
				cop.WR.collisionDetection(cop.mario);
				move();
				
				if(cop.mario.fallenToDeath())
				{
					
					cop.mario.kill();
				}
			}
			while(!move)
			{
				if(cop.mario.isFalling)
				{
					//System.out.println ("falling loop");
					cop.WR.collisionDetection(cop.mario);
					fallMove();
					
					
				}
				else if(cop.mario.isJumping)
				{
					//System.out.println ("jumping loop");
					cop.WR.collisionDetection(cop.mario);
					jump();
					
				}
				
				if(cop.mario.fallenToDeath())
				{
					cop.mario.kill();
				}
				
	
			}

			for(int o = 0; o < cop.WR.everything.size() - 1; o ++)
			{
				if(cop.WR.everything.get(o) instanceof Moveable)
				{
					if(((Moveable)cop.WR.everything.get(o)).getStatus())
					{
						cop.WR.collisionMovingDetection((Moveable)cop.WR.everything.get(o));
						((Moveable)cop.WR.everything.get(o)).move();
					}
				}
			}
				
		}
	}
	
	/*
	 *decides which mario movements should be called based of collision statuses
	 */
	public void move() throws InterruptedException
	{
		if(move)
		{
			if(dir && !collisionR)
				{
							
					cop.mario.walkRight(true);
					if(cop.mario.getLeft() > 300)
					{
						xPos -= cop.mario.walkSpeed;
						cop.getWorldReader().scrollEverything((-1)*cop.mario.walkSpeed);
						cop.mario.move(false, run, false);
					}
					else
					{
						cop.mario.move(true, run, false);
					}
				}
			else if(dir)
			{
				cop.mario.move(false, run, true);
			}
			else if(!dir && !collisionL)
				{
					
					cop.mario.walkRight(false);
					if(cop.mario.getLeft() < 100)
					{
						xPos += cop.mario.walkSpeed;
						cop.getWorldReader().scrollEverything(cop.mario.walkSpeed);
						cop.mario.move(false, run, false);
					}
					else
					{
						cop.mario.move(true, run, false);
					
					}
				}
			else  
			{
				cop.mario.move(false, run, true);
			}
			
			
			
		}
		
			
		cop.repaint();
		Thread.sleep(3);
	}
	
	/*
	 *decides which mario movements should be called based of collision statuses
	 */
	public void fallMove() throws InterruptedException
	{
		if(!collisionD)
		{
			cop.mario.verticalMove();
					
		}
				
			
				
		cop.repaint();
		Thread.sleep(3);
		
	}
	
	/**
	 *makes mario jump
	 */
	public void jump() throws InterruptedException
	{
	//	if(!collisionU)
			cop.mario.verticalMove();
		
		cop.repaint();
		Thread.sleep(3);
	}
	
	/*
	 *not used
	 */
	public void keyTyped(KeyEvent e)
	{
		if(!godMode)
		{
			if(e.getKeyChar() == 'p') //KEY_ENTER = 13;
			{
				
				
				if(godMod.equals("godmode"));
				{
					godMode = true;
					godMod = "";
				}
				
				godMod = "";
			}	
			else
			{
				char key = e.getKeyChar();
				godMod += key;
				
				
			
			}
		}
		else if(godMode)
		{
		
			
			if(e.getKeyChar() == 'p'  && !(godMod.equals("")) && isInteger(godMod)) //KEY_ENTER = 13;
			{
				
			
				
				goToLevel = Integer.parseInt(godMod);
				godMod = "";
				try	
				{
					godModeLevelChange(goToLevel, cop.mario);
				}
				catch(Exception n )
				{
					
				}
			}
			else if (e.getKeyChar() == 'p' ) //KEY_ENTER = 13;
			{
				
			
				godMod = "";

			}
			else
			{
				char key = e.getKeyChar();
				godMod += key;

			}
		}
		
		

	}

	public boolean isInteger( String input )
	{
    		try 
		{
        		Integer.parseInt( input );
        		return true;
   		}
    		catch( Exception e ) 
		{
      		  return false;
    		}
	}
	
	/*
	 *listens to the users key movements and relays the information as movements
	 */
	public void keyPressed(KeyEvent e)
	{
		
		if(e.getKeyChar() == 'a')
		{
			if(!cop.mario.isJumping && !cop.mario.isFalling)
			run = true;
			cop.mario.setWalkSpeed(2);
		}
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			
			move = true;
			dir = true;
			
			
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			move = true;
			dir = false;
		
			
		}
		
		if(e.getKeyCode() == KeyEvent.VK_UP)
		{
			
		
			if(cop.mario.canJump && !cop.mario.isFalling)
			{
				cop.mario.setIsFalling(false);
				cop.mario.setIsJumping(true);
			
					
			}
			else if(!cop.mario.canJump && collisionU )
			{

				cop.mario.setIsFalling(true);
				cop.mario.jumpStep = 140;	
				cop.mario.setIsJumping(false);
				
			}
						
		}
		
	
			
			
	
		
	}
	
	/*
	 *listens to the users key movements and relays the information as movements
	 */
	public void keyReleased(KeyEvent e)
	{
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			move = false;
			cop.mario.walkRight = false;
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			move = false;
			cop.mario.walkLeft  = false;
		}
		
		if(e.getKeyChar() == 'a')
		{
			run = false;
			cop.mario.setWalkSpeed(1);
		}
		
		if(e.getKeyCode() == KeyEvent.VK_UP)
		{
			
				cop.mario.setIsFalling(true);
				cop.mario.setIsJumping(false);
									
		}
		
		
		
		
		
		
	}

	public void godModeLevelChange(int goTo, Mario luigi) throws IOException
	{
			int currentWorld = cop.WR.getWorld();
						
			int newlevel = goTo;
					
			
			int size = luigi.getMarioSize();
			
			luigi.resetMario();
			luigi.setMarioSize(size);
			
			if(newlevel == 1)
			{
				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("mario-1-1.GIF");
				InputStream is = new BufferedInputStream((yes));
					
    				background =  ImageIO.read(is);
				
				 //original
			}
			if(newlevel == 2)
			{
				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("mario-1-1a.gif");
				InputStream is = new BufferedInputStream((yes));
					
				background =  ImageIO.read(is);
    				
			
			}
			if(newlevel == 3)
			{
				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("mario-1-2.gif");
				InputStream is = new BufferedInputStream((yes));
					
				background =  ImageIO.read(is);
				
				//background = ImageIO.read(new File("mario-1-2.gif"));
			}	
			if(newlevel == 4)
			{
				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("mario-1-2a.gif");
				InputStream is = new BufferedInputStream((yes));
					
				background =  ImageIO.read(is);
				
				//background = ImageIO.read(new File("mario-1-2a.gif"));
			}
			if(newlevel == 5)
			{
				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("mario-1-3.gif");
				InputStream is = new BufferedInputStream((yes));
					
				background =  ImageIO.read(is);
				
			//	background = ImageIO.read(new File("mario-1-3.gif"));
			}
			if(newlevel == 6)
			{
				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("mario-1-4.GIF");
				InputStream is = new BufferedInputStream((yes));
					
				background =  ImageIO.read(is);
				
			//	background = ImageIO.read(new File("mario-1-4.GIF"));  //1-4
			}
			if(newlevel == 7)
			{
				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("mario-2-1.GIF");
				InputStream is = new BufferedInputStream((yes));
					
				background =  ImageIO.read(is);
				
			//	background = ImageIO.read(new File("mario-2-1.GIF")); // 2-1
			}
			if(newlevel == 8)
			{
				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("mario-2-2.gif");
				InputStream is = new BufferedInputStream((yes));
					
				background =  ImageIO.read(is);
				
			//	background = ImageIO.read(new File("mario-2-2.gif")); // 2-2
			}
			if(newlevel == 9)
			{
				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("mario-2-4.GIF");
				InputStream is = new BufferedInputStream((yes));
					
				background =  ImageIO.read(is);
				
			//	background = ImageIO.read(new File("mario-2-4.GIF")); // 2-4
			}
			if(newlevel == 10)
			{
				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("mario-3-1.GIF");
				InputStream is = new BufferedInputStream((yes));
					
				background =  ImageIO.read(is);
				
			//	background = ImageIO.read(new File("mario-3-1.GIF")); //3-1
			}
			if(newlevel == 11)
			{
				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("mario-3-2.GIF");
				InputStream is = new BufferedInputStream((yes));
					
				background =  ImageIO.read(is);
				
			//	background = ImageIO.read(new File("mario-3-2.GIF"));  //3-2
			}
			if(newlevel == 12)  //doesnt work either :(
			{
				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("mario-3-3.GIF");
				InputStream is = new BufferedInputStream((yes));
					
				background =  ImageIO.read(is);
				
				//background = ImageIO.read(new File("mario-3-3.gif"));  // 3-3
			}
			if(newlevel == 13)
			{
				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("mario-3-4.GIF");
				InputStream is = new BufferedInputStream((yes));
					
				background =  ImageIO.read(is);
				
			//	background = ImageIO.read(new File("mario-3-4.GIF")); // 3-4
			}
			if(newlevel == 14)
			{
				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("mario-4-1.GIF");
				InputStream is = new BufferedInputStream((yes));
					
				background =  ImageIO.read(is);
				
				//background = ImageIO.read(new File("mario-4-1.GIF")); //4-1
			}
			if(newlevel == 15)
			{
				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("mario-1-1a.gif");
				InputStream is = new BufferedInputStream((yes));
					
				background =  ImageIO.read(is);
				
				//background = ImageIO.read(new File("mario-1-1a.gif"));  // transition
			}
			if(newlevel == 16)
			{
				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("mario-4-2.GIF");
				InputStream is = new BufferedInputStream((yes));
					
				background =  ImageIO.read(is);
				
				//background = ImageIO.read(new File("mario-4-2.GIF")); // 4-2
			}
			if(newlevel == 17)
			{
				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("mario-1-2a.gif");
				InputStream is = new BufferedInputStream((yes));
					
				background =  ImageIO.read(is);
				
				//background = ImageIO.read(new File("mario-1-2a.gif")); // transition
			}
			if(newlevel == 18)
			{
				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("mario-4-3.bmp");
				InputStream is = new BufferedInputStream((yes));
					
				background =  ImageIO.read(is);
				
			//	background = ImageIO.read(new File("mario-4-3.gif"));  // 4-3
			}
			if(newlevel == 19)
			{
				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("mario-4-4.GIF");
				InputStream is = new BufferedInputStream((yes));
					
				background =  ImageIO.read(is);
				
				//background = ImageIO.read(new File("mario-4-4.GIF")); // 4-4
			}
			if(newlevel == 20)
			{
				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("mario-5-1.GIF");
				InputStream is = new BufferedInputStream((yes));
					
				background =  ImageIO.read(is);
				
			//background = ImageIO.read(new File("mario-5-1.GIF"));  // 5-1
			}
			if(newlevel == 21)
			{
				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("mario-5-2.GIF");
				InputStream is = new BufferedInputStream((yes));
					
				background =  ImageIO.read(is);
				
				//background = ImageIO.read(new File("mario-5-2.GIF"));  // 5-2
			}
			if(newlevel >= 22)
			{
				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("mario-goomba.GIF");
				InputStream is = new BufferedInputStream((yes));
					
				background =  ImageIO.read(is);
				
				//background = ImageIO.read(new File("mario-goomba.GIF"));  //level 0
			}
						
			cop.WR = new WorldReader(cop, currentWorld, newlevel);	

	
	}

}