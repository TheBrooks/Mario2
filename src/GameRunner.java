import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.*;

public class GameRunner extends JPanel    // this class will have to have the collision detection
{

	private Worlds world;
	private File f;
	private String str;
	
	public static final String LEVEL_TEXT_FILE = "blockWorld.txt";
	
	public GameRunner()
	{
		f = new File(GameRunner.LEVEL_TEXT_FILE);
		world = null;
		str = getNextLevel();
		if(!str.equals(""));
			world = new Worlds(str);
			
		passLevel();
		
		
		
		//when you hit the flag pole you need to call world = new worlds(getNextLevel)
		
	}
	
	public String getNextLevel()
	{
		try
		{
			Scanner in = new Scanner(f);
			Scanner foreshadow = new Scanner(f);
			String ref = "";
			if(world.equals(null))
			{
				ref =  in.nextLine();
				ref = ref.substring(ref.indexOf("-")+1);
			}
			else
			{
				ref = "-"+world.getLevelName();
				boolean finished = false;
				if(foreshadow.hasNext())
					foreshadow.nextLine();
				
				while(in.hasNext() && !finished)
				{
					String testLine = in.nextLine();
					String endCheck = foreshadow.nextLine();
					
					if(testLine.equals(ref))
					{
						if(!endCheck.equalsIgnoreCase("LEVEL_REFERENCE_END"))
						{
							ref =  in.nextLine();
							ref = ref.substring(ref.indexOf("-")+1);
							return ref;
						}
					}
					else
					{
						System.out.println("Finished game");
						//they've reached the last level, WTF do i do now????
						//posibly go to high scrore enter pane?
					}
				}
			}
			return ref;
		}
		catch (FileNotFoundException e)
		{
			System.out.println(f);
			e.printStackTrace();
			return "";
		}
	}
	
	public boolean passLevel()
	{
		try
		{
			Scanner in = new Scanner(f);
			String findLevel = "<"+str+">";
			while(in.hasNext())
			{
				if(in.nextLine().equals(findLevel))
				{
					int hieght = in.nextInt();
					int width = in.nextInt();
					world.setDim(hieght, width);
					in.nextLine();
					for(int row = 0; row < hieght; row++)
					{
						for(int col = 0; col < width; col++)
						{
							char[] across = in.nextLine().toCharArray();
							if(across[col] == '.')
								world.add(row, col, new WorldItem(across[col]));
							else
								world.add(row, col, null);
							return true;
						}
					}
				}
			}
			world.setDim(1,1);
			world.add(1,1,new WorldItem('.'));
			return false;
		}
		catch (FileNotFoundException e)
		{
			world.setDim(1,1);
			world.add(1,1,new WorldItem('.'));
			return false;
		}
	}
}
