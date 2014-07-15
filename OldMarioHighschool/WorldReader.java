import java.io.*;
import java.util.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.Component;
import java.net.URL;


public class WorldReader 
{

//a list of all the elements in the Level
	public ArrayList<Object> everything;
												//public Moving[] monsters;
	
//the position of everything in the level
	public Object[][] everywhere;
												//public Moving[][] monsterPlacement;
	
//the String version of "everywhere" which reads straight from the file
	public String[][] world;
	
//needed component info	
	public World compo;	

// Size information
	public int leftBorder;
	public int rightBorder;
	public int levelWidth;
	public int w;
	public int l;
	public boolean end;

	/**Constructs a new world reader from a text file, 
	 * creates arrays that hold what everything is and where everything is
	 * @param component the World
	 * @param worldI world number
	 * @param level the current level on
	 */
	public WorldReader(Component component, int worldI, int level) throws IOException
	{
		end = false;
		compo = (World)component;
		w=worldI;
		l = level;
		//generates a String array representation of the world
		InputStream worldMaps = this.getClass().getClassLoader().getResourceAsStream("MegaWorld.txt");
		Scanner in = new Scanner((worldMaps));
    	boolean found = false;
    	String chev = "<"+"World_"+ w+ "-"+ l +">";
    	
    	while(in.hasNextLine() && !found)
    	{
    		String readed = in.nextLine();
    		
    		if(chev.equals(readed))
    		{
    			found = true;
    			
    		}
    	}
    	
    	leftBorder = 0;
    	rightBorder = 512;
    	 	
    	int numRows = in.nextInt();
    	int numCols = in.nextInt();
       	levelWidth = (numCols*32) - 16;
    	in.nextLine();
    	world = new String[numRows][numCols];
    	for(int row = 0; row < numRows-1 ; row++)
    	{
    		String read = in.nextLine();
    		
    		for(int col = 0; col < numCols; col ++)
    		{
    			if(read.length()==1)
    			{
    				world[row][col] = read;
    			}
    			else
    			{
    				world[row][col] = read.substring(0,1);
    				read = read.substring(1);	
    			}
    			
    		
    		}
    		
    	}
    	//////////////////////////////////////////////////////////////////////////String 2D array GENERATED
    	//instantial the level
    	everywhere = new Physical[numRows][numCols];
    	int actuals = 0; 
    	for(int row = 0; row < numRows-1; row++)
    	{
    		for(int col = 0; col < numCols; col ++)
    		{
    			//IF statements to make a different constructr for each type of STRING REPRESENTATION
    			String type = world[row][col];
    			
    			if(type.equals("."))  //Air
    			{
    				everywhere[row][col] = null;
    			}
    			else if(type.equals("z"))  //UpperWorldGround
    			{
    				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("Ground.png");
					InputStream is = new BufferedInputStream((yes));
					
    				BufferedImage pic =  ImageIO.read(is);



    				//BufferedImage pic = ImageIO.read(new File("Ground.png"));
    			Ground	phys = new Ground(col*32, row*32, pic, compo,      1    );
    				everywhere[row][col] = phys;
    				actuals++;
    			}
    			else if(type.equals("Z"))  //LowerWorldGround
    			{
    				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("BlueGround.png");
					InputStream is = new BufferedInputStream((yes));
    				BufferedImage pic =  ImageIO.read(is);

    			//	BufferedImage pic = ImageIO.read(new File("BlueGround.png"));
    			Ground	phys = new Ground(col*32, row*32, pic, compo,       2   );
    				everywhere[row][col] = phys;
    				actuals++;
    			}
    			else if(type.equals("x"))  //AboweBowser
    			{
    				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("AboveBowser.png");
					InputStream is = new BufferedInputStream((yes));
					
    				BufferedImage pic =  ImageIO.read(is);
    				
    				//BufferedImage pic = ImageIO.read(new File("AboveBowser.png"));
    			Ground	phys = new Ground(col*32, row*32, pic, compo,     3     );
    				everywhere[row][col] = phys;
    				actuals++;
    			}
    			else if(type.equals("X"))  //Block
    			{
    				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("Block.png");
					InputStream is = new BufferedInputStream((yes));
					
    				BufferedImage pic =  ImageIO.read(is);
    				
    				//BufferedImage pic = ImageIO.read(new File("Block.png"));
    			Ground	phys = new Ground(col*32, row*32, pic, compo,      4    );
    				everywhere[row][col] = phys;
    				actuals++;
    			}
    			else if(type.equals("v"))  //DungeonBrick
    			{
    				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("DungeonBrick.png");
					InputStream is = new BufferedInputStream((yes));
					
    				BufferedImage pic =  ImageIO.read(is);
    			//	BufferedImage pic = ImageIO.read(new File("DungeonBrick.png"));
    			Ground	phys = new Ground(col*32, row*32, pic, compo,     5     );
    				everywhere[row][col] = phys;
    				actuals++;
    			}
    			else if(type.equals("V"))  //InvisibleWall
    			{
    					InputStream yes = this.getClass().getClassLoader().getResourceAsStream("invisible.png");
					InputStream is = new BufferedInputStream((yes));
					
    				BufferedImage pic =  ImageIO.read(is);
    			//	BufferedImage pic = ImageIO.read(new File("invisible.png"));
    			Ground	phys = new Ground(col*32, row*32, pic, compo,      6    );
    				everywhere[row][col] = phys;
    				actuals++;
    			}
    			else if(type.equals("q"))  //QuestionCoin
    			{
    					InputStream yes = this.getClass().getClassLoader().getResourceAsStream("Question.png");
					InputStream is = new BufferedInputStream((yes));
					
    				BufferedImage pic =  ImageIO.read(is);
    				
    				//BufferedImage pic = ImageIO.read(new File("Question.png"));
    			Metal	phys = new Metal(col*32, row*32, pic, compo,   7       );
    				everywhere[row][col] = phys;
    				actuals++;
    			}
    			else if(type.equals("m"))  //MushroomCoin
    			{
    				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("Question.png");
					InputStream is = new BufferedInputStream((yes));
					
    				BufferedImage pic =  ImageIO.read(is);
    				
    				//BufferedImage pic = ImageIO.read(new File("Question.png"));
    			Metal	phys = new Metal(col*32, row*32, pic, compo,     9     );
    				everywhere[row][col] = phys;
    				actuals++;
    			}
    			else if(type.equals("i"))  //DeadBronze - Invisible
    			{
    				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("invisible.png");
					InputStream is = new BufferedInputStream((yes));
					
    				BufferedImage pic =  ImageIO.read(is);
    					
    				//BufferedImage pic = ImageIO.read(new File("invisible.png"));
    			Metal	phys = new Metal(col*32, row*32, pic, compo,     11     );
    				everywhere[row][col] = phys;
    				actuals++;
    			}
    			else if(type.equals("I"))  //DeadBronze - Visible
    			{
    				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("QuestionDead.png");
					InputStream is = new BufferedInputStream((yes));
					
    				BufferedImage pic =  ImageIO.read(is);
    					
    				
    			//	BufferedImage pic = ImageIO.read(new File("QuestionDead.png"));
    			Metal	phys = new Metal(col*32, row*32, pic, compo,     12     );
    				everywhere[row][col] = phys;
    				actuals++;
    			}
    			else if(type.equals("e"))  //OneUp - invisible
    			{
    				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("invisible.png");
					InputStream is = new BufferedInputStream((yes));
					
    				BufferedImage pic =  ImageIO.read(is);
    					
    			//	BufferedImage pic = ImageIO.read(new File("invisible.png"));
    			Metal	phys = new Metal(col*32, row*32, pic, compo,     13     );
    				everywhere[row][col] = phys;
    				actuals++;
    			}
    			else if(type.equals("*"))  //Star
    			{
    				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("Question.png");
					InputStream is = new BufferedInputStream((yes));
					
    				BufferedImage pic =  ImageIO.read(is);
    				
    				//BufferedImage pic = ImageIO.read(new File("Question.png"));
    			Metal	phys = new Metal(col*32, row*32, pic, compo,      15    );
    				everywhere[row][col] = phys;
    				actuals++;
    			}
    			else if(type.equals("s"))  //DeadSteel - Invisible
    			{
    				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("invisible.png");
					InputStream is = new BufferedInputStream((yes));
					
    				BufferedImage pic =  ImageIO.read(is);
    				
    			//	BufferedImage pic = ImageIO.read(new File("invisible.png"));
    			Metal	phys = new Metal(col*32, row*32, pic, compo,    17      );
    				everywhere[row][col] = phys;
    				actuals++;
    			}
    			else if(type.equals("S"))  //DealSteel - Visible
    			{
    				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("SteelBlock.png");
					InputStream is = new BufferedInputStream((yes));
					
    				BufferedImage pic =  ImageIO.read(is);
    				
    				//BufferedImage pic = ImageIO.read(new File("SteelBlock.png"));
    			Metal	phys = new Metal(col*32, row*32, pic, compo,     18     );
    				everywhere[row][col] = phys;
    				actuals++;
    			}
    			else if(type.equals("B"))  // Brick - UpperWorld
    			{
    				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("Brick.png");
					InputStream is = new BufferedInputStream((yes));
					
    				BufferedImage pic =  ImageIO.read(is);
    				
    				//BufferedImage pic = ImageIO.read(new File("Brick.png"));
    			Brick	phys = new Brick(col*32, row*32, pic, compo,         19 );
    				everywhere[row][col] = phys;
    				actuals++;
    			}
    			else if(type.equals("b"))  //Brick - LowerWorld
    			{
    				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("BlueBrick.png");
					InputStream is = new BufferedInputStream((yes));
					
    				BufferedImage pic =  ImageIO.read(is);
    				
    				//BufferedImage pic = ImageIO.read(new File("BlueBrick.png"));
    			Brick	phys = new Brick(col*32, row*32, pic, compo,        20  );
    				everywhere[row][col] = phys;
    				actuals++;
    			}
//    			else if(type.equals("h"))  // Hammer
//    			{
//    				BufferedImage pic = ImageIO.read(new File("Hammer.png"));
//    			Hammer	phys = new Hammer(col*32, row*32, pic, compo,       21   );
//    				everywhere[row][col] = phys;
//    				actuals++;
//    			}
    			else if(type.equals("c"))    		//coin
    			{
    				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("coin.png");
					InputStream is = new BufferedInputStream((yes));
					
    				BufferedImage pic =  ImageIO.read(is);	
    				//BufferedImage pic = ImageIO.read(new File("coin.png"));
    			Collectable	phys = new Collectable(col*32, row*32, pic, compo,       23   );
    				everywhere[row][col] = phys;
    				actuals++;
    			}
    			else if(type.equals("M"))  // Mushroom
    			{
    				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("Mushroom.png");
					InputStream is = new BufferedInputStream((yes));
					
    				BufferedImage pic =  ImageIO.read(is);	
    				
    				//BufferedImage pic = ImageIO.read(new File("Mushroom.png"));
    			Collectable	phys = new Collectable(col*32, row*32, pic, compo,    25      );
    				everywhere[row][col] = phys;
    				actuals++;
    			}
    			else if(type.equals("E"))  // OneUp
    			{
    				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("OneUp.png");
					InputStream is = new BufferedInputStream((yes));
					
    				BufferedImage pic =  ImageIO.read(is);
    				
    				//BufferedImage pic = ImageIO.read(new File("OneUp.png"));
    			Collectable	phys = new Collectable(col*32, row*32, pic, compo,       27   );
    				everywhere[row][col] = phys;
    				actuals++;
    			}
    			else if(type.equals("F")) // Flower
    			{
    				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("flower.png");
					InputStream is = new BufferedInputStream((yes));
					
    				BufferedImage pic =  ImageIO.read(is);
    				
    				//BufferedImage pic = ImageIO.read(new File("flower.png"));
    			Collectable	phys = new Collectable(col*32, row*32, pic, compo,     29     );
    				everywhere[row][col] = phys;
    				actuals++;
    			}
    			else if(type.equals("p"))  // WarpPipe - Body -Left  up
    			{
    				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("BottomLeftTube.png");
					InputStream is = new BufferedInputStream((yes));
					
    				BufferedImage pic =  ImageIO.read(is);	
    				//BufferedImage pic = ImageIO.read(new File("BottomLeftTube.png"));
    			WarpPipeBody	phys = new WarpPipeBody(col*32, row*32, pic, compo,    31      );
    				everywhere[row][col] = phys;
    				actuals++;
    			}
    			else if(type.equals("P")) // WarpPipe - Body leftSide
    			{
    				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("SideLeftTubeBody.png");
					InputStream is = new BufferedInputStream((yes));
					
    				BufferedImage pic =  ImageIO.read(is);	
    				
    				//BufferedImage pic = ImageIO.read(new File("SideLeftTubeBody.png"));
    			WarpPipeBody	phys = new WarpPipeBody(col*32, row*32, pic, compo,     32     );
    				everywhere[row][col] = phys;
    				actuals++;
    			}
    			else if(type.equals("o")) // WarpPipe - Body - Right up
    			{
    			InputStream yes = this.getClass().getClassLoader().getResourceAsStream("BottomRightTube.png");
					InputStream is = new BufferedInputStream((yes));
					
    				BufferedImage pic =  ImageIO.read(is);	
    				
    				//BufferedImage pic = ImageIO.read(new File("BottomRightTube.png"));
    			WarpPipeBody	phys = new WarpPipeBody(col*32, row*32, pic, compo,   33       );
    				everywhere[row][col] = phys;
    				actuals++;
    			}
    			else if(type.equals("O")) // WarpPipe - Body - Right Side
    			{
    				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("SideRightTubeBody.png");
					InputStream is = new BufferedInputStream((yes));
					
    				BufferedImage pic =  ImageIO.read(is);
    				
    				//BufferedImage pic = ImageIO.read(new File("SideRightTubeBody.png"));
    			WarpPipeBody	phys = new WarpPipeBody(col*32, row*32, pic, compo,     34     );
    				everywhere[row][col] = phys;
    				actuals++;
    			}
    			else if(type.equals("l")) // WarpPipe - Entrance - Left up
    			{
    					InputStream yes = this.getClass().getClassLoader().getResourceAsStream("TopLeftTube.png");
					InputStream is = new BufferedInputStream((yes));
					
    				BufferedImage pic =  ImageIO.read(is);
    				
    				//BufferedImage pic = ImageIO.read(new File("TopLeftTube.png"));
    			WarpPipeEntrance	phys = new WarpPipeEntrance(col*32, row*32, pic, compo,     35     );
    				everywhere[row][col] = phys;
    				actuals++;
    			}
    			else if(type.equals("L")) // WarpPipe - Entrance - Left side
    			{
    				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("SideLeftTubeTop.png");
					InputStream is = new BufferedInputStream((yes));
					
    				BufferedImage pic =  ImageIO.read(is);
    				
    				//BufferedImage pic = ImageIO.read(new File("SideLeftTubeTop.png"));
    			WarpPipeEntrance	phys = new WarpPipeEntrance(col*32, row*32, pic, compo,      36    );
    				everywhere[row][col] = phys;
    				actuals++;
    			}
    			else if(type.equals("k")) // WarpPipe - Entrance -  Right up
    			{
    				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("TopRightTube.png");
					InputStream is = new BufferedInputStream((yes));
					
    				BufferedImage pic =  ImageIO.read(is);
    				
    				//BufferedImage pic = ImageIO.read(new File("TopRightTube.png"));
    			WarpPipeEntrance	phys = new WarpPipeEntrance(col*32, row*32, pic, compo,      37    );
    				everywhere[row][col] = phys;
    				actuals++;
    			}
    			else if(type.equals("K")) // WarpPipe - Entrance -  Right Side
    			{
    				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("SideLeftTubeTop.png");
					InputStream is = new BufferedInputStream((yes));
					
    				BufferedImage pic =  ImageIO.read(is);
    				
    				
    				//BufferedImage pic = ImageIO.read(new File("SideLeftTubeTop.png"));
    			WarpPipeEntrance	phys = new WarpPipeEntrance(col*32, row*32, pic, compo,      38    );
    				everywhere[row][col] = phys;
    				actuals++;
    			}
    			else if(type.equals(">")) // WarpPipe - Teleport - Right up
    			{
    				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("StandingRight.png");
					InputStream is = new BufferedInputStream((yes));
					
    				BufferedImage pic =  ImageIO.read(is);
    				
    				
    				//BufferedImage pic = ImageIO.read(new File("StandingRight.png"));
    			WarpPipeEntrance	phys = new WarpPipeEntrance(col*32, row*32, pic, compo,    39      );
    				everywhere[row][col] = phys;
    				actuals++;
    			}
    			else if(type.equals("^")) // WarpPipe - Teleport - Right side
    			{
    				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("StandingRight.png");
					InputStream is = new BufferedInputStream((yes));
					
    				BufferedImage pic =  ImageIO.read(is);
    				
    				
    				//BufferedImage pic = ImageIO.read(new File("StandingRight.png"));
    			WarpPipeEntrance	phys = new WarpPipeEntrance(col*32, row*32, pic, compo,     40     );
    				everywhere[row][col] = phys;
    				actuals++;
    			}
    			else if(type.equals("|"))  //Flagpole
    			{
    				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("invisible.png");
					InputStream is = new BufferedInputStream((yes));
					
    				BufferedImage pic =  ImageIO.read(is);
    				
    				//BufferedImage pic = ImageIO.read(new File("invisible.png"));
    			FlagPole	phys = new FlagPole(col*32, row*32, pic, compo,      41    );
    				everywhere[row][col] = phys;
    				actuals++;
    			}
    			else if(type.equals("1"))  // Goomba
    			{
    				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("goomba.png");
					InputStream is = new BufferedInputStream((yes));
					
    				BufferedImage pic =  ImageIO.read(is);
    				
    				//BufferedImage pic = ImageIO.read(new File("goomba.png"));   // ANIMATE!!!!
    			Goomba	phys = new Goomba(col*32, row*32, pic, compo,     100      );
    				everywhere[row][col] = phys;
    				actuals++;
    			}
    			else if(type.equals("="))  // BowserPlatform
    			{
   					InputStream yes = this.getClass().getClassLoader().getResourceAsStream("BowserPlankBlock.png");
					InputStream is = new BufferedInputStream((yes));
					
    				BufferedImage pic =  ImageIO.read(is);
    				
    				//BufferedImage pic = ImageIO.read(new File("BowserPlankBlock.png"));
    			Ground	phys = new Ground(col*32, row*32, pic, compo,      45    );
    				everywhere[row][col] = phys;
    				actuals++;
    			}
    			else if(type.equals("{"))  // BowserPlatform
    			{
    				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("LeftThree.png");
					InputStream is = new BufferedInputStream((yes));
					
    				BufferedImage pic =  ImageIO.read(is);
    				//BufferedImage pic = ImageIO.read(new File("LeftThree.png"));
    			Ground	phys = new Ground(col*32, row*32, pic, compo,      100    );
    				everywhere[row][col] = phys;
    				actuals++;
    			}
    			else if(type.equals(","))  // BowserPlatform
    			{
    					InputStream yes = this.getClass().getClassLoader().getResourceAsStream("MiddleG.png");
					InputStream is = new BufferedInputStream((yes));
					
    				BufferedImage pic =  ImageIO.read(is);
    				//BufferedImage pic = ImageIO.read(new File("MiddleG.png"));
    			Ground	phys = new Ground(col*32, row*32, pic, compo,      101    );
    				everywhere[row][col] = phys;
    				actuals++;
    			}
    			else if(type.equals("}"))  // BowserPlatform
    			{
    					InputStream yes = this.getClass().getClassLoader().getResourceAsStream("RightG.png");
					InputStream is = new BufferedInputStream((yes));
					
    				BufferedImage pic =  ImageIO.read(is);
    				
    				//BufferedImage pic = ImageIO.read(new File("RightG.png"));
    			Ground	phys = new Ground(col*32, row*32, pic, compo,      102    );
    				everywhere[row][col] = phys;
    				actuals++;
    			}
    			else if(type.equals("8"))  // BowserPlatform
    			{
    				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("LeftP.png");
				InputStream is = new BufferedInputStream((yes));
					
    				BufferedImage pic =  ImageIO.read(is);
    				
    			//	BufferedImage pic = ImageIO.read(new File("LeftP.png"));
    			Ground	phys = new Ground(col*32, row*32, pic, compo,      102    );
    				everywhere[row][col] = phys;
    				actuals++;
    			}
    			else if(type.equals("9"))  // BowserPlatform
    			{
    				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("MiddleP.png");
					InputStream is = new BufferedInputStream((yes));
					
    				BufferedImage pic =  ImageIO.read(is);
    				
    				//BufferedImage pic = ImageIO.read(new File("MiddleP.png"));
    			Ground	phys = new Ground(col*32, row*32, pic, compo,      102    );
    				everywhere[row][col] = phys;
    				actuals++;
    			}
    			else if(type.equals("0"))  // BowserPlatform
    			{
    				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("RightP.png");
					InputStream is = new BufferedInputStream((yes));
					
    				BufferedImage pic =  ImageIO.read(is);
    				
    				//BufferedImage pic = ImageIO.read(new File("RightP.png"));
    			Ground	phys = new Ground(col*32, row*32, pic, compo,      102    );
    				everywhere[row][col] = phys;
    				actuals++;
    			}
    		/*	else if(type.equals(""))
    			{
    				BufferedImage pic = ImageIO.read(new File("StandingRight.png"));
    				phys = new (col*32, row*32, pic, compo,          );
    				everywhere[row][col] = phys;
    				actuals++;
    			}
    			else if(type.equals(""))
    			{
    				BufferedImage pic = ImageIO.read(new File("StandingRight.png"));
    				phys = new (col*32, row*32, pic, compo,          );
    				everywhere[row][col] = phys;
    				actuals++;
    			}
    			else if(type.equals(""))
    			{
    				BufferedImage pic = ImageIO.read(new File("StandingRight.png"));
    				phys = new (col*32, row*32, pic, compo,          );
    				everywhere[row][col] = phys;
    				actuals++;
    			}*/
    		    			
    		}
    	}

//////////////////////////////////////////add all the non Null elements into an array so that they can be redrawn
    	everything = new ArrayList<Object>();
    		int index = 0;
    		for(int col = 0; col < numCols; col++ )
    		{
    			for(int row = 0; row< numRows; row++)
    			{
    				if(everywhere[row][col] != null)
    				{
    					everything.add(everywhere[row][col]);
    					if(everywhere[row][col] instanceof Physical)
    						((Physical)everywhere[row][col]).setIndex(index);
    					else if(everywhere[row][col] instanceof Moveable)
    						((Moveable)everywhere[row][col]).setIndex(index);
    					index++;
    				}
    				
    			
    			}
    		
    		}
    	
    	    	
    	 
	}

	/*
	 *not implemented in time :(
	 */
	public void collisionMovingDetection(Moveable mover)
	{
		int moverLeft = mover.getLeftWorld();
 		int moverRight = moverLeft + 32; 		
 		int moverTop = mover.getYTopBound();
 		int moverBottom = moverTop + 32;
 		
 		int moverCenter = (moverLeft + moverRight)/2;
 		
		Object rightOfMe = collisionDetectionRight(moverCenter,moverTop, moverBottom, true);
		if(rightOfMe != null)
 		{
			mover.speed = -1 * mover.speed;
		}

		Object rightOfMe2 = collisionDetectionRight(moverCenter,moverTop, moverBottom, false);
		if(rightOfMe2 != null && rightOfMe == null)
 		{
			mover.speed = -1 * mover.speed;
		}

		Object leftOfMe = collisionDetectionLeft(moverCenter,moverTop, moverBottom, true);
 		if(leftOfMe != null)
 		{
			mover.speed = -1 * mover.speed;
		}

		Object leftOfMe2 = collisionDetectionLeft(moverCenter,moverTop, moverBottom, false);
 		if(leftOfMe2 != null && leftOfMe == null)
 		{
			mover.speed = -1 * mover.speed;
		}

		Object bottomOfMe = collisionDetectionBottom(moverCenter, moverBottom, true);
 		if(bottomOfMe != null)
 		{
			mover.collisionD = true;
			mover.isFalling = false;
		}

		Object bottomOfMe2 = collisionDetectionBottom(moverCenter,moverBottom, false);
 		if(bottomOfMe2 != null)
 		{
			mover.collisionD = true;
			mover.isFalling = false;
		}

		if(bottomOfMe == null && bottomOfMe2 == null)
			{
			mover.collisionD = false;
			mover.isFalling = true;
			}

		

		
	
	}

	
	
 	/**
	 *collision detection for mario
	 *@param mario the character being played
	 */
 	public void collisionDetection(Mario mario)  throws IOException, ClassNotFoundException
 	{
 		int marioLeft = mario.getLeftWorld();
 		int marioRight = marioLeft +  Constants.marioWidth; 		
 		int marioTop = mario.getTopWorld();
 		int marioBottom = mario.getBottomWorld();
 		
 		int marioCenter = (marioLeft + marioRight)/2;
 		
 		
 		Object bottomOfMe = collisionDetectionBottom(marioCenter, marioBottom, true);
 		if(bottomOfMe != null)
 		{
 			String response = "_";
	 		boolean ignore = false;
	 		
 			if(bottomOfMe instanceof Physical)	
 			{
	 			
	 			if(bottomOfMe instanceof Collectable)
	 			{
	 				if(((Collectable)bottomOfMe).active())
	 				{
	 				 response = ((Collectable)bottomOfMe).physicalTopReact(mario);
	 				}
	 				else
	 				{
	 					ignore = true;
	 				}
	 			
	 			}
	 			else if(bottomOfMe instanceof Goomba)
				{
					if(((Goomba)bottomOfMe).active())
					{
						response = ((Goomba)bottomOfMe).physicalBottomReact(mario);	
						compo.back.collisionD = true;
		 				compo.mario.isFalling = false;
		 				compo.mario.canJump = true;
		 				compo.mario.isJumping = true;
		 				compo.mario.jumpStep = 30;
		 				compo.mario.fallStep = 0;
					}
					
					else
						ignore = true;
	
							
				}
	 			else 
	 			{
	 				compo.back.collisionD = true;
		 			compo.mario.isFalling = false;
		 			compo.mario.canJump = true;
		 			compo.mario.jumpStep = 0;
		 			compo.mario.fallStep = 0;
	 			}
	 			
	 			if(!response.equals("_") && !ignore)
	 			{
	 				
	 				updateEvery(((Physical)bottomOfMe).getRow(),((Physical)bottomOfMe).getCol(),response, ((Physical)bottomOfMe).getIndex());
	 			}
 			}
// 			else if(bottomOfMe instanceof Moveable)
// 			{
// 			
//				if(bottomOfMe instanceof Goomba)
//				{
//					if(((Goomba)bottomOfMe).active())
//					{
//						response = ((Goomba)bottomOfMe).moveableBottomReact(mario);
//						//mario bounces
//					}
//					else
//						ignore = true;
//	
//							
//				}
//				else if (bottomOfMe instanceof MovingCollectable)
//				{
//					if(((MovingCollectable)bottomOfMe).active())
//						response = ((MovingCollectable)bottomOfMe).moveableBottomReact(mario);
//					else
//						ignore = true;
//				}
//				else 
//				{
//					//nothing as of now
//				}
//				
//				if(!response.equals("_") && !ignore)
//	 			{
//	 				//updateMonsters(((Moveable)bottomOfMe).getRow(),((Moveable)bottomOfMe).getCol(),response);
//	 			}
// 			}
 			
 			
 		}
 		
 		Object bottomOfMe2 = collisionDetectionBottom(marioCenter, marioBottom, false);
 		if(bottomOfMe2 != null)
 		{
 			String response = "_";
	 		boolean ignore = false;
	 		
 			if(bottomOfMe2 instanceof Physical)	
 			{
	 			
	 			if(bottomOfMe2 instanceof Collectable)
	 			{
	 				if(((Collectable)bottomOfMe2).active())
	 				{
	 				 response = ((Collectable)bottomOfMe2).physicalTopReact(mario);
	 				}
	 				else
	 				{
	 					ignore = true;
	 				}
	 			
	 			}
	 			else if(bottomOfMe2 instanceof Goomba)
				{
					if(((Goomba)bottomOfMe2).active())
					{
						response = ((Goomba)bottomOfMe2).physicalBottomReact(mario);
						compo.back.collisionD = true;
		 				compo.mario.isFalling = false;
		 				compo.mario.isJumping = true;
		 				compo.mario.canJump = true;
		 				compo.mario.jumpStep = 30;
		 				compo.mario.fallStep = 0;
					}
					
					else
						ignore = true;
	
							
				}
	 			else 
	 			{
	 				compo.back.collisionD = true;
		 			compo.mario.isFalling = false;
		 			compo.mario.canJump = true;
		 			compo.mario.jumpStep = 0;
		 			compo.mario.fallStep = 0;
	 			}
	 			
	 			if(!response.equals("_") && !ignore)
	 			{
	 				
	 				updateEvery(((Physical)bottomOfMe2).getRow(),((Physical)bottomOfMe2).getCol(),response, ((Physical)bottomOfMe2).getIndex());
	 			}
 			}
// 			else if(bottomOfMe2 instanceof Moveable)
// 			{
// 				
//	
//				if(bottomOfMe2 instanceof Goomba)
//				{
//					if(((Goomba)bottomOfMe2).active())
//					{
//						response = ((Goomba)bottomOfMe2).moveableBottomReact(mario);
//						//mario bounces
//					}
//					else
//						ignore = true;
//	
//							
//				}
//				else if (bottomOfMe2 instanceof MovingCollectable)
//				{
//					if(((MovingCollectable)bottomOfMe2).active())
//						response = ((MovingCollectable)bottomOfMe2).moveableBottomReact(mario);
//					else
//						ignore = true;
//				}
//				else 
//				{
//					//nothing as of now
//				}
//				
//				if(!response.equals("_") && !ignore)
//	 			{
//	 				//updateMonsters(((Moveable)bottomOfMe2).getRow(),((Moveable)bottomOfMe2).getCol(),response);
//	 			}
// 			}
 		}
 		
 		if(bottomOfMe == null && bottomOfMe2 == null)
 		{
			if(compo.mario.isJumping == false)
			{
 			compo.back.collisionD = false;
 			compo.mario.isFalling = true;
 			compo.mario.canJump = false;
 			}
 		}
 		
 		
 		//test players collision with objects.  
 		Object rightOfMe = collisionDetectionRight(marioCenter,marioTop, marioBottom, true);
 		//same thing but for moveables?  moveables = enemies, mushrooms, coins, collectables.
 		//smae thing but for finishables(teleport pipe, flag)
 		if(rightOfMe != null)
 		{
 			String response = "_";
	 		boolean ignore = false;
	 			
 			if(rightOfMe instanceof Physical)
 			{
 				if(rightOfMe instanceof Collectable)
	 			{
	 				if(((Collectable)rightOfMe).active())
		 			{
		 				response = ((Collectable)rightOfMe).physicalRightReact(mario);
		 			}
		 			else
		 			{
		 				ignore = true;
		 			}
		 			
	 			}
	 			else if(rightOfMe instanceof Goomba)
				{
					if(((Goomba)rightOfMe).active())
						response = ((Goomba)rightOfMe).physicalRightReact(mario);
					else
						ignore = true;
	
							
				}
	 			else if(rightOfMe instanceof FlagPole)
	 			{
	 				if(((FlagPole)rightOfMe).active())
		 			{
		 				response = ((FlagPole)rightOfMe).physicalRightReact(mario);
		 				compo.back.collisionR = true;
		 			}
		 			else
		 			{
		 				ignore = true;
		 			}
		 			
	 			}
 				else
	 			{
	 				compo.back.collisionR = true;
	 					 				
 				}
 				
 				if(!response.equals("_") && !ignore)
	 			{
	 				updateEvery(((Physical)rightOfMe).getRow(),((Physical)rightOfMe).getCol(),response, ((Physical)rightOfMe).getIndex());
	 			}
 					
 			}
// 			else if(rightOfMe instanceof Moveable)
// 			{
//	 			
////				if(rightOfMe instanceof Goomba)
////				{
////					if(((Goomba)rightOfMe).active())
////						response = ((Goomba)rightOfMe).moveableRightReact(mario);
////					else
////						ignore = true;
////	
////							
////				}
//				else if (rightOfMe instanceof MovingCollectable)
//				{
//					if(((MovingCollectable)rightOfMe).active())
//						response = ((MovingCollectable)rightOfMe).moveableRightReact(mario);
//					else
//						ignore = true;
//				}
//				else 
//				{
//					//nothing as of now
//				}
//				
//				if(!response.equals("_") && !ignore)
//	 			{
//	 				//Still got to figure out how im going to have the monsters freaking do things
//	 			//	updateMonsters(((Moveable)rightOfMe).getRow(),((Moveable)rightOfMe).getCol(),response);
//	 			}
// 			}
 			
 		}
 		
 		Object rightOfMe2= null;
 		if(!end)
 		{
	 		 rightOfMe2 = collisionDetectionRight(marioCenter,marioTop, marioBottom, false);
	 		//same thing but for moveables?  moveables = enemies, mushrooms, coins, collectables.
	 		//smae thing but for finishables(teleport pipe, flag)
	 		if(rightOfMe2 != null)
	 		{
	 			String response = "_";
		 		boolean ignore = false;
		 			
	 			if(rightOfMe2 instanceof Physical)
	 			{
	 				if(rightOfMe2 instanceof Collectable)
		 			{
		 				if(((Collectable)rightOfMe2).active())
			 			{
			 				response = ((Collectable)rightOfMe2).physicalRightReact(mario);
			 			}
			 			else
			 			{
			 				ignore = true;
			 			}
			 			
		 			}
		 			else if(rightOfMe2 instanceof Goomba && mario.isBig())
					{
						if(((Goomba)rightOfMe2).active())
							response = ((Goomba)rightOfMe2).physicalRightReact(mario);
						else
							ignore = true;
	
							
					}
		 			else if(rightOfMe2 instanceof FlagPole)
		 			{
		 				if(((FlagPole)rightOfMe2).active())
			 			{
			 				response = ((FlagPole)rightOfMe2).physicalRightReact(mario);
			 				compo.back.collisionR = true;
			 			}
			 			else
			 			{
			 				ignore = true;
			 			}
			 			
		 			}
	 				else
		 			{
		 				compo.back.collisionR = true;
		 				
	 				}
	 				
	 				if(!response.equals("_") && !ignore)
		 			{
		 				updateEvery(((Physical)rightOfMe2).getRow(),((Physical)rightOfMe2).getCol(),response, ((Physical)rightOfMe2).getIndex());
		 			}
	 					
	 			}
//		 		else if(rightOfMe2 instanceof Moveable)
//	 			{
//		 			
//					if(rightOfMe2 instanceof Goomba)
//					{
//						if(((Goomba)rightOfMe2).active())
//							response = ((Goomba)rightOfMe2).moveableRightReact(mario);
//						else
//							ignore = true;
//		
//								
//					}
//					else if (rightOfMe2 instanceof MovingCollectable)
//					{
//						if(((MovingCollectable)rightOfMe2).active())
//							response = ((MovingCollectable)rightOfMe2).moveableRightReact(mario);
//						else
//							ignore = true;
//					}
//					else 
//					{
//						//nothing as of now
//					}
//					
//					if(!response.equals("_") && !ignore)
//		 			{
//		 				//Still got to figure out how im going to have the monsters freaking do things
//		 			//	updateMonsters(((Moveable)rightOfMe2).getRow(),((Moveable)rightOfMe2).getCol(),response);
//		 			}
//	 			}
	 		}
 		
 		
 		}
 		if(rightOfMe == null && rightOfMe2 == null)
 		{
 			compo.back.collisionR = false;
 		}
 		
 		
 		
 		Object leftOfMe = collisionDetectionLeft(marioCenter,marioTop, marioBottom, true);
 		if(leftOfMe != null)
 		{
 			
 			String response = "_";
	 		boolean ignore = false;
	 			
 			if(leftOfMe instanceof Physical)
 			{
 				if(leftOfMe instanceof Collectable)
	 			{
	 				if(((Collectable)leftOfMe).active())
		 			{
		 				response = ((Collectable)leftOfMe).physicalRightReact(mario);
		 			}
		 			else
		 			{
		 				ignore = true;
		 			}
		 			
	 			}
	 			else if(leftOfMe instanceof Goomba)
				{
					if(((Goomba)leftOfMe).active())
						response = ((Goomba)leftOfMe).physicalLeftReact(mario);
					else
						ignore = true;
	
							
				}
 				else
	 			{
	 				compo.back.collisionL = true;
	 					 				
 				}
 				
 				if(!response.equals("_") && !ignore)
	 			{
	 				updateEvery(((Physical)leftOfMe).getRow(),((Physical)leftOfMe).getCol(),response, ((Physical)leftOfMe).getIndex());
	 			}
 					
 			}
// 			else if (leftOfMe instanceof Moveable)
// 			{
// 			
//				if(leftOfMe instanceof Goomba)
//				{
//					if(((Goomba)leftOfMe).active())
//						response = ((Goomba)leftOfMe).moveableLeftReact(mario);
//					else
//						ignore = true;
//	
//							
//				}
//				else if (leftOfMe instanceof MovingCollectable)
//				{
//					if(((MovingCollectable)leftOfMe).active())
//						response = ((MovingCollectable)leftOfMe).moveableLeftReact(mario);
//					else
//						ignore = true;
//				}
//				else 
//				{
//					//nothing as of now
//				}
//				
//				if(!response.equals("_") && !ignore)
//	 			{
//	 				//Still got to figure out how im going to have the monsters freaking do things
//	 				//updateMonsters(((Moveable)leftOfMe).getRow(),((Moveable)leftOfMe).getCol(),response);
//	 			}
// 			}
 			
 		}
 		
 		Object leftOfMe2 = collisionDetectionLeft(marioCenter,marioTop, marioBottom, false);
 		if(leftOfMe2 != null)
 		{
 			String response = "_";
	 		boolean ignore = false;
	 			
 			if(leftOfMe2 instanceof Physical)
 			{
 				if(leftOfMe2 instanceof Collectable)
	 			{
	 				if(((Collectable)leftOfMe2).active())
		 			{
		 				response = ((Collectable)leftOfMe2).physicalRightReact(mario);
		 			}
		 			else
		 			{
		 				ignore = true;
		 			}
		 			
	 			}
	 			else if(leftOfMe2 instanceof Goomba  && mario.isBig())
				{
					if(((Goomba)leftOfMe2).active())
						response = ((Goomba)leftOfMe2).physicalLeftReact(mario);
					else
						ignore = true;
	
							
				}
 				else
	 			{
	 				compo.back.collisionL = true;
	 					 				
 				}
 				
 				if(!response.equals("_") && !ignore)
	 			{
	 				updateEvery(((Physical)leftOfMe2).getRow(),((Physical)leftOfMe2).getCol(),response, ((Physical)leftOfMe2).getIndex());
	 			}
 					
 			}
// 			else if (leftOfMe2 instanceof Moveable)
// 			{
// 				
//				if(leftOfMe2 instanceof Goomba)
//				{
//					if(((Goomba)leftOfMe2).active())
//						response = ((Goomba)leftOfMe2).moveableLeftReact(mario);
//					else
//						ignore = true;
//	
//							
//				}
//				else if (leftOfMe2 instanceof MovingCollectable)
//				{
//					if(((MovingCollectable)leftOfMe2).active())
//						response = ((MovingCollectable)leftOfMe2).moveableLeftReact(mario);
//					else
//						ignore = true;
//				}
//				else 
//				{
//					//nothing as of now
//				}
//				
//				if(!response.equals("_") && !ignore)
//	 			{
//	 				//Still got to figure out how im going to have the monsters freaking do things
//	 				//updateMonsters(((Moveable)leftOfMe2).getRow(),((Moveable)leftOfMe2).getCol(),response);
//	 			}
// 			}
 			
 		}
 		
 		
 		if(leftOfMe == null && leftOfMe2 == null)
 		{
 			compo.back.collisionL = false;
 		}
 		
 		Object topCenter = collisionDetectionTopCenter(marioCenter,marioTop);
 		if(topCenter!= null)
 		{
 			
 			
 			String response = "_";
	 		boolean ignore = false;
	 			
 			if(topCenter instanceof Physical)
 			{
 				
	 			if(topCenter instanceof Collectable)
	 			{
	 				if(((Collectable)topCenter).active())
	 				{
	 				 response = ((Collectable)topCenter).physicalTopReact(mario);
	 				}
	 				else
	 				{
	 					ignore = true;
	 				}
	 				
	 			}
	 			else if(topCenter instanceof Goomba)
				{
					if(((Goomba)topCenter).active())
						response = ((Goomba)topCenter).physicalTopReact(mario);
					else
						ignore = true;
								
				}
	 			else if(topCenter instanceof Brick)
	 			{
	 				if(((Brick)topCenter).active())
	 				{
	 					compo.back.collisionU = true;
 						compo.mario.canJump = false;
 						compo.mario.isJumping = false;
 						compo.mario.isFalling = true;
 						
	 				 	response = ((Brick)topCenter).physicalTopReact(mario);
	 				 	
	 				}
	 				else
	 				{
	 					ignore = true;
	 				}
	 			
	 			}
	 			else if(topCenter instanceof Metal)
	 			{
	 				if(((Metal)topCenter).active())
	 				{
	 					compo.back.collisionU = true;
 						compo.mario.canJump = false;
 						
	 				 	response = ((Metal)topCenter).physicalTopReact(mario);
	 				 	
	 				}
	 				else
	 				{
	 					ignore = true;
	 				}
	 				
	 			}
	 			else 
	 			{
	 				 
	 				compo.back.collisionU = true;
 					compo.mario.canJump = false;
	 			}
	 			
	 			if(!response.equals("_") && !ignore)
	 			{
	 				
	 				updateEvery(((Physical)topCenter).getRow(),((Physical)topCenter).getCol(),response, ((Physical)topCenter).getIndex());
	 			}

 			}
// 			else if (topCenter instanceof Moveable)
// 			{
// 				
//				if(topCenter instanceof Goomba)
//				{
//					if(((Goomba)topCenter).active())
//						response = ((Goomba)topCenter).moveableTopReact(mario);
//					else
//						ignore = true;
//	
//							
//				}
//				else if (topCenter instanceof MovingCollectable)
//				{
//					if(((MovingCollectable)topCenter).active())
//						response = ((MovingCollectable)topCenter).moveableTopReact(mario);
//					else
//						ignore = true;
//				}
//				else 
//				{
//					//nothing as of now
//				}
//				
//				if(!response.equals("_") && !ignore)
//	 			{
//	 				//Still got to figure out how im going to have the monsters freaking do things	
//	 				//updateMonsters(((Moveable)topCenter).getRow(),((Moveable)topCenter).getCol(),response);
//	 			}
// 			}
 			
 			
 		}
 		
 		Object topOfMe = collisionDetectionTop(marioCenter,marioTop, true);
 		if(topOfMe!= null)
 		{
 			if(!(topOfMe instanceof Collectable) && !(topOfMe instanceof Goomba))
			{
			compo.back.collisionU = true;
			compo.mario.canJump = false;
			}
 			 			
 		}
 		
 		Object topOfMe2 = collisionDetectionTop(marioCenter,marioTop, false);
 		if(topOfMe2 != null)
 		{
			if(!(topOfMe2 instanceof Collectable)&& !(topOfMe instanceof Goomba))
			{
				compo.back.collisionU = true;
				compo.mario.canJump = false;
			}
		
 					
 		}
 		
 		
 		if(topOfMe == null && topOfMe2 == null)
 		{
 			compo.back.collisionU = false;
 		}
 		
 		
 		
 		
 		
 		
 		
 	}
 	/**Checks the object to the right of mario
 	 *@param center mario's x center posisition
 	 *@param top mario's y position
 	 *@param bottom mario's bottom position
 	 *@param left weather to look top or bottom
 	 *@return the object the is there
 	 */
 	public Object collisionDetectionRight(int center,int top, int bottom, boolean left) 
 	{
 		if(left)
 		{
	 			 		
	 		int col = (int)((center+17)/32); 
	 		int row = -1; 
	 			
	 		if(compo.mario.isBig)
	 		{
	 		
	 			row = (int)((top+2)/32) + 1;
	 			
	 		}
	 		else
	 		{
	 			
	 			row = (int)((bottom+15)/32);
	 		}
	 		
	 		
	 		try
	 		{
	 			if(compo.mario.isBig)
	 			{
	 				if(everywhere[row][col] != null)
	 				{
	 					return everywhere[row][col];
	 				}
	 			}
	 			if(everywhere[row][col] != null)
	 			{
	 				return everywhere[row][col];
	 			}
	 			else
	 			{
	 				return null;
	 			}
	 		}
	 		catch(ArrayIndexOutOfBoundsException e)
	 		{
	 			return null;
	 		}	
 		}
 		else
 		{
 			
	 		
	 		int col = (int)((center+17)/32);  
	 			
	 		int row  = -1;
	 		
	 		if(compo.mario.isBig)
	 		{
	 			
	 			row = (int)((bottom-2)/32);
	 			
	 		}
	 		else
	 		{
	 			
	 			row = (int)((bottom-15)/32);
	 		}
	 		
	 		
	 		try
	 		{
	 			
	 			if(compo.mario.isBig)
	 			{
	 				if(everywhere[row][col] != null)
	 				{
	 					return everywhere[row][col];
	 				}
	 			}
	 			if(everywhere[row][col] != null)
	 			{
	 				return everywhere[row][col];
	 			}
	 			else
	 			{
	 				return null;
	 			}
	 		}
	 		catch(ArrayIndexOutOfBoundsException e)
	 		{
	 			return null;
	 		}	
 		}
 		
 		 		
 	}
    
    /**Checks the object to the left of mario
 	 *@param center mario's x center posisition
 	 *@param top mario's y position
 	 *@param bottom mario's bottom position
 	 *@param left weather to look top or bottom
 	 *@return the object the is there
 	 */
    public Object collisionDetectionLeft(int center, int top, int bottom, boolean left)
    {
    	if(left)
    	{
	    	int row  = -1;
    		int col = (int)((center-17)/32);  
	 		
	 		if(compo.mario.isBig)
	 		{
	 		
	 			row = (int)((top+2)/32) + 1;
	 			
	 		}
	 		else
	 		{
	 			
	 			row = (int)((bottom+15)/32);
	 		}
	 		
	 		
	 		try
	 		{
	 			if(compo.mario.isBig)
	 			{
	 				if(everywhere[row][col] != null)
	 				{
	 					return everywhere[row][col];
	 				}
	 			}
	 			if(everywhere[row][col] != null)
	 			{
	 				return everywhere[row][col];
	 			}
	 			else
	 			{
	 				return null;
	 			}
	 		}
	 		catch(ArrayIndexOutOfBoundsException e)// makes sure not leaving the left of the screen
	 		{
	 			return null;
	 		}
    	}
    	else
    	{
    		
    		int col = (int)((center-17)/32);  
    			
	 		int row  = -1;
	 		if(compo.mario.isBig)
	 		{
	 			
	 			row = (int)((bottom-2)/32);
	 			
	 		}
	 		else
	 		{
	 			
	 			row = (int)((bottom-15)/32);
	 		}
	 		
	 		
	 		try
	 		{
	 			if(compo.mario.isBig)
	 			{
	 				if(everywhere[row][col] != null)
	 				{
	 					return everywhere[row][col];
	 				}
	 			}
	 			if(everywhere[row][col] != null)
	 			{
	 				return everywhere[row][col];
	 			}
	 			else
	 			{
	 				return null;
	 			}
	 		}
	 		catch(ArrayIndexOutOfBoundsException e)// makes sure not leaving the left of the screen
	 		{
	 			return null;
	 		}
    	}
    	
    }
    
    /**Checks the object to the top of mario
 	 *@param center mario's x center posisition
 	 *@param top mario's y position
  	 *@return the object the is there
 	 */
    public Object collisionDetectionTopCenter(int center, int top)
    {
	   	int col = (int)((center)/32);
    	int row = (int)((top + 17 )/32);
    	
    	try
    	{
    		
    		if(everywhere[row][col] != null)
    		{
    			return everywhere[row][col];
    		}
    		else
    		{
    			return null;
    		}
    	}
    	catch(ArrayIndexOutOfBoundsException e)
 		{
 			return null;
 		}
    }
    
     /**Checks the object to the top left and top right of mario
 	 *@param center mario's x center posisition
 	 *@param top mario's y position
 	 *@param left weather to look left or right
  	 *@return the object the is there
 	 */
    public Object collisionDetectionTop(int center, int top, boolean left)
    {
    	if(left)
    	{
	    	int col = (int)((center+14)/32);
	    	int row = (int)((top + 17 )/32);
	    	
	    	try
	    	{
	    		
	    		if(everywhere[row][col] != null)
	    		{
	    			return everywhere[row][col];
	    		}
	    		else
	    		{
	    			return null;
	    		}
	    	}
	    	catch(ArrayIndexOutOfBoundsException e)
	 		{
	 			return null;
	 		}
    	}
    	else
    	{
    		int col = (int)((center-14)/32);
	    	int row = (int)((top + 17 )/32);
	    	
	    	try
	    	{
	    		
	    		if(everywhere[row][col] != null)
	    		{
	    			return everywhere[row][col];
	    		}
	    		else
	    		{
	    			return null;
	    		}
	    	}
	    	catch(ArrayIndexOutOfBoundsException e)
	 		{
	 			return null;
	 		}
    	}
    	
    }
    
     /**Checks the object to the top left and top right of mario
 	 *@param center mario's x center posisition
 	 *@param bottom mario's y position
 	 *@param left weather to look left or right
  	 *@return the object the is there
 	 */
    public Object collisionDetectionBottom(int center, int bottom, boolean left)
    {
    	if(left)
    	{
	    	int col = ((center + 14)/32);
	    	int row = ((bottom+17)/32);
	    	
	    	try
	    	{
	    		
	    		if(everywhere[row][col] != null)
	    		{
	    			return everywhere[row][col];
	    		}
	    		else
	    		{
	    			return null;
	    		}
	    	}
	    	catch(ArrayIndexOutOfBoundsException e)
	 		{
	 			return null;
	 		}
    	}
    	else
    	{
    		int col = ((center - 14)/32);
	    	int row = ((bottom+17)/32);
	    	
	    	try
	    	{
	    		
	    		if(everywhere[row][col] != null)
	    		{
	    			return everywhere[row][col];
	    		}
	    		else
	    		{
	    			return null;
	    		}
	    	}
	    	catch(ArrayIndexOutOfBoundsException e)
	 		{
	 			return null;
	 		}	
    	}
    	
    	
    	
    	
    }
    
    /**
     *checks to see if the level can scroll
     */
    public boolean scrollable()  
    {
    	if (rightBorder > levelWidth)
    	{
    		return false;
    	}
    	else
    		return true;
    }
    
    /**
     *scrolls everything the speed that mario is moving
     *@param marioSpeed the speed to scroll everything
     */
    public void scrollEverything(int marioSpeed)
    {
    	rightBorder += marioSpeed;
    	leftBorder += marioSpeed;
    	
    	for(int i = 0; i < everything.size(); i++)
    	{
    		if(everything.get(i) instanceof Physical)
    			((Physical)everything.get(i)).ScrollingAwareness(marioSpeed);
    		if(everything.get(i) instanceof Moveable)
    			((Moveable)everything.get(i)).ScrollingAwareness(marioSpeed);
    	}
    	
    	
    }
    
    /**
     *Paints everything in the level
     *@param g the graphics component
     */
    public void paintLevel(Graphics g)
	{
		try
		{
		
			for(int i=0; i<everything.size(); i++)
			{
				if(everything.get(i) instanceof Physical)
    			((Physical)	everything.get(i)).physicalPaint(g);
    			if(everything.get(i) instanceof Moveable)
    			((Moveable)	everything.get(i)).physicalPaint(g);
				
			}
		}
		catch(Exception ex)
		{
			
		}
	}
    
    /**
     *Updates the two arrays containing all the level information
     *@param col the collum to be changed
     *@param row the row to be changed
     *@param type what to change it to
     *@param index where in the arraylist to make the change
     */
    public void updateEvery(int col, int row,  String type, int index) throws IOException
    {
    	
    	int actuals = 0;
    	
    			if(type.equals("."))  //Air
    			{
    				everywhere[row][col] = null;
    			}
    			else if(type.equals("z"))  //UpperWorldGround
    			{
    				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("Ground.png");
					InputStream is = new BufferedInputStream((yes));
					
    				BufferedImage pic =  ImageIO.read(is);
    				//BufferedImage pic = ImageIO.read(new File("Ground.png"));
    			Ground	phys = new Ground(col*32, row*32, pic, compo,      1    );
    				everywhere[row][col] = phys;
    				actuals++;
    			}
    			else if(type.equals("Z"))  //LowerWorldGround
    			{
    				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("BlueGround.png");
					InputStream is = new BufferedInputStream((yes));
    				BufferedImage pic =  ImageIO.read(is);

    			//	BufferedImage pic = ImageIO.read(new File("BlueGround.png"));
    			Ground	phys = new Ground(col*32, row*32, pic, compo,       2   );
    				everywhere[row][col] = phys;
    				actuals++;
    			}
    			else if(type.equals("x"))  //AboweBowser
    			{
    				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("AboveBowser.png");
					InputStream is = new BufferedInputStream((yes));
					
    				BufferedImage pic =  ImageIO.read(is);
    				
    				//BufferedImage pic = ImageIO.read(new File("AboveBowser.png"));
    			Ground	phys = new Ground(col*32, row*32, pic, compo,     3     );
    				everywhere[row][col] = phys;
    				actuals++;
    			}
    			else if(type.equals("X"))  //Block
    			{
    				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("Block.png");
					InputStream is = new BufferedInputStream((yes));
					
    				BufferedImage pic =  ImageIO.read(is);
    				
    				//BufferedImage pic = ImageIO.read(new File("Block.png"));
    			Ground	phys = new Ground(col*32, row*32, pic, compo,      4    );
    				everywhere[row][col] = phys;
    				actuals++;
    			}
    			else if(type.equals("v"))  //DungeonBrick
    			{
    				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("DungeonBrick.png");
					InputStream is = new BufferedInputStream((yes));
					
    				BufferedImage pic =  ImageIO.read(is);
    			//	BufferedImage pic = ImageIO.read(new File("DungeonBrick.png"));
    			Ground	phys = new Ground(col*32, row*32, pic, compo,     5     );
    				everywhere[row][col] = phys;
    				actuals++;
    			}
    			else if(type.equals("V"))  //InvisibleWall
    			{
    					InputStream yes = this.getClass().getClassLoader().getResourceAsStream("invisible.png");
					InputStream is = new BufferedInputStream((yes));
					
    				BufferedImage pic =  ImageIO.read(is);
    			//	BufferedImage pic = ImageIO.read(new File("invisible.png"));
    			Ground	phys = new Ground(col*32, row*32, pic, compo,      6    );
    				everywhere[row][col] = phys;
    				actuals++;
    			}
    			else if(type.equals("q"))  //QuestionCoin
    			{
    					InputStream yes = this.getClass().getClassLoader().getResourceAsStream("Question.png");
					InputStream is = new BufferedInputStream((yes));
					
    				BufferedImage pic =  ImageIO.read(is);
    				
    				//BufferedImage pic = ImageIO.read(new File("Question.png"));
    			Metal	phys = new Metal(col*32, row*32, pic, compo,   7       );
    				everywhere[row][col] = phys;
    				actuals++;
    			}
    			else if(type.equals("m"))  //MushroomCoin
    			{
    				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("Question.png");
					InputStream is = new BufferedInputStream((yes));
					
    				BufferedImage pic =  ImageIO.read(is);
    				
    				//BufferedImage pic = ImageIO.read(new File("Question.png"));
    			Metal	phys = new Metal(col*32, row*32, pic, compo,     9     );
    				everywhere[row][col] = phys;
    				actuals++;
    			}
    			else if(type.equals("i"))  //DeadBronze - Invisible
    			{
    				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("invisible.png");
					InputStream is = new BufferedInputStream((yes));
					
    				BufferedImage pic =  ImageIO.read(is);
    					
    				//BufferedImage pic = ImageIO.read(new File("invisible.png"));
    			Metal	phys = new Metal(col*32, row*32, pic, compo,     11     );
    				everywhere[row][col] = phys;
    				actuals++;
    			}
    			else if(type.equals("I"))  //DeadBronze - Visible
    			{
    				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("QuestionDead.png");
					InputStream is = new BufferedInputStream((yes));
					
    				BufferedImage pic =  ImageIO.read(is);
    					
    				
    			//	BufferedImage pic = ImageIO.read(new File("QuestionDead.png"));
    			Metal	phys = new Metal(col*32, row*32, pic, compo,     12     );
    				everywhere[row][col] = phys;
    				actuals++;
    			}
    			else if(type.equals("e"))  //OneUp - invisible
    			{
    				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("invisible.png");
					InputStream is = new BufferedInputStream((yes));
					
    				BufferedImage pic =  ImageIO.read(is);
    					
    			//	BufferedImage pic = ImageIO.read(new File("invisible.png"));
    			Metal	phys = new Metal(col*32, row*32, pic, compo,     13     );
    				everywhere[row][col] = phys;
    				actuals++;
    			}
    			else if(type.equals("*"))  //Star
    			{
    				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("Question.png");
					InputStream is = new BufferedInputStream((yes));
					
    				BufferedImage pic =  ImageIO.read(is);
    				
    				//BufferedImage pic = ImageIO.read(new File("Question.png"));
    			Metal	phys = new Metal(col*32, row*32, pic, compo,      15    );
    				everywhere[row][col] = phys;
    				actuals++;
    			}
    			else if(type.equals("s"))  //DeadSteel - Invisible
    			{
    				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("invisible.png");
					InputStream is = new BufferedInputStream((yes));
					
    				BufferedImage pic =  ImageIO.read(is);
    				
    			//	BufferedImage pic = ImageIO.read(new File("invisible.png"));
    			Metal	phys = new Metal(col*32, row*32, pic, compo,    17      );
    				everywhere[row][col] = phys;
    				actuals++;
    			}
    			else if(type.equals("S"))  //DealSteel - Visible
    			{
    				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("SteelBlock.png");
					InputStream is = new BufferedInputStream((yes));
					
    				BufferedImage pic =  ImageIO.read(is);
    				
    				//BufferedImage pic = ImageIO.read(new File("SteelBlock.png"));
    			Metal	phys = new Metal(col*32, row*32, pic, compo,     18     );
    				everywhere[row][col] = phys;
    				actuals++;
    			}
    			else if(type.equals("B"))  // Brick - UpperWorld
    			{
    				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("Brick.png");
					InputStream is = new BufferedInputStream((yes));
					
    				BufferedImage pic =  ImageIO.read(is);
    				
    				//BufferedImage pic = ImageIO.read(new File("Brick.png"));
    			Brick	phys = new Brick(col*32, row*32, pic, compo,         19 );
    				everywhere[row][col] = phys;
    				actuals++;
    			}
    			else if(type.equals("b"))  //Brick - LowerWorld
    			{
    				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("BlueBrick.png");
					InputStream is = new BufferedInputStream((yes));
					
    				BufferedImage pic =  ImageIO.read(is);
    				
    				//BufferedImage pic = ImageIO.read(new File("BlueBrick.png"));
    			Brick	phys = new Brick(col*32, row*32, pic, compo,        20  );
    				everywhere[row][col] = phys;
    				actuals++;
    			}
//    			else if(type.equals("h"))  // Hammer
//    			{
//    				BufferedImage pic = ImageIO.read(new File("Hammer.png"));
//    			Hammer	phys = new Hammer(col*32, row*32, pic, compo,       21   );
//    				everywhere[row][col] = phys;
//    				actuals++;
//    			}
    			else if(type.equals("c"))    		//coin
    			{
    				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("coin.png");
					InputStream is = new BufferedInputStream((yes));
					
    				BufferedImage pic =  ImageIO.read(is);	
    				//BufferedImage pic = ImageIO.read(new File("coin.png"));
    			Collectable	phys = new Collectable(col*32, row*32, pic, compo,       23   );
    				everywhere[row][col] = phys;
    				actuals++;
    			}
    			else if(type.equals("M"))  // Mushroom
    			{
    				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("Mushroom.png");
					InputStream is = new BufferedInputStream((yes));
					
    				BufferedImage pic =  ImageIO.read(is);	
    				
    				//BufferedImage pic = ImageIO.read(new File("Mushroom.png"));
    			Collectable	phys = new Collectable(col*32, row*32, pic, compo,    25      );
    				everywhere[row][col] = phys;
    				actuals++;
    			}
    			else if(type.equals("E"))  // OneUp
    			{
    				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("OneUp.png");
					InputStream is = new BufferedInputStream((yes));
					
    				BufferedImage pic =  ImageIO.read(is);
    				
    				//BufferedImage pic = ImageIO.read(new File("OneUp.png"));
    			Collectable	phys = new Collectable(col*32, row*32, pic, compo,       27   );
    				everywhere[row][col] = phys;
    				actuals++;
    			}
    			else if(type.equals("F")) // Flower
    			{
    				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("flower.png");
					InputStream is = new BufferedInputStream((yes));
					
    				BufferedImage pic =  ImageIO.read(is);
    				
    				//BufferedImage pic = ImageIO.read(new File("flower.png"));
    			Collectable	phys = new Collectable(col*32, row*32, pic, compo,     29     );
    				everywhere[row][col] = phys;
    				actuals++;
    			}
    			else if(type.equals("p"))  // WarpPipe - Body -Left  up
    			{
    				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("BottomLeftTube.png");
					InputStream is = new BufferedInputStream((yes));
					
    				BufferedImage pic =  ImageIO.read(is);	
    				//BufferedImage pic = ImageIO.read(new File("BottomLeftTube.png"));
    			WarpPipeBody	phys = new WarpPipeBody(col*32, row*32, pic, compo,    31      );
    				everywhere[row][col] = phys;
    				actuals++;
    			}
    			else if(type.equals("P")) // WarpPipe - Body leftSide
    			{
    				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("SideLeftTubeBody.png");
					InputStream is = new BufferedInputStream((yes));
					
    				BufferedImage pic =  ImageIO.read(is);	
    				
    				//BufferedImage pic = ImageIO.read(new File("SideLeftTubeBody.png"));
    			WarpPipeBody	phys = new WarpPipeBody(col*32, row*32, pic, compo,     32     );
    				everywhere[row][col] = phys;
    				actuals++;
    			}
    			else if(type.equals("o")) // WarpPipe - Body - Right up
    			{
    			InputStream yes = this.getClass().getClassLoader().getResourceAsStream("BottomRightTube.png");
					InputStream is = new BufferedInputStream((yes));
					
    				BufferedImage pic =  ImageIO.read(is);	
    				
    				//BufferedImage pic = ImageIO.read(new File("BottomRightTube.png"));
    			WarpPipeBody	phys = new WarpPipeBody(col*32, row*32, pic, compo,   33       );
    				everywhere[row][col] = phys;
    				actuals++;
    			}
    			else if(type.equals("O")) // WarpPipe - Body - Right Side
    			{
    				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("SideRightTubeBody.png");
					InputStream is = new BufferedInputStream((yes));
					
    				BufferedImage pic =  ImageIO.read(is);
    				
    				//BufferedImage pic = ImageIO.read(new File("SideRightTubeBody.png"));
    			WarpPipeBody	phys = new WarpPipeBody(col*32, row*32, pic, compo,     34     );
    				everywhere[row][col] = phys;
    				actuals++;
    			}
    			else if(type.equals("l")) // WarpPipe - Entrance - Left up
    			{
    					InputStream yes = this.getClass().getClassLoader().getResourceAsStream("TopLeftTube.png");
					InputStream is = new BufferedInputStream((yes));
					
    				BufferedImage pic =  ImageIO.read(is);
    				
    				//BufferedImage pic = ImageIO.read(new File("TopLeftTube.png"));
    			WarpPipeEntrance	phys = new WarpPipeEntrance(col*32, row*32, pic, compo,     35     );
    				everywhere[row][col] = phys;
    				actuals++;
    			}
    			else if(type.equals("L")) // WarpPipe - Entrance - Left side
    			{
    				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("SideLeftTubeTop.png");
					InputStream is = new BufferedInputStream((yes));
					
    				BufferedImage pic =  ImageIO.read(is);
    				
    				//BufferedImage pic = ImageIO.read(new File("SideLeftTubeTop.png"));
    			WarpPipeEntrance	phys = new WarpPipeEntrance(col*32, row*32, pic, compo,      36    );
    				everywhere[row][col] = phys;
    				actuals++;
    			}
    			else if(type.equals("k")) // WarpPipe - Entrance -  Right up
    			{
    				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("TopRightTube.png");
					InputStream is = new BufferedInputStream((yes));
					
    				BufferedImage pic =  ImageIO.read(is);
    				
    				//BufferedImage pic = ImageIO.read(new File("TopRightTube.png"));
    			WarpPipeEntrance	phys = new WarpPipeEntrance(col*32, row*32, pic, compo,      37    );
    				everywhere[row][col] = phys;
    				actuals++;
    			}
    			else if(type.equals("K")) // WarpPipe - Entrance -  Right Side
    			{
    				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("SideLeftTubeTop.png");
					InputStream is = new BufferedInputStream((yes));
					
    				BufferedImage pic =  ImageIO.read(is);
    				
    				
    				//BufferedImage pic = ImageIO.read(new File("SideLeftTubeTop.png"));
    			WarpPipeEntrance	phys = new WarpPipeEntrance(col*32, row*32, pic, compo,      38    );
    				everywhere[row][col] = phys;
    				actuals++;
    			}
    			else if(type.equals(">")) // WarpPipe - Teleport - Right up
    			{
    				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("StandingRight.png");
					InputStream is = new BufferedInputStream((yes));
					
    				BufferedImage pic =  ImageIO.read(is);
    				
    				
    				//BufferedImage pic = ImageIO.read(new File("StandingRight.png"));
    			WarpPipeEntrance	phys = new WarpPipeEntrance(col*32, row*32, pic, compo,    39      );
    				everywhere[row][col] = phys;
    				actuals++;
    			}
    			else if(type.equals("^")) // WarpPipe - Teleport - Right side
    			{
    				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("StandingRight.png");
					InputStream is = new BufferedInputStream((yes));
					
    				BufferedImage pic =  ImageIO.read(is);
    				
    				
    				//BufferedImage pic = ImageIO.read(new File("StandingRight.png"));
    			WarpPipeEntrance	phys = new WarpPipeEntrance(col*32, row*32, pic, compo,     40     );
    				everywhere[row][col] = phys;
    				actuals++;
    			}
    			else if(type.equals("|"))  //Flagpole
    			{
    				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("invisible.png");
					InputStream is = new BufferedInputStream((yes));
					
    				BufferedImage pic =  ImageIO.read(is);
    				
    				//BufferedImage pic = ImageIO.read(new File("invisible.png"));
    			FlagPole	phys = new FlagPole(col*32, row*32, pic, compo,      41    );
    				everywhere[row][col] = phys;
    				actuals++;
    			}
    			else if(type.equals("1"))  // Goomba
    			{
    				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("goomba.png");
					InputStream is = new BufferedInputStream((yes));
					
    				BufferedImage pic =  ImageIO.read(is);
    				
    				//BufferedImage pic = ImageIO.read(new File("goomba.png"));   // ANIMATE!!!!
    			Goomba	phys = new Goomba(col*32, row*32, pic, compo,     100      );
    				everywhere[row][col] = phys;
    				actuals++;
    			}
    			else if(type.equals("="))  // BowserPlatform
    			{
   					InputStream yes = this.getClass().getClassLoader().getResourceAsStream("BowserPlankBlock.png");
					InputStream is = new BufferedInputStream((yes));
					
    				BufferedImage pic =  ImageIO.read(is);
    				
    				//BufferedImage pic = ImageIO.read(new File("BowserPlankBlock.png"));
    			Ground	phys = new Ground(col*32, row*32, pic, compo,      45    );
    				everywhere[row][col] = phys;
    				actuals++;
    			}
    			else if(type.equals("{"))  // BowserPlatform
    			{
    				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("LeftThree.png");
					InputStream is = new BufferedInputStream((yes));
					
    				BufferedImage pic =  ImageIO.read(is);
    				//BufferedImage pic = ImageIO.read(new File("LeftThree.png"));
    			Ground	phys = new Ground(col*32, row*32, pic, compo,      100    );
    				everywhere[row][col] = phys;
    				actuals++;
    			}
    			else if(type.equals(","))  // BowserPlatform
    			{
    					InputStream yes = this.getClass().getClassLoader().getResourceAsStream("MiddleG.png");
					InputStream is = new BufferedInputStream((yes));
					
    				BufferedImage pic =  ImageIO.read(is);
    				//BufferedImage pic = ImageIO.read(new File("MiddleG.png"));
    			Ground	phys = new Ground(col*32, row*32, pic, compo,      101    );
    				everywhere[row][col] = phys;
    				actuals++;
    			}
    			else if(type.equals("}"))  // BowserPlatform
    			{
    					InputStream yes = this.getClass().getClassLoader().getResourceAsStream("RightG.png");
					InputStream is = new BufferedInputStream((yes));
					
    				BufferedImage pic =  ImageIO.read(is);
    				
    				//BufferedImage pic = ImageIO.read(new File("RightG.png"));
    			Ground	phys = new Ground(col*32, row*32, pic, compo,      102    );
    				everywhere[row][col] = phys;
    				actuals++;
    			}
    			else if(type.equals("8"))  // BowserPlatform
    			{
    				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("LeftP.png");
					InputStream is = new BufferedInputStream((yes));
					
    				BufferedImage pic =  ImageIO.read(is);
    				
    			//	BufferedImage pic = ImageIO.read(new File("LeftP.png"));
    			Ground	phys = new Ground(col*32, row*32, pic, compo,      102    );
    				everywhere[row][col] = phys;
    				actuals++;
    			}
    			else if(type.equals("9"))  // BowserPlatform
    			{
    				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("MiddleP.png");
					InputStream is = new BufferedInputStream((yes));
					
    				BufferedImage pic =  ImageIO.read(is);
    				
    				//BufferedImage pic = ImageIO.read(new File("MiddleP.png"));
    			Ground	phys = new Ground(col*32, row*32, pic, compo,      102    );
    				everywhere[row][col] = phys;
    				actuals++;
    			}
    			else if(type.equals("0"))  // BowserPlatform
    			{
    				InputStream yes = this.getClass().getClassLoader().getResourceAsStream("RightP.png");
					InputStream is = new BufferedInputStream((yes));
					
    				BufferedImage pic =  ImageIO.read(is);
    				
    				//BufferedImage pic = ImageIO.read(new File("RightP.png"));
    			Ground	phys = new Ground(col*32, row*32, pic, compo,      102    );
    				everywhere[row][col] = phys;
    				actuals++;
    			}
    		/*	else if(type.equals(""))
    			{
    				BufferedImage pic = ImageIO.read(new File("StandingRight.png"));
    				phys = new (col*32, row*32, pic, compo,          );
    				everywhere[row][col] = phys;
    				actuals++;
    			}
    			else if(type.equals(""))
    			{
    				BufferedImage pic = ImageIO.read(new File("StandingRight.png"));
    				phys = new (col*32, row*32, pic, compo,          );
    				everywhere[row][col] = phys;
    				actuals++;
    			}
    			else if(type.equals(""))
    			{
    				BufferedImage pic = ImageIO.read(new File("StandingRight.png"));
    				phys = new (col*32, row*32, pic, compo,          );
    				everywhere[row][col] = phys;
    				actuals++;
    			}*/
    		
    			
    			
    			if(everywhere[row][col] instanceof Physical && everywhere[row][col] != null)
    			{
    				if(everything.get(index) instanceof Physical)
    				{
    					((Physical)everywhere[row][col]).setIndex(index);
	    				((Physical)everywhere[row][col]).xLeftBound = ((Physical)everything.get(index)).xLeftBound;
	    				((Physical)everywhere[row][col]).yTopBound = ((Physical)everything.get(index)).yTopBound;
	    				((Physical)everywhere[row][col]).xRightBound = ((Physical)everything.get(index)).xRightBound;
	    				((Physical)everywhere[row][col]).yBottomBound = ((Physical)everything.get(index)).yBottomBound;
	    				everything.set(index, everywhere[row][col]);
	    				
	    				
    				}
    				else
    				{
    					((Physical)everywhere[row][col]).setIndex(index);
	    				((Physical)everywhere[row][col]).xLeftBound = ((Moveable)everything.get(index)).xLeftBound;
	    				((Physical)everywhere[row][col]).yTopBound = ((Moveable)everything.get(index)).yTopBound;
	    				((Physical)everywhere[row][col]).xRightBound = ((Moveable)everything.get(index)).xRightBound;
	    				((Physical)everywhere[row][col]).yBottomBound = ((Moveable)everything.get(index)).yBottomBound;
	    				everything.set(index, everywhere[row][col]);
	    				
	    				
    				}    			
    			}
    			
//    			if(everywhere[row][col] instanceof Moveable && everywhere[row][col] != null)
//    			{
//    				everything.add(everywhere[row][col]);
//    				((Moveable)everywhere[row][col]).setIndex(everything.size());
//    			}
    			
    			
    		
    }   
    
    /**
     *@return the current world number
     */	
    public int getWorld()
    {
    	return w;
    }
    
     /**
     *@return the current level number
     */	
    public int getLevel()
    {
    	return l;
    }
    
}
