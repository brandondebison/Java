import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Tank extends Sprite{
	
	private JLabel TankLabel;

	public Tank() {
		super(0,0,"tanknorth.png", 50, 50);
	}
	
	public void setTankLabel(JLabel tank) {
		// because there is no new this allows to pass a ref in to be changed
		TankLabel = tank;
	}
	
	public void moveTank (KeyEvent e) {
				
		int tankX = this.spriteX;
		int tankY = this.spriteY;
		
		if (e.getKeyCode()==KeyEvent.VK_DOWN) {
			
			tankY += GameProperties.CHARACTER_STEP;
			this.setSpriteName("tanksouth.png");
			TankLabel.setIcon(new ImageIcon(getClass().getResource(this.getSpriteName())));
			if (tankY > 775) {tankY = 775;}
			
		} else if (e.getKeyCode()==KeyEvent.VK_UP) {
			tankY -= GameProperties.CHARACTER_STEP;
			this.setSpriteName("tanknorth.png");
			TankLabel.setIcon(new ImageIcon(getClass().getResource(this.getSpriteName())));
			if (tankY < 125) {tankY = 125;}
			
			
		} else if (e.getKeyCode()==KeyEvent.VK_LEFT) {
			this.setSpriteName("tankwest.png");
			tankX -= GameProperties.CHARACTER_STEP;
			TankLabel.setIcon(new ImageIcon(getClass().getResource(this.getSpriteName())));
			if (tankX < 50) {tankX = 50;}
			
			
		} else if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
			tankX += GameProperties.CHARACTER_STEP;
			this.setSpriteName("tankeast.png");
			TankLabel.setIcon(new ImageIcon(getClass().getResource(this.getSpriteName())));
			if (tankX > 800) {tankX = 800;}
			
		}
		
		this.setSpriteX(tankX);
		this.setSpriteY(tankY);
		
		TankLabel.setLocation(this.spriteX, this.spriteY);

	}
}
