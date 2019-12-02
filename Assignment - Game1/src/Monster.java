import java.awt.Container;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Monster extends Sprite implements Runnable {
	private Boolean visible;
	private Boolean moving;
	private Thread t;
	
	private JLabel MonsterLabel;
	private Tank myTank;
	private JLabel TankLabel;
	
	private JLabel theart1, theart2, theart3, ScoreLabel;
	private ImageIcon lHeart; 
	private Container content;
	
	public int tankHealth =3;
	
	
	
	public Monster() {
		super(0,0,"monster.png",75,88);
		moving = false;
		visible = true;
	}
	
	// Section for sending things back and forth 
	public void setMonsterLabel(JLabel temp) {
		this.MonsterLabel = temp;
	}	
	public void setTankLabel(JLabel temp) {
		this.TankLabel = temp;
	}
	public void setmyTank(Tank temp) {
		this.myTank = temp;
	}
	
	//Tank Hearts
	public void setTHeart1(JLabel temp) {
		this.theart1 = temp;
	}
	public void setTHeart2(JLabel temp) {
		this.theart2 = temp;
	}
	public void setTHeart3(JLabel temp) {
		this.theart3 = temp;
	}
	public void setScore(JLabel temp) {
		this.ScoreLabel = temp;
	}
	

	
	
	// Define getters and setters
	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public Boolean getMoving() {
		return moving;
	}

	public void setMoving(Boolean moving) {
		this.moving = moving;
	}
	
	public void Display() {
		System.out.println("X,Y" + spriteX + "," + spriteY + " / v: " + visible + " / m: " + moving);

	}
	
	public void boom(Rectangle tempT, Rectangle tempM ) {
		
		lHeart = new ImageIcon (getClass().getResource("lostheart.png"));
		
		
			//System.out.println("Boom");
				
					
			String name = myTank.getSpriteName();
			
			switch (name) {
			
				case "tanknorth.png": myTank.setSpriteName("hurttanknorth.png"); TankLabel.setIcon(new ImageIcon(getClass().getResource(myTank.getSpriteName())));  break;
				case "tankeast.png": myTank.setSpriteName("hurttankeast.png");TankLabel.setIcon(new ImageIcon(getClass().getResource(myTank.getSpriteName()))); break;
				case "tankwest.png": myTank.setSpriteName("hurttankwest.png");TankLabel.setIcon(new ImageIcon(getClass().getResource(myTank.getSpriteName()))); break;
				case "tanksouth.png": myTank.setSpriteName("hurttanksouth.png");TankLabel.setIcon(new ImageIcon(getClass().getResource(myTank.getSpriteName()))); break;
				
				default: myTank.setSpriteName("hurttank.png");TankLabel.setIcon(new ImageIcon(getClass().getResource(myTank.getSpriteName()))); break;
			}
			
			
			try { Thread.sleep(600);  
			
			} catch (Exception e) {
				
			}
			
			
			if (tankHealth == 3) {
				theart1.setIcon(lHeart);
				tankHealth = 2;
				GameProperties.GAME_SCORE = GameProperties.GAME_SCORE - 25;
				ScoreLabel.setText( "Score: " + GameProperties.GAME_SCORE);
				
			} else if (tankHealth == 2) {
				theart2.setIcon(lHeart);
				tankHealth = 1;
				GameProperties.GAME_SCORE = GameProperties.GAME_SCORE - 25;
				ScoreLabel.setText( "Score: " + GameProperties.GAME_SCORE);
				
			}else if (tankHealth == 1) {
				theart3.setIcon(lHeart);
				tankHealth = 0;
				GameProperties.GAME_SCORE = GameProperties.GAME_SCORE - 25;
				ScoreLabel.setText( "Score: " + GameProperties.GAME_SCORE);
				
				JOptionPane.showMessageDialog(content, "You have Lost!\n your score was: " + GameProperties.GAME_SCORE);
				try { Thread.sleep(400);  
				
				} catch (Exception e) {
					
				}
				enterDatabaseM();
				System.exit(0);
				
			}
			
			
}
	
	
	
	
	
	public void stopMonster( ) {
		this.moving = false;		
	}
	
	public void moveMonster() {
		this.moving = true;
		t = new Thread(this, "Monster");
		t.start();
	}
	
	public static int getRandomNumber(){
		Random random = new Random();
	    int z = random.nextInt(3);
	    return z;
	}

	@Override
	public void run() {
		this.setSpriteName("monster.png");
		MonsterLabel.setIcon(new ImageIcon(getClass().getResource(this.getSpriteName())));
		Rectangle rTank = myTank.getRectangle();
		Rectangle rMonster = this.r;
				
		int startLocation = getRandomNumber();
		
		/*
		 * Options for setting location starts 
		myTank.setSpriteX(50); myTank.setSpriteY(150); // Top Left
		myTank.setSpriteX(50); myTank.setSpriteY(750); // Bottom Left
		myTank.setSpriteX(775); myTank.setSpriteY(150); // Top Right
		myTank.setSpriteX(775); myTank.setSpriteY(750); // Bottom Right
		
		this.setSpriteX(50); this.setSpriteY(150); //Top Left
		this.setSpriteX(50); this.setSpriteY(750); // Bottom Left
		this.setSpriteX(775); this.setSpriteY(150); // Top Right
		this.setSpriteX(775); this.setSpriteY(750); // Bottom Right
		*/
		
		switch (startLocation) {
		
			case 1: myTank.setSpriteX(775); myTank.setSpriteY(150); // Top Right
					this.setSpriteX(50); this.setSpriteY(750); // Bottom Left
					TankLabel.setLocation(myTank.spriteX, myTank.spriteY);
					break;
					
			case 2: myTank.setSpriteX(775); myTank.setSpriteY(750); // Bottom Right
					this.setSpriteX(50); this.setSpriteY(150); //Top Left
					TankLabel.setLocation(myTank.spriteX, myTank.spriteY);
					break; 
			
			case 3: myTank.setSpriteX(50); myTank.setSpriteY(750); // Bottom Left
					this.setSpriteX(775); this.setSpriteY(150); // Top Right
					TankLabel.setLocation(myTank.spriteX, myTank.spriteY);
					break;
			
			default:	myTank.setSpriteX(50); myTank.setSpriteY(150); // Top Left
						this.setSpriteX(775); this.setSpriteY(750); // Bottom Right
						TankLabel.setLocation(myTank.spriteX, myTank.spriteY);
						break;
					
		
		}
	
				
		int monsterX = this.spriteX;
		int monsterY = this.spriteY;
		
		
		while (moving) {
			//moving code
			 
			
			int direction = getRandomNumber();
			
			System.out.println("Direction number is " + direction);
			
			if (rTank.intersects(rMonster)) {boom(rTank, rMonster);}
			
			switch (direction) {
			
				// Running Right
				case 1: if (monsterX > (800 - this.spriteW)) {break;}
						for (int i =0; i < 10; i++) {
							if (monsterX > (800 - this.spriteW)) {break;
							} else {
								this.setSpriteX(monsterX + 30);
								monsterX = monsterX +30;
								MonsterLabel.setLocation(monsterX, this.spriteY);
								try {
									Thread.sleep(250); // Without this the screen cannot refresh 
							
								} catch (Exception e) {
									
								}
								
							}
							System.out.println("Moving Right ");
						}
						break;
				
				// Running Left
				case 2: if (monsterX < (150 + this.spriteW)) {break;}
						for (int i =0; i < 10; i++) {
							if (monsterX < (150 + this.spriteW)) {break;
							} else {
								this.setSpriteX(monsterX - 30);
								monsterX = monsterX -30;
								MonsterLabel.setLocation(monsterX, this.spriteY);
								try {
									Thread.sleep(250); // Without this the screen cannot refresh 
						
								} catch (Exception e) {
						
								}
							}
							System.out.println("Moving Left ");
						}break;
				
				// Running Down
				case 3: if (monsterY > (775 - this.spriteH)) {break;}
						for (int i =0; i < 10; i++) {
							if (monsterY > (775 - this.spriteH)) {break;
							} else {
								
								this.setSpriteY(monsterY + 30);
								monsterY = monsterY +30;
								MonsterLabel.setLocation(this.spriteX, monsterY);
								try {
									Thread.sleep(250); // Without this the screen cannot refresh 
					
								} catch (Exception e) {
					
								}
							}
							System.out.println("Moving Down ");
							
						}
						break;
					
					
					
					
					
				// Running Up
				default: 	if (monsterY < (125 + this.spriteH)) {break;}
							for (int i =0; i < 10; i++) {
								if (monsterY < (125 + this.spriteH)) {
									break;
								}else { 
									this.setSpriteY(monsterY - 30);
									monsterY = monsterY -30;
									MonsterLabel.setLocation(this.spriteX, monsterY);
									try {
										Thread.sleep(250); // Without this the screen cannot refresh 
									} catch (Exception e) {		
						
									}
								}
				
								System.out.println("Moving Up ");
							}
							break;
			}
			
						
			
			int tX = this.spriteX;
			int tY = this.spriteY; 
			
						
			// Collision Detection 
			if (rTank.intersects(rMonster)) { boom(rTank, rMonster);}
				
				
			//pause
			
			try {
				Thread.sleep(200); // Without this the screen cannot refresh 
				
			} catch (Exception e) {
				
			}
			
			
		}
		
	
		
	}

	
	public static void enterDatabaseM() {
		
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
}
