package tiles;

import java.awt.Rectangle;

import javafx.scene.image.Image;

public class Flame extends Obstacle {
	
	/**
	 * Set Flame Image
	 * 
	 */
	Image flameSprite = new Image("/src/images/Flame1.gif");

	public Flame(int x, int y) {
		super(x, y);
		this.tileType = 'F';
	}
	
	public Rectangle getHitBox() {
		return hitBox;
	}

}
