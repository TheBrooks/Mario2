import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;


public class WorldItem
{
	
	private int blockID;
	private BufferedImage pic;
	
	public WorldItem(char letter)
	{
		switch(letter)
		{
			case('.'):
			{	blockID = 0;
				break;	}
			case('b'):
			{	blockID = 1;
				break;	}
			case('B'):
			{	blockID = 2;
				break;	}
			case('#'):
			{	blockID = 3;
				break;	}
			case('x'):
			{	blockID = 4;
				break;	}
			case('X'):
			{	blockID = 5;
				break;	}
			case('{'):
			{	blockID = 6;
				break;	}
			case('_'):
			{	blockID = 7;
				break;	}
			case('}'):
			{	blockID = 8;
				break;	}
			case('['):
			{	blockID = 9;
				break;	}
			case('-'):
			{	blockID = 10;
				break;	}
			case(']'):
			{	blockID = 11;
				break;	}
			case('d'):
			{	blockID = 12;
				break;	}
			case('<'):
			{	blockID = 13;
				break;	}
			case('>'):
			{	blockID = 14;
				break;	}
			case('m'):
			{	blockID = 15;
				break;	}
			case(','):
			{	blockID = 16;
				break;	}
			case('('):
			{	blockID = 17;
				break;	}
			case(')'):
			{	blockID = 18;
				break;	}
			case('9'):
			{	blockID = 19;
				break;	}
			case('0'):
			{	blockID = 20;
				break;	}
			case('?'):
			{	blockID = 21;
				break;	}
			case('@'):
			{	blockID = 22;
				break;	}
			case('&'):
			{	blockID = 23;
				break;	}
			case('$'):
			{	blockID = 24;
				break;	}
			case('^'):
			{	blockID = 25;
				break;	}
			case('i'):
			{	blockID = 50;
				break;	}
			case('h'):
			{	blockID = 51;
				break;	}
			case('n'):
			{	blockID = 52;
				break;	}
			case(':'):
			{	blockID = 53;
				break;	}
			case('\"'):
			{	blockID = 54;
				break;	}
			case(';'):
			{	blockID = 55;
				break;	}
			case('\''):
			{	blockID = 56;
				break;	}
			default:
			{	blockID = 0;
				break;	}
		}
		
		
		assignImage();
	}
	
	public void assignImage() 
	{
		try
		{
			File newFile;
			switch(blockID)
			{
				case(0):													/////invisible
				{	newFile = new File("Images/WorldItems/invsible.png");
					break;	}
				case(1):													/////GroundBlocks
				{	newFile = new File("Images/WorldItems/Block.png");
				break;	}
				case(2):										
				{	newFile = new File("Images/WorldItems/BlueGround.png");
				break;	}
				case(3):										
				{	newFile = new File("Images/WorldItems/Ground.png");
				break;	}
				case(4):													//////Bricks
				{	newFile = new File("Images/WorldItems/Brick.png");
				break;	}
				case(5):										
				{	newFile = new File("Images/WorldItems/BlueBrick.png");
				break;	}
				case(6):													////////air Platforms
				{	newFile = new File("Images/WorldItems/LeftP.png");
				break;	}
				case(7):										
				{	newFile = new File("Images/WorldItems/MiddleP.png");
				break;	}
				case(8):										
				{	newFile = new File("Images/WorldItems/RightP.png");
				break;	}
				case(9):										
				{	newFile = new File("Images/WorldItems/LeftG.png");
				break;	}
				case(10):										
				{	newFile = new File("Images/WorldItems/MiddleG.png");
				break;	}
				case(11):										
				{	newFile = new File("Images/WorldItems/RightG.png");
				break;	}
				case(12):													//////dungeon brick
				{	newFile = new File("Images/WorldItems/DungeonBrick.png");
				break;	}
				case(13):													///////Tube -------  Upright
				{	newFile = new File("Images/WorldItems/TopLeftTube.png");
				break;	}
				case(14):										
				{	newFile = new File("Images/WorldItems/TopRightTube.png");
				break;	}
				case(15):										
				{	newFile = new File("Images/WorldItems/BottomLeftTube.png");
				break;	}
				case(16):										
				{	newFile = new File("Images/WorldItems/BottomRightTube.png");
				break;	}
				case(17):														/////////Tube ------- On side
				{	newFile = new File("Images/WorldItems/SideRightTubeTop.png");
				break;	}
				case(18):										
				{	newFile = new File("Images/WorldItems/SideRightTubeBody.png");
				break;	}
				case(19):										
				{	newFile = new File("Images/WorldItems/SideLeftTubeTop.png");
				break;	}
				case(20):										
				{	newFile = new File("Images/WorldItems/SideLeftTubeBody.png");
				break;	}
				case(21):														////////questions, metals, coins and power-ups
				{	newFile = new File("Images/WorldItems/Question.png");
				break;	}
				case(22):										
				{	newFile = new File("Images/WorldItems/QuestionDead.png");
				break;	}
				case(23):										
				{	newFile = new File("Images/WorldItems/SteelBlock.png");
				break;	}
				case(24):										
				{	newFile = new File("Images/WorldItems/coin.png");
				break;	}
				case(25):										
				{	newFile = new File("Images/WorldItems/flower.png");
				break;	}
			
				case(50):										//one up - invisible
				{	newFile = new File("Images/WorldItems/invisible.png");
				break;	}
				case(51):										//one up - brick
				{	newFile = new File("Images/WorldItems/Brick.png");
				break;	}
				case(52):										//one up - blue brick
				{	newFile = new File("Images/WorldItems/BlueBrick.png");
				break;	}
				case(53):													///////Tube -------  Upright  -- TELEPORT
				{	newFile = new File("Images/WorldItems/TopLeftTube.png");
				break;	}
				case(54):										
				{	newFile = new File("Images/WorldItems/TopRightTube.png");
				break;	}
				case(55):														/////////Tube ------- On side  -- TELEPORT
				{	newFile = new File("Images/WorldItems/SideRightTubeTop.png");
				break;	}
				case(56):										
				{	newFile = new File("Images/WorldItems/SideRightTubeTop.png");
				break;	}
				case(57):										
				{	newFile = new File("Images/WorldItems/SideLeftTubeBody.png");
				break;	}
				default:
				{	newFile = new File("Images/WorldItems/invsible.png");	}
				
			}
			
			pic = ImageIO.read(newFile);
		
		}
		catch (IOException e)
		{
			System.out.println(blockID + " Image not found");
		}
	}
	

}
