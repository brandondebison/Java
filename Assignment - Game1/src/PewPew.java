import java.awt.Container;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class PewPew extends Sprite implements Runnable {
	private Boolean visible;
	private Boolean pewMoving;
	private Thread pews;
	
	private JLabel PewPewLabel;
	private Tank myTank;
	private JLabel TankLabel;
	private Monster myMonster;
	private JLabel MonsterLabel;
	
	private JLabel mheart1, mheart2, mheart3, ScoreLabel;
	private ImageIcon lHeart;
	private Container content;
	
	public int monsterHealth =3;
	
	
	public PewPew() {
		super(0,0,"pewpew.png",8,8);
		pewMoving = false;
		visible = true;
	}
	
	// Section for sending things back and forth 
	public void setPewPewLabel(JLabel temp) {
		this.PewPewLabel = temp;
	}	
	public void setTankLabel(JLabel ttemp) {
		this.TankLabel = ttemp;
	}
	public void setmyTank(Tank ttemp) {
		this.myTank = ttemp;
	}
	public void setMonsterLabel(JLabel temp) {
		this.MonsterLabel = temp;
	}
	public void setmyMonster(Monster mtemp) {
		this.myMonster = mtemp;
	}
	
	
	//Monster Hearts
		public void setMHeart1(JLabel temp) {
			this.mheart1 = temp;
		}
		public void setMHeart2(JLabel temp) {
			this.mheart2 = temp;
		}
		public void setMHeart3(JLabel temp) {
			this.mheart3 = temp;
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
		return pewMoving;
	}

	public void setPewMoving(Boolean pewMoving) {
		this.pewMoving = pewMoving;
	}
	
	public void Display() {
		System.out.println("X,Y" + spriteX + "," + spriteY + " / v: " + visible + " / m: " + pewMoving);

	}
	
	
	//Show and Hide functions for the PewPew
	
	public void show() {
		this.visible = true;
	}
	
	public void hide() {
		this.visible = false;
	}
	
	public void stopPew( ) {
		this.pewMoving = false;
	}
	
	public void movePew() {
		this.pewMoving = true;
		pews = new Thread(this, "Pew");
		pews.start();
	}
	
	
	
	public void pewBoom(Rectangle tempT, Rectangle tempM, Rectangle tempP ) {
		
		lHeart = new ImageIcon (getClass().getResource("lostheart.png"));
		
		
			System.out.println("Boom");
			this.pewMoving = false;
			
			this.setSpriteName("hurtmonster.png");
			
			this.setSpriteX(0);
			this.setSpriteY(0);
			PewPewLabel.setLocation(this.spriteX, this.spriteY);
		
			
			MonsterLabel.setIcon(new ImageIcon(
					getClass().getResource(this.getSpriteName())
					));
			
			
			if (monsterHealth == 3) {
				mheart1.setIcon(lHeart);
				monsterHealth = 2;
				GameProperties.GAME_SCORE = GameProperties.GAME_SCORE + 50;
				ScoreLabel.setText( "Score: " + GameProperties.GAME_SCORE);
				
			} else if (monsterHealth == 2) {
				mheart2.setIcon(lHeart);
				monsterHealth = 1;
				GameProperties.GAME_SCORE = GameProperties.GAME_SCORE + 50;
				ScoreLabel.setText( "Score: " + GameProperties.GAME_SCORE);
			}else if (monsterHealth == 1) {
				mheart3.setIcon(lHeart);
				monsterHealth = 0;
				GameProperties.GAME_SCORE = GameProperties.GAME_SCORE + 50;
				ScoreLabel.setText( "Score: " + GameProperties.GAME_SCORE);
				JOptionPane.showMessageDialog(content, "You have Won! \n your score was: " + GameProperties.GAME_SCORE);
				enterDatabaseT();
				try {
					Thread.sleep(400); // Without this the screen cannot refresh 
					
				} catch (Exception e) {
					
				}
				System.exit(0);
				
				
			}
			//theart1.setIcon(lHeart);
			try {
				Thread.sleep(600); // Without this the screen cannot refresh 
				this.setSpriteName("monster.png");
				
				MonsterLabel.setIcon(new ImageIcon(
						getClass().getResource(this.getSpriteName())
						));
			} catch (Exception e) {
				
			}
			
			
			
			
}
	
	public static void enterDatabaseT() {
		
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
	
	

	
	public void run() {
		
		Rectangle rTank = myTank.getRectangle();
		Rectangle rMonster = myMonster.getRectangle();
		Rectangle rPewPew = this.r;
		
		System.out.println("Made it to Run " );
		String nameTank = myTank.getSpriteName();
		int ttX = myTank.getSpriteX();
		int ttY = myTank.getSpriteY();
		int ptX = this.spriteX;
		int ptY = this.spriteY;
		
		switch (nameTank) {
		

			case "tanknorth.png": 	ptX = ttX;
									ptY = ttY;
									
									PewPewLabel.setLocation(this.spriteX, this.spriteY);
									PewPewLabel.setIcon(new ImageIcon(getClass().getResource(this.getSpriteName())));
									System.out.println("Tank X: " + myTank.getSpriteX());
									System.out.println("Tank Y: " + myTank.getSpriteY());
									System.out.println("Pew x is " + this.spriteX);
									System.out.println("Pew Y is " + this.spriteY);
									
									while (pewMoving) {
										this.setSpriteX(ptX + 25);
										this.setSpriteY(ptY + 25);
										if (rPewPew.intersects(rMonster)) {pewBoom(rTank, rMonster, rPewPew);};
										ptY -= GameProperties.CHARACTER_STEP *4; // This will move twice as fast
										if(ptX > GameProperties.SCREEN_WIDTH || ptY > GameProperties.SCREEN_HEIGHT ) {
											this.pewMoving = false;
											this.setSpriteX(0);
											this.setSpriteY(0);
											PewPewLabel.setLocation(this.spriteX, this.spriteY);
											PewPewLabel.setIcon(new ImageIcon(getClass().getResource(this.getSpriteName())));
											break;
										} 
										
										
										
										PewPewLabel.setLocation(this.spriteX, this.spriteY);
										PewPewLabel.setIcon(new ImageIcon(getClass().getResource(this.getSpriteName())));
										try {
											Thread.sleep(200); // Without this the screen cannot refresh 
											
										} catch (Exception e) {
											
										}
									}
									//System.out.println("Made it to north " );
									break;
									
									
			case "tankeast.png": 	ptX = ttX;
									ptY = ttY;
			
									PewPewLabel.setLocation(this.spriteX, this.spriteY);
									
									while (pewMoving) {
										this.setSpriteX(ptX + 25);
										this.setSpriteY(ptY + 25);
										if (rPewPew.intersects(rMonster)) {pewBoom(rTank, rMonster, rPewPew);};
										ptX += GameProperties.CHARACTER_STEP *4; // This will move twice as fast
									if(ptX > GameProperties.SCREEN_WIDTH || ptY > GameProperties.SCREEN_HEIGHT ) {
										this.pewMoving = false;
										this.setSpriteX(0);
										this.setSpriteY(0);
										
										PewPewLabel.setLocation(this.spriteX, this.spriteY);
										PewPewLabel.setIcon(new ImageIcon(
												getClass().getResource(this.getSpriteName())
												));
										break;
									} 
									
									
									
									PewPewLabel.setLocation(this.spriteX, this.spriteY);
									try {
										Thread.sleep(200); // Without this the screen cannot refresh 
											
										} catch (Exception e) {
											
										}
									}
									//System.out.println("Made it to east " );
									break;
									
									
			case "tankwest.png":	ptX = ttX;
									ptY = ttY;
									
									PewPewLabel.setLocation(this.spriteX, this.spriteY);
									
									while (pewMoving) {
										this.setSpriteX(ptX + 25);
										this.setSpriteY(ptY + 25);
										if (rPewPew.intersects(rMonster)) {pewBoom(rTank, rMonster, rPewPew);};
										ptX -= GameProperties.CHARACTER_STEP *4; // This will move twice as fast
									if(ptX > GameProperties.SCREEN_WIDTH || ptY > GameProperties.SCREEN_HEIGHT ) {
										this.pewMoving = false;
										this.setSpriteX(0);
										this.setSpriteY(0);
										
										PewPewLabel.setLocation(this.spriteX, this.spriteY);
										PewPewLabel.setIcon(new ImageIcon(
												getClass().getResource(this.getSpriteName())
												));
										break;
									} 
									
									
									
									PewPewLabel.setLocation(this.spriteX, this.spriteY);
									try {
										Thread.sleep(200); // Without this the screen cannot refresh 
											
										} catch (Exception e) {
											
										}
									}
									//System.out.println("Made it to west " );
									break;
									
									
			case "tanksouth.png": 	ptX = ttX;
									ptY = ttY;
									
									PewPewLabel.setLocation(this.spriteX, this.spriteY);
									
									while (pewMoving) {
										this.setSpriteX(ptX + 25);
										this.setSpriteY(ptY + 25 );
										if (rPewPew.intersects(rMonster)) {pewBoom(rTank, rMonster, rPewPew);};
										ptY += GameProperties.CHARACTER_STEP *4; // This will move twice as fast
										if(ptX > GameProperties.SCREEN_WIDTH || ptY > GameProperties.SCREEN_HEIGHT ) {
											this.pewMoving = false;
											this.setSpriteX(0);
											this.setSpriteY(0);
											
											PewPewLabel.setLocation(this.spriteX, this.spriteY);
											PewPewLabel.setIcon(new ImageIcon(
													getClass().getResource(this.getSpriteName())
													));
											break;
										} 
										
										
										
										PewPewLabel.setLocation(this.spriteX, this.spriteY);
										try {
											Thread.sleep(200); // Without this the screen cannot refresh 
											
										} catch (Exception e) {
											
										}
									}
															
									//System.out.println("Made it to south " + ptX + ","+ptY );
									break;
			
									
			default: 	ptX = ttX;
						ptY = ttY;
						
						PewPewLabel.setLocation(this.spriteX, this.spriteY);
						
						while (pewMoving) {
							this.setSpriteX(ptX);
							this.setSpriteY(ptY);
							if (rPewPew.intersects(rMonster)) {pewBoom(rTank, rMonster, rPewPew);};
							ptY -= GameProperties.CHARACTER_STEP *4; // This will move twice as fast
							if(ptX > GameProperties.SCREEN_WIDTH || ptY > GameProperties.SCREEN_HEIGHT ) {
								this.pewMoving = false;
								this.setSpriteX(0);
								this.setSpriteY(0);
								
								PewPewLabel.setLocation(this.spriteX, this.spriteY);
								PewPewLabel.setIcon(new ImageIcon(
										getClass().getResource(this.getSpriteName())
										));
							} 
							
							
							
							PewPewLabel.setLocation(this.spriteX, this.spriteY);
							try {
								Thread.sleep(200); // Without this the screen cannot refresh 
								
							} catch (Exception e) {
								
							}
						}
						//System.out.println("Made it to default " );
						break;
		}
	
		
		this.setSpriteName("pewpew.png");
		
		myMonster.setSpriteName("monster.png");
		
		
		PewPewLabel.setIcon(new ImageIcon(
				getClass().getResource(this.getSpriteName())
				));
		
			
		
	}
	
	public void movePewPew (KeyEvent e) {
				
		if (e.getKeyCode()==KeyEvent.VK_SPACE) {
			//System.out.println("Space is working" );
			run();
			
		}
	}
	
	
}
