
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.locks.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

public class MarioStart extends JFrame
{
	
	public JPanel cards;
	public CardLayout cl;
	public MarioStart go = this;
	
	public final static String MAINMENU = "main menu";
	public final static String GAME = "the actual playing game";
	public final static String INSTRUCTIONS = "instructions";
	public final static String HIGHSCORES = "highscores";
	
	public TitleScreen startScreen;
	public StartGame gamePanel;
	public HighScore scorePanel;
	public Instructions instructionPanel;
	
	public int quitNum;
	public JFrame superSmashWindow;
	
	public Lock scrollingPicLock;
	
	
	public MarioStart() throws IOException, ClassNotFoundException
	{
		superSmashWindow = this;
		superSmashWindow.setSize(512,470);
		superSmashWindow.setTitle("Ryan Brooks - SuperSmashBro's");
		superSmashWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		superSmashWindow.setLocationRelativeTo(null);
		superSmashWindow.setResizable(false);
		
		cards = new JPanel();
		cl = new CardLayout();
		cards.setLayout(cl);
		
		scrollingPicLock = new ReentrantLock();
		
		Container content = superSmashWindow.getContentPane();
		
		startScreen = new TitleScreen();
		gamePanel = new StartGame();
		scorePanel = new HighScore();
		instructionPanel = new Instructions();
		
		cards.add(startScreen, MAINMENU);
		cards.add(gamePanel, GAME);
		cards.add(scorePanel, HIGHSCORES);
		cards.add(instructionPanel, INSTRUCTIONS);
		
		content.add(cards, BorderLayout.CENTER);
		
		superSmashWindow.setFocusable(true);
		superSmashWindow.setVisible(true);
		
		superSmashWindow.addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent e)
			{
				if(e.getKeyCode() == KeyEvent.VK_ESCAPE && gamePanel.focused)
				{
					if(quitNum == 0)
					{
						quitNum++;
						gamePanel.focused = false;
						
						//make a paused screen?!?!?!?!?  thatd be awesome
						
						QuitPlaying done = new QuitPlaying();
					}	
				}
			}		
			public void keyReleased(KeyEvent e){}
			public void keyTyped(KeyEvent e){}
		});
		
		
	}
	
	public void cardChange(String str)
	{
		
		
		
		if(str.equals(MarioStart.GAME))
		{
			gamePanel.focused = true;
			startScreen.focused = false;
			instructionPanel.focused = false;
			scorePanel.focused = false;
			
			superSmashWindow.requestFocus();
		}
		else if(str.equals(MarioStart.MAINMENU))
		{			
			gamePanel.focused = false;
			startScreen.focused = true;
			instructionPanel.focused = false;
			scorePanel.focused = false;
			
			startScreen.th = new Thread(startScreen.r);
			startScreen.th.start();
		}
		else if(str.equals(MarioStart.INSTRUCTIONS))
		{
			gamePanel.focused = false;
			startScreen.focused = false;
			instructionPanel.focused = true;
			scorePanel.focused = false;
			superSmashWindow.setSize(512, 240);
		}
		else if(str.equals(MarioStart.HIGHSCORES))
		{
			gamePanel.focused = false;
			startScreen.focused = false;
			instructionPanel.focused = false;
			scorePanel.focused = true;	
			
			scorePanel.t = new Thread(scorePanel.r);
			scorePanel.t.start();
		}
		
		cl.show(cards, str);
	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////                                                                                                                              ///
////     [][][][]  []    []    [][][]  [][][][]  [][][]    []    []   [][][]   [][][][]  [][][][]   [][][]   []    []    [][][]    ///                                 
/////       []     [][]  []  []           []     []    []  []    []  []    []     []        []     []    []  [][]  []  []          ///
////        []     []  [][]    [][]       []     []    []  []    []  []           []        []     []    []  []  [][]    [][]      ///
/////       []     []    []        []     []     [][][]    []    []  []           []        []     []    []  []    []        []    ///
////        []     []    []  []    []     []     []   []   []    []  []    []     []        []     []    []  []    []   []   []    ///
/////    [][][][]  []    []    [][]       []     []    []   [][][]    [][][]      []     [][][][]   [][][]   []    []    [][]      ///                                        
////                                                                                                                               ///
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	private class Instructions extends JPanel
	{
		
		public JPanel instr;
		public JButton back;
		public JTextField keyLeft, keyRight, keyDown, keyUp, keyRun, keyMenu;
		
		public boolean focused = false;
		//public Image instructionImage;
		
		public Instructions() throws IOException
		{
			
			//File f = new File("/Users/dootfiddle/Desktop/Mario 2.0/src/Images/toad.png");
			//instructionImage = ImageIO.read(f);
			
			JPanel blank = new JPanel();
			instr = this;
			instr.setLayout(new BorderLayout());
			
			JPanel buttons = new JPanel(new FlowLayout(FlowLayout.LEFT));
			back = new JButton("Main menu");
			
			buttons.add(back);
			
			back.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					superSmashWindow.setSize(512, 470);
					cardChange(MarioStart.MAINMENU);
				}
				
			});
			
			instr.add(buttons, BorderLayout.NORTH);
			
			JPanel centerDivide = new JPanel(new GridLayout(1,2));
			
			JPanel marioHistory = new JPanel(new BorderLayout());
			JLabel toadPic = new JLabel( new ImageIcon("/Users/dootfiddle/Desktop/Mario 2.0/src/Images/toad.png"));
			marioHistory.add(toadPic, BorderLayout.CENTER);
			
			ButtonControl control = new ButtonControl();
			
			JPanel keys = new JPanel(new GridLayout(6,2));
			keyLeft = new JTextField(""+ButtonControl.DEFAULT_KEY_LEFT, 1);  
			keyRight = new JTextField(""+ButtonControl.DEFAULT_KEY_RIGHT, 1); 
			keyDown = new JTextField(""+ButtonControl.DEFAULT_KEY_DOWN, 1);
			keyUp = new JTextField(""+ButtonControl.DEFAULT_KEY_UP, 1);
			keyRun = new JTextField(""+ButtonControl.DEFAULT_KEY_RUN, 1);
			keyMenu = new JTextField(""+ButtonControl.DEFAULT_KEY_MENU, 3);
			
			JLabel label = new JLabel("Left: ");
			keys.add(label); keys.add(keyLeft);
			
			label = new JLabel("Right: ");
			keys.add(label); keys.add(keyRight);
			
			label = new JLabel("Up: ");
			keys.add(label); keys.add(keyUp);
			
			label = new JLabel("Down: ");
			keys.add(label); keys.add(keyDown);
			
			label = new JLabel("Run: ");
			keys.add(label); keys.add(keyRun);
			
			label = new JLabel("To menu: ");
			keys.add(label); keys.add(keyMenu);
			
			
			
			centerDivide.add(marioHistory);
			centerDivide.add(keys);
			
			instr.add(centerDivide, BorderLayout.CENTER);
			
			JButton resetKeys = new JButton("Default Keys");
			JPanel south = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			south.add(resetKeys);  instr.add(south, BorderLayout.SOUTH);
			
		}
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////                                                                                                 ///
	////       []    []   [][][]   [][][]    []    []        [][][]     [][][]    [][]   [][][]  [][][]   ///
	/////      []    []     []    []     []  []    []       []    []   []    []  []  []  []   [] []       ///               
	////       [][][][]     []    []         [][][][]       []         []        []  []  []   [] []       ///
	/////      []    []     []    []   [][]  []    []        [][][]    []        []  []  [][][]  [][]     ///                        
	////       []    []     []    []     []  []    []             []   []    []  []  []  []   [] []       ///
	/////      []    []   [][][]   [][][]    []    []      [][][][]     [][][]    [][]   []   [] [][][]   ///                                         
	////                                                                                                  /// 
	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private class HighScore extends JPanel
	{
		
		public JPanel highScore, buttons, scores, south;
		public JButton back;
		public GridBagLayout gbl;
		public HighScoreRecorder playerHistory;
		
		public int resetInt = 0;
		public Image highScoreImage;
		
		public Thread t;
		public Runnable r;
		public double xPosition = 0;
		
		
		public boolean focused = false;
		
		public HighScore() throws IOException, ClassNotFoundException
		{
			File newFile = new File("/Users/dootfiddle/Desktop/Mario 2.0/src/Images/mario-1-3.gif");
			highScoreImage = ImageIO.read(newFile);
			
			highScore = this;
			
			highScore.setLayout(new BorderLayout());
			JPanel buttons = new JPanel(new FlowLayout(FlowLayout.LEFT));
			JButton back = new  JButton("Main Menu");
			JButton reset = new JButton("Reset High Scores");
			buttons.add(back);
			buttons.add(reset);
			
			south = new JPanel(new BorderLayout());
			
			playerHistory = new HighScoreRecorder();
			
			draw();	
			highScore.add(buttons, BorderLayout.NORTH);
			highScore.add(south, BorderLayout.CENTER);
			
			highScore.setOpaque(false);
			south.setOpaque(false);
			buttons.setOpaque(false);
			
			
			back.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					stopScrollingPic();
					cardChange(MarioStart.MAINMENU);
				}
				
			});
			
			reset.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					if(resetInt == 0)
					{
						resetInt++;
						ResetQuestion resetHighScores = new ResetQuestion();
					}
						
				}
				
			});
			
			r = new ScrollingPicture(5264);
			System.out.println(r);
			t = new Thread(r);
			
			
		}
		
		public void stopScrollingPic()
		{
			t.interrupt();
			xPosition = 0;
		}
		
		private class ScrollingPicture implements Runnable
		{
		
		public int picLength;
			
			public ScrollingPicture(int x)
			{
				picLength = x;
			}

			public void run()
			{	
				
				try
				{
					while(focused && Math.abs(xPosition)< (picLength - 512))
					{
						xPosition -= .11;
						Thread.sleep(2);
						highScore.repaint();
						highScore.validate();
					}
					t.interrupt();
				}
				catch(InterruptedException e)
				{}
				
			}
		}
		
		public void paintComponent(Graphics g)
		{
			g.drawImage(highScoreImage, (int)xPosition, 0, null);
			
		}
		
		
		public void draw()
		{
			
			JPanel rank = new JPanel(new GridLayout(HighScoreRecorder.TOP_SCORE_LENGTH+1,1));
		
			scores = new JPanel(new GridLayout(HighScoreRecorder.TOP_SCORE_LENGTH+ 1 ,2));
			
			JLabel names = new JLabel("Names");
			JLabel namedScore = new JLabel("Scores");
			scores.add(names);
			scores.add(namedScore); 
			
			names.setForeground(Color.white);
			namedScore.setForeground(Color.white);
			names.setFont(new Font("Monospaced", Font.BOLD, 24));
			namedScore.setFont(new Font("Monospaced", Font.BOLD, 24));
			
			//only JPanels need to be non opaque to see Pictures
			rank.setOpaque(false);
			scores.setOpaque(false);
			
			JLabel rankName = new JLabel();
			JLabel rankScore = new JLabel();
			
			for(int i = 0; i < HighScoreRecorder.TOP_SCORE_LENGTH; i++)
			{
				rankName = new JLabel(playerHistory.getScorer(i));
				rankName.setForeground(Color.white);
				rankName.setFont(new Font("Monospaced", Font.BOLD, 24));
				scores.add(rankName);
				rankScore = new JLabel(playerHistory.getScore(i) + "");
				rankScore.setForeground(Color.white);
				rankScore.setFont(new Font("Monospaced", Font.BOLD, 24));
				scores.add(rankScore);
			}
			
			rankName.setOpaque(false);
			rankScore.setOpaque(false);
			
			rankName = new JLabel("Ranks ");
			rankName.setFont(new Font("Monospaced", Font.BOLD, 24));
			rankName.setForeground(Color.white);
			
			rank.add(rankName);
			for(int i = 1; i <= HighScoreRecorder.TOP_SCORE_LENGTH; i++)
			{
				JLabel rankNum = new JLabel(" "+i+". ");
				rankNum.setForeground(Color.white);
				rankNum.setFont(new Font("Monospaced", Font.BOLD, 24));
				rank.add(rankNum);
			}
			
			south.add(rank, BorderLayout.WEST);
			south.add(scores, BorderLayout.CENTER);
			
			validate();
			repaint();
		}
		
		public void reDraw()
		{
			south.removeAll();
			draw();
		}
		
		
		private class ResetQuestion extends JFrame
		{
			public JButton backToHighScores, cancel;
			public JFrame resetNow;
			
			
			public ResetQuestion()
			{
				resetNow = this;
				
				resetNow.setTitle("Reser High Scores?");
				resetNow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				resetNow.setLocationRelativeTo(null);
				resetNow.setResizable(false);
				
				Container content = resetNow.getContentPane();
				content.setLayout(new GridLayout(2,1));
				
				JPanel text = new JPanel();
				JLabel ask = new JLabel("\tAre you sure you want to reset the high Scores? ");
				text.add(ask);
				
				JPanel south = new JPanel();
				south.setLayout(new FlowLayout(FlowLayout.RIGHT));
				
				backToHighScores = new JButton("Reset");
				JButton cancel = new JButton("Cancel");
				south.add(cancel);
				south.add(backToHighScores);
				
				content.add(text);
				content.add(south);
				
		
				resetNow.pack();
				resetNow.setVisible(true);
				
				backToHighScores.addActionListener(new ActionListener() 
				{public void actionPerformed(ActionEvent eh) 
					{
						resetInt--;
						try
						{
							playerHistory.reset();
						}
						catch (IOException e)
						{
							e.printStackTrace();
						}
						resetNow.dispose();
						reDraw();
					}
				});
				
				cancel.addActionListener(new ActionListener()
				{public void actionPerformed(ActionEvent eh)
					{
						resetInt--;
						resetNow.dispose();
					}
				});
			}
			
		}
	}
	
	///////////////////////////////////////////////////////////////////////////////////////
	/////                                                                               ///                  
	////      [][][][]        [][]      []        []    [][][][]     [][][]  [][][]     ///                                                       
	/////    []      []      []  []     [][]    [][]    []           [][][]  [][][]     ///                                                
	////     []             []    []    [] []  [] []    []            [][]    [][]      ///                                      
	/////    []    [][][]   [][][][]    []  [][]  []    [][]                            ///                                            
	////     []      []     []    []    []        []    []            [][]    [][]      ///
	/////     [][][][]      []    []    []        []    [][][][]      [][]    [][]      ///                                                  
	////                                                                                ///
	///////////////////////////////////////////////////////////////////////////////////////
	
	private class StartGame extends JPanel 
	{
		private JPanel mainGame;
		
		public boolean focused;
		
		public StartGame()
		{
			mainGame = this;
			
			mainGame.setVisible(true);
			mainGame.setFocusable(true);
			
			//Worlds worlds = new Worlds();
			//content.add(worlds);
			//worlds.startAnimation();
		
			quitNum = 0;
			focused = false;
		}
		
		public void setFocused(boolean focus)
		{
			focused = focus;
		}

			
	}
	
	private class QuitPlaying extends JFrame
	{
		
		public JButton backToMenu, cancel;
		public JFrame exitNow;
		
		
		public QuitPlaying()
		{
			exitNow = this;
			
			exitNow.setTitle("Quit Playing?");
			exitNow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			exitNow.setLocationRelativeTo(null);
			exitNow.setResizable(false);
			
			Container content = exitNow.getContentPane();
			content.setLayout(new BorderLayout());
			
			JPanel text = new JPanel(new GridLayout(4,1));
			JLabel ask = new JLabel("\tWould you really like to quit?  Your score "); 
			JLabel ask2 = new JLabel("\twill be added to the high scores.  Press 'cancel'\n "); 
			JLabel ask3 = new JLabel("\tto keep playing your game.");
			
			text.add(ask);
			text.add(ask2);
			text.add(ask3);
			
			JPanel south = new JPanel();
			south.setLayout(new FlowLayout(FlowLayout.RIGHT));
			
			JButton backToMenu = new JButton("Main Menu");
			JButton cancel = new JButton("Cancel");
			south.add(cancel);
			south.add(backToMenu);
			
			content.add(text,BorderLayout.CENTER);
			content.add(south,BorderLayout.SOUTH);
			
	
			exitNow.pack();
			exitNow.setVisible(true);
			
			backToMenu.addActionListener(new ActionListener()
			{public void actionPerformed(ActionEvent eh)
				{
					quitNum--;
					exitNow.dispose();
					cardChange(MarioStart.MAINMENU);
				}
			});
			
			cancel.addActionListener(new ActionListener()
			{public void actionPerformed(ActionEvent eh)
				{
					quitNum--;
					exitNow.dispose();
					cardChange(MarioStart.GAME);
				}
			});
		}
	}
	
	
	/////////////////////////////////////////////////////////////////////////
	/////                                                                 ///                              
	////      [][][][][]  [][][][][]  [][][][][]  []        [][][][][]    ///                                                                       
	/////         []          []          []      []        []            ///                                            
	////          []          []          []      []        [][][]        ///                                               
	/////         []          []          []      []        []            ///                                            
	////          []          []          []      []        []            ///                                           
	/////         []      [][][][][]      []      [][][][]  [][][][][]    ///                                                              
	////                                                                  ///                             
	/////////////////////////////////////////////////////////////////////////
	
	private class TitleScreen extends JPanel implements ActionListener
	{
		private JButton start, inst, highScores;
		private JPanel mainTitle;
		public Image image;
		
		public Thread th;
		public Runnable r;
		public double xPos = 0;
		
		public boolean focused;

		
		public TitleScreen() throws IOException
		{
			
			File newFile = new File("/Users/dootfiddle/Desktop/Mario 2.0/src/Images/mario-1-1.gif");
			image = ImageIO.read(newFile);
			
			mainTitle = this;
			
			mainTitle.setLayout(new GridLayout (1,3));
			
			start = new JButton("Start Game");
			inst = new JButton("Instructions/Settings");
			highScores = new JButton("High Scores");
			
			start.setOpaque(false);
			
			JPanel leftBlank = new JPanel();
			JPanel center = new JPanel();
			JPanel rightBlank = new JPanel();
			JPanel topBlank = new JPanel();
			JPanel centerButtons = new JPanel();
			JPanel bottomBlank = new JPanel();

			
			center.setLayout(new GridLayout(3,1));
			
			centerButtons.setLayout(new GridLayout(3,1,10,10));
			centerButtons.add(start);
			centerButtons.add(inst);
			centerButtons.add(highScores);
			
			center.add(topBlank);
			center.add(centerButtons);
			center.add(bottomBlank);

			mainTitle.add(leftBlank);
			mainTitle.add(center);
			mainTitle.add(rightBlank);
			
			start.addActionListener(this);
			inst.addActionListener(this);
			highScores.addActionListener(this);
			mainTitle.setVisible(true);
			
			//Make Transparent
			mainTitle.setOpaque(false);
			leftBlank.setOpaque(false);
			rightBlank.setOpaque(false);
			center.setOpaque(false);
			topBlank.setOpaque(false);
			centerButtons.setOpaque(false);
			bottomBlank.setOpaque(false);
			
			focused = true;
			
			r = new ScrollingPicture(6768);
			System.out.println(r);
			th = new Thread(r);
			
			th.start();
			
		}
		
		private class ScrollingPicture implements Runnable
			{
			
			public int picLength;
				
				public ScrollingPicture(int x)
				{
					picLength = x;
				}

				public void run()
				{	
					scrollingPicLock.lock();
					
					try
					{
						while(focused && Math.abs(xPos)< (picLength - 512))
						{
							xPos -= .11;
							Thread.sleep(2);
							mainTitle.repaint();
							mainTitle.validate();
						}
						th.interrupt();
					}
					catch(InterruptedException e)
					{}
					finally
					{
						scrollingPicLock.unlock();
					}
				}
			}
		
		public void paintComponent(Graphics g) 
		{
		    g.drawImage(image, (int)xPos, 0, null); 
		}

		public void stopScrollingPic()
		{
			th.interrupt();
			xPos = 0;
		}
		
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == start)
			{
				stopScrollingPic();
				gamePanel.focused = true;
				startScreen.focused = false;
				cardChange(MarioStart.GAME);
			}
			else if(e.getSource() == inst)
			{
				stopScrollingPic();
				cardChange(MarioStart.INSTRUCTIONS);
			}
			else if(e.getSource() == highScores)
			{
				stopScrollingPic();
				cardChange(MarioStart.HIGHSCORES);
			}
		}
	}
	
	public static void main(String[] args) throws IOException, ClassNotFoundException
	{
		
		
		MarioStart go = new MarioStart();
		
		
		//Worlds worlds = new Worlds();
		//superSmashWindow.add(worlds);
		//worlds.startAnimation();
		
	}

}


