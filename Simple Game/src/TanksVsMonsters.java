import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

//Database stuff
import java.sql.Connection; 
import java.sql.DatabaseMetaData; // this is for SQLite
import java.sql.DriverManager; // includes the Jar file we added
import java.sql.ResultSet; // For getting data back
import java.sql.SQLException; //errors
import java.sql.Statement;


/* To work on Section 
 * 	- 
 * 
 * 
 * 
 * 
 * 
 */

public class TanksVsMonsters extends JFrame implements ActionListener, KeyListener {
	
	
	private static final long serialVersionUID = 1L;
	// Declare our sprite objects
	private Tank myTank;
	private Monster myMonster;
	private PewPew myPewPew;
	
	// Image Icons to hold the png graphics
	private ImageIcon TankImage;
	private ImageIcon MonsterImage;
	private ImageIcon PewPewImage;
	private ImageIcon vertWall, hortWall, toolBar, hHeart, lHeart; 
	
	// Labels to display the image icons 
	private JLabel TankLabel, MonsterLabel, PewPewLabel,ScoreLabel;
	private JLabel vWall1, vWall2, vWall3, vWall4, vWall5, vWall6, vWall7, vWall8;
	private JLabel hWall1, hWall2, hWall3, hWall4, hWall5, hWall6, hWall7, hWall8; 
	private JLabel infoBar, mheart1, mheart2, mheart3, theart1, theart2, theart3;
	
		
	// Container for the graphics 
	private static Container content;
	public static int start = 0;
	 
	
	public TanksVsMonsters() {
	
	// Initialize variables 
		
		
			myTank = new Tank();
			myMonster = new Monster();
			myPewPew = new PewPew();
			TankLabel = new JLabel();
			MonsterLabel = new JLabel();
			PewPewLabel = new JLabel();
			ScoreLabel = new JLabel();
			
			infoBar = new JLabel();
			mheart1 = new JLabel();
			mheart2 = new JLabel();
			mheart3 = new JLabel();
			
			theart1 = new JLabel();
			theart2 = new JLabel();
			theart3 = new JLabel();

			
			vWall1 = new JLabel();
			vWall2 = new JLabel();
			vWall3 = new JLabel();
			vWall4 = new JLabel();
			vWall5 = new JLabel();
			vWall6 = new JLabel();
			vWall7 = new JLabel();
			vWall8 = new JLabel();
			
			hWall1 = new JLabel();
			hWall2 = new JLabel();
			hWall3 = new JLabel();
			hWall4 = new JLabel();
			hWall5 = new JLabel();
			hWall6 = new JLabel();
			hWall7 = new JLabel();
			hWall8 = new JLabel();
			
			vertWall = new ImageIcon( getClass().getResource("VerticalWall.png"));
			hortWall = new ImageIcon( getClass().getResource("HorizontalWall.png"));
			
			hHeart = new ImageIcon (getClass().getResource("heart.png"));
			lHeart = new ImageIcon (getClass().getResource("lostheart.png"));
			toolBar = new ImageIcon (getClass().getResource("toolbar.png"));
			
			
			TankImage = new ImageIcon(getClass().getResource(myTank.getSpriteName()));
			MonsterImage = new ImageIcon(getClass().getResource(myMonster.getSpriteName()));
			PewPewImage = new ImageIcon(getClass().getResource(myPewPew.getSpriteName()));
			
			content = getContentPane();
			
			
			// Set up the GUI
			setSize(GameProperties.SCREEN_WIDTH, GameProperties.SCREEN_HEIGHT);
			content.setBackground(new Color(167,114,57));
			setLayout(null); // This means there is no layout, by assuming that you will be setting up the information.
			
			GameProperties.USER_NAME = JOptionPane.showInputDialog(content,
				    "Please enter your Username to save your score");
			
			mheart1.setSize(20, 20);
			mheart1.setIcon(hHeart);
			mheart1.setLocation(GameProperties.SCREEN_WIDTH-100, GameProperties.SCREEN_HEIGHT-850);
			add(mheart1);
			
			mheart2.setSize(20, 20);
			mheart2.setIcon(hHeart);
			mheart2.setLocation(GameProperties.SCREEN_WIDTH-130, GameProperties.SCREEN_HEIGHT-850);
			add(mheart2);
			
			mheart3.setSize(20, 20);
			mheart3.setIcon(hHeart);
			mheart3.setLocation(GameProperties.SCREEN_WIDTH-160, GameProperties.SCREEN_HEIGHT-850);
			add(mheart3);
			
			// Tank Hearts
			
			theart1.setSize(20, 20);
			theart1.setIcon(hHeart);
			theart1.setLocation(GameProperties.SCREEN_WIDTH-860, GameProperties.SCREEN_HEIGHT-850);
			add(theart1);
			
			theart2.setSize(20, 20);
			theart2.setIcon(hHeart);
			theart2.setLocation(GameProperties.SCREEN_WIDTH-830, GameProperties.SCREEN_HEIGHT-850);
			add(theart2);
			
			theart3.setSize(20, 20);
			theart3.setIcon(hHeart);
			theart3.setLocation(GameProperties.SCREEN_WIDTH-800, GameProperties.SCREEN_HEIGHT-850);
			add(theart3);
			
			int score = 0;
			ScoreLabel.setSize(200, 30);
			ScoreLabel.setText("Score is " + score);
			ScoreLabel.setForeground(Color.white);
			ScoreLabel.setLocation(GameProperties.SCREEN_WIDTH-500, GameProperties.SCREEN_HEIGHT-875);
			add(ScoreLabel);
			
				
			
			
			
			//wall.setIcon(vWall);
			vWall1.setSize(50, 200);
			vWall1.setIcon(vertWall);
			vWall1.setLocation(GameProperties.SCREEN_WIDTH-900, GameProperties.SCREEN_HEIGHT-825);
			add(vWall1);
			
			vWall2.setSize(50, 200);
			vWall2.setIcon(vertWall);
			vWall2.setLocation(GameProperties.SCREEN_WIDTH-900, GameProperties.SCREEN_HEIGHT-625);
			add(vWall2);
			
			vWall3.setSize(50, 200);
			vWall3.setIcon(vertWall);
			vWall3.setLocation(GameProperties.SCREEN_WIDTH-900, GameProperties.SCREEN_HEIGHT-425);
			add(vWall3);
			
			vWall4.setSize(50, 200);
			vWall4.setIcon(vertWall);
			vWall4.setLocation(GameProperties.SCREEN_WIDTH-900, GameProperties.SCREEN_HEIGHT-225);
			add(vWall4);
			
			vWall5.setSize(50, 200);
			vWall5.setIcon(vertWall);
			vWall5.setLocation(GameProperties.SCREEN_WIDTH-50, GameProperties.SCREEN_HEIGHT-825);
			add(vWall5);
			
			vWall6.setSize(50, 200);
			vWall6.setIcon(vertWall);
			vWall6.setLocation(GameProperties.SCREEN_WIDTH-50, GameProperties.SCREEN_HEIGHT-625);
			add(vWall6);
			
			vWall7.setSize(50, 200);
			vWall7.setIcon(vertWall);
			vWall7.setLocation(GameProperties.SCREEN_WIDTH-50, GameProperties.SCREEN_HEIGHT-425);
			add(vWall7);
			
			vWall8.setSize(50, 200);
			vWall8.setIcon(vertWall);
			vWall8.setLocation(GameProperties.SCREEN_WIDTH-50, GameProperties.SCREEN_HEIGHT-225);
			add(vWall8);
			
			// Horizontal Walls
			hWall1.setSize(200, 50);
			hWall1.setIcon(hortWall);
			hWall1.setLocation(GameProperties.SCREEN_WIDTH-850, GameProperties.SCREEN_HEIGHT-825);
			add(hWall1);

			hWall2.setSize(200, 50);
			hWall2.setIcon(hortWall);
			hWall2.setLocation(GameProperties.SCREEN_WIDTH-650, GameProperties.SCREEN_HEIGHT-825);
			add(hWall2);
			
			hWall3.setSize(200, 50);
			hWall3.setIcon(hortWall);
			hWall3.setLocation(GameProperties.SCREEN_WIDTH-450, GameProperties.SCREEN_HEIGHT-825);
			add(hWall3);
			
			hWall4.setSize(200, 50);
			hWall4.setIcon(hortWall);
			hWall4.setLocation(GameProperties.SCREEN_WIDTH-250, GameProperties.SCREEN_HEIGHT-825);
			add(hWall4);
			
			hWall5.setSize(200, 50);
			hWall5.setIcon(hortWall);
			hWall5.setLocation(GameProperties.SCREEN_WIDTH-850, GameProperties.SCREEN_HEIGHT-72);
			add(hWall5);
			
			hWall6.setSize(200, 50);
			hWall6.setIcon(hortWall);
			hWall6.setLocation(GameProperties.SCREEN_WIDTH-650, GameProperties.SCREEN_HEIGHT-72);
			add(hWall6);
			
			hWall7.setSize(200, 50);
			hWall7.setIcon(hortWall);
			hWall7.setLocation(GameProperties.SCREEN_WIDTH-450, GameProperties.SCREEN_HEIGHT-72);
			add(hWall7);
			
			hWall8.setSize(200, 50);
			hWall8.setIcon(hortWall);
			hWall8.setLocation(GameProperties.SCREEN_WIDTH-250, GameProperties.SCREEN_HEIGHT-72);
			add(hWall8);
			
			
			
			
			// setting up sending information back and forth 
			myMonster.setMonsterLabel(MonsterLabel);
			myMonster.setmyTank(myTank);
			myMonster.setTankLabel(TankLabel); 
			myMonster.setTHeart1(theart1);
			myMonster.setTHeart2(theart2);
			myMonster.setTHeart3(theart3);
			myMonster.setScore(ScoreLabel);
			
			
			
			myPewPew.setPewPewLabel(PewPewLabel);
			myPewPew.setmyTank(myTank);
			myPewPew.setTankLabel(TankLabel);
			myPewPew.setmyMonster(myMonster);
			myPewPew.setMonsterLabel(MonsterLabel); 
			myPewPew.setMHeart1(mheart1);
			myPewPew.setMHeart2(mheart2);
			myPewPew.setMHeart3(mheart3);
			myPewPew.setScore(ScoreLabel);
			
			
			// Set up the Tank
			//This is sending the label into the Tank so the function can change it
			myTank.setTankLabel(TankLabel);
			myTank.setSpriteX(50);
			myTank.setSpriteY(150);
			TankLabel.setIcon(TankImage);
			TankLabel.setSize(myTank.getSpriteW(), myTank.getSpriteH());
			TankLabel.setLocation(myTank.getSpriteX(),myTank.getSpriteY());
			add(TankLabel);
			
			// Set up the Monster
			myMonster.setMonsterLabel(MonsterLabel); // this is to set the label
			myMonster.setSpriteX(775);
			myMonster.setSpriteY(150);
			MonsterLabel.setIcon(MonsterImage);
			MonsterLabel.setSize(myMonster.getSpriteW(), myMonster.getSpriteH());
			MonsterLabel.setLocation(myMonster.getSpriteX(),myMonster.getSpriteY());
			myMonster.setMoving(false);
			myMonster.setVisible(true);
			add(MonsterLabel);
			
			// Set up the PewPew
			myPewPew.setTankLabel(TankLabel);
			myPewPew.setSpriteX(0);
			myPewPew.setSpriteY(0);
			PewPewLabel.setIcon(PewPewImage);
			PewPewLabel.setSize(myPewPew.getSpriteW(), myPewPew.getSpriteH());
			PewPewLabel.setLocation(myPewPew.getSpriteX(),myPewPew.getSpriteY());
			add(PewPewLabel);
			
						
			//Information Bar
			infoBar.setSize(900, 75);
			infoBar.setIcon(toolBar);
			infoBar.setBackground(Color.DARK_GRAY);
			infoBar.setLocation(GameProperties.SCREEN_WIDTH-900, GameProperties.SCREEN_HEIGHT-900);
			add(infoBar);
			
			
			
			content.addKeyListener(this);
			content.setFocusable(true);
			
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		
		public static void main (String [] args) {
			
			Connection conn = null;
			Statement stmt = null; 
			
			try {
				//load the DB driver
				Class.forName("org.sqlite.JDBC");
				String dbURL = "jdbc:sqlite:TankVsMonster.db";
				conn = DriverManager.getConnection(dbURL);
				if (conn != null) {
					System.out.println("Connection Established");
					
					conn.setAutoCommit(false); // this stops it from doing it automatically, we will do it manually 
					
					stmt = conn.createStatement(); // this is where the query gets sent 
					String sql = ""; // the query we want to run
					
									
					
					
						
					
					conn.close();
				} else {
					System.out.println("Cannot Established Connection");
				}
				
				
			} catch (ClassNotFoundException e) { //this is loading the database
				e.printStackTrace();
				
			} catch (SQLException e) { // this is where there is an issue with the SQL 
				e.printStackTrace();
			} catch (Exception e) { // this one will catch everything unless one of them have been caught above 
				e.printStackTrace();
			} finally {
				//cleanup code
			}
			
			TanksVsMonsters myGame = new TanksVsMonsters();
			myGame.setVisible(true);
			
			JOptionPane.showMessageDialog(content, "Press Any Key to start" );
			
			
			
		}
		
		
		public static void enterDatabase() {
			
			Connection conn = null;
			Statement stmt = null;
			
			try {
				//load the DB driver
				Class.forName("org.sqlite.JDBC");
				String dbURL = "jdbc:sqlite:TankVsMonster.db";
				conn = DriverManager.getConnection(dbURL);
				if (conn != null) {
					System.out.println("Connection Established");
					
					conn.setAutoCommit(false); // this stops it from doing it automatically, we will do it manually 
					
					stmt = conn.createStatement(); // this is where the query gets sent into to be executed
					String sql = ""; // the query we want to run
					
									
					//CREATE TABLE IF NOT EXIST
					sql = "CREATE TABLE IF NOT EXISTS company ("+ 
						  "id INTEGER PRIMARY KEY, " +
						  "name TEXT NOT NULL, " + 
						  "score REAL NOT NULL)";
					stmt.executeUpdate(sql);
					conn.commit();
					
					//Insert data
					String name = GameProperties.USER_NAME; 
					double score = GameProperties.GAME_SCORE;
					sql = "INSERT INTO company (name, score) VALUES ('" +
					       name + "', " + score + ")";
					stmt.executeUpdate(sql);
					conn.commit();

					
					conn.close();
				} else {
					System.out.println("Cannot Established Connection");
				}
				
				
			} catch (ClassNotFoundException e) { //this is loading the database
				e.printStackTrace();
				
			} catch (SQLException e) { // this is where there is an issue with the SQL 
				e.printStackTrace();
			} catch (Exception e) { // this one will catch everything unless one of them have been caught above 
				e.printStackTrace();
			} finally {
				//cleanup code
			}
		}
		
		


		@Override
		public void keyTyped(KeyEvent e) {
			//System.out.println(e);
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if(start == 0) {
				myMonster.moveMonster();
				try {
					Thread.sleep(200); // Without this the screen cannot refresh 
					
				} catch (Exception i) {
					
				}
				start = 1;
			} 
			
			
			
			if(e.getKeyCode()!=KeyEvent.VK_SPACE) {
				myTank.moveTank(e);
				
			
			} else if (e.getKeyCode()==KeyEvent.VK_SPACE) {
				myPewPew.movePew();
					
			}
				
		}
			

		@Override
		public void keyReleased(KeyEvent e) {
			//System.out.println("Key has been released");
			
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		

}
