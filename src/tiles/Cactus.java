package tiles;

import java.awt.Rectangle;

import javafx.scene.image.Image;

public class Cactus extends Obstacle{
	
	/**
	 * Set Cactus Image
	 */
	Image cactus = new Image("/src/images/cactus.png");
	
	// Constructor
	public Cactus(int x, int y) {
		super(x, y);
	}
	
    public Rectangle getHitBox() {
    	return hitBox;
    }	
}
